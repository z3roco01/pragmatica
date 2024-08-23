package z3roco01.pragmatica.screen

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.math.BlockPos
import z3roco01.pragmatica.block.entity.BatteryBlockEntity

abstract class EnergyScreenHandler(type: ScreenHandlerType<*>, syncId: Int, playerInv: PlayerInventory, val data: EnergyContainerScreenData): ScreenHandler(type, syncId) {
    val blockEntity: BatteryBlockEntity

    init {
        blockEntity = playerInv.player.world.getBlockEntity(data.pos) as BatteryBlockEntity
    }

    data class EnergyContainerScreenData(val amount: Long, val capacity: Long, val pos: BlockPos) {
        companion object {
            val PACKET_CODEC: PacketCodec<RegistryByteBuf, EnergyContainerScreenData> = PacketCodec.tuple(
                PacketCodecs.VAR_LONG, EnergyContainerScreenData::amount,
                PacketCodecs.VAR_LONG, EnergyContainerScreenData::capacity,
                BlockPos.PACKET_CODEC, EnergyContainerScreenData::pos,
            ) { amount: Long, capacity: Long, pos: BlockPos -> EnergyContainerScreenData(amount, capacity, pos) }
        }
    }
}