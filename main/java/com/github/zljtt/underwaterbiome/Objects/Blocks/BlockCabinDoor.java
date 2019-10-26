package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterLoggedBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCabinDoor extends BlockWaterLoggedBase 
{
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	protected static final VoxelShape SHAPE_BASE = Block.makeCuboidShape(0, 0, 0, 	16, 2, 16);
    protected static final VoxelShape SHAPE_TOP = Block.makeCuboidShape(16, 16, 16, 	0, 14, 0);

    protected static final VoxelShape SHAPE_MID_N = Block.makeCuboidShape(0, 2, 0, 		16, 14, 2);
    protected static final VoxelShape SHAPE_MID_S = Block.makeCuboidShape(16, 2, 16, 	0, 14, 14);
    
    protected static final VoxelShape SHAPE_MID_E = Block.makeCuboidShape(16, 2, 16, 	14, 14, 0);
    protected static final VoxelShape SHAPE_MID_W = Block.makeCuboidShape(0, 2, 0, 		2, 14, 16);
    
    protected static final VoxelShape SHAPE_MID_NI = Block.makeCuboidShape(0, 2, 0, 		16, 14, 1);
    protected static final VoxelShape SHAPE_MID_SI = Block.makeCuboidShape(16, 2, 16, 	0, 14, 15);
    
    protected static final VoxelShape SHAPE_MID_EI = Block.makeCuboidShape(16, 2, 16, 	15, 14, 0);
    protected static final VoxelShape SHAPE_MID_WI = Block.makeCuboidShape(0, 2, 0, 		1, 14, 16);

    protected static final VoxelShape SHAPE_ALL = VoxelShapes.combine(SHAPE_TOP, SHAPE_BASE, IBooleanFunction.OR);


	public BlockCabinDoor(String name, Properties porperty, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, porperty, needBlueprint,  type,  difficulty);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
	    Direction direction = state.get(FACING);
	    switch(direction) 
	    {
	    case EAST:
	         return combineALl(SHAPE_ALL,SHAPE_MID_EI,SHAPE_MID_S,SHAPE_MID_N);
	    case WEST:
	    	return combineALl(SHAPE_ALL,SHAPE_MID_WI,SHAPE_MID_S,SHAPE_MID_N);
	    case SOUTH:
	    	return combineALl(SHAPE_ALL,SHAPE_MID_SI,SHAPE_MID_W,SHAPE_MID_E);
	    case NORTH://
	    	return combineALl(SHAPE_ALL,SHAPE_MID_NI,SHAPE_MID_W,SHAPE_MID_W);
	    default:
	        return combineALl(SHAPE_ALL,SHAPE_MID_NI,SHAPE_MID_W,SHAPE_MID_W);
      }	
	}

	static VoxelShape combineALl(VoxelShape a,VoxelShape b, VoxelShape c, VoxelShape d)
	{
		return VoxelShapes.combine(VoxelShapes.combine(a,b,IBooleanFunction.OR), VoxelShapes.combine(c,d,IBooleanFunction.OR), IBooleanFunction.OR);
	}
	@Override
	 public BlockRenderLayer getRenderLayer() 
	 {
	      return BlockRenderLayer.CUTOUT_MIPPED;
	 }
	@Override
	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) 
	{
	      return 1;
	   }
	
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
	{
		return true;
	}
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) 
	{
		 if (state.get(WATERLOGGED))
         {
			 
            worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
         }
		 if (player.getDistanceSq(pos.getX()+0.5,pos.getY(),pos.getZ()+0.5)<6)
		 {
			 if (player.getAdjustedHorizontalFacing() == state.get(FACING).getOpposite().getOpposite()||hit.getFace() == state.get(FACING).getOpposite())
			 {
				 BlockPos pos_behind = pos.offset(state.get(FACING));
				 if  ((worldIn.getBlockState(pos_behind).allowsMovement(worldIn, pos_behind, PathType.WATER)
						 ||worldIn.getBlockState(pos_behind).allowsMovement(worldIn, pos_behind, PathType.LAND))
					&& (worldIn.getBlockState(pos_behind.down()).allowsMovement(worldIn, pos_behind.down(), PathType.WATER)
						 ||worldIn.getBlockState(pos_behind.down()).allowsMovement(worldIn, pos_behind.down(), PathType.LAND)))
				 {
					 player.setPosition(pos_behind.down().getX()+0.5, pos_behind.down().getY(), pos_behind.down().getZ()+0.5);
						return true;

				 }
				 else if ((worldIn.getBlockState(pos_behind).allowsMovement(worldIn, pos_behind, PathType.WATER)
						 ||worldIn.getBlockState(pos_behind).allowsMovement(worldIn, pos_behind, PathType.LAND))
					&& (worldIn.getBlockState(pos_behind.up()).allowsMovement(worldIn, pos_behind.up(), PathType.WATER)
						 ||worldIn.getBlockState(pos_behind.up()).allowsMovement(worldIn, pos_behind.up(), PathType.LAND)))
				 {
					 player.setPosition(pos_behind.getX()+0.5, pos_behind.getY(), pos_behind.getZ()+0.5);
						return true;
				 }
			 }
			 else if (player.getAdjustedHorizontalFacing() == state.get(FACING).getOpposite()||hit.getFace() == state.get(FACING))
			 {
				 BlockPos pos_front =  pos.offset(state.get(FACING).getOpposite());
				 if  ((worldIn.getBlockState(pos_front).allowsMovement(worldIn, pos_front, PathType.WATER)
						 ||worldIn.getBlockState(pos_front).allowsMovement(worldIn, pos_front, PathType.LAND))
					&& (worldIn.getBlockState(pos_front.down()).allowsMovement(worldIn, pos_front.down(), PathType.WATER)
						 ||worldIn.getBlockState(pos_front.down()).allowsMovement(worldIn, pos_front.down(), PathType.LAND)))
				 {
					 player.setPosition(pos_front.down().getX()+0.5, pos_front.down().getY(), pos_front.down().getZ()+0.5);
						return true;	
				 }
				 else if ((worldIn.getBlockState(pos_front).allowsMovement(worldIn, pos_front, PathType.WATER)
						 ||worldIn.getBlockState(pos_front).allowsMovement(worldIn, pos_front, PathType.LAND))
					&& (worldIn.getBlockState(pos_front.up()).allowsMovement(worldIn, pos_front.up(), PathType.WATER)
						 ||worldIn.getBlockState(pos_front.up()).allowsMovement(worldIn, pos_front.up(), PathType.LAND)))
				 {
					 player.setPosition(pos_front.getX()+0.5, pos_front.getY(), pos_front.getZ()+0.5);
						return true;

				 }
			 }
		 }


		 return false;

	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
	      BlockPos blockpos = context.getPos();
	      IFluidState ifluidstate = context.getWorld().getFluidState(blockpos);
	      return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
	      builder.add(FACING, WATERLOGGED);
	}

}
