package com.github.zljtt.underwaterbiome.Utils.Rooms;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RoomSquare extends RoomBase
{
	Boolean[] hasWindow;
	Boolean[] hasDoor;
	/**
	 * 	left (up) -> bottom (right) -> right (down) -> top (left)
	 */
	public RoomSquare(String name, int min_width, int min_height, int min_depth, boolean hasSecond, Boolean[] hasWindow, Boolean[] hasDoor,  int... difficulty_range)
	{
		super(name, min_width, min_height, min_depth, hasSecond, true, difficulty_range);
		this.hasWindow=hasWindow;
		this.hasDoor=hasDoor;
	}

	//left (up) -> bottom (right) -> right (down) -> top (left)
	@Override
	public void generate(IWorld worldIn, RoomInfo info, WreckageConfig config, Random ran) 
	{
		Map<BlockPos,BlockState> tobuild = new HashMap<BlockPos,BlockState>();
		//build floor
		tobuild.putAll(info.buildFloor(worldIn, config));

		//build walls
		tobuild.putAll((hasWindow.length> 0 && hasWindow[0])?
				info.buildWindowWall(worldIn, config, info.start_pos.up(), info.width, info.direction, hasDoor.length> 0 && hasDoor[0]):
				info.buildSolidWall(worldIn, config, info.start_pos.up(), info.width, info.direction, hasDoor.length> 0 && hasDoor[0]));

		tobuild.putAll((hasWindow.length> 1 && hasWindow[1])?
				info.buildWindowWall(worldIn, config, info.start_pos.up(), info.height, info.direction.rotateY(), hasDoor.length> 1 && hasDoor[1]):
				info.buildSolidWall(worldIn, config, info.start_pos.up(), info.height, info.direction.rotateY(), hasDoor.length> 1 && hasDoor[1]));
		
		tobuild.putAll((hasWindow.length> 2 && hasWindow[2])?
				info.buildWindowWall(worldIn, config, 
						info.start_pos.offset(info.direction,info.width-1).offset(info.direction.rotateY(),info.height-1).up(), 
						info.width, info.direction.getOpposite(), 
						hasDoor.length> 2 && hasDoor[2]):
				info.buildSolidWall(worldIn, config, 
						info.start_pos.offset(info.direction,info.width-1).offset(info.direction.rotateY(),info.height-1).up(), 
						info.width, info.direction.getOpposite(), hasDoor.length> 2 && hasDoor[2]));
		
		tobuild.putAll((hasWindow.length> 3 && hasWindow[3])?
				info.buildWindowWall(worldIn, config, 
					info.start_pos.offset(info.direction,info.width-1).offset(info.direction.rotateY(),info.height-1).up(), 
					info.height, info.direction.rotateY().getOpposite(), hasDoor.length> 3 && hasDoor[3]):
				info.buildSolidWall(worldIn, config, 
					info.start_pos.offset(info.direction,info.width-1).offset(info.direction.rotateY(),info.height-1).up(), 
					info.height, info.direction.rotateY().getOpposite(), hasDoor.length> 3 && hasDoor[3]));
		//build ceiling 
		tobuild.putAll(info.buildSecondFloor(worldIn, config, info.depth));
		//build support
		BlockPos mid_start = info.start_pos;
		int w1,h1;
		if (info.width%2 != 0){
			mid_start = mid_start.offset(info.direction,(info.width-1)/2-1);
			w1 = 3;
		}else {
			mid_start = mid_start.offset(info.direction,info.width/2-1);
			w1 = 2;
		}if (info.height%2 != 0){
			mid_start = mid_start.offset(info.direction.rotateY(),(info.height-1)/2-1);
			h1 = 3;
		}else {
			mid_start = mid_start.offset(info.direction.rotateY(),info.height/2-1);
			h1 = 2;
		}
		putSupport(tobuild,  worldIn,  w1, h1,  mid_start.down(),  info,  config,  ran,0);
		//build decoration
		this.addDecoration(tobuild, worldIn, info, config, ran);
		
		tobuild.forEach((pos,state)->
		{
			if (pos.getX()>1000000||pos.getY()>128||pos.getZ()>1000000||pos.getX()<-1000000||pos.getY()<0||pos.getZ()<-1000000)
			{
				System.out.println("Unknown BlockPos: "+pos.getX()+"|"+pos.getY()+"|"+pos.getZ()+"|"+state.getBlock().getRegistryName());
			}
			else
				worldIn.setBlockState(pos, state, 3);

//			if (!worldIn.getBlockState(pos).isSolid()) 
//			{
//				worldIn.setBlockState(pos, state, 2);
//			}		
		});

	}
	public void addDecoration(Map<BlockPos,BlockState> tobuild, IWorld worldIn, RoomInfo info, WreckageConfig config, Random ran)
	{
		
	}
	@Override
	public RoomInfo getModeledRoomInfo(Random ran, int range, Direction dir) 
	{
		return new RoomInfo(this, null, 
				this.min_width+ran.nextInt(range),
				this.min_height+ran.nextInt(2), 
				this.min_depth+ran.nextInt(range),dir,ran);	
	}
	protected void putSupport(Map<BlockPos,BlockState> tobuild, IWorld worldIn, int w1,int h1, BlockPos mid_start, RoomInfo info, WreckageConfig config, Random ran, int count)
	{
		for(int i = 0; i<w1;i++)
		{
			for(int j = 0; j<h1;j++)
			{
				tobuild.put(mid_start.offset(info.direction, i).offset(info.direction.rotateY(), j), RoomInfo.getByChance(RoomInfo.iron_block, 1.0F, config, ran));
			}
		}
		if (!worldIn.getBlockState(mid_start).isSolid() && count < 30)
		{
			count+=1;
			putSupport(tobuild,  worldIn,  w1, h1,  mid_start.down(),  info,  config,  ran, count);
		}
	}

	

}
