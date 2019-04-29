package de.gerolmed.wandustry.block;

import de.gerolmed.wandustry.CreativeTabs;
import de.gerolmed.wandustry.block.entity.EnchanterBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
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

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {

        EnchanterBlockEntity enchanterBlockEntity = (EnchanterBlockEntity) world_1.getBlockEntity(blockPos_1);
        enchanterBlockEntity.setItemStack(playerEntity_1.getMainHandStack());
        System.out.println("Setting finished!");

        return super.activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
    }
}
