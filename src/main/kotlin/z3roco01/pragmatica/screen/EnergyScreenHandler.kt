package z3roco01.pragmatica.screen

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.math.BlockPos
import z3roco01.pragmatica.block.entity.EnergyContainer

abstract class EnergyScreenHandler(type: ScreenHandlerType<*>, syncId: Int, playerInv: PlayerInventory, val data: EnergyContainerScreenData): ScreenHandler(type, syncId) {
    val blockEntity: EnergyContainer

    init {
        blockEntity = playerInv.player.world.getBlockEntity(data.pos) as EnergyContainer
    }

    override fun quickMove(player: PlayerEntity, slot: Int): ItemStack {
        var itemStack = ItemStack.EMPTY
        val slot2 = slots[slot]
        if (slot2.hasStack()) {
            val itemStack2 = slot2.stack
            itemStack = itemStack2.copy()
            if (if (slot < 27) !this.insertItem(itemStack2, 27, slots.size, true) else !this.insertItem(
                    itemStack2,
                    0,
                    27,
                    false
                )
            ) {
                return ItemStack.EMPTY
            }
            if (itemStack2.isEmpty) {
                slot2.stack = ItemStack.EMPTY
            } else {
                slot2.markDirty()
            }
        }
        return itemStack!!
    }

    override fun canUse(player: PlayerEntity) = true

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