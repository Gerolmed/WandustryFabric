package de.gerolmed.fabricmod;

import de.gerolmed.fabricmod.block.BasicBlock;
import de.gerolmed.fabricmod.block.BlockRubyOre;
import de.gerolmed.fabricmod.block.BlockTest;

/**
 * Class offers all custom blocks
 */
public class Blocks {

    public static final BlockTest BLOCK_TEST = new BlockTest();
    public static final BlockRubyOre BLOCK_RUBY_ORE = new BlockRubyOre();

    public static BasicBlock[] values() {
        return new BasicBlock[] {BLOCK_TEST, BLOCK_RUBY_ORE};
    }
}
