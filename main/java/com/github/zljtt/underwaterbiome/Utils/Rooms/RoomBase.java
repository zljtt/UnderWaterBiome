package com.github.zljtt.underwaterbiome.Utils.Rooms;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Handlers.LootTableHandler;
import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;
import com.github.zljtt.underwaterbiome.Utils.Enum.Rooms;

import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorld;;

public abstract class RoomBase
{
	public String name;
	public int min_width;
	public int min_height;
	public int min_depth;
	public int[] difficulty_range;
	public boolean is_corridor;

	public RoomBase(String name, int min_width, int min_height, int min_depth, boolean hasSecond, boolean isRoomNotCorridor,int... difficulty_range)
	{
		this.min_depth=min_depth;
		this.min_height=min_height;
		this.min_width=min_width;
		this.name=name;
		this.difficulty_range=difficulty_range;
		if (isRoomNotCorridor) 
		{
			is_corridor=false;
			Rooms.ROOMS.add(this);
		}
		else
		{
			is_corridor=true;
			Rooms.CORRIDORS.add(this);
		}

	}
	
	public void generate(IWorld worldIn, RoomInfo info, WreckageConfig config, Random ran)
	{
		System.out.println("generating "+name);
	}
	;
	
	/**
	 * Important! no start position 
	 */
	public RoomInfo getModeledRoomInfo(Random ran, int range, Direction dir)
	{
		return new RoomInfo(this, null, 
				this.min_width+ran.nextInt(range),
				this.min_height+ran.nextInt(range), 
				this.min_depth+ran.nextInt(range),dir,ran);
	}
	
	protected ResourceLocation getChestResourceLocation(int difficulty)
	{
		return LootTableHandler.RESEARCH_STATION_DEFAULT;
	}
	

}
