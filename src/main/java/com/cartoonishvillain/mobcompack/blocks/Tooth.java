package com.cartoonishvillain.mobcompack.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class Tooth extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public Tooth(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public RenderShape getRenderShape(BlockState p_49090_) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49048_) {
        return this.defaultBlockState().setValue(FACING, p_49048_.getNearestLookingDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState p_49085_, Rotation p_49086_) {
        return p_49085_.setValue(FACING, p_49086_.rotate(p_49085_.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_49082_, Mirror p_49083_) {
        return p_49082_.rotate(p_49083_.getRotation(p_49082_.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(FACING);
    }
}
