package z3roco01.pragmatica.datagen.provider

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.*
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import z3roco01.pragmatica.Pragmatica
import z3roco01.pragmatica.item.PragmaticaItems

class PragmaticaModelProvider(dataOutput: FabricDataOutput): FabricModelProvider(dataOutput) {
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {
    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        itemModelGenerator.register(PragmaticaItems.PINK_GOLD_INGOT, Models.GENERATED)
        itemModelGenerator.register(PragmaticaItems.PINK_GOLD_DUST, Models.GENERATED)
        itemModelGenerator.register(PragmaticaItems.PINK_GOLD_NUGGET, Models.GENERATED)
    }

}