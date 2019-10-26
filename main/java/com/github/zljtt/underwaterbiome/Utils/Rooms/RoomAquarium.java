package com.github.zljtt.underwaterbiome.Utils.Rooms;

import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Handlers.LootTableHandler;
import com.github.zljtt.underwaterbiome.Inits.EntityInit;
import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RoomAquarium extends RoomSquare
{

	public RoomAquarium(String name, int min_width, int min_height, int min_depth, boolean hasSecond,
			int... difficulty_range) 
	
	{
		super(name, min_width, min_height, min_depth, hasSecond, 
				new Boolean[] {true, true, true, true}, 
				new Boolean[] {false, true, false, true}, 
				difficulty_range);
	}
	@Override
	public void addDecoration(Map<BlockPos, BlockState> tobuild, IWorld worldIn, RoomInfo info, WreckageConfig config,
			Random ran) 
	
	{
		
		BlockPos start_pos = info.start_pos.offset(info.direction, 2).offset(info.direction.rotateY(), 2).up();
		int width = info.width-4;
		int height = info.height-4;
		//put base + chests
		for (int i = 0; i < width; i++) // right + left
		{			
			//right
			BlockPos pos0 = start_pos.offset(info.direction,i);
			if (ran.nextInt(100)<8)
				RoomInfo.addChest(pos0, worldIn, getChestResourceLocation(config.difficulty), ran, info.direction.rotateYCCW());
			else 
				tobuild.put(pos0, RoomInfo.iron_block);
			//left
			BlockPos pos1 = start_pos.offset(info.direction.rotateY(), height-1).offset(info.direction,i);
			if (ran.nextInt(100)<8)
				RoomInfo.addChest(pos1, worldIn, getChestResourceLocation(config.difficulty), ran, info.direction.rotateY());
			else 
				tobuild.put(pos1, RoomInfo.iron_block);
		}
		for (int i = 0; i < height; i++) // up + down 
		{
			//down
			BlockPos pos0 = start_pos.offset(info.direction.rotateY(),i);
			if (ran.nextInt(100)<8)
				RoomInfo.addChest(pos0, worldIn, getChestResourceLocation(config.difficulty), ran, info.direction.getOpposite());
			else 
				tobuild.put(pos0, RoomInfo.iron_block);
			//up
			BlockPos pos1 = start_pos.offset(info.direction, width-1).offset(info.direction.rotateY(),i);
			if (ran.nextInt(100)<8)
				RoomInfo.addChest(pos1, worldIn, getChestResourceLocation(config.difficulty), ran, info.direction);
			else 
				tobuild.put(pos1, RoomInfo.iron_block);
		}
		//put glasses
		for(int depth = 1 ; depth < info.depth-1; depth++)
		{
			for (int i = 0; i < width; i++)
			{
				
				tobuild.put(start_pos.offset(info.direction,i).up(depth), getGlass(config,ran));
				tobuild.put(start_pos.offset(info.direction.rotateY(), height-1).offset(info.direction,i).up(depth), getGlass(config,ran));
			}
			for (int i = 0; i < height; i++)
			{
				tobuild.put(start_pos.offset(info.direction.rotateY(),i).up(depth), getGlass(config,ran));
				tobuild.put(start_pos.offset(info.direction, width-1).offset(info.direction.rotateY(),i).up(depth), getGlass(config,ran));
			}
		}
		for (int i = 1; i < width-1; i++)
		{
			for (int j = 1; j < height-1; j++)
			{
				tobuild.put(start_pos.offset(info.direction,i).offset(info.direction.rotateY(),j), 
						RoomInfo.getByTwo(Blocks.SAND.getDefaultState(), Blocks.DIRT.getDefaultState(), 0.8F, config, ran));
				tobuild.put(start_pos.offset(info.direction,i).offset(info.direction.rotateY(),j).up(), 
						RoomInfo.getByChance(Blocks.SEAGRASS.getDefaultState(), 0.5F, config, ran));
			}
		}
		Entity entity_in_room =  EntityInit.ENTITY_CONCH.create(worldIn.getWorld());
		BlockPos entity_pos = start_pos.offset(info.direction, 1).offset(info.direction.rotateY(), 1).up();
		entity_in_room.setPosition(entity_pos.getX(), entity_pos.getY(), entity_pos.getZ());
		worldIn.addEntity(entity_in_room);
		
		super.addDecoration(tobuild, worldIn, info, config, ran);
	}
	private BlockState getGlass(WreckageConfig config, Random ran)
	{
		if (ran.nextInt(100)>config.broken)
		{
			return Blocks.BLUE_STAINED_GLASS.getDefaultState();
		}
		else return Blocks.WATER.getDefaultState();
	}
	@Override
	protected ResourceLocation getChestResourceLocation(int difficulty) 
	{
		switch (difficulty)
		{
		case 0: 
			return LootTableHandler.RESEARCH_STATION_AQUARIUM_0;
		case 1: 
			return LootTableHandler.RESEARCH_STATION_AQUARIUM_1;
		case 2: 
			return LootTableHandler.RESEARCH_STATION_AQUARIUM_2;
		default:
			return LootTableHandler.RESEARCH_STATION_DEFAULT;
		}
	}


}
