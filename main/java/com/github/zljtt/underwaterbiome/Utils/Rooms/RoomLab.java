package com.github.zljtt.underwaterbiome.Utils.Rooms;

import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Handlers.LootTableHandler;
import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RoomLab extends RoomSquare
{

	public RoomLab(String name, int min_width, int min_height, int min_depth, boolean hasSecond,
			int... difficulty_range) 
	
	{
		super(name, min_width, min_height, min_depth, hasSecond, 
				new Boolean[] {true, false, true, false}, 
				new Boolean[] {false, true, false, true}, 
				difficulty_range);
	}
	@Override
	public void addDecoration(Map<BlockPos, BlockState> tobuild, IWorld worldIn, RoomInfo info, WreckageConfig config,
			Random ran) 
	{ 
		Direction d  = info.direction;
		RoomLab.addDesk(tobuild, info.start_pos.offset(d,1+ran.nextInt(2)).offset(d.rotateY()).up(), 3+ran.nextInt(2), d, ran,true);
		RoomLab.addDesk(tobuild, info.start_pos.offset(d,1+ran.nextInt(2)).offset(d.rotateY(), info.height-2).up(), 3+ran.nextInt(2), d, ran,true);
		this.addShelfWithChest(tobuild, worldIn, info.start_pos.offset(d,1+ran.nextInt(2)).offset(d.rotateY()).up(info.depth-2), 3+ran.nextInt(2), d, d.rotateY(), config, ran);
		this.addShelfWithChest(tobuild, worldIn, info.start_pos.offset(d,1+ran.nextInt(2)).offset(d.rotateY(), info.height-2).up(info.depth-2), 3+ran.nextInt(2), d, d.rotateYCCW(), config, ran);

	}
	
	public static void addDesk(Map<BlockPos, BlockState> tobuild, BlockPos pos, int length, Direction dir, Random ran, Boolean iron) 
	{
		BlockState putblock = iron?RoomInfo.iron_block:Blocks.OAK_PLANKS.getDefaultState();
		BlockState putslab = iron?RoomInfo.iron_slab_top:Blocks.OAK_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.TOP).with(BlockStateProperties.WATERLOGGED, true);

		tobuild.put(pos, putblock);
		for (int i = 1; i<length-1; i++)
		{
			tobuild.put(pos.offset(dir,i),putslab);
		}
		tobuild.put(pos.offset(dir, length-1), putblock);
	}
	
	public static void addShelf(Map<BlockPos, BlockState> tobuild, BlockPos pos, int length, Direction dir, Random ran) 
	{
		for (int i = 0; i<length; i++)
		{
			tobuild.put(pos.offset(dir,i), RoomInfo.iron_slab_top);
		}
	}
	
	public void addShelfWithChest(Map<BlockPos, BlockState> tobuild, IWorld worldIn, BlockPos pos, int length, Direction dir_shelf,Direction dir_chest,  WreckageConfig config,Random ran) 
	{
		for (int i = 0; i<length; i++)
		{
			RoomInfo.addChest(pos.offset(dir_shelf,i).up(), worldIn, getChestResourceLocation(config.difficulty), ran, dir_chest);
			tobuild.put(pos.offset(dir_shelf,i), RoomInfo.iron_slab_top);
		}
	}
	
	@Override
	protected ResourceLocation getChestResourceLocation(int difficulty) 
	{
		Random ran= new Random();
		if (ran.nextInt(100)>50)
		{
			switch (difficulty)
			{
			case 0: 
				return LootTableHandler.RESEARCH_STATION_LAB_0;
			case 1: 
				return LootTableHandler.RESEARCH_STATION_LAB_1;
			case 2: 
				return LootTableHandler.RESEARCH_STATION_LAB_2;
			default:
				return LootTableHandler.RESEARCH_STATION_DEFAULT;
			}
		}
		else 
			return LootTableHandler.RESEARCH_STATION_DEFAULT;

		
	}
	@Override
	public RoomInfo getModeledRoomInfo(Random ran, int range, Direction dir) 
	{
		return new RoomInfo(this, null, 
				this.min_width+ran.nextInt(range),
				this.min_height+ran.nextInt(2), 
				this.min_depth,
				dir,ran);
	}

}
