package de.gerolmed.wandustry.block;

import de.gerolmed.wandustry.CreativeTabs;
import de.gerolmed.wandustry.block.entity.EnchanterBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class BlockEnchanter extends BasicBlock implements BlockEntityProvider {
    public BlockEnchanter() {
        super("block_enchanter",
                FabricBlockSettings.of(Material.METAL).build(),
                new Item.Settings().itemGroup(CreativeTabs.BASE_GROUP));
    }

    public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }


    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new EnchanterBlockEntity();
    }
}
