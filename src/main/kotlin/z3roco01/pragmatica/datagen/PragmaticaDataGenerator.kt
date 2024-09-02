package z3roco01.pragmatica.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import z3roco01.pragmatica.datagen.provider.PragmaticaModelProvider
import z3roco01.pragmatica.datagen.provider.PragmaticaRecipeProvider

object PragmaticaDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack()

		pack.addProvider(::PragmaticaModelProvider)
		pack.addProvider(::PragmaticaRecipeProvider)
	}
}