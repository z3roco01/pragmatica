package z3roco01.pragmatica.block.entity

import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import team.reborn.energy.api.EnergyStorage
import z3roco01.pragmatica.Pragmatica
import z3roco01.pragmatica.block.PragmaticaBlocks

class PragmaticaBlockEntities {
    companion object {
        val BATTERY_BLOCK = BlockEntityType.Builder.create(::BatteryBlockEntity, PragmaticaBlocks.BATTERY_BLOCK).build()
        val ALLOYER_BLOCK = BlockEntityType.Builder.create(::AlloyerBlockEntity, PragmaticaBlocks.ALLOYER_BLOCK).build()

        fun register() {
            registerEnergyContainer(BATTERY_BLOCK, "battery")
            registerEnergyContainer(ALLOYER_BLOCK, "alloyer")
        }

        private fun register(type: BlockEntityType<*>, id: String) = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Pragmatica.MOD_ID, id), type)

        private fun registerEnergyContainer(type: BlockEntityType<*>, id: String) {
            register(type, id)
            EnergyStorage.SIDED.registerForBlockEntity({blockEntity, direction -> blockEntity.energyStorage.getSideStorage(direction)}, BATTERY_BLOCK)
        }
    }
}