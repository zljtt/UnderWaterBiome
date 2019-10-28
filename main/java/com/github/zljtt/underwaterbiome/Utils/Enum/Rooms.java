package com.github.zljtt.underwaterbiome.Utils.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomAquarium;
import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomBase;
import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomControl;
import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomCorridor;
import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomDorm;
import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomLab;
import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomLibrary;
import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomSmelt;
import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomStorage;

public enum Rooms 
{
	
	;
	
	public static List<RoomBase> ROOMS = new  ArrayList<RoomBase>();
	public static List<RoomBase> CORRIDORS = new  ArrayList<RoomBase>();

//	public static final RoomBase EMPTY = new RoomBase("empty", 0, 0, 0, 0);
	public static final RoomBase CORRIDOR = new RoomCorridor("corridor", 4, 4, 3, false, 0,1,2) ;
	public static final RoomBase DOUBLE_CORRIDOR = new RoomCorridor("double_corridor", 4, 4, 3, true, 1,2);
	
	public static final RoomBase CONTROL = new RoomControl("control", 7, 6, 3, false, 0, 1, 2);
	public static final RoomBase AQUARIUM = new RoomAquarium("aquarium", 8, 8, 5, false, 0, 1, 2);
	public static final RoomBase LAB = new RoomLab("lab", 7, 5, 5, false, 0, 1, 2);
	public static final RoomBase DORM = new RoomDorm("dorm", 6, 6, 4, false, 0, 1, 2);
	public static final RoomBase STORAGE = new RoomStorage("storage", 6, 6, 4, false, 1, 2);
	public static final RoomBase LIBRARY = new RoomLibrary("library", 7, 7, 4, false, 0, 1);
	public static final RoomBase SMELT = new RoomSmelt("smelt", 5, 7, 4, false, 1, 2);

	
	
	
	public static List<RoomBase> getRoomListByDifficulty(Random ran, int difficulty,int size)
	{
		List<RoomBase> confirmed_list = new ArrayList<RoomBase>();
		List<RoomBase> return_list = new ArrayList<RoomBase>();
		List<RoomBase> rooms = ROOMS;
		
		for(RoomBase room : rooms)
		{
			for(int i : room.difficulty_range)
			{
				if (difficulty == i)
				{
					confirmed_list.add(room);
				}
			}
		}	
		for(int i=0; i<size+1; i++)
		{
			int ran_n = ran.nextInt(confirmed_list.size());
			return_list.add(confirmed_list.get(ran_n));
			if(confirmed_list.size()>1)
			{
				confirmed_list.remove(ran_n);
			}
		}
		return return_list;
	}
	public static RoomBase getCorridor(Random ran, int difficulty)
	{
		List<RoomBase> return_list = new ArrayList<RoomBase>();
		List<RoomBase> corridors = CORRIDORS;
		for(RoomBase room : corridors)
		{
			for(int i : room.difficulty_range)
			{
				if (difficulty == i)
				{
					return_list.add(room);
				}
			}
		}	
		if (return_list.size()>0)
		{
			return return_list.get(ran.nextInt(return_list.size()));
		}
		return CORRIDOR;
	}
	
	
	

}
