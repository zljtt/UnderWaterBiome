package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DeadCoralWallFanBlock;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.CoralFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FeatureDeadCoral extends CoralFeature {

	public FeatureDeadCoral(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
		super(config);
	}
	public BlockState randomDeadCoral(Random rand)
	{
		int ran = rand.nextInt(5);
		Block block;
		switch (ran) {
		case 0:
			block = Blocks.DEAD_BRAIN_CORAL_BLOCK;
			break;
		case 1:
			block = Blocks.DEAD_BUBBLE_CORAL_BLOCK;
			break;
		case 2:
			block = Blocks.DEAD_FIRE_CORAL_BLOCK;
			break;
		case 3:
			block = Blocks.DEAD_HORN_CORAL_BLOCK;
			break;
		case 4:
			block = Blocks.DEAD_TUBE_CORAL_BLOCK;
			break;
		default:
			block = Blocks.DEAD_BRAIN_CORAL_BLOCK;
		}
		return block.getDefaultState();
	}
		public BlockState randomLiveCoral(Random rand)
		{
			int ran = rand.nextInt(5);
			Block block;
			switch (ran) {
			case 0:
				block = Blocks.BRAIN_CORAL_BLOCK;
				break;
			case 1:
				block = Blocks.BUBBLE_CORAL_BLOCK;
				break;
			case 2:
				block = Blocks.FIRE_CORAL_BLOCK;
				break;
			case 3:
				block = Blocks.HORN_CORAL_BLOCK;
				break;
			case 4:
				block = Blocks.TUBE_CORAL_BLOCK;
				break;
			default:
				block = Blocks.BRAIN_CORAL_BLOCK;
			}
			return block.getDefaultState();
		}	
	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) 
	{
		 int posibility = rand.nextInt(100);
		 int posibility0 = rand.nextInt(100);
		 if (posibility<70)
		 {
			return this.func_204623_a(worldIn, rand, pos, posibility0<20?randomLiveCoral(rand):randomDeadCoral(rand));
		 }
		 else
		 {
			return this.func_204623_a(worldIn, rand, pos, posibility0<60?randomLiveCoral(rand):randomDeadCoral(rand));
		 }
	}

	@Override
	protected boolean func_204623_a(IWorld world, Random ran, BlockPos pos,BlockState state) 
	{
		int i = ran.nextInt(3) + 3;
		int j = ran.nextInt(3) + 3;
		int k = ran.nextInt(3) + 3;
		int l = ran.nextInt(3) + 1;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(pos);

		for (int i1 = 0; i1 <= j; ++i1) {
			for (int j1 = 0; j1 <= i; ++j1) {
				for (int k1 = 0; k1 <= k; ++k1) {
					blockpos$mutableblockpos.setPos(i1 + pos.getX(), j1 + pos.getY(),
							k1 + pos.getZ());
					blockpos$mutableblockpos.move(Direction.DOWN, l);
					if ((i1 != 0 && i1 != j || j1 != 0 && j1 != i) && (k1 != 0 && k1 != k || j1 != 0 && j1 != i)
							&& (i1 != 0 && i1 != j || k1 != 0 && k1 != k)
							&& (i1 == 0 || i1 == j || j1 == 0 || j1 == i || k1 == 0 || k1 == k)
							&& !(ran.nextFloat() < 0.1F)
							&& !this.generateOthers(world, ran, blockpos$mutableblockpos, state)) 
					{
						;
					}
				}
			}
		}
		return true;
	}

	protected boolean generateOthers(IWorld world, Random rand, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos.up();
		BlockState blockstate = world.getBlockState(pos);
		if ((blockstate.getBlock() == Blocks.WATER || blockstate.isIn(BlockTags.CORALS))
				&& world.getBlockState(blockpos).getBlock() == Blocks.WATER) {
			world.setBlockState(pos, state, 3);
			if (rand.nextFloat() < 0.05F) {
				world.setBlockState(blockpos, BlockTags.CORALS.getRandomElement(rand).getDefaultState(), 2);
			} else if (rand.nextFloat() < 0.01F) {
				world.setBlockState(blockpos, Blocks.SEA_PICKLE.getDefaultState().with(SeaPickleBlock.PICKLES,
						Integer.valueOf(rand.nextInt(4) + 1)), 2);
			}

			for (Direction direction : Direction.Plane.HORIZONTAL) {
				if (rand.nextFloat() < 0.04F) {
					BlockPos blockpos1 = pos.offset(direction);
					if (world.getBlockState(blockpos1).getBlock() == Blocks.WATER) {
						BlockState blockstate1 = BlockTags.WALL_CORALS.getRandomElement(rand).getDefaultState()
								.with(DeadCoralWallFanBlock.FACING, direction);
						world.setBlockState(blockpos1, blockstate1, 2);
					}
				}
			}

			return true;
		} else {
			return false;
		}
	}
}
