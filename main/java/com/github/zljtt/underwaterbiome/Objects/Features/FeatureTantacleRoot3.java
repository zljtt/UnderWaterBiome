package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.Random;
import java.util.function.Function;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FeatureTantacleRoot3 extends Feature<NoFeatureConfig> 
{
	public FeatureTantacleRoot3(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51487_1_) 
	{
	      super(p_i51487_1_);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) 
	{		
		BlockPos blockpos_start = new BlockPos(pos.getX(), worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ()).down();
		
		if (worldIn.getBlockState(blockpos_start).getBlock()!=BlockInit.LIVING_ROOT)
		{
			int height = 5 + rand.nextInt(12);
			int size =  0 + (height > 6 ? 1:0) + (height > 13 ? 1:0);
			int up_height_1 = 4*size + rand.nextInt(1+size);
			int up_height_2 = 2*size + rand.nextInt(1+size);

			BlockState state = BlockInit.LIVING_ROOT.getDefaultState();;
			for (int i = 0; i< height - up_height_1;i++)
			{
				worldIn.setBlockState(blockpos_start.up(i), state, 2);
				if(size > 0)
				{
					worldIn.setBlockState(blockpos_start.up(i).east(), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).north(), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).south(), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).west(), state, 2);
				}
				if(size > 1)
				{
					worldIn.setBlockState(blockpos_start.up(i).east().north(), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).east().south(), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).west().north(), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).west().south(), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).east(2), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).north(2), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).south(2), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).west(2), state, 2);
				}

			}
			for (int i = height - up_height_1; i< height - up_height_2;i++)
			{
				worldIn.setBlockState(blockpos_start.up(i), state, 2);
				 
				if(size > 1)
				{
					worldIn.setBlockState(blockpos_start.up(i).east(), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).north(), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).south(), state, 2);
					worldIn.setBlockState(blockpos_start.up(i).west(), state, 2);
				}

			}
			for (int i = height - up_height_2; i<height;i++)
			{
				worldIn.setBlockState(blockpos_start.up(i), state, 2);
			}
				
			return true;
		}
		else
			return false;

		
	}

}
