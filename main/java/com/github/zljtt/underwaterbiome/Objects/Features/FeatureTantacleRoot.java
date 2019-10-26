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

public class FeatureTantacleRoot extends Feature<NoFeatureConfig> 
{
	public FeatureTantacleRoot(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51487_1_) 
	{
	      super(p_i51487_1_);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) 
	{		
		BlockPos blockpos_start = new BlockPos(pos.getX(), worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ()).down();
		
		if (worldIn.getBlockState(blockpos_start).getBlock()!=BlockInit.LIVING_ROOT)
		{
			int height = 6 + rand.nextInt(6);
			int size = 0+(height>10?1:0);
			
			for(int j =0; j<=height; ++j)
			{
				for(int i =-1-size; i<=1+size; ++i)
				{
					for(int k =-1-size; k<=1+size; ++k)
					{
						if ((float)(i*i + k*k) <= rand.nextFloat() * (1.5+size)-rand.nextFloat() * (0.5+size/4)
								&&worldIn.getBlockState(pos.add(i, -1+j, k)).getBlock()==Blocks.WATER) 
				        {
				            worldIn.setBlockState(pos.add(i, -1+j, k), BlockInit.LIVING_ROOT.getDefaultState(), 2);
				        }
					}					
				}
				if (j<=height*2/3
						&&worldIn.getBlockState(pos.add(0, -1+j, 0)).getBlock()==Blocks.WATER)
				{
		            worldIn.setBlockState(pos.add(0, -1+j, 0), BlockInit.LIVING_ROOT.getDefaultState(), 2);
				}
			}
			for(int i =-5; i<=5; ++i)
			{
				for(int k = -5; k<=5; ++k)
				{
					if ((float)(i*i + k*k) <= rand.nextFloat() * 12 - rand.nextFloat() * 5F 
							&& worldIn.getBlockState(pos.add(i, 0, k)).getBlock()==Blocks.SAND) 
			        {
			            worldIn.setBlockState(new BlockPos(pos.getX()+i, worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX()+i, pos.getZ()+k), pos.getZ()+k), BlockInit.POLLUTED_SAND.getDefaultState(), 2);

			        }
				}
			}

	        return true;
		}
		else
			return false;

		
	}

}
