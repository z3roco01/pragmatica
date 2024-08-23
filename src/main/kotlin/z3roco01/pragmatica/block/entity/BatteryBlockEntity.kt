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
import z3roco01.pragmatica.screen.EnergyScreenHandler


class BatteryBlockEntity(pos: BlockPos, state: BlockState) : EnergyContainer(PragmaticaBlockEntities.BATTERY_BLOCK, pos, state), ExtendedScreenHandlerFactory<EnergyScreenHandler.EnergyContainerScreenData> {
    override fun getEnergyCapacity(): Long {
        return 32000
    }

    override fun getMaxIns(): Long {
        return 1000
    }

    override fun getMaxExt(): Long {
        return 1000
    }

    override fun createMenu(syncId: Int, playerInv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return BatteryScreenHandler(syncId, playerInv,
            EnergyScreenHandler.EnergyContainerScreenData(this.getEnergy(), this.getEnergyCapacity(), pos)
        )
    }

    override fun getDisplayName(): Text {
        return Text.translatable(cachedState.block.translationKey)
    }

    override fun getScreenOpeningData(player: ServerPlayerEntity): EnergyScreenHandler.EnergyContainerScreenData {
        return EnergyScreenHandler.EnergyContainerScreenData(this.getEnergy(), this.getEnergyCapacity(), pos)
    }

}