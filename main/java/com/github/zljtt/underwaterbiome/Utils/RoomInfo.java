package com.github.zljtt.underwaterbiome.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockShipDoor;
import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.Rooms.RoomBase;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RoomInfo
{
	public static BlockState iron_block =  BlockInit.IRON_BLOCK.getDefaultState();
	public static BlockState iron_slab =  BlockInit.IRON_SLAB.getDefaultState().with(BlockStateProperties.WATERLOGGED, true);
	public static BlockState iron_slab_top =  BlockInit.IRON_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.TOP).with(BlockStateProperties.WATERLOGGED, true);
	public static BlockState glass =  Blocks.GLASS.getDefaultState();
	public static BlockState water =  Blocks.WATER.getDefaultState();
	public static BlockState door_top =  BlockInit.SHIP_DOOR.getDefaultState().with(BlockShipDoor.HALF, DoubleBlockHalf.UPPER).with(DoorBlock.OPEN, false);
	public static BlockState door_bottom =  BlockInit.SHIP_DOOR.getDefaultState().with(BlockShipDoor.HALF, DoubleBlockHalf.LOWER).with(DoorBlock.OPEN, false);

	public Random ran;
	public BlockPos start_pos;
	public int width;
	public int height;
	public int depth;
	public RoomBase room;
	public Direction direction;

	public RoomInfo(RoomBase room, BlockPos pos, int width, int height, int depth, Direction direction, Random rand)
	{
		this.room = room;
		this.ran = rand;
		this.start_pos=pos;
		this.width=width;
		this.height=height;
		this.depth=depth;
		this.direction=direction;
	}
	@Override
	public String toString() 
	{
		return ""+room.name+":"+start_pos.toString()+"Width("+width+")Height("+height+")Depth("+depth+")Direction("+direction.toString()+")";
	}
	
	public Map<BlockPos,BlockState> buildFloor(IWorld worldIn, WreckageConfig config)
	{
		Map<BlockPos,BlockState> list_pos0 = new HashMap<BlockPos,BlockState>();
		for (int i = 0; i<width; i++)
		{
			for (int j = 0; j<height; j++)
			{
				putIronBlock(list_pos0, 1.0F, 2F, start_pos.offset(direction,i).offset(direction.rotateY(), j), config, ran);
				putRandomVerticalBlock(list_pos0, worldIn, 0.2F, 0.2F, start_pos.offset(direction,i).offset(direction.rotateY(), j), config, ran);
			}
		}
		return list_pos0;
	}
	public Map<BlockPos,BlockState> buildSecondFloor(IWorld worldIn, WreckageConfig config, int depth)
	{
		Map<BlockPos,BlockState> list_pos0 = new HashMap<BlockPos,BlockState>();
		for (int i = 0; i<width; i++)
		{
			for (int j = 0; j<height; j++)
			{
				putIronBlock(list_pos0, 0.5F, 0.4F, start_pos.offset(direction,i).offset(direction.rotateY(), j).up(depth), config, ran);
				putRandomVerticalBlock(list_pos0, worldIn, 0.05F, 0.3F, start_pos.offset(direction,i).offset(direction.rotateY(), j).up(depth), config, ran);

			}
		}
		//remove isolated block
		return removeIsolatedBlock(list_pos0);
	}
	
	public Map<BlockPos,BlockState> buildSolidWall(IWorld worldIn, WreckageConfig config, BlockPos start, int length, Direction dir, boolean withdoor)
	{
		List<Map<BlockPos,BlockState>> list_layers = new ArrayList<Map<BlockPos,BlockState>>();
		//generate first layer
		Map<BlockPos,BlockState> list_pos0 = new HashMap<BlockPos,BlockState>();
		for (int i = 0; i<length; i++)
		{
			putIronBlock(list_pos0, 1.5F, 1.2F, start.offset(dir, i), config, ran);
			putRandomHorizontalBlock(list_pos0, worldIn, 0.3F, 0.2F, start.offset(dir, i), config, ran);
		}
		list_layers.add(list_pos0);
		
		//generate top layers
		for (int d = 1; d<depth; d++)
		{
			Map<BlockPos,BlockState> list_pos = new HashMap<BlockPos,BlockState>();
			for (int i = 0; i<length; i++)
			{
				BlockPos pos_add = start.offset(dir, i).up(d);
				Map<BlockPos,BlockState> layer_below = list_layers.get(d-1);
				if (layer_below.containsKey(pos_add.down()) && !layer_below.get(pos_add.down()).equals(iron_slab))
				{
					putIronBlock(list_pos, 0.9F, 0.8F, pos_add, config, ran);
				}				
			}
			list_layers.add(list_pos);
		}
		
			
			
		Map<BlockPos,BlockState> returnlayer = new HashMap<BlockPos,BlockState>();
		list_layers.forEach(layer->returnlayer.putAll(layer));
		//generate door
		if (withdoor && length-4>0)
		{
			int door_pos = 2+ran.nextInt(length-4);
			if (ran.nextInt(100)>config.broken*1.5)
			{
				returnlayer.put(start.offset(dir, door_pos), door_bottom);
				returnlayer.put(start.offset(dir, door_pos).up(), door_top);
			}
			else
			{
				returnlayer.put(start.offset(dir, door_pos), water);
				returnlayer.put(start.offset(dir, door_pos).up(), water);
			}
		}
		return returnlayer;
	}
	
	
	
	public Map<BlockPos,BlockState> buildWindowWall(IWorld worldIn, WreckageConfig config, BlockPos start, int length, Direction dir, boolean withdoor)
	{
		List<Map<BlockPos,BlockState>> list_layers = new ArrayList<Map<BlockPos,BlockState>>();
		//generate first layer
		Map<BlockPos,BlockState> list_pos0 = new HashMap<BlockPos,BlockState>();
		for (int i = 0; i<length; i++)
		{
			putIronBlock(list_pos0, 1.5F, 1.2F, start.offset(dir, i), config, ran);
			putRandomHorizontalBlock(list_pos0, worldIn, 0.3F, 0.2F, start.offset(dir, i), config, ran);
		}
		list_layers.add(list_pos0);
		
		//generate top layers
		for (int d = 1; d<depth; d++)
		{
			Map<BlockPos,BlockState> list_pos = new HashMap<BlockPos,BlockState>();
			for (int i = 0; i<length; i++)
			{
				BlockPos pos_add = start.offset(dir, i).up(d);
				Map<BlockPos,BlockState> layer_below = list_layers.get(d-1);
				if (layer_below.containsKey(pos_add.down())&& !(layer_below.get(pos_add.down()).equals(iron_slab)))
				{
					//generate window layers
					if(d==1)
					{
						putWindowBlock(list_pos, 0.7F, 0.4F, pos_add, config, ran);
					}
					else	
					{
						putIronBlock(list_pos, 0.9F, 0.8F, pos_add, config, ran);
					}
				}				
			}
			list_layers.add(list_pos);
		}
		Map<BlockPos,BlockState> returnlayer = new HashMap<BlockPos,BlockState>();
		list_layers.forEach(layer->returnlayer.putAll(layer));
		//generate door
		if (withdoor)
		{
			int door_pos = 2+ran.nextInt(length-4);
			if (ran.nextInt(100)>config.broken*1.6)
			{
				returnlayer.put(start.offset(dir, door_pos), door_bottom);
				returnlayer.put(start.offset(dir, door_pos).up(), door_top);
			}
			else
			{
				returnlayer.put(start.offset(dir, door_pos), water);
				returnlayer.put(start.offset(dir, door_pos).up(), water);
			}
		}
		return returnlayer;
	}

	
	
	
	
	
	
	
	
	
	
	
	private void putIronBlock(Map<BlockPos,BlockState> map,float chance1, float chance2, BlockPos pos, WreckageConfig config, Random ran)
	{
		if (ran.nextInt(100)<=(100-config.broken)*chance1)
		{
			map.put(pos, iron_block);
		}
		else if(ran.nextInt(100)<=(100-config.broken)*chance2)
		{
			map.put(pos, iron_slab);
		}
	}
	
	private void putWindowBlock(Map<BlockPos,BlockState> map,float chance1, float chance2, BlockPos pos, WreckageConfig config, Random ran)
	{
		if (ran.nextInt(100)<=(100-config.broken)*chance1)
		{
			map.put(pos, glass);
		}
		else if(ran.nextInt(100)<=(100-config.broken)*chance2)
		{
			map.put(pos, iron_block);
		}
		else if(ran.nextInt(100)<=(100-config.broken)*chance2)
		{
			map.put(pos, iron_slab);
		}
		else if(ran.nextInt(100)<=(100-config.broken)*chance2)
		{
			map.put(pos, water);
		}
	}
	
	private void putRandomHorizontalBlock(Map<BlockPos,BlockState> map, IWorld world, float chance1, float chance2, BlockPos pos, WreckageConfig config, Random ran)
	{
		BlockPos pos_s =  pos.add(-1+ran.nextInt(3), 0, -1+ran.nextInt(3));
		if (!pos_s.equals(pos) && world.getBlockState(pos_s.down()).getBlock()!=Blocks.WATER)
		{
			putIronBlock(map, chance1, chance2,pos_s, config, ran);
		}
	}
	
	private void putRandomVerticalBlock(Map<BlockPos,BlockState> map, IWorld world, float chance1, float chance2, BlockPos pos, WreckageConfig config, Random ran)
	{
		BlockPos pos_s =  pos.add(0, -1+ran.nextInt(3), 0);
		if (!pos_s.equals(pos))
		{
			if (ran.nextInt(100)<=(100-config.broken)*chance1)
			{
				map.put(pos_s, iron_block);
			}
			else if(ran.nextInt(100)<=(100-config.broken)*chance2)
			{
				if (world.getBlockState(pos_s.down()).getBlock()==Blocks.WATER)
				{
					map.put(pos, iron_slab_top);

				}
				else if (world.getBlockState(pos_s).getBlock()==Blocks.WATER)
					map.put(pos, iron_slab);
			}		
		}
	}
	
	public static Map<BlockPos,BlockState> removeIsolatedBlock(Map<BlockPos,BlockState> list_pos0)
	{
		Map<BlockPos,BlockState> toRemove = new HashMap<BlockPos,BlockState>();
		toRemove.putAll(list_pos0);
		toRemove.forEach((pos, state)->
		{	
			boolean do_remove = true;
			for (int i = -1; i < 2;i++)
			{
				for (int j = -1; j < 2;j++)
				{
					for (int k = -1; k < 2;k++)
					{
						if (toRemove.containsKey(pos.add(i, j, k)))
						{
							do_remove = false;
						}
					}
				}
			}
			if (do_remove)
			{
				list_pos0.remove(pos);
			}
		});
		return list_pos0;
	}
	public static BlockState getByTwo(BlockState state1,BlockState state2, float chance, WreckageConfig config, Random ran)
	{
		if (ran.nextDouble()<=chance)
			return state1;
		else return state2;
	}
	public static BlockState getByChance(BlockState state1, float chance, WreckageConfig config, Random ran)
	{
		if (ran.nextInt(100)<=(100-config.broken)*chance)
			return state1;
		else return Blocks.WATER.getDefaultState();
	}
	
	public static void addChest(BlockPos chest_pos, IWorld worldIn, ResourceLocation rl,
			Random ran, Direction dir)//Map<BlockPos, BlockState> tobuild,
	{
//		tobuild.put(chest_pos, BlockInit.SHIP_CHEST.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, dir.getOpposite()));
        worldIn.setBlockState(chest_pos, BlockInit.SHIP_CHEST.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, dir.getOpposite()), 3);
        TileEntity tileentity = worldIn.getTileEntity(chest_pos);
        if (tileentity instanceof LockableLootTileEntity) 
        {
            ((LockableLootTileEntity)tileentity).setLootTable(rl, ran.nextLong());
        }
	}
	public void setBlockPos(BlockPos pos)
	{
		start_pos = pos;
	}
	
}