package z3roco01.pragmatica.block.entity

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import z3roco01.pragmatica.screen.BatteryScreenHandler


class BatteryBlockEntity(pos: BlockPos, state: BlockState) : EnergyContainer(PragmaticaBlockEntities.BATTERY_BLOCK, pos, state), ExtendedScreenHandlerFactory<BatteryScreenHandler.EnergyContainerScreenData> {
    override fun getEnergyCapacity(): Long {
        return 128000
    }

    override fun getMaxIns(): Long {
        return 1000
    }

    override fun getMaxExt(): Long {
        return 1000
    }

    override fun createMenu(syncId: Int, playerInv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return BatteryScreenHandler(syncId, playerInv, BatteryScreenHandler.EnergyContainerScreenData(this.getEnergy(), this.getEnergyCapacity()))
    }

    override fun getDisplayName(): Text {
        return Text.translatable(cachedState.block.translationKey)
    }

    override fun getScreenOpeningData(player: ServerPlayerEntity): BatteryScreenHandler.EnergyContainerScreenData {
        return BatteryScreenHandler.EnergyContainerScreenData(this.getEnergy(), this.getEnergyCapacity())
    }

}