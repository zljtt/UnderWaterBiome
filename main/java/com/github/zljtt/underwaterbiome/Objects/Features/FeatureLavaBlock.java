package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FeatureLavaBlock extends Feature<NoFeatureConfig> 
{
	public FeatureLavaBlock(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51487_1_) 
	{
	      super(p_i51487_1_);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random random,
			BlockPos pos, NoFeatureConfig config) 
	{		
		int amount = 3+random.nextInt(5);
		for (int i =0; i<amount; i++)
		{
			int x = pos.getX()+ random.nextInt(8)-random.nextInt(8);
			int z = pos.getZ()+ random.nextInt(8)-random.nextInt(8);
			BlockPos blockpos = new BlockPos(x, worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, x, z), z).down();
			worldIn.setBlockState(blockpos, Blocks.MAGMA_BLOCK.getDefaultState(), 2);
			worldIn.setBlockState(blockpos.down(), Blocks.LAVA.getDefaultState(), 2);
		}
		
		return true;
	}

}
