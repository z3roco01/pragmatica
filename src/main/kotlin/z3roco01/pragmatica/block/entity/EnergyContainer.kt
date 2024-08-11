package z3roco01.pragmatica.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import team.reborn.energy.api.EnergyStorage
import team.reborn.energy.api.EnergyStorageUtil
import team.reborn.energy.api.base.SimpleSidedEnergyContainer
import kotlin.math.max
import kotlin.math.min

abstract class EnergyContainer(type: BlockEntityType<*>, pos: BlockPos, state: BlockState) : BlockEntity(type, pos, state), BlockEntityTicker<EnergyContainer> {
    val sideIOMap = mapOf(
        Direction.DOWN  to IOPermission.INSERT,
        Direction.UP    to IOPermission.INSERT,
        Direction.NORTH to IOPermission.INSERT,
        Direction.SOUTH to IOPermission.INSERT,
        Direction.WEST  to IOPermission.INSERT,
        Direction.EAST  to IOPermission.INSERT)

    val energyStorage = object: SimpleSidedEnergyContainer() {
        override fun onFinalCommit() {
            markDirty()
        }

        override fun getCapacity(): Long {
            return getEnergyCapacity()
        }

        override fun getMaxInsert(side: Direction?): Long {
            if(side == null || canInsertSide(side))
                return getMaxIns()
            return 0
        }

        override fun getMaxExtract(side: Direction?): Long {
            if(side == null || canExtractSide(side))
                return getMaxExt()
            return 0
        }
    }

    // Implemented by all subclasses that implement an energy container
    abstract fun getEnergyCapacity(): Long

    abstract protected fun getMaxIns(): Long

    abstract protected fun getMaxExt(): Long

    // push power to all blocks accepting that are beside this block
    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: EnergyContainer) {
        if(world.isClient || getEnergy() <= 0) return

        for(side in Direction.entries) {
            EnergyStorageUtil.move(
                energyStorage.getSideStorage(side),
                EnergyStorage.SIDED.find(world, pos.offset(side), side.opposite),
                Long.MAX_VALUE,
                null
            )
        }
    }

    protected fun canExtractSide(side: Direction): Boolean {
        val sidePerms = sideIOMap.get(side)
        return (sidePerms == IOPermission.EXTRACT || sidePerms == IOPermission.INS_EXT)
    }

    protected fun canInsertSide(side: Direction): Boolean {
        val sidePerms = sideIOMap.get(side)
        return (sidePerms == IOPermission.INSERT || sidePerms == IOPermission.INS_EXT)
    }

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
        setEnergy(min(getEnergy() + amount, energyStorage.getCapacity()))
    }

    fun decrementEnergy(amount: Long) {
        // get the energy and decrement the amount, but ensure it doesnt go bellow 0
        setEnergy(max(getEnergy() - amount, 0))
    }

    override fun readNbt(nbt: NbtCompound, registryLookup: RegistryWrapper.WrapperLookup) {
        super.readNbt(nbt, registryLookup)
        // extract all information abt energy from the nbt
        val nbtData = nbt.get(ENERGY_DATA_KEY) as NbtCompound
        this.energyStorage.amount = min(nbtData.getLong(AMOUNT_ENERGY_KEY), getEnergyCapacity()) // make sure the amount of energy doesnt exceed the max
    }

    override fun writeNbt(nbt: NbtCompound, registryLookup: RegistryWrapper.WrapperLookup) {
        super.writeNbt(nbt, registryLookup)
        // store all information abt energy in the nbt
        val nbtData = NbtCompound()
        nbtData.putLong(AMOUNT_ENERGY_KEY, energyStorage.amount)
        nbt.put(ENERGY_DATA_KEY, nbtData)
    }

    companion object {
        val ENERGY_DATA_KEY = "pragmatica:energy"
        val AMOUNT_ENERGY_KEY = "energy"
    }

    enum class IOPermission {
        NONE,
        INSERT,
        EXTRACT,
        INS_EXT
    }
}