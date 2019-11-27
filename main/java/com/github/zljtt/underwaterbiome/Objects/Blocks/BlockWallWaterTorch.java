package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockWallWaterTorch extends BlockWaterTorch
{
	public static final DirectionProperty HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
	   private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.makeCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.makeCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.makeCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.makeCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

	 public BlockWallWaterTorch(String name, Properties porperty, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, porperty, needBlueprint, type, difficulty);
	    this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false));
	}
	public String getTranslationKey() 
	{
	      return "blocks.underwaterbiome.water_torch";
	   }

	   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	   {
	      return func_220289_j(state);
	   }

	   public static VoxelShape func_220289_j(BlockState p_220289_0_) 
	   {
	      return SHAPES.get(p_220289_0_.get(HORIZONTAL_FACING));
	   }

	   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) 
	   {
	      Direction direction = state.get(HORIZONTAL_FACING);
	      BlockPos blockpos = pos.offset(direction.getOpposite());
	      BlockState blockstate = worldIn.getBlockState(blockpos);
	      return blockstate.func_224755_d(worldIn, blockpos, direction);
	   }

	   @Nullable
	   public BlockState getStateForPlacement(BlockItemUseContext context) 
	   {
	      BlockState blockstate = this.getDefaultState();
	      IWorldReader iworldreader = context.getWorld();
	      BlockPos blockpos = context.getPos();
	      Direction[] adirection = context.getNearestLookingDirections();
	      IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
	      
	      for(Direction direction : adirection) 
	      {
	         if (direction.getAxis().isHorizontal()) 
	         {
	            Direction direction1 = direction.getOpposite();
	            blockstate = blockstate.with(HORIZONTAL_FACING, direction1);
	            if (blockstate.isValidPosition(iworldreader, blockpos)) 
	            {
	               return blockstate.with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	            }
	         }
	      }

	      return null;
	   }

	   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) 
	   {
		   if (facing.getOpposite() == stateIn.get(HORIZONTAL_FACING) && !stateIn.isValidPosition(worldIn, currentPos))
			{
				worldIn.destroyBlock(currentPos, true);
				
			}
		   if (stateIn.get(WATERLOGGED)) 
		      {
		         worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		      }

	      return  super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	   }


	   @OnlyIn(Dist.CLIENT)
	   public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	      Direction direction = stateIn.get(HORIZONTAL_FACING);
	      double d0 = (double)pos.getX() + 0.5D;
	      double d1 = (double)pos.getY() + 0.7D;
	      double d2 = (double)pos.getZ() + 0.5D;
	      double d3 = 0.22D;
	      double d4 = 0.27D;
	      Direction direction1 = direction.getOpposite();
	      if(stateIn.get(WATERLOGGED))
	      {
		      worldIn.addParticle(ParticleTypes.BUBBLE, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);

	      }
	      else
	      {
		      worldIn.addParticle(ParticleTypes.SMOKE, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		      worldIn.addParticle(ParticleTypes.FLAME, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
	      }
	   }

	   public BlockState rotate(BlockState state, Rotation rot) {
	      return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
	   }

	   public BlockState mirror(BlockState state, Mirror mirrorIn) 
	   {
	      return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
	   }

	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	   {
	      builder.add(HORIZONTAL_FACING,WATERLOGGED);
	   }
	   public ResourceLocation getLootTable() 
		{
			return new ResourceLocation(Reference.MODID,"blocks/wall_water_torch");
			
		};
}
