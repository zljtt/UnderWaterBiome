package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.TorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockWaterTorch extends TorchBlock implements IWaterLoggable,INeedBluePrint
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	BlueprintInfo info;
	public BlockWaterTorch(String name, Block.Properties porperty, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(porperty);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		this.info= new BlueprintInfo(needBlueprint, difficulty, type);
	    this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false));

		BlockInit.BLOCKS.add(this);
//		ItemInit.ITEMS.add(new BlockItem(this, new Item.Properties().group((BlockInit.blockGroup))).setRegistryName(this.getRegistryName()));

	}

	@Override
	public BlueprintInfo getBlueprintInfo() 
	{
		return info;
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
	      return false;
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
	      IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
	      return super.getStateForPlacement(context).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));

	}
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) 
	{
	      if (stateIn.get(WATERLOGGED)) 
	      {
	         worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	      }

	      return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos) ? 
	    		  (stateIn.get(WATERLOGGED)?Blocks.WATER.getDefaultState():Blocks.AIR.getDefaultState()):
	    			  super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	   }

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	{
	      builder.add(WATERLOGGED);
	}
//	@Override
//	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) 
//	{
//	      return worldIn.getBlockState(pos.down()).isSolid();
//	}
	@Override
	@OnlyIn(Dist.CLIENT)
	   public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
	      double d0 = (double)pos.getX() + 0.5D;
	      double d1 = (double)pos.getY() + 0.7D;
	      double d2 = (double)pos.getZ() + 0.5D;
	      if(stateIn.get(WATERLOGGED))
	      {
		      worldIn.addParticle(ParticleTypes.BUBBLE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	      }else
	      {
		      worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		      worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);

	      }
	   }

}
