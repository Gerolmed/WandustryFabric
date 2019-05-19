package de.gerolmed.wandustry.block;

import de.gerolmed.wandustry.CreativeTabs;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class BlockWandEditor extends BasicBlock {

    public static final DirectionProperty FACING;
    protected static final VoxelShape SHAPE = VoxelShapes.cuboid(.3, .01, .3, .7, .99, .7);


    public BlockWandEditor() {
        super("block_eand_editor",
                FabricBlockSettings.of(Material.METAL).build(),
                new Item.Settings().itemGroup(CreativeTabs.BASE_GROUP));
        this.setDefaultState((this.stateFactory.getDefaultState()).with(FACING, Direction.NORTH));

    }

    @Override
    public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        // Container registration
        if(!world.isClient) {
            //ContainerProviderRegistry.INSTANCE.openContainer(Containers.WAND_EDITOR, player, buf -> buf.writeBlockPos(blockPos));
        }
        return true;
    }

    public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return true;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
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

}
