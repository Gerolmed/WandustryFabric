package de.gerolmed.wandustry;

import de.gerolmed.wandustry.block.*;

/**
 * Class offers all custom blocks
 */
@SuppressWarnings("WeakerAccess")
public class Blocks {

    public static final BlockTest BLOCK_TEST = new BlockTest();
    public static final BlockRubyOre BLOCK_RUBY_ORE = new BlockRubyOre();
    public static final BlockSapphireOre BLOCK_SAPPHIRE_ORE = new BlockSapphireOre();
    public static final BlockEmeraldOre BLOCK_EMERALD_ORE = new BlockEmeraldOre();
    public static final BlockMagicOre BLOCK_MAGIC_ORE = new BlockMagicOre();
    public static final BlockEnchanter BLOCK_ENCHANTER = new BlockEnchanter();
    public static final BlockManaExtractor BLOCK_MANA_EXTRACTOR = new BlockManaExtractor();
    public static final BlockWandEditor BLOCK_WAND_EDITOR = new BlockWandEditor();

    public static BasicBlock[] values() {
        return new BasicBlock[] {BLOCK_TEST, BLOCK_RUBY_ORE, BLOCK_SAPPHIRE_ORE, BLOCK_EMERALD_ORE, BLOCK_MAGIC_ORE, BLOCK_ENCHANTER, BLOCK_MANA_EXTRACTOR, BLOCK_WAND_EDITOR};
    }
}
