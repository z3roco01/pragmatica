package z3roco01.pragmatica.item

import net.minecraft.item.Item
import net.minecraft.item.Item.Settings
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import z3roco01.pragmatica.Pragmatica


class PragmaticaItems {
    companion object {

        val PINK_GOLD_INGOT  = Item(Settings())
        val PINK_GOLD_NUGGET = Item(Settings())
        val PINK_GOLD_DUST = Item(Settings())

        val RAW_CHALCOPYRITE = Item(Settings())
        val RAW_CHRYSOCOLLA = Item(Settings())
        val RAW_TETRAHEDRITE = Item(Settings())

        fun register() {
            register(PINK_GOLD_INGOT, "pink_gold_ingot")
            register(PINK_GOLD_NUGGET, "pink_gold_nugget")
            register(PINK_GOLD_DUST, "pink_gold_dust")

            register(RAW_CHALCOPYRITE, "raw_chalcopyrite")
            register(RAW_CHRYSOCOLLA, "raw_chrysocolla")
            register(RAW_TETRAHEDRITE, "raw_tetrahedrite")
        }

        private fun register(item: Item, id: String) = Registry.register(Registries.ITEM, Identifier.of(Pragmatica.MOD_ID, id), item)
    }
}