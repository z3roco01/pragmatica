package z3roco01.pragmatica.block

import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandlerFactory
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import z3roco01.pragmatica.block.entity.BatteryBlockEntity

class BatteryBlock(val energyCapacity: Long, val maxIo: Long) : Block(Settings.create().sounds(BlockSoundGroup.METAL)), BlockEntityProvider {
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return BatteryBlockEntity(pos, state)
    }

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hit: BlockHitResult): ActionResult {
        if(world.isClient) return ActionResult.PASS

        val screenHandlerFactory = (world.getBlockEntity(pos) as NamedScreenHandlerFactory)

        if(screenHandlerFactory != null)
            player.openHandledScreen(screenHandlerFactory)

        return ActionResult.SUCCESS
    }
}