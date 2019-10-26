package com.github.zljtt.underwaterbiome.Utils.Rooms;

import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Handlers.LootTableHandler;
import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RoomSmelt extends RoomSquare
{

	public RoomSmelt(String name, int min_width, int min_height, int min_depth, boolean hasSecond,
			int... difficulty_range) 
	
	{
		super(name, min_width, min_height, min_depth, hasSecond, 
				new Boolean[] {false, false, false, true}, 
				new Boolean[] {true, true, true, false}, 
				difficulty_range);
	}
	@Override
	public void addDecoration(Map<BlockPos, BlockState> tobuild, IWorld worldIn, RoomInfo info, WreckageConfig config,
			Random ran) 
	{ 
		Direction d  = info.direction;
		BlockState furnace = Blocks.FURNACE.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, d);
		BlockPos pos = info.start_pos.offset(d);
		tobuild.put(pos.up(1).offset(d.rotateY(),1), furnace);
		tobuild.put(pos.up(2).offset(d.rotateY(),1), furnace);
		tobuild.put(pos.up(3).offset(d.rotateY(),1), RoomInfo.iron_block);
		tobuild.put(pos.up(1).offset(d.rotateY(),2), RoomInfo.iron_block);
		tobuild.put(pos.up(2).offset(d.rotateY(),2), RoomInfo.iron_block);
		tobuild.put(pos.up(3).offset(d.rotateY(),2), RoomInfo.iron_slab);

		tobuild.put(pos.up(1).offset(d.rotateY(),info.height-2), furnace);
		tobuild.put(pos.up(2).offset(d.rotateY(),info.height-2), furnace);
		tobuild.put(pos.up(3).offset(d.rotateY(),info.height-2), RoomInfo.iron_block);
		tobuild.put(pos.up(1).offset(d.rotateY(),info.height-3), RoomInfo.iron_block);
		tobuild.put(pos.up(2).offset(d.rotateY(),info.height-3), RoomInfo.iron_block);
		tobuild.put(pos.up(3).offset(d.rotateY(),info.height-3), RoomInfo.iron_slab);
		
		RoomInfo.addChest(info.start_pos.up().offset(d, info.width-2), worldIn, getChestResourceLocation(config.difficulty), ran, info.direction.getOpposite());
		RoomInfo.addChest(info.start_pos.up().offset(d, info.width-2).offset(d.rotateY(), info.height-2), worldIn, getChestResourceLocation(config.difficulty), ran, info.direction.getOpposite());
		RoomLab.addDesk(tobuild, info.start_pos.up().offset(d, info.width-2).offset(d.rotateY()), info.height-4, d.rotateY(), ran, true);
	}
	
	@Override
	protected ResourceLocation getChestResourceLocation(int difficulty) 
	{
		switch (difficulty)
		{
		case 0: 
			return LootTableHandler.RESEARCH_STATION_SMELT_0;
		case 1: 
			return LootTableHandler.RESEARCH_STATION_SMELT_1;
		default:
			return LootTableHandler.RESEARCH_STATION_DEFAULT;
		}
	}
	

}
