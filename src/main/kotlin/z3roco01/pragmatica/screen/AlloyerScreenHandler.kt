package z3roco01.pragmatica.screen

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.slot.Slot

class AlloyerScreenHandler(syncId: Int, playerInv: PlayerInventory, data: EnergyContainerScreenData) : EnergyScreenHandler(PragmaticaScreenHandlers.ALLOYER_SCREEN_HANDLER, syncId, playerInv, data){
    init {
        addPlayerInventory(playerInv)
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