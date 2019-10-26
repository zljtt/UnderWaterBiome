package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.Random;
import java.util.function.Function;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FeatureLime extends Feature<NoFeatureConfig> 
{
	public FeatureLime(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51487_1_) 
	{
	      super(p_i51487_1_);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) 
	{		
		int amount = 3+rand.nextInt(5);
		for (int i = 0; i < amount ; i++)
		{
			int x = pos.getX() + rand.nextInt(8) - rand.nextInt(8);
			int z = pos.getZ() + rand.nextInt(8) - rand.nextInt(8);
			BlockPos blockpos = new BlockPos(x, worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, x, z), z);
			if (worldIn.getBlockState(blockpos.down()).getBlock()==Blocks.SAND)
			{
				worldIn.setBlockState(blockpos, BlockInit.LIME.getStateWithRandomDirection(), 2);
				return true;
			}	
			
		}
		return false;
		
	}


}
