package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.Random;
import java.util.function.Function;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockKelps.BlockKelpTop;
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

public class FeatureLightingKelp extends Feature<NoFeatureConfig> 
{
	public FeatureLightingKelp(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51487_1_) 
	{
	      super(p_i51487_1_);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
	      int i = 0;
	      int j = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX(), pos.getZ());
	      BlockPos blockpos = new BlockPos(pos.getX(), j, pos.getZ());
	      if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER) 
	      {
	         BlockState blockstate_top = BlockInit.KELP_TOP.getDefaultState();
	         BlockState blockstate_body = BlockInit.KELP.getDefaultState();
	         BlockState blockstate_light = BlockInit.KELP_LIGHT.getDefaultState();
	         int k = 1 + rand.nextInt(20);

	         for(int l = 0; l <= k; ++l) 
	         {
	            if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER 
	            		&& worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.WATER
	            		&& blockstate_body.isValidPosition(worldIn, blockpos)) 
	            {
	            	if (l == k) 
	            	{
	            		worldIn.setBlockState(blockpos, blockstate_top.with(BlockKelpTop.AGE, Integer.valueOf(rand.nextInt(23))), 2);
	            		++i;
	            	} 
	            	else 
	            	{
	            		if (rand.nextInt(100) < 4)
	            		{
		            		worldIn.setBlockState(blockpos, blockstate_light, 2);
	            		}
	            		else
		            		worldIn.setBlockState(blockpos, blockstate_body, 2);
	            	}
	            } 
	            else if (l > 0) 
	            {
	               BlockPos blockpos1 = blockpos.down();
	               if (blockstate_top.isValidPosition(worldIn, blockpos1) && worldIn.getBlockState(blockpos1.down()).getBlock() != BlockInit.KELP_TOP) 
	               {
	                  worldIn.setBlockState(blockpos1, blockstate_top.with(BlockKelpTop.AGE, Integer.valueOf(rand.nextInt(23))), 2);
	                  ++i;
	               }
	               break;
	            }

	            blockpos = blockpos.up();
	         }
	      }

	      return i > 0;
	   }

}
