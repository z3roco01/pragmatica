package z3roco01.pragmatica.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.item.ItemStack
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.recipe.input.CraftingRecipeInput
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import net.minecraft.world.World
import z3roco01.pragmatica.Pragmatica

class AlloyingRecipe(val rightInput: SizedIngredient, val middleInput: SizedIngredient, val leftInput: SizedIngredient, val result: ItemStack, val energyPerTick: Int, val alloyTime: Int) : Recipe<CraftingRecipeInput>{
    fun fits(input: CraftingRecipeInput) = fits(input.width, input.height)

    override fun matches(input: CraftingRecipeInput, world: World): Boolean {
        if(!fits(input)) return false
        val right = input.getStackInSlot(0)
        val middle = input.getStackInSlot(1)
        val left = input.getStackInSlot(2)

        return rightInput.test(right) && middleInput.test(middle) && leftInput.test(left)
    }

    override fun getSerializer() = Serializer.INSTANCE
    override fun craft(input: CraftingRecipeInput, lookup: RegistryWrapper.WrapperLookup) = this.result.copy()
    override fun fits(width: Int, height: Int) = (width == 3 && height == 1)
    override fun getResult(registriesLookup: RegistryWrapper.WrapperLookup) = this.result
    override fun getType(): RecipeType<*> = Type.INSTANCE

    class Serializer: RecipeSerializer<AlloyingRecipe> {
        override fun codec() = CODEC
        override fun packetCodec() = PACKET_CODE

        companion object {
            val CODEC: MapCodec<AlloyingRecipe> = RecordCodecBuilder.mapCodec { instance ->
                instance.group(
                    SizedIngredient.CODEC.fieldOf("right").forGetter(AlloyingRecipe::rightInput),
                    SizedIngredient.CODEC.fieldOf("middle").forGetter(AlloyingRecipe::middleInput),
                    SizedIngredient.CODEC.fieldOf("left").forGetter(AlloyingRecipe::leftInput),
                    ItemStack.CODEC.fieldOf("result").forGetter(AlloyingRecipe::result),
                    Codec.INT.fieldOf("energyPerTick").forGetter(AlloyingRecipe::energyPerTick),
                    Codec.INT.fieldOf("alloyTime").forGetter(AlloyingRecipe::alloyTime)
                ).apply(instance, ::AlloyingRecipe)}
            val PACKET_CODE: PacketCodec<RegistryByteBuf, AlloyingRecipe> = PacketCodec.tuple(
                SizedIngredient.PACKET_CODEC, AlloyingRecipe::rightInput,
                SizedIngredient.PACKET_CODEC, AlloyingRecipe::middleInput,
                SizedIngredient.PACKET_CODEC, AlloyingRecipe::leftInput,
                ItemStack.PACKET_CODEC, AlloyingRecipe::result,
                PacketCodecs.INTEGER, AlloyingRecipe::energyPerTick,
                PacketCodecs.INTEGER, AlloyingRecipe::alloyTime,
                ::AlloyingRecipe
            )
            val INSTANCE = Serializer()
            val ID = Identifier.of(Pragmatica.MOD_ID, "alloying")
        }
    }

    class Type: RecipeType<AlloyingRecipe> {
        companion object {
            val INSTANCE = Type()
            val ID = Identifier.of(Pragmatica.MOD_ID, "alloying")
        }
    }
}