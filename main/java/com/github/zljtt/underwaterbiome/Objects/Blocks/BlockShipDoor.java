package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedItem;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockShipDoor extends DoorBlock implements INeedBluePrint, INeedItem
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	BlueprintInfo info;
	public BlockShipDoor(String name, Properties porperty, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(porperty);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		this.info= new BlueprintInfo(needBlueprint, difficulty, type);

		BlockInit.BLOCKS.add(this);

	}
	public IFluidState getFluidState(BlockState state) 
	{
	      return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
	      BlockPos blockpos = context.getPos();
	      IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
	      if (blockpos.getY() < 255 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context)) 
	      {
	         World world = context.getWorld();
	         boolean flag = world.isBlockPowered(blockpos) || world.isBlockPowered(blockpos.up());
	         return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing())
	        		 .with(HINGE, this.getHingeSide(context))
	        		 .with(POWERED, Boolean.valueOf(flag))
	        		 .with(OPEN, Boolean.valueOf(flag))
	        		 .with(HALF, DoubleBlockHalf.LOWER)
	        		 .with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	      } 
	      else 
	      {
	         return null;
	      }
	   }
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (state.get(WATERLOGGED))
        {
           worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
           }
		if (player.getHeldItem(handIn).getItem().equals(ItemInit.DECIPHER_DEVICE))
		{
			 state = state.cycle(OPEN);
	         worldIn.setBlockState(pos, state, 10);
	         worldIn.playEvent(player, 
						state.get(BlockStateProperties.OPEN) ? 		
								(state.getMaterial() == Material.IRON ? 1005 : 1006) : (state.getMaterial() == Material.IRON ? 1011 : 1012)
						, pos, 0);	
	 	    return true;
		}
		else return false;
	                 
	      
	   }
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) 
	{
		if (stateIn.get(WATERLOGGED)) 
		{
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	    }
		DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
	    if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP))
	    {
	         return facingState.getBlock() == this && facingState.get(HALF) != doubleblockhalf ? stateIn.with(FACING, facingState.get(FACING)).with(OPEN, facingState.get(OPEN)).with(HINGE, facingState.get(HINGE)).with(POWERED, facingState.get(POWERED)) : Blocks.AIR.getDefaultState();
	    } 
	    else
	    {
	        return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	    }
	}

	private DoorHingeSide getHingeSide(BlockItemUseContext p_208073_1_) 
	{
	      IBlockReader iblockreader = p_208073_1_.getWorld();
	      BlockPos blockpos = p_208073_1_.getPos();
	      Direction direction = p_208073_1_.getPlacementHorizontalFacing();
	      BlockPos blockpos1 = blockpos.up();
	      Direction direction1 = direction.rotateYCCW();
	      BlockPos blockpos2 = blockpos.offset(direction1);
	      BlockState blockstate = iblockreader.getBlockState(blockpos2);
	      BlockPos blockpos3 = blockpos1.offset(direction1);
	      BlockState blockstate1 = iblockreader.getBlockState(blockpos3);
	      Direction direction2 = direction.rotateY();
	      BlockPos blockpos4 = blockpos.offset(direction2);
	      BlockState blockstate2 = iblockreader.getBlockState(blockpos4);
	      BlockPos blockpos5 = blockpos1.offset(direction2);
	      BlockState blockstate3 = iblockreader.getBlockState(blockpos5);
	      int i = (blockstate.func_224756_o(iblockreader, blockpos2) ? -1 : 0) + (blockstate1.func_224756_o(iblockreader, blockpos3) ? -1 : 0) + (blockstate2.func_224756_o(iblockreader, blockpos4) ? 1 : 0) + (blockstate3.func_224756_o(iblockreader, blockpos5) ? 1 : 0);
	      boolean flag = blockstate.getBlock() == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
	      boolean flag1 = blockstate2.getBlock() == this && blockstate2.get(HALF) == DoubleBlockHalf.LOWER;
	      if ((!flag || flag1) && i <= 0) {
	         if ((!flag1 || flag) && i >= 0) {
	            int j = direction.getXOffset();
	            int k = direction.getZOffset();
	            Vec3d vec3d = p_208073_1_.getHitVec();
	            double d0 = vec3d.x - (double)blockpos.getX();
	            double d1 = vec3d.z - (double)blockpos.getZ();
	            return (j >= 0 || !(d1 < 0.5D)) && (j <= 0 || !(d1 > 0.5D)) && (k >= 0 || !(d0 > 0.5D)) && (k <= 0 || !(d0 < 0.5D)) ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
	         } else {
	            return DoorHingeSide.LEFT;
	         }
	      } else {
	         return DoorHingeSide.RIGHT;
	      }
	   }
	@Override
	public BlueprintInfo getBlueprintInfo() 
	{
		return info;
	}
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	{
	      builder.add(HALF, FACING, OPEN, HINGE, POWERED,WATERLOGGED);
	}
	@Override
	public boolean needItem() {
		// TODO Auto-generated method stub
		return true;
	}

}
