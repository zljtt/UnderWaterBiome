package com.github.zljtt.underwaterbiome.Utils.Rooms;

import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.RoomInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RoomLibrary extends RoomSquare
{

	public RoomLibrary(String name, int min_width, int min_height, int min_depth, boolean hasSecond,
			int... difficulty_range) 
	
	{
		super(name, min_width, min_height, min_depth, hasSecond, 
				new Boolean[] {true, false, true, false}, 
				new Boolean[] {false, true, false, true}, 
				difficulty_range);
	}
	@Override
	public void addDecoration(Map<BlockPos, BlockState> tobuild, IWorld worldIn, RoomInfo info, WreckageConfig config,
			Random ran) 
	{ 
		Direction d  = info.direction;
		
		this.addBookShelfs(tobuild, info.start_pos.offset(d,2).offset(d.rotateY(),2).up(), config, ran);
		this.addBookShelfs(tobuild, info.start_pos.offset(d,2).offset(d.rotateY(),4).up(), config, ran);
		this.addBookShelfs(tobuild, info.start_pos.offset(d,4).offset(d.rotateY(),2).up(), config, ran);
		this.addBookShelfs(tobuild, info.start_pos.offset(d,4).offset(d.rotateY(),4).up(), config, ran);
		if (info.width==9)
		{
			this.addBookShelfs(tobuild, info.start_pos.offset(d,6).offset(d.rotateY(),2).up(), config, ran);
			this.addBookShelfs(tobuild, info.start_pos.offset(d,6).offset(d.rotateY(),4).up(), config, ran);
		}
		if (info.height==9)
		{
			this.addBookShelfs(tobuild, info.start_pos.offset(d,2).offset(d.rotateY(),6).up(), config, ran);
			this.addBookShelfs(tobuild, info.start_pos.offset(d,4).offset(d.rotateY(),6).up(), config, ran);
		}
		if (info.width==9&&info.height==9)
		{
			this.addBookShelfs(tobuild, info.start_pos.offset(d,6).offset(d.rotateY(),6).up(), config, ran);
		}
	}
	

	public void addBookShelfs(Map<BlockPos, BlockState> tobuild, BlockPos pos,  WreckageConfig config,Random ran) 
	{
		addLadder(tobuild, pos,  config,ran);
		addLadder(tobuild, pos.up(1),  config,ran);
		addLadder(tobuild, pos.up(2),  config,ran);
		tobuild.put(pos.up(3), RoomInfo.iron_block);
	}
	public void addLadder(Map<BlockPos, BlockState> tobuild, BlockPos pos,  WreckageConfig config,Random ran) 
	{
		tobuild.put(pos, Blocks.BOOKSHELF.getDefaultState());
		for(Direction dir : Reference.HORIZONTAL_DIRECTIONS)
		{
			if (ran.nextBoolean())
			{
				tobuild.put(pos.offset(dir), Blocks.LADDER.getDefaultState().with(BlockStateProperties.WATERLOGGED, true).with(HorizontalBlock.HORIZONTAL_FACING, dir.getOpposite()));
			}
		}
	}
	@Override
	public RoomInfo getModeledRoomInfo(Random ran, int range, Direction dir) 
	{
		return new RoomInfo(this, null, 
				this.min_width+(ran.nextBoolean()?0:2),
				this.min_height+(ran.nextBoolean()?0:2), 
				this.min_depth+ran.nextInt(2)
				,dir,ran);	
	}

	

}
