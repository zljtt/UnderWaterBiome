package com.github.zljtt.underwaterbiome.Utils.Rooms;

import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Handlers.LootTableHandler;
import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;

import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RoomStorage extends RoomSquare
{

	public RoomStorage(String name, int min_width, int min_height, int min_depth, boolean hasSecond,
			int... difficulty_range) 
	
	{
		super(name, min_width, min_height, min_depth, hasSecond, 
				new Boolean[] {false, false, false, false}, 
				new Boolean[] {false, false, false, false}, 
				difficulty_range);
	}
	@Override
	public void addDecoration(Map<BlockPos, BlockState> tobuild, IWorld worldIn, RoomInfo info, WreckageConfig config,
			Random ran) 
	{ 
		BlockPos mid_start = info.start_pos.up();
		int w1 = info.width-3;

		if (info.height%2 != 0)
		{
			mid_start = mid_start.offset(info.direction.rotateY(),(info.height-1)/2-1);
			for(int i = 0; i<w1; i++)
			{
				for(int k = 0; k<3;k++)
				{
					if (ran.nextInt(3)==0)
						RoomInfo.addChest(mid_start.offset(info.direction, 2+i).up(k), worldIn, getChestResourceLocation(config.difficulty), ran, info.direction.rotateYCCW());
					else
						tobuild.put(mid_start.offset(info.direction, 2+i).up(k), RoomInfo.iron_block);
					if (ran.nextInt(3)==0)
						RoomInfo.addChest(mid_start.offset(info.direction, 2+i).offset(info.direction.rotateY(),2).up(k), worldIn, getChestResourceLocation(config.difficulty), ran, info.direction.rotateY());
					else
						tobuild.put(mid_start.offset(info.direction, 2+i).offset(info.direction.rotateY(),2).up(k), RoomInfo.iron_block);
					tobuild.put(mid_start.offset(info.direction, 2+i).offset(info.direction.rotateY()).up(k), RoomInfo.iron_block);
				}
			}
		}
		else
		{
			mid_start = mid_start.offset(info.direction.rotateY(),info.height/2-1);
			for(int i = 0; i<w1;i++)
			{
				for(int k = 0; k<3;k++)
				{
					if (ran.nextInt(3)==0)
					RoomInfo.addChest(mid_start.offset(info.direction, 2+i).up(k), worldIn, getChestResourceLocation(config.difficulty), ran, info.direction.rotateYCCW());
					else
						tobuild.put(mid_start.offset(info.direction, 2+i).up(k), RoomInfo.iron_block);
					if (ran.nextInt(3)==0)
					RoomInfo.addChest(mid_start.offset(info.direction, 2+i).offset(info.direction.rotateY()).up(k), worldIn, getChestResourceLocation(config.difficulty), ran, info.direction.rotateY());
					else
						tobuild.put(mid_start.offset(info.direction, 2+i).offset(info.direction.rotateY()).up(k), RoomInfo.iron_block);
				}
			}
		}
		
	}
	@Override
	protected ResourceLocation getChestResourceLocation(int difficulty) 
	{
		Random ran = new Random();
		int r = ran.nextInt(10);
		switch (r)
		{
		case 0: 
			return LootTableHandler.RESEARCH_STATION_STORAGE_EQUIPMENT;
		case 1: 
			return LootTableHandler.RESEARCH_STATION_STORAGE_FOOD;
		case 2: 
			return LootTableHandler.RESEARCH_STATION_STORAGE_RESOURCE;
		default:
			return LootTableHandler.RESEARCH_STATION_DEFAULT;
		}
	}

	

	

}
