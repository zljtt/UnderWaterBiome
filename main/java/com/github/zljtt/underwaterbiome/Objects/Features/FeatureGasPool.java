package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.HashMap;
import java.util.Map;
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

public class FeatureGasPool extends Feature<NoFeatureConfig>
{
	Random random = new Random();

	public FeatureGasPool(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51487_1_) 
	{
	      super(p_i51487_1_);
	}


	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		Map<BlockPos, BlockState> map = new HashMap<BlockPos,BlockState>();
		int range = 4;
		for (int i = 0; i < range*range*range;i++)
		{
			double angle_1 = random.nextDouble()*360;
			double random_range = random.nextDouble()*range;
			double x = pos.getX() + Math.ceil((random_range)*Math.cos(Math.toRadians(angle_1)));
			double z = pos.getZ() + Math.ceil((random_range)*Math.sin(Math.toRadians(angle_1)));
			BlockPos gen_pos_0 =new BlockPos(x, worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, (int)x, (int)z)-1, z);
			{
				map.put(gen_pos_0, Blocks.WATER.getDefaultState());
			}
		}
		for (int i = 0; i < range;i++)
		{
			double angle_1 = random.nextDouble()*360;
			double random_range = random.nextDouble()*2;
			double x = pos.getX() + Math.ceil((random_range)*Math.cos(Math.toRadians(angle_1)));
			double z = pos.getZ() + Math.ceil((random_range)*Math.sin(Math.toRadians(angle_1)));
			BlockPos gen_pos_0 =new BlockPos(x, worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, (int)x, (int)z)-2, z);
			{
				map.put(gen_pos_0, BlockInit.GAS.getDefaultState());
				map.put(gen_pos_0.up(), Blocks.WATER.getDefaultState());
			}
		}
		map.forEach((pos0,state)->{
			worldIn.setBlockState(pos0, state, 2);
		});
		return true;
	}
	


}
