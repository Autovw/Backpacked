package com.mrcrayfish.backpacked.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Author: MrCrayfish
 */
public class ShelfBlock extends HorizontalBlock
{
    private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(
        Direction.NORTH, Block.box(2, 1, 7, 14, 5, 16),
        Direction.SOUTH, Block.box(2, 1, 0, 14, 5, 9),
        Direction.WEST, Block.box(7, 1, 2, 16, 5, 14),
        Direction.EAST, Block.box(0, 1, 2, 9, 5, 14))
    );

    public ShelfBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result)
    {
        if(!world.isClientSide())
        {
            System.out.println("Yo");
        }
        return super.use(state, world, pos, player, hand, result);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos)
    {
        Direction facing = state.getValue(FACING);
        BlockPos relativePos = pos.relative(facing.getOpposite());
        BlockState relativeState = reader.getBlockState(relativePos);
        return relativeState.isFaceSturdy(reader, relativePos, facing);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState potentialState = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        if(this.canSurvive(potentialState, context.getLevel(), context.getClickedPos()))
        {
            return potentialState;
        }
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }
}