package de.gerolmed.wandustry;

import de.gerolmed.wandustry.block.BasicBlock;
import de.gerolmed.wandustry.enchanting.EnchantingManager;
import de.gerolmed.wandustry.enchanting.EnchantingRecipe;
import de.gerolmed.wandustry.item.BasicItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
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

		registerEnchanterRecipes();

		Containers.register();
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

	/**
	 * Register enchanter recipes
	 */
	private void registerEnchanterRecipes() {
		EnchantingManager.register(new EnchantingRecipe(
				0,
				20*10,
				new ItemStack(net.minecraft.block.Blocks.STONE),

				new ItemStack(net.minecraft.block.Blocks.DIRT),
				new ItemStack(net.minecraft.block.Blocks.GRAVEL)
		));
		EnchantingManager.register(new EnchantingRecipe(
				3,
				20*60,
				new ItemStack(net.minecraft.block.Blocks.COBBLESTONE),

				new ItemStack(net.minecraft.block.Blocks.STONE)
		));
		EnchantingManager.register(new EnchantingRecipe(
				3,
				20*10,
				new ItemStack(net.minecraft.block.Blocks.BEDROCK),

				new ItemStack(net.minecraft.block.Blocks.STONE),
				new ItemStack(net.minecraft.block.Blocks.OBSIDIAN),
				new ItemStack(net.minecraft.block.Blocks.OBSIDIAN),
				new ItemStack(net.minecraft.block.Blocks.OBSIDIAN),
				new ItemStack(Items.ITEM_MAGIC_INGOT)
		));

		// Ingot recipe

        /* Craft one magic iron ingot */
        EnchantingManager.register(new EnchantingRecipe(
                0,
                20*10,
                new ItemStack[] {
                        new ItemStack(Items.ITEM_MAGIC_IRON)
                },

                new ItemStack(net.minecraft.item.Items.IRON_INGOT),
                new ItemStack(Items.ITEM_MAGIC_INGOT)
        ));
        /* Craft two magic iron ingots */
        EnchantingManager.register(new EnchantingRecipe(
                1,
                20*15,
                new ItemStack[] {
                        new ItemStack(Items.ITEM_MAGIC_IRON),
                        new ItemStack(Items.ITEM_MAGIC_IRON)
                },

                new ItemStack(net.minecraft.item.Items.IRON_INGOT),
                new ItemStack(net.minecraft.item.Items.IRON_INGOT),
                new ItemStack(Items.ITEM_MAGIC_INGOT),
                new ItemStack(Items.ITEM_MAGIC_INGOT)
        ));
        /* Craft one magic gold ingot */
        EnchantingManager.register(new EnchantingRecipe(
                1,
                20*10,
                new ItemStack[] {
                        new ItemStack(Items.ITEM_MAGIC_GOLD)
                },

                new ItemStack(net.minecraft.item.Items.GOLD_INGOT),
                new ItemStack(Items.ITEM_MAGIC_INGOT)
        ));
        /* Craft two magic gold ingots */
        EnchantingManager.register(new EnchantingRecipe(
                2,
                20*15,
                new ItemStack[] {
                        new ItemStack(Items.ITEM_MAGIC_GOLD),
                        new ItemStack(Items.ITEM_MAGIC_GOLD)
                },

                new ItemStack(net.minecraft.item.Items.GOLD_INGOT),
                new ItemStack(net.minecraft.item.Items.GOLD_INGOT),
                new ItemStack(Items.ITEM_MAGIC_INGOT),
                new ItemStack(Items.ITEM_MAGIC_INGOT)
        ));
        /* Craft one magic diamond */
        EnchantingManager.register(new EnchantingRecipe(
                3,
                20*17,
                new ItemStack[] {
                        new ItemStack(Items.ITEM_MAGIC_DIAMOND)
                },

                new ItemStack(net.minecraft.item.Items.DIAMOND),
                new ItemStack(Items.ITEM_MAGIC_INGOT),
                new ItemStack(Items.ITEM_MAGIC_INGOT),
                new ItemStack(Items.ITEM_MAGIC_INGOT)
        ));
	}
}
