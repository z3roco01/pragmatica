package z3roco01.pragmatica.screen

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier
import z3roco01.pragmatica.Pragmatica

class PragmaticaScreenHandlers {
    companion object {
        val BATTERY_SCREEN_HANDLER = ExtendedScreenHandlerType<BatteryScreenHandler, BatteryScreenHandler.EnergyContainerScreenData>({ syncId, playerInv, data -> BatteryScreenHandler(syncId, playerInv, data)}, BatteryScreenHandler.EnergyContainerScreenData.PACKET_CODEC)

        fun register() {
            register("battery", BATTERY_SCREEN_HANDLER)
        }

        private fun register(id: String, type: ScreenHandlerType<*>) {
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Pragmatica.MOD_ID, id), type)
        }
    }
}