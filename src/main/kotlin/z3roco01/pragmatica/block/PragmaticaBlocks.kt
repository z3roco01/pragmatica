package z3roco01.pragmatica.block

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import z3roco01.pragmatica.Pragmatica

class PragmaticaBlocks {
    companion object {
        val BATTERY_BLOCK = BatteryBlock()

        fun register() {
            register(BATTERY_BLOCK, "battery")
        }

        private fun register(block: Block, id: String) {
            Registry.register(Registries.BLOCK, Identifier.of(Pragmatica.MOD_ID, id), block)
            Registry.register(Registries.ITEM, Identifier.of(Pragmatica.MOD_ID, id), BlockItem(block, Item.Settings()))
        }
    }
}