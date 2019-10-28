package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterPlantBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockMeandrina extends BlockWaterPlantBase
{
    protected static final VoxelShape SHAPE_BASE = Block.makeCuboidShape(1, 0, 1, 15, 4, 15);
    protected static final VoxelShape SHAPE_MID = Block.makeCuboidShape(2, 4, 2, 14, 8, 14);
    protected static final VoxelShape SHAPE_TOP = Block.makeCuboidShape(4, 8, 4, 12, 10, 12);

    protected static final VoxelShape SHAPE0 = VoxelShapes.combine(SHAPE_BASE, SHAPE_MID, IBooleanFunction.OR);
    protected static final VoxelShape SHAPE = VoxelShapes.combine(SHAPE0, SHAPE_TOP, IBooleanFunction.OR);


	public BlockMeandrina(String name, Properties porperty) 
	{
		super(name, porperty,SHAPE, true);
	}

	@Override
	public BlockRenderLayer getRenderLayer() 
	{
	      return BlockRenderLayer.SOLID;
	}
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) 
	{
	      return SHAPE;
	}
	
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		double d0 = (double)pos.getX() + 0.5D+rand.nextDouble()-rand.nextDouble();
	      double d1 = (double)pos.getY() + 0.7D+rand.nextDouble()-rand.nextDouble();
	      double d2 = (double)pos.getZ() + 0.5D+rand.nextDouble()-rand.nextDouble();
		if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.WATER)
		{
		      worldIn.addParticle(ParticleTypes.BUBBLE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) 
	{
		BlockPos blockpos = pos.down();
	    BlockState blockstate = worldIn.getBlockState(blockpos);
	    Block block = blockstate.getBlock();
	    if (block == Blocks.MAGMA_BLOCK) 
	    {
	    	return false;
	    } 
	    else 
	    {
	    	return blockstate.func_224755_d(worldIn, blockpos, Direction.UP) &&  worldIn.getBlockState(pos.up()).getBlock()==Blocks.WATER ;
	    }
	}
}
