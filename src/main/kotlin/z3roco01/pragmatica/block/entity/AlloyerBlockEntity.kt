package z3roco01.pragmatica.block.entity

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import z3roco01.pragmatica.screen.AlloyerScreenHandler
import z3roco01.pragmatica.screen.EnergyScreenHandler

class AlloyerBlockEntity(pos: BlockPos, state: BlockState): EnergyContainer(PragmaticaBlockEntities.ALLOYER_BLOCK, pos, state), ExtendedScreenHandlerFactory<EnergyScreenHandler.EnergyContainerScreenData> {
    override fun getEnergyCapacity() = 32000L
    override fun getMaxIns() = 500L
    override fun getMaxExt() = 500L

    override fun createMenu(syncId: Int, playerInv: PlayerInventory, player: PlayerEntity) =
        AlloyerScreenHandler(syncId, playerInv, EnergyScreenHandler.EnergyContainerScreenData(this.getEnergy(), this.getEnergyCapacity(), pos))
    override fun getDisplayName() = Text.translatable(cachedState.block.translationKey)
    override fun getScreenOpeningData(player: ServerPlayerEntity) =
        EnergyScreenHandler.EnergyContainerScreenData(this.getEnergy(), this.getEnergyCapacity(), pos)
}