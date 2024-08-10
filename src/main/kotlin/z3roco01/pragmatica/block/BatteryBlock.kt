package z3roco01.pragmatica.block

import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import z3roco01.pragmatica.Pragmatica
import z3roco01.pragmatica.block.entity.BatteryBlockEntity

class BatteryBlock(val energyCapacity: Long, val maxIo: Long) : Block(Settings.create().sounds(BlockSoundGroup.METAL)), BlockEntityProvider {
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        Pragmatica.LOGGER.info("poop")
        return BatteryBlockEntity(pos, state)
    }

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hit: BlockHitResult): ActionResult {
        if(world.isClient) return ActionResult.PASS

        val entity = world.getBlockEntity(pos) as BatteryBlockEntity
        player.sendMessage(Text.literal(entity.energyStorage.getMaxInsert(null).toString() + " " + entity.energyStorage.capacity.toString() + " " + energyCapacity.toString() + " " + maxIo.toString()))

        return ActionResult.SUCCESS
    }
}