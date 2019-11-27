package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterPlantBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockWaterGrass extends BlockWaterPlantBase
{

	public BlockWaterGrass(String name, Properties porperty) 
	{
		super(name, porperty, Block.makeCuboidShape(2, 0, 2, 14, 8, 14), true);
	}
	
	
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) 
	{
		BlockPos blockpos = pos.down();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		Block block = blockstate.getBlock();
		return block != Blocks.MAGMA_BLOCK || blockstate.func_224755_d(worldIn, blockpos, Direction.UP);
	}
}
