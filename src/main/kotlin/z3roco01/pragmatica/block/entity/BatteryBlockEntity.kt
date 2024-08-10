package z3roco01.pragmatica.block.entity

import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos

class BatteryBlockEntity(pos: BlockPos, state: BlockState) : EnergyContainer(PragmaticaBlockEntities.BATTERY_BLOCK, pos, state) {
    override fun getEnergyCapacity(): Long {
        return 128000
    }

    override fun getMaxIns(): Long {
        return 1000
    }

    override fun getMaxExt(): Long {
        return 1000
    }
}