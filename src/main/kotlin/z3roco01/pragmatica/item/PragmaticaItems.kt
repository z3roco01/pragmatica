package z3roco01.pragmatica.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.AbstractBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.Item.Settings
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import z3roco01.pragmatica.Pragmatica
import z3roco01.pragmatica.block.PragmaticaBlocks


class PragmaticaItems {
    companion object {
        val INGREDIENTS_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.key, Identifier.of(Pragmatica.MOD_ID, "ingredients"))
        val INGREDIENTS_GROUP = FabricItemGroup.builder()
            .icon{ ItemStack(PINK_GOLD_INGOT) }
            .displayName(Text.translatable("itemGroup.pragmatica.ingredients"))
            .build()

        val PINK_GOLD_INGOT  = Item(Settings())
        val PINK_GOLD_NUGGET = Item(Settings())
        val PINK_GOLD_DUST = Item(Settings())

        val RAW_CHALCOPYRITE = Item(Settings())
        val RAW_CHRYSOCOLLA = Item(Settings())
        val RAW_TETRAHEDRITE = Item(Settings())

        fun register() {
            Registry.register(Registries.ITEM_GROUP, INGREDIENTS_GROUP_KEY, INGREDIENTS_GROUP)
            register(PINK_GOLD_INGOT, "pink_gold_ingot")
            register(PINK_GOLD_NUGGET, "pink_gold_nugget")
            register(PINK_GOLD_DUST, "pink_gold_dust")

            register(RAW_CHALCOPYRITE, "chalcopyrite")
            register(RAW_CHRYSOCOLLA, "chrysocolla")
            register(RAW_TETRAHEDRITE, "tetrahedrite")

            ItemGroupEvents.modifyEntriesEvent(INGREDIENTS_GROUP_KEY).register{itemGroup ->
                itemGroup.add(PragmaticaBlocks.PINK_GOLD_BLOCK.asItem())
                itemGroup.add(PINK_GOLD_INGOT)
                itemGroup.add(PINK_GOLD_NUGGET)
                itemGroup.add(PINK_GOLD_DUST)
            }
        }

        private fun register(item: Item, id: String) {
            Registry.register(Registries.ITEM, Identifier.of(Pragmatica.MOD_ID, id), item)
        }
    }
}