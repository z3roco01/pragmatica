package z3roco01.pragmatica.screen

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import z3roco01.pragmatica.Pragmatica
import z3roco01.pragmatica.screen.element.EnergyBarElement

class BatteryScreen(handler: BatteryScreenHandler, playerInv: PlayerInventory, title: Text) : HandledScreen<BatteryScreenHandler>(handler, playerInv, title) {
    val TEXTURE: Identifier = Identifier.of(Pragmatica.MOD_ID, "textures/gui/container/battery.png")
    lateinit var energyBar: EnergyBarElement

    override fun init() {
        super.init()
        this.titleX = (this.backgroundWidth - textRenderer.getWidth(this.title)) / 2
        energyBar = EnergyBarElement(80, 68, handler, client!!)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        this.drawMouseoverTooltip(context, mouseX, mouseY)
        energyBar.drawMouseoverTooltip(context, mouseX, mouseY)
    }

    override fun drawForeground(context: DrawContext, mouseX: Int, mouseY: Int) {
        super.drawForeground(context, mouseX, mouseY)
        energyBar.render(context, mouseX, mouseY)
    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        val x = (this.width - this.backgroundWidth) / 2
        val y = (this.height - this.backgroundHeight) / 2

        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight)
    }
}