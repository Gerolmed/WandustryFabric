package de.gerolmed.wandustry.block;

import de.gerolmed.wandustry.CreativeTabs;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tag.FabricItemTags;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class BlockSapphireOre extends BasicBlock {
    public BlockSapphireOre() {
        super("block_sapphire_ore",
                FabricBlockSettings.of(Material.METAL)
                        .strength(4,10)
                        .breakByTool(FabricItemTags.PICKAXES, 2)
                        .build(),
                new Item.Settings().itemGroup(CreativeTabs.BASE_GROUP));
    }

    public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.MODEL;
    }
}
