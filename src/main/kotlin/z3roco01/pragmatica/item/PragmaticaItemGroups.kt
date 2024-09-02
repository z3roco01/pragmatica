package z3roco01.pragmatica.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import z3roco01.pragmatica.Pragmatica
import z3roco01.pragmatica.block.PragmaticaBlocks

class PragmaticaItemGroups {
    companion object {
        val MACHINES_GROUP_KEY = mkGroupKey("machines")
        private val MACHINES_GROUP = mkGroup(PragmaticaBlocks.ALLOYER_BLOCK.asItem(), "machines")
        val INGREDIENTS_GROUP_KEY = mkGroupKey("ingredients")
        private val INGREDIENTS_GROUP = mkGroup(PragmaticaItems.PINK_GOLD_INGOT, "ingredients")

        fun register() {
            Registry.register(Registries.ITEM_GROUP, INGREDIENTS_GROUP_KEY, INGREDIENTS_GROUP)
            Registry.register(Registries.ITEM_GROUP, MACHINES_GROUP_KEY, MACHINES_GROUP)

            ItemGroupEvents.modifyEntriesEvent(MACHINES_GROUP_KEY).register{itemGroup ->
                itemGroup.add(PragmaticaBlocks.BATTERY_BLOCK.asItem())
                itemGroup.add(PragmaticaBlocks.ALLOYER_BLOCK.asItem())
            }

            ItemGroupEvents.modifyEntriesEvent(INGREDIENTS_GROUP_KEY).register{ itemGroup ->
                itemGroup.add(PragmaticaBlocks.PINK_GOLD_BLOCK.asItem())
                itemGroup.add(PragmaticaItems.PINK_GOLD_INGOT)
                itemGroup.add(PragmaticaItems.PINK_GOLD_NUGGET)
                itemGroup.add(PragmaticaItems.PINK_GOLD_DUST)
            }
        }

        private fun mkGroupKey(id: String) = RegistryKey.of(Registries.ITEM_GROUP.key, Identifier.of(Pragmatica.MOD_ID, id))

        private fun mkGroup(item: Item, id: String) = FabricItemGroup.builder()
            .icon{ItemStack(item)}
            .displayName(Text.translatable("itemGroup.pragmatica.${id}"))
            .build()
    }
}