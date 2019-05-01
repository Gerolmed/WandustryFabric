package de.gerolmed.wandustry.block;

import de.gerolmed.wandustry.CreativeTabs;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;

public class BlockManaExtractor extends BasicBlock {

    public static final DirectionProperty FACING;
    protected static final VoxelShape SHAPE = VoxelShapes.cuboid(.3, .01, .3, .7, .99, .7);


    public BlockManaExtractor() {
        super("block_mana_extractor",
                FabricBlockSettings.of(Material.METAL).build(),
                new Item.Settings().itemGroup(CreativeTabs.BASE_GROUP));
        this.setDefaultState((this.stateFactory.getDefaultState()).with(FACING, Direction.NORTH));

    }

    public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return true;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(FACING);
    }

    @Override
    public BlockState rotate(BlockState blockState_1, BlockRotation blockRotation_1) {
        return blockState_1.with(FACING, blockRotation_1.rotate(blockState_1.get(FACING)));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getPlayerHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState mirror(BlockState blockState_1, BlockMirror blockMirror_1) {
        return blockState_1.rotate(blockMirror_1.getRotation(blockState_1.get(FACING)));
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {

        if(direction_1 == Direction.DOWN) {
            if(blockState_2.getBlock() == Blocks.AIR) {
                iWorld_1.getWorld().spawnEntity(new ItemEntity(iWorld_1.getWorld(), blockPos_1.getX()+0.5, blockPos_1.getY()+.2, blockPos_1.getZ()+0.5, new ItemStack(this)));
                return Blocks.AIR.getDefaultState();
            }
        }

        return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
    }

    @Override
    public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        if(viewableWorld_1.getBlockState(blockPos_1.down()).getBlock() == Blocks.AIR) {
            return false;
        }
        return super.canPlaceAt(blockState_1, viewableWorld_1, blockPos_1);
    }
}
