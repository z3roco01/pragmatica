package z3roco01.pragmatica.screen.element

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.Element
import net.minecraft.client.gui.Narratable

abstract class GUIElement(val x: Int, val y: Int) : Element, Narratable{
    private var focused = false

    override fun setFocused(focused: Boolean) {
        this.focused = focused
    }

    override fun isFocused() = focused

    abstract fun render(context: DrawContext, mouseX: Int, mouseY: Int)
    open fun drawMouseoverTooltip(context: DrawContext, mouseX: Int, mouseY: Int) {}
}