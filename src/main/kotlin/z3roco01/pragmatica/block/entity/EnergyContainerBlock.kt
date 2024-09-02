package z3roco01.pragmatica.block.entity

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class EnergyContainerBlock(settings: Settings) : Block(settings), BlockEntityProvider{
    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hit: BlockHitResult): ActionResult {
        if(world.isClient) return ActionResult.PASS

        val blockEntity = world.getBlockEntity(pos)

        if(!(blockEntity is ExtendedScreenHandlerFactory<*>)) return ActionResult.PASS

        val screenHandlerFactory = (blockEntity as ExtendedScreenHandlerFactory<*>)
        player.openHandledScreen(screenHandlerFactory)

        return ActionResult.SUCCESS
    }
}