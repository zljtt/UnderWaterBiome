package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;
import com.github.zljtt.underwaterbiome.Utils.Enum.Rooms;
import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomBase;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;

public class FeatureResearchStationWreckage extends Feature<WreckageConfig>
{

	public FeatureResearchStationWreckage(Function<Dynamic<?>, ? extends WreckageConfig> configFactoryIn) 
	{
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, WreckageConfig config) 
	{
//		System.out.println("Place Wreckage at: "+pos.toString());
		if (worldIn.getBlockState(pos.up(7)).getBlock().equals(Blocks.AIR))return false;
			
		List<RoomInfo> room_list = generateStationMap(worldIn.getWorld(), rand, pos, config);
		room_list.forEach(room_info->
		{
//			if (!room_info.room.is_corridor)
//			{
//				System.out.println("With Room: "+room_info.toString());
//			}
			room_info.room.generate(worldIn, room_info, config, rand);
		});
		return true;
	}
	
	public static List<RoomInfo> generateStationMap(IWorld worldIn, Random rand, BlockPos start_blockpos, WreckageConfig config) 
	{
		List<RoomInfo> map = new ArrayList<RoomInfo>();			
		int i = 0;
		BlockPos pos_add = start_blockpos;
		
		List<RoomBase> room_map = Rooms.getRoomListByDifficulty(rand, config.difficulty, config.size);

		// create single room
		Direction pre_dir = Direction.byHorizontalIndex(rand.nextInt(4));
		RoomInfo room_info0 = room_map.get(0).getModeledRoomInfo(rand, 2, pre_dir);
		room_info0.setBlockPos(start_blockpos);
		map.add(room_info0);
		
		// create others
		if (config.size>0)
		{
			pos_add = pos_add.offset(pre_dir, room_info0.width).offset(pre_dir.rotateY(), rand.nextInt(2)+1);
			for (int k = 0; i < config.size && k < 10; k++)
			{
				Direction dir_room = Direction.byHorizontalIndex(rand.nextInt(4));
				if (dir_room != pre_dir.getOpposite())
				{
					//get modeled rooms info without blockPos 
					RoomBase corridor = Rooms.getCorridor(rand, config.difficulty);
					RoomInfo corridor_info = corridor.getModeledRoomInfo(rand, 4, pre_dir);
					
					RoomBase room = room_map.get(i+1);
					RoomInfo room_info = room.getModeledRoomInfo(rand, 3, dir_room);
					
					//decide blockPos for corridor
					corridor_info.setBlockPos(pos_add);
					
					//decide blockPos for room
					pos_add = pos_add.offset(pre_dir, corridor_info.width).offset(pre_dir.rotateYCCW(), rand.nextInt(2)+1);
					if (dir_room == pre_dir.rotateY())
					{
						pos_add = pos_add.offset(pre_dir, room_info.height-1);
					}
					if (dir_room == pre_dir.rotateYCCW())
					{
						pos_add = pos_add.offset(pre_dir.rotateY(), room_info.width-1);
					}
					room_info.setBlockPos(pos_add);
					pos_add = pos_add.offset(dir_room, room_info.width).offset(dir_room.rotateY(), rand.nextInt(2)+1);
					pre_dir = dir_room;
					i+=1;

					//add to map
					map.add(corridor_info);
					map.add(room_info);
				}
				
				
			}

		
		}
		
		return map;
	}

}
