package z3roco01.pragmatica.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.fabricmc.fabric.api.recipe.v1.ingredient.FabricIngredient
import net.minecraft.item.ItemStack
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.recipe.Ingredient
import java.util.function.Predicate

class SizedIngredient(val ingredient: Ingredient, val count: Int) : Predicate<ItemStack>, FabricIngredient{
    override fun test(itemStack: ItemStack): Boolean {
        if(itemStack.isEmpty) return false
        if(itemStack.count < count) return false

        return ingredient.test(itemStack)
    }

    override fun toString(): String {
        return "$count $ingredient"
    }

    companion object {
        val CODEC: MapCodec<SizedIngredient> = RecordCodecBuilder.mapCodec{instance ->
            instance.group(
                MapCodec.assumeMapUnsafe(Ingredient.DISALLOW_EMPTY_CODEC).forGetter(SizedIngredient::ingredient),
                Codec.INT.optionalFieldOf("count", 1).forGetter(SizedIngredient::count)
            ).apply(instance, ::SizedIngredient)}
        val PACKET_CODEC: PacketCodec<RegistryByteBuf, SizedIngredient> = PacketCodec.tuple(
            Ingredient.PACKET_CODEC, SizedIngredient::ingredient,
            PacketCodecs.INTEGER, SizedIngredient::count,
            ::SizedIngredient
        )
    }

}