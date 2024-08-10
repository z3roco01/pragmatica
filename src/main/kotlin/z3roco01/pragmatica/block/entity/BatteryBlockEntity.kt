package z3roco01.pragmatica.block.entity

import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import z3roco01.pragmatica.screen.BatteryScreenHandler

class BatteryBlockEntity(pos: BlockPos, state: BlockState) : EnergyContainer(PragmaticaBlockEntities.BATTERY_BLOCK, pos, state), NamedScreenHandlerFactory {
    override fun getEnergyCapacity(): Long {
        return 128000
    }

    override fun getMaxIns(): Long {
        return 1000
    }

    override fun getMaxExt(): Long {
        return 1000
    }

    override fun createMenu(syncId: Int, playerInv: PlayerInventory, player: PlayerEntity): ScreenHandler? {
        return BatteryScreenHandler(syncId, playerInv)
    }

    override fun getDisplayName(): Text {
        return Text.translatable(cachedState.block.translationKey)
    }
}