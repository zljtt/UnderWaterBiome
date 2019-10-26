package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.Random;
import java.util.function.Function;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FeatureReef extends Feature<NoFeatureConfig>
{
	public FeatureReef(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51487_1_) 
	{
	      super(p_i51487_1_);
	}


	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		int size = 2 + rand.nextInt(3);

		for(int i =-size; i<=size; i++)
		{
			for(int j =-1; j<=1; j++)
			{
				for(int k =-size; k<=size; k++)
				{
					if ((float)(i * i + k * k+ j* j) <= rand.nextFloat() * 15F - rand.nextFloat() * 5F) 
			        {
			            worldIn.setBlockState(pos.add(i, -1+j, k), BlockInit.REEF.getDefaultState(), 2);
			        }
				}
				
			}
		}

        return true;
	}
	


}
