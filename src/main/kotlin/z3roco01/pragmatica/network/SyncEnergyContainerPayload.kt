package z3roco01.pragmatica.network

import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.network.packet.CustomPayload
import net.minecraft.network.packet.CustomPayload.Id
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import z3roco01.pragmatica.Pragmatica

data class SyncEnergyContainerPayload(val amount: Long, val pos: BlockPos) : CustomPayload {
    companion object {
        val ID = Id<SyncEnergyContainerPayload>(Identifier.of(Pragmatica.MOD_ID, "sync_energy_container"))
        val PACKET_CODEC = PacketCodec.tuple(PacketCodecs.VAR_LONG, SyncEnergyContainerPayload::amount, BlockPos.PACKET_CODEC, SyncEnergyContainerPayload::pos, ::SyncEnergyContainerPayload)
    }

    override fun getId(): Id<out CustomPayload> = ID
}
