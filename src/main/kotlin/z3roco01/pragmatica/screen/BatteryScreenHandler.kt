package z3roco01.pragmatica.screen

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot

class BatteryScreenHandler(syncId: Int, playerInv: PlayerInventory, data: EnergyContainerScreenData) : EnergyScreenHandler(PragmaticaScreenHandlers.BATTERY_SCREEN_HANDLER, syncId, playerInv, data){
    init {
        addPlayerInventory(playerInv)
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

    override fun canUse(player: PlayerEntity): Boolean {
        return true
    }

    private fun addPlayerInventory(playerInv: PlayerInventory) {
        for (i in 0..2) {
            for (l in 0..8) {
                this.addSlot(Slot(playerInv, l + i * 9 + 9, 8 + l * 18, 84 + i * 18))
            }
        }

        addHotbar(playerInv)
    }

    private fun addHotbar(playerInv: PlayerInventory) {
        for (i in 0..8) {
            this.addSlot(Slot(playerInv, i, 8 + i * 18, 142))
        }
    }

}