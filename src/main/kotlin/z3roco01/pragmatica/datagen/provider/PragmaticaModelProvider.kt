package z3roco01.pragmatica.datagen.provider

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.*
import z3roco01.pragmatica.item.PragmaticaItems

class PragmaticaModelProvider(dataOutput: FabricDataOutput): FabricModelProvider(dataOutput) {
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {
    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        itemModelGenerator.register(PragmaticaItems.PINK_GOLD_INGOT, Models.GENERATED)
        itemModelGenerator.register(PragmaticaItems.PINK_GOLD_DUST, Models.GENERATED)
        itemModelGenerator.register(PragmaticaItems.PINK_GOLD_NUGGET, Models.GENERATED)
        itemModelGenerator.register(PragmaticaItems.RAW_CHRYSOCOLLA, Models.GENERATED)
        itemModelGenerator.register(PragmaticaItems.RAW_CHALCOPYRITE, Models.GENERATED)
        itemModelGenerator.register(PragmaticaItems.RAW_TETRAHEDRITE, Models.GENERATED)
    }

}