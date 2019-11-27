package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedItem;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockMangroveLeaf extends LeavesBlock implements IWaterLoggable, INeedItem
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BlockMangroveLeaf(String name, Properties properties)
	{
		super(properties);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
	    this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false));
		BlockInit.BLOCKS.add(this);
//		ItemInit.ITEMS.add(new BlockItem(this, new Item.Properties().group((BlockInit.blockGroup))).setRegistryName(this.getRegistryName()));

		// TODO Auto-generated constructor stub
	}
	@Override
	public void randomTick(BlockState state, World worldIn, BlockPos pos, Random random) 
	{
		if (!state.get(PERSISTENT) && state.get(DISTANCE) == 7) 
		{
	         spawnDrops(state, worldIn, pos);
	         worldIn.removeBlock(pos, false);
	     }	
//		else 
//		{
//			CodEntity fish = new CodEntity(EntityType.COD, worldIn);
//			fish.setPosition(pos.getX(),pos.getY() , pos.getZ());
//			worldIn.addEntity(fish);
//		}
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
	@Override
	public boolean needItem() {
		// TODO Auto-generated method stub
		return true;
	}

}
