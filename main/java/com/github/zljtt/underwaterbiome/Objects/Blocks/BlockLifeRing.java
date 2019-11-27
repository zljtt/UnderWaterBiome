package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockBase;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterLoggedBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class BlockLifeRing extends BlockWaterLoggedBase
{
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

	public BlockLifeRing(String name, Properties porperty, boolean needitem, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, porperty, needitem, needBlueprint, type, difficulty);
	}
	@Override
	public BlockState getStateForPlacement(BlockState state, Direction facing, BlockState state2, IWorld world,
			BlockPos pos1, BlockPos pos2, Hand hand) 
	{
		return super.getStateForPlacement(state, facing, state2, world, pos1, pos2, hand).with(HORIZONTAL_FACING, facing);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	{
	      builder.add(HORIZONTAL_FACING);
	      super.fillStateContainer(builder);
	}

}
