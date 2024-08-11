package z3roco01.pragmatica.screen

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import z3roco01.pragmatica.Pragmatica
import z3roco01.pragmatica.PragmaticaClient
import z3roco01.pragmatica.block.entity.BatteryBlockEntity
import kotlin.math.round

class BatteryScreen(handler: BatteryScreenHandler, playerInv: PlayerInventory, title: Text) : HandledScreen<BatteryScreenHandler>(handler, playerInv, title) {
    val TEXTURE: Identifier = Identifier.of(Pragmatica.MOD_ID, "textures/gui/container/battery.png")
    val ENERGY_BAR_TEXT = Identifier.of(Pragmatica.MOD_ID, "textures/gui/container/sprite/energy_bar.png")

    override fun init() {
        super.init()
        this.titleX = (this.backgroundWidth - textRenderer.getWidth(this.title)) / 2

        PragmaticaClient.LOGGER.info(this.getAmount().toString() + " / " + this.getCapacity().toString())
    }

    protected fun getWorld(): ClientWorld? {
        return this.client!!.world
    }

    protected fun getPos(): BlockPos {
        return handler.data.pos
    }

    protected fun getAmount(): Long {
        PragmaticaClient.LOGGER.info(handler.blockEntity.getEnergy().toString() + " " + handler.data.amount)

        return handler.blockEntity.getEnergy()
    }

    protected fun getCapacity(): Long {
        return handler.blockEntity.getEnergyCapacity()
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        this.drawMouseoverTooltip(context, mouseX, mouseY)
    }

    override fun drawForeground(context: DrawContext, mouseX: Int, mouseY: Int) {
        super.drawForeground(context, mouseX, mouseY)
// 79 69
        val barHeight = round((getAmount().toFloat() / getCapacity().toFloat()) * 52f).toInt()
        context.drawTexture(ENERGY_BAR_TEXT, 80, 68 - barHeight + 1, 0, 0f, 52f - barHeight.toFloat(), 16, barHeight, 16, 52)
    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        val x = (this.width - this.backgroundWidth) / 2
        val y = (this.height - this.backgroundHeight) / 2

        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight)
    }
}