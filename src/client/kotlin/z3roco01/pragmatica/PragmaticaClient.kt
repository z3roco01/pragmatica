package z3roco01.pragmatica

import net.fabricmc.api.ClientModInitializer
import org.slf4j.LoggerFactory
import z3roco01.pragmatica.Pragmatica.MOD_ID

object PragmaticaClient : ClientModInitializer {
	val LOGGER = LoggerFactory.getLogger(MOD_ID + "_client")

	override fun onInitializeClient() {
		LOGGER.info("client init :p")
	}
}