package de.gerolmed.fabricmod;

import de.gerolmed.fabricmod.block.BasicBlock;
import de.gerolmed.fabricmod.item.BasicItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleMod implements ModInitializer {

	/**
	 * Logger used for logging all messages
	 */
	private static final Logger LOGGER = LogManager.getLogger(ExampleMod.class);

	/**
	 * Mod Id of the mod
	 */
	public static final String MOD_ID = "modid";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Attempting to load this great mod...");

		registerItems();
		registerBlocks();

		Features.initialize();
	}

	/**
	 * Register items
	 */
	private void registerItems() {
		LOGGER.info("Starting to register items...");
		for(BasicItem item : Items.values()) {
			LOGGER.debug("Registering item {}...", item.getRegistryName());
			Registry.register(Registry.ITEM, new Identifier(MOD_ID, item.getRegistryName()), item);
		}

		LOGGER.info("Finished!");
	}

	/**
	 * Register blocks
	 */
	private void registerBlocks() {
		LOGGER.info("Starting to register blocks...");
		for(BasicBlock block : Blocks.values()) {
			LOGGER.debug("Registering block {}...", block.getRegistryName());
			Registry.register(Registry.BLOCK, new Identifier(MOD_ID, block.getRegistryName()), block);

			if(block.hasBlockItem())
				Registry.register(Registry.ITEM, new Identifier(MOD_ID, block.getRegistryName()), new BlockItem(block, block.getItemSettings()));
		}

		LOGGER.info("Finished!");
	}
}
