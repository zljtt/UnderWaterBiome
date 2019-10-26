package com.github.zljtt.underwaterbiome.Utils.Rooms;

import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RoomControl extends RoomSquare
{
	BlockState sea_lantern = Blocks.SEA_LANTERN.getDefaultState();
	BlockState closed_trap_door = Blocks.OAK_TRAPDOOR.getDefaultState()
			.with(BlockStateProperties.WATERLOGGED, true).with(BlockStateProperties.HALF, Half.BOTTOM).with(BlockStateProperties.OPEN, Boolean.valueOf(false));

	public RoomControl(String name, int min_width, int min_height, int min_depth, boolean hasSecond,
			int... difficulty_range) 
	
	{
		super(name, min_width, min_height, min_depth, hasSecond, 
				new Boolean[] {false, false, false, true}, 
				new Boolean[] {false, true, false, false}, 
				difficulty_range);
	}
	@Override
	public void addDecoration(Map<BlockPos, BlockState> tobuild, IWorld worldIn, RoomInfo info, WreckageConfig config,
			Random ran) 
	{
		BlockPos mid = info.start_pos.offset(info.direction.rotateY(),info.height/2).offset(info.direction,2).up();
		RoomLab.addDesk(tobuild, mid, info.width-4, info.direction, ran,false);
		tobuild.put(info.start_pos.offset(info.direction,1).offset(info.direction.rotateY(),1), sea_lantern);
		tobuild.put(info.start_pos.offset(info.direction,1).offset(info.direction.rotateY(),1).up(), closed_trap_door);
		
		tobuild.put(info.start_pos.offset(info.direction,1).offset(info.direction.rotateY(),info.height-2), sea_lantern);
		tobuild.put(info.start_pos.offset(info.direction,1).offset(info.direction.rotateY(),info.height-2).up(), closed_trap_door);

		tobuild.put(info.start_pos.offset(info.direction,info.width-2).offset(info.direction.rotateY(),info.height-2), sea_lantern);
		tobuild.put(info.start_pos.offset(info.direction,info.width-2).offset(info.direction.rotateY(),info.height-2).up(), closed_trap_door);

		tobuild.put(info.start_pos.offset(info.direction,info.width-2).offset(info.direction.rotateY(),1), sea_lantern);
		tobuild.put(info.start_pos.offset(info.direction,info.width-2).offset(info.direction.rotateY(),1).up(), closed_trap_door);


		super.addDecoration(tobuild, worldIn, info, config, ran);
	}
	
	
	

}
