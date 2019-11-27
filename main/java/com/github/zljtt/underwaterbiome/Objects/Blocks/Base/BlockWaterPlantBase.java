package com.github.zljtt.underwaterbiome.Objects.Blocks.Base;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedItem;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockWaterPlantBase extends Block implements ILiquidContainer, INeedItem
{

	VoxelShape shape;
	boolean put;
	public BlockWaterPlantBase(String name, Properties porperty, VoxelShape shape, boolean putIntoTab) 
	{
		super(porperty);
		this.shape = shape;
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		BlockInit.BLOCKS.add(this);
		put = putIntoTab;

	}

	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	
	public IFluidState getFluidState(BlockState state) {
		return Fluids.WATER.getStillFluidState(false);
	}
	@Override
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) 
	{
		if (!stateIn.isValidPosition(worldIn, currentPos)) 
		{
			worldIn.destroyBlock(currentPos, true);
			return Blocks.WATER.getDefaultState();
		}
//		worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) 
	{
		return VoxelShapes.empty() ;
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return shape;
	}
	@SuppressWarnings("deprecation")
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
	{
		if (!state.isValidPosition(worldIn, pos))
		{
			worldIn.destroyBlock(pos, true);
		}
		super.tick(state, worldIn, pos, random);
	}
	
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return false;
	}

	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
		return false;
	}
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) 
	{
		BlockPos blockpos = pos.down();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		Block block = blockstate.getBlock();
		return block != Blocks.MAGMA_BLOCK && blockstate.func_224755_d(worldIn, blockpos, Direction.UP);
	}
	
	@Override
	public boolean needItem() {
		// TODO Auto-generated method stub
		return put;
	}
}
