package z3roco01.pragmatica.screen

import net.minecraft.client.gui.screen.ingame.HandledScreens

class PragmaticaScreens {
    companion object {
        fun register() {
            HandledScreens.register(PragmaticaScreenHandlers.BATTERY_SCREEN_HANDLER, ::BatteryScreen)
        }
    }
}