package de.gerolmed.wandustry;

import de.gerolmed.wandustry.block.BasicBlock;
import de.gerolmed.wandustry.item.BasicItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WandustryMod implements ModInitializer {

	/**
	 * Logger used for logging all messages
	 */
	private static final Logger LOGGER = LogManager.getLogger(WandustryMod.class);

	/**
	 * Mod Id of the mod
	 */
	public static final String MOD_ID = "wandustry";

	@Override
	public void onInitialize() {
		LOGGER.info("Attempting to load this great mod...");

		registerItems();
		registerBlocks();
		registerBlockEntities();

		Features.initialize();
	}

	/**
	 * Register BlockEntities
	 */
	private void registerBlockEntities() {
		BlockEntities.register();
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
