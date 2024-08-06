package z3roco01.pragmatica

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object Pragmatica : ModInitializer {
	val MOD_ID = "pragmatica"
    val LOGGER = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		LOGGER.info("init :3")
	}
}