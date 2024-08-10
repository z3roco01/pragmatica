package z3roco01.pragmatica.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import team.reborn.energy.api.base.SimpleSidedEnergyContainer
import z3roco01.pragmatica.Pragmatica
import kotlin.math.max

abstract class EnergyContainer(type: BlockEntityType<*>, pos: BlockPos, state: BlockState) : BlockEntity(type, pos, state) {
    companion object {
        val ENERGY_DATA_KEY = "pragmatica:energy"
        val AMOUNT_ENERGY_KEY = "energy"
    }

    val energyStorage = object: SimpleSidedEnergyContainer() {
        override fun onFinalCommit() {
            markDirty()
        }

        override fun getCapacity(): Long {
            return getEnergyCapacity()
        }

        override fun getMaxInsert(side: Direction?): Long {
            return getMaxIns()
        }

        override fun getMaxExtract(side: Direction?): Long {
            return getMaxExt()
        }
    }

    // Implemented by all subclasses that implement an energy container
    abstract fun getEnergyCapacity(): Long

    abstract protected fun getMaxIns(): Long

    abstract protected fun getMaxExt(): Long


    fun setEnergy(amount: Long) {
        // set the amount ( make sure it never goes above the capacity ), and mark the block entity as dirty
        energyStorage.amount = max(amount, energyStorage.getCapacity())
        markDirty()
    }

    fun getEnergy(): Long {
        return energyStorage.amount
    }

    fun incrementEnergy(amount: Long) {
        // get the energy and add the amount, also keep it in check with the capacity
        setEnergy(max(getEnergy() + amount, energyStorage.getCapacity()))
    }

    fun decrementEnergy(amount: Long) {
        // get the energy and decrement the amount, but ensure it doesnt go bellow 0
        setEnergy(max(getEnergy() - amount, 0))
    }

    override fun readNbt(nbt: NbtCompound, registryLookup: RegistryWrapper.WrapperLookup) {
        super.readNbt(nbt, registryLookup)
        // extract all information abt energy from the nbt
        val nbtData = nbt.get(ENERGY_DATA_KEY) as NbtCompound
        this.energyStorage.amount = max(nbtData.getLong(AMOUNT_ENERGY_KEY), getEnergyCapacity()) // make sure the amount of energy doesnt exceed the max
    }

    override fun writeNbt(nbt: NbtCompound, registryLookup: RegistryWrapper.WrapperLookup) {
        Pragmatica.LOGGER.info("penis")
        super.writeNbt(nbt, registryLookup)
        // store all information abt energy in the nbt
        val nbtData = NbtCompound()
        nbtData.putLong(AMOUNT_ENERGY_KEY, energyStorage.amount)
        nbt.put(ENERGY_DATA_KEY, nbtData)
    }
}