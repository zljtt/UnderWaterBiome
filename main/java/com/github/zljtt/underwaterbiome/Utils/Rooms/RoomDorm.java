package com.github.zljtt.underwaterbiome.Utils.Rooms;

import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RoomDorm extends RoomSquare
{

	public RoomDorm(String name, int min_width, int min_height, int min_depth, boolean hasSecond,
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
		Direction d  = info.direction;
		Direction d_bed  = ran.nextBoolean()?d:d.getOpposite();
		tobuild.put(info.start_pos.offset(d, info.width-2).offset(d.rotateY()).up(1), Blocks.BOOKSHELF.getDefaultState());
		tobuild.put(info.start_pos.offset(d, info.width-2).offset(d.rotateY()).up(2), Blocks.BOOKSHELF.getDefaultState());
		tobuild.put(info.start_pos.offset(d, info.width-2).offset(d.rotateY(),info.height-2).up(1), Blocks.BOOKSHELF.getDefaultState());
		tobuild.put(info.start_pos.offset(d, info.width-2).offset(d.rotateY(),info.height-2).up(2), Blocks.BOOKSHELF.getDefaultState());
		
		this.addBed(tobuild, info.start_pos.offset(d,d_bed.equals(d)?1:3).offset(d.rotateY()).up(), d_bed, config, ran);
		this.addBed(tobuild, info.start_pos.offset(d,d_bed.equals(d)?1:3).offset(d.rotateY(), info.height-2).up(), d_bed, config, ran);
	}
	

	public void addBed(Map<BlockPos, BlockState> tobuild, BlockPos pos, Direction dir,  WreckageConfig config,Random ran) 
	{
		tobuild.put(pos, Blocks.WHITE_WOOL.getDefaultState());
		tobuild.put(pos.offset(dir,1), Blocks.RED_WOOL.getDefaultState());
		tobuild.put(pos.offset(dir,2), Blocks.RED_WOOL.getDefaultState());
	}
	

}
