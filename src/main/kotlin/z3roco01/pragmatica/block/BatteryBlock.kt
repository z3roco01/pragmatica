package z3roco01.pragmatica.block

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import z3roco01.pragmatica.block.entity.BatteryBlockEntity

class BatteryBlock : Block(Settings.create().sounds(BlockSoundGroup.METAL)), BlockEntityProvider {
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return BatteryBlockEntity(pos, state)
    }

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hit: BlockHitResult): ActionResult {
        if(world.isClient) return ActionResult.PASS

        val blockEntity = world.getBlockEntity(pos)

        if(!(blockEntity is ExtendedScreenHandlerFactory<*>)) return ActionResult.PASS

        val screenHandlerFactory = (blockEntity as ExtendedScreenHandlerFactory<*>)
        player.openHandledScreen(screenHandlerFactory)

        return ActionResult.SUCCESS
    }
}