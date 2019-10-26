package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.Random;
import java.util.function.Function;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FeatureWaterGrass extends Feature<NoFeatureConfig> 
{
	public FeatureWaterGrass(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51487_1_) 
	{
	      super(p_i51487_1_);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) 
	{		
		int i = 0;

	      for(int j = 0; j < 10; ++j) 
	      {
	         int k = rand.nextInt(8) - rand.nextInt(8);
	         int l = rand.nextInt(8) - rand.nextInt(8);
	         int i1 = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + k, pos.getZ() + l);
	         BlockPos blockpos = new BlockPos(pos.getX() + k, i1, pos.getZ() + l);
	         if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER) 
	         {
	            BlockState blockstate = rand.nextBoolean() ? BlockInit.WATER_GRASS_GREEN.getDefaultState() : BlockInit.WATER_GRASS_RED.getDefaultState();
	            if (blockstate.isValidPosition(worldIn, blockpos)) 
	            {
	            	if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER) 
	                  {
	                     worldIn.setBlockState(blockpos, blockstate, 2);
	                  } 
	               ++i;
	            }
	         }
	      }

	      return i > 0;
		
	}


}
