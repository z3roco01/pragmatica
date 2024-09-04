package z3roco01.pragmatica.recipe

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

class PragmaticaRecipes {
    companion object {
        fun register() {
            Registry.register(Registries.RECIPE_SERIALIZER, AlloyingRecipe.Serializer.ID, AlloyingRecipe.Serializer.INSTANCE)
            Registry.register(Registries.RECIPE_TYPE, AlloyingRecipe.Type.ID, AlloyingRecipe.Type.INSTANCE)
        }
    }
}