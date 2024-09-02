package z3roco01.pragmatica.screen.element

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.util.Identifier
import z3roco01.pragmatica.Pragmatica
import z3roco01.pragmatica.screen.EnergyScreenHandler
import kotlin.math.round

class EnergyBarElement(x: Int, y: Int, val handler: EnergyScreenHandler): GUIElement(x, y) {
    val ENERGY_BAR_TEXT = Identifier.of(Pragmatica.MOD_ID, "textures/gui/container/sprite/energy_bar/energy_bar.png")
    val ENERGY_BAR_BG_TEXT = Identifier.of(Pragmatica.MOD_ID, "textures/gui/container/sprite/energy_bar/energy_bar_bg.png")

    protected fun getPos() = handler.data.pos

    protected fun getAmount() = handler.blockEntity.getEnergy()

    protected fun getCapacity() = handler.blockEntity.getEnergyCapacity()

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int) {
        val barHeight = round((getAmount().toFloat() / getCapacity().toFloat()) * 52f).toInt()
        context.drawTexture(ENERGY_BAR_BG_TEXT, x-1, y-52, 0f, 0f, 18, 54, 18, 54)
        context.drawTexture(ENERGY_BAR_TEXT, x, y - barHeight + 1, 0, 0f, 52f - barHeight.toFloat(), 16, barHeight, 16, 52)
    }

    override fun drawMouseoverTooltip(context: DrawContext, mouseX: Int, mouseY: Int) {
    }

    override fun appendNarrations(builder: NarrationMessageBuilder) {
    }
}