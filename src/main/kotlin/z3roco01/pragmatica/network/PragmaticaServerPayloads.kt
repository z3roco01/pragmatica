package z3roco01.pragmatica.network

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry

class PragmaticaServerPayloads {
    companion object {
        fun register() {
            PayloadTypeRegistry.playS2C().register(SyncEnergyContainerPayload.ID, SyncEnergyContainerPayload.PACKET_CODEC)
        }
    }
}