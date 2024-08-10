package z3roco01.pragmatica

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
import z3roco01.pragmatica.block.PragmaticaBlocks
import z3roco01.pragmatica.block.entity.PragmaticaBlockEntities

object Pragmatica : ModInitializer {
	val MOD_ID = "pragmatica"
    val LOGGER = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		LOGGER.info("init :3")

		PragmaticaBlocks.register()
		PragmaticaBlockEntities.register()
	}
}