package z3roco01.pragmatica.block

import net.minecraft.block.BlockState
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.math.BlockPos
import z3roco01.pragmatica.block.entity.AlloyerBlockEntity
import z3roco01.pragmatica.block.entity.EnergyContainerBlock

class AlloyerBlock: EnergyContainerBlock(Settings.create().sounds(BlockSoundGroup.METAL)) {
    override fun createBlockEntity(pos: BlockPos, state: BlockState) = AlloyerBlockEntity(pos, state)
}