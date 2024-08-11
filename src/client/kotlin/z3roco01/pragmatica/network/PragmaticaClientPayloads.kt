package z3roco01.pragmatica.network

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import z3roco01.pragmatica.block.entity.EnergyContainer

class PragmaticaClientPayloads {
    companion object {
        fun register() {
            ClientPlayNetworking.registerGlobalReceiver(SyncEnergyContainerPayload.ID, {payload, context ->
                val energy = payload.amount
                val pos = payload.pos
                val world = context.client().world

                if(world == null) return@registerGlobalReceiver

                val blockEntity = world.getBlockEntity(pos)

                if(blockEntity is EnergyContainer)
                    blockEntity.energyStorage.amount = energy
            })
        }
    }
}