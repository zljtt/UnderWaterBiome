package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import com.github.zljtt.underwaterbiome.Handlers.LootTableHandler;
import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.HorizontalFaceBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FeatureStarter extends Feature<NoFeatureConfig> 
{
	
	public FeatureStarter(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51487_1_) 
	{
	      super(p_i51487_1_);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) 
	{		
		generate(pos, worldIn);
		return true;	
	}
	
	public static BlockPos generate(BlockPos posIn, IWorld worldIn)
	{
		Random ran = new Random();
		BlockPos pos0 = posIn.add(
				ran.nextBoolean()?2+ran.nextInt(64):-2-ran.nextInt(64), 
				0, 
				ran.nextBoolean()?2+ran.nextInt(64):-2-ran.nextInt(64));
		BlockPos pos =new BlockPos(pos0.getX(), worldIn.getSeaLevel()-2, pos0.getZ());
//		if (height < worldIn.getSeaLevel())
		{
			//floor 1
			buildWall(pos.add(1,0,1), worldIn, 3,  1,  1,  RoomInfo.iron_block);
			buildWall(pos.add(1,0,3), worldIn, 3,  1,  1,  RoomInfo.iron_block);
			worldIn.setBlockState(pos.add(1,0,2), RoomInfo.iron_block, 3);	
			worldIn.setBlockState(pos.add(3,0,2), RoomInfo.iron_block, 3);	
			//floor 2 + 3  + 4
			buildWall(pos.add(1,1,1), worldIn, 3,  1,  3,  RoomInfo.air);
			buildWall(pos.add(1,1,0), worldIn, 3,  3,  1,  RoomInfo.iron_block);
			buildWall(pos.add(0,1,1), worldIn, 1,  3,  3,  RoomInfo.iron_block);
			buildWall(pos.add(1,1,4), worldIn, 3,  3,  1,  RoomInfo.iron_block);
			buildWall(pos.add(4,1,1), worldIn, 1,  3,  3,  RoomInfo.iron_block);
			//floor 5
			worldIn.setBlockState(pos.add(2,4,0), RoomInfo.iron_block, 3);	
			buildWall(pos.add(1,4,1), worldIn, 3,  1,  1,  RoomInfo.iron_block);
			buildWall(pos.add(0,4,2), worldIn, 5,  1,  1,  RoomInfo.iron_block);
			buildWall(pos.add(1,4,3), worldIn, 3,  1,  1,  RoomInfo.iron_block);
			worldIn.setBlockState(pos.add(2,4,4), RoomInfo.iron_block, 3);
			//floor 6
			worldIn.setBlockState(pos.add(2,5,1), RoomInfo.iron_slab_nowater, 3);
			worldIn.setBlockState(pos.add(1,5,2), RoomInfo.iron_slab_nowater, 3);
			worldIn.setBlockState(pos.add(2,5,3), RoomInfo.iron_slab_nowater, 3);
			worldIn.setBlockState(pos.add(3,5,2), RoomInfo.iron_slab_nowater, 3);
			//orange 
			buildWall(pos.add(0,1,-1), worldIn, 5,  1,  1,  RoomInfo.iron_block_orange);
			buildWall(pos.add(0,1,5), worldIn, 5,  1,  1,  RoomInfo.iron_block_orange);
			buildWall(pos.add(-1,1,0), worldIn, 1,  1,  5,  RoomInfo.iron_block_orange);
			buildWall(pos.add(5,1,0), worldIn, 1,  1,  5,  RoomInfo.iron_block_orange);
			worldIn.setBlockState(pos.add(0,1,0), RoomInfo.iron_block_orange, 3);
			worldIn.setBlockState(pos.add(4,1,4), RoomInfo.iron_block_orange, 3);
			worldIn.setBlockState(pos.add(4,1,0), RoomInfo.iron_block_orange, 3);
			worldIn.setBlockState(pos.add(0,1,4), RoomInfo.iron_block_orange, 3);

			//others
			worldIn.setBlockState(pos.add(0,2,2), RoomInfo.glass, 3);	
			worldIn.setBlockState(pos.add(2,2,4), RoomInfo.glass, 3);	
			worldIn.setBlockState(pos.add(4,2,2), RoomInfo.glass, 3);	
			worldIn.setBlockState(pos.add(1,2,0), BlockInit.OXYGEN_HOLDER.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.NORTH), 3);
			RoomInfo.addChest(pos.add(2,1,1), worldIn, LootTableHandler.STARTER_1, ran, Direction.WEST);
			RoomInfo.addChest(pos.add(2,2,1), worldIn, LootTableHandler.STARTER_2, ran, Direction.WEST);
			RoomInfo.addChest(pos.add(2,3,1), worldIn, LootTableHandler.STARTER_3, ran, Direction.WEST);
			worldIn.setBlockState(pos.add(2,4,1), RoomInfo.iron_block, 3);
			worldIn.setBlockState(pos.add(2,-1,1), RoomInfo.iron_block, 3);	
			worldIn.setBlockState(pos.add(2,-1,2), Blocks.LADDER.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.SOUTH).with(BlockStateProperties.WATERLOGGED, true), 3);	
			worldIn.setBlockState(pos.add(2,0,2), Blocks.LADDER.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.SOUTH).with(BlockStateProperties.WATERLOGGED, true), 3);	
			worldIn.setBlockState(pos.add(2,1,2), Blocks.LADDER.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.SOUTH), 3);	
			worldIn.setBlockState(pos.add(2,2,2), Blocks.LADDER.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.SOUTH), 3);	
			worldIn.setBlockState(pos.add(2,3,2), Blocks.LADDER.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.SOUTH), 3);	
			worldIn.setBlockState(pos.add(2,4,2), Blocks.LADDER.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.SOUTH), 3);	
			worldIn.setBlockState(pos.add(3,1,1), Blocks.CRAFTING_TABLE.getDefaultState(), 3);	
			worldIn.setBlockState(pos.add(3,2,1), Blocks.BOOKSHELF.getDefaultState(), 3);	
			worldIn.setBlockState(pos.add(3,3,1), Blocks.BOOKSHELF.getDefaultState(), 3);	
			return pos;
		}
//		else
//			return false;
	}
	public static void buildWall(BlockPos posIn, IWorld worldIn,int x, int y, int z, BlockState state)
	{
		for(int x0 = 0 ; x0<x; x0++)
		{
			for(int y0 = 0 ; y0<y; y0++)
			{
				for(int z0 = 0 ; z0<z; z0++)
				{
					worldIn.setBlockState(posIn.add(x0,y0,z0), state, 3);
				}
			}
		}	
	}


}
