package com.github.zljtt.underwaterbiome.Objects.Blocks.Base;

import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockWaterLoggedBase extends BlockBase implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BlockWaterLoggedBase(String name, Properties porperty,boolean item, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, porperty,item, needBlueprint,  type, difficulty);
	    this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false));

	}

	public IFluidState getFluidState(BlockState state) 
	{
	      return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{

	         if (state.get(WATERLOGGED))
	         {
	            worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	         }
	         return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
	      IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
	      return super.getStateForPlacement(context).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));

	}
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
	      if (stateIn.get(WATERLOGGED)) 
	      {
	         worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	      }

	      return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	   }

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	{
	      builder.add(WATERLOGGED);
	      super.fillStateContainer(builder);
	}
}
