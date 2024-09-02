package z3roco01.pragmatica.datagen.provider

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import z3roco01.pragmatica.item.PragmaticaItems
import java.util.concurrent.CompletableFuture

class PragmaticaRecipeProvider(dataOutput: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>): FabricRecipeProvider(dataOutput, registriesFuture) {
    override fun generate(exporter: RecipeExporter) {
        offerSmelting(exporter, listOf(PragmaticaItems.RAW_CHALCOPYRITE, PragmaticaItems.RAW_CHRYSOCOLLA, PragmaticaItems.RAW_TETRAHEDRITE), RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 200, "copper_ingot")
    }
}