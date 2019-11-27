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

public class FeatureFloatingIsland extends Feature<NoFeatureConfig> 
{
	public FeatureFloatingIsland(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51487_1_) 
	{
	      super(p_i51487_1_);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random random,
			BlockPos pos, NoFeatureConfig config) 
	{		
		int j = 0;
		int height = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX(), pos.getZ());
		int sealevel =worldIn.getSeaLevel();
		if (sealevel-height-7<=0)
		{
			return false;
		}
		else
		{
			int gen_height_offset = 5 + random.nextInt(sealevel-height-7);
			BlockPos gen_pos = new BlockPos(
					pos.getX() + random.nextInt(8)-random.nextInt(8),
					height + gen_height_offset,
					pos.getZ() + random.nextInt(8)-random.nextInt(8));
			
			int range = 4+ random.nextInt(4);
			worldIn.setBlockState(gen_pos, BlockInit.FLOATING_CORE.getDefaultState(), 2);
			for (int i = 0; i < range*range*range*range;i++)
			{
				double angle_1 = random.nextDouble()*360;
				double angle_2 = -random.nextDouble()*90;
				double random_range = random.nextDouble()*range;
				BlockPos gen_pos_0 =new BlockPos(
						gen_pos.getX() + Math.ceil((random_range)*Math.cos(Math.toRadians(angle_2))*Math.cos(Math.toRadians(angle_1))), 
						
						gen_pos.getY() + Math.ceil((random_range)*Math.sin(Math.toRadians(angle_2))), 
						gen_pos.getZ() + Math.ceil((random_range)*Math.cos(Math.toRadians(angle_2))*Math.sin(Math.toRadians(angle_1))));
				
				if (worldIn.getBlockState(gen_pos_0).getBlock()==Blocks.WATER)
				{
						worldIn.setBlockState(gen_pos_0,Blocks.STONE.getDefaultState(), 2);
				}
				
				j+=1;
			}
			for (int i = 0; i < range*range*range;i++)
			{
				double angle_1 = random.nextDouble()*360;
				double random_range = random.nextDouble()*range;
				BlockPos gen_pos_0 =new BlockPos(
						gen_pos.getX() + Math.ceil((random_range)*Math.cos(Math.toRadians(angle_1))), 
						gen_pos.getY() + 1, 
						gen_pos.getZ() + Math.ceil((random_range)*Math.sin(Math.toRadians(angle_1))));
				
				if (worldIn.getBlockState(gen_pos_0).getBlock()==Blocks.WATER)
				{
					if (random.nextInt(15)<1)
					{
						worldIn.setBlockState(gen_pos_0,BlockInit.WATER_CORE.getDefaultState(), 2);
					}
					else
						worldIn.setBlockState(gen_pos_0,Blocks.SAND.getDefaultState(), 2);
				}
			}
			for (int i = 0; i < range*range;i++)
			{
				double angle_1 = random.nextDouble()*360;
				double random_range = random.nextDouble()*range;
				BlockPos gen_pos_0 =new BlockPos(
						gen_pos.getX() + Math.ceil((random_range)*Math.cos(Math.toRadians(angle_1))), 
						gen_pos.getY() + 2, 
						gen_pos.getZ() + Math.ceil((random_range)*Math.sin(Math.toRadians(angle_1))));
				
				if (worldIn.getBlockState(gen_pos_0).getBlock()==Blocks.WATER && worldIn.getBlockState(gen_pos_0.down()).getBlock()==Blocks.SAND)
				{
					if (random.nextBoolean()==true)
					{
						worldIn.setBlockState(gen_pos_0,BlockInit.WATER_GRASS_RED.getDefaultState(), 2);
					}
					else
						worldIn.setBlockState(gen_pos_0,BlockInit.WATER_GRASS_GREEN.getDefaultState(), 2);
				}
			}
			
			
			return j>0;
		}
		
		
	}


}
