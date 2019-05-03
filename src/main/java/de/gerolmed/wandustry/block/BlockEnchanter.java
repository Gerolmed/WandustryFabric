package de.gerolmed.wandustry.block;

import de.gerolmed.wandustry.CreativeTabs;
import de.gerolmed.wandustry.block.entity.EnchanterBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.ArrayList;

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

        EnchanterBlockEntity enchanter = (EnchanterBlockEntity) world_1.getBlockEntity(blockPos_1);

        ItemStack itemStack = playerEntity_1.getMainHandStack();
        boolean emptyClick = itemStack.getItem() == Items.AIR;
        boolean sneak = playerEntity_1.isSneaking();
        if(emptyClick && sneak) {
            // Remove all items
            ArrayList<ItemStack> items = enchanter.clearAllItems();
            for(ItemStack item : items) {
                world_1.spawnEntity(new ItemEntity(world_1, blockPos_1.getX()+0.5, blockPos_1.getY()+1, blockPos_1.getZ()+0.5, item));
            }
        } else if(emptyClick) {
            // Remove last
            ItemStack item = enchanter.clearLastItem();

            if(item != null)
                world_1.spawnEntity(new ItemEntity(world_1, blockPos_1.getX()+0.5, blockPos_1.getY()+1, blockPos_1.getZ()+0.5, item));

        } else {
            // Add an item
            ItemStack itemAdd = itemStack.copy();

            itemAdd.setAmount(1);

            boolean added = enchanter.addItemStack(itemAdd);

            if(added)
                itemStack.setAmount(itemStack.getAmount()-1);
            return true;
        }

        return super.activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
    }
}
