package com.github.zljtt.underwaterbiome.Utils.Rooms;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;;

public class RoomCorridor extends RoomBase
{
	Boolean has_second_floor;
	public RoomCorridor(String name, int min_width, int min_height, int min_depth, boolean hasSecond, int... difficulty_range)
	{
		super(name, min_width,min_height,min_depth,hasSecond,false, difficulty_range);
		this.has_second_floor = hasSecond;
		
	}
	
	@Override
	public void generate(IWorld worldIn, RoomInfo info, WreckageConfig config, Random ran)
	{
		Map<BlockPos,BlockState> tobuild = new HashMap<BlockPos,BlockState>();
		tobuild.putAll(info.clearRoom());
		//build floor
		tobuild.putAll(info.buildFloor(worldIn, config));
		//build walls
		Boolean rand1 = ran.nextInt(3)==0;
		if(rand1){
			tobuild.putAll(info.buildSolidWall(worldIn, config, info.start_pos.up(), info.width, info.direction, false));
		}else{
			tobuild.putAll(info.buildWindowWall(worldIn, config, info.start_pos.up(), info.width, info.direction,false));
		}
		Boolean rand2 = ran.nextInt(3)==0;
		if(rand2){
			tobuild.putAll(info.buildSolidWall(worldIn, config, info.start_pos.offset(info.direction.rotateY(), info.height-1).up(), info.width, info.direction,false));
		}else{
			tobuild.putAll(info.buildWindowWall(worldIn, config, info.start_pos.offset(info.direction.rotateY(), info.height-1).up(), info.width, info.direction,false));
		}
		//build second floor
		if (has_second_floor)
		{
			tobuild.putAll(info.buildSecondFloor(worldIn, config, min_depth));
		}
		
		
		tobuild.forEach((pos,state)->
		{
			if (!worldIn.getBlockState(pos).isSolid()) 
			{
				worldIn.setBlockState(pos, state, 2);
			}		
		});		
	}
	@Override
	public RoomInfo getModeledRoomInfo(Random ran, int range, Direction dir) 
	{
		int depth = this.min_depth;
		if (this.has_second_floor)
		{
			depth+=(2+ran.nextInt(3));
		}
		RoomInfo info = new RoomInfo(this, null, 
				this.min_width+ran.nextInt(range),
				this.min_height+ran.nextInt(2), 
				depth, dir,ran);
		return info;
	}

}
