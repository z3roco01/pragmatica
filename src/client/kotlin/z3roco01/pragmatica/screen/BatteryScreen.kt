package z3roco01.pragmatica.screen

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import z3roco01.pragmatica.PragmaticaClient

class BatteryScreen(handler: BatteryScreenHandler, playerInv: PlayerInventory, title: Text) : HandledScreen<BatteryScreenHandler>(handler, playerInv, title) {
    val TEXTURE: Identifier = Identifier.ofVanilla("textures/gui/container/dispenser.png")

    override fun init() {
        super.init()
        this.titleX = (this.backgroundWidth - textRenderer.getWidth(this.title)) / 2

        PragmaticaClient.LOGGER.info(this.getAmount().toString() + " / " + this.getCapacity().toString())
    }

    protected fun getAmount(): Long {
        return handler.data.amount
    }

    protected fun getCapacity(): Long {
        return handler.data.capacity
    }

    override fun render(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        this.drawMouseoverTooltip(context, mouseX, mouseY)
    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        val x = (this.width - this.backgroundWidth) / 2
        val y = (this.height - this.backgroundHeight) / 2

        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight)
    }
}