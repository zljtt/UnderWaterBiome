package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterLoggedBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockLime extends BlockWaterLoggedBase 
{
	   public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    protected static final VoxelShape BOUNDING_BOX_0 = Block.makeCuboidShape(3, 0, 3, 15, 2, 15);

	public BlockLime(String name, Properties porperty, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, porperty, true, needBlueprint, type,  difficulty);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		// TODO Auto-generated method stub
		return BOUNDING_BOX_0;
	}
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) 
	{
		return BOUNDING_BOX_0;
	}
	public BlockState getStateWithRandomDirection()
	{
		Random rand = new Random();
		int ran = rand.nextInt(4);
		Direction facing;
		switch(ran)
		{
		case 0:
			facing=Direction.EAST;
			break;
		case 1:
			facing=Direction.WEST;
			break;
		case 2:
			facing=Direction.NORTH;
			break;
		default:
			facing=Direction.EAST;
		}
		return this.getDefaultState().with(BlockLime.FACING, facing);
	}
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
	      Direction direction = context.getFace();
	      BlockPos blockpos = context.getPos();
	      IFluidState ifluidstate = context.getWorld().getFluidState(blockpos);
	      return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
	      builder.add(FACING, WATERLOGGED);
	}

}
