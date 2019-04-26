package de.gerolmed.fabricmod;

import de.gerolmed.fabricmod.block.*;

/**
 * Class offers all custom blocks
 */
public class Blocks {

    public static final BlockTest BLOCK_TEST = new BlockTest();
    public static final BlockRubyOre BLOCK_RUBY_ORE = new BlockRubyOre();
    public static final BlockSapphireOre BLOCK_SAPPHIRE_ORE = new BlockSapphireOre();
    public static final BlockEmeraldOre BLOCK_EMERALD_ORE = new BlockEmeraldOre();
    public static final BlockMagicOre BLOCK_MAGIC_ORE = new BlockMagicOre();

    public static BasicBlock[] values() {
        return new BasicBlock[] {BLOCK_TEST, BLOCK_RUBY_ORE, BLOCK_SAPPHIRE_ORE, BLOCK_EMERALD_ORE, BLOCK_MAGIC_ORE};
    }
}
