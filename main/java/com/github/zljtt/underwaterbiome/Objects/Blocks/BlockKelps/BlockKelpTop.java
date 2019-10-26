package com.github.zljtt.underwaterbiome.Objects.Blocks.BlockKelps;

import java.util.Random;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterPlantBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockKelpTop extends BlockWaterPlantBase {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_25;
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

	public BlockKelpTop(String name, Block.Properties builder) {
		super(name, builder,Block.makeCuboidShape(0, 0, 0, 16, 9, 16), false);
		this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8 ? this.randomAge(context.getWorld())
				: null;
	}

	public BlockState randomAge(IWorld p_209906_1_) {
		return this.getDefaultState().with(AGE, Integer.valueOf(p_209906_1_.getRandom().nextInt(25)));
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!state.isValidPosition(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		} else {
			BlockPos blockpos = pos.up();
			BlockState blockstate = worldIn.getBlockState(blockpos);
			if (blockstate.getBlock() == Blocks.WATER && state.get(AGE) < 25 && random.nextDouble() < 0.14D) {
				worldIn.setBlockState(blockpos, state.cycle(AGE));
			}

		}
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		Block block = blockstate.getBlock();
		if (block == Blocks.MAGMA_BLOCK) {
			return false;
		} else {
			return block == this || block == BlockInit.KELP || block == BlockInit.KELP_LIGHT
					|| blockstate.func_224755_d(worldIn, blockpos, Direction.UP);
		}
	}

	/**
	 * Update the provided state given the provided neighbor facing and neighbor
	 * state, returning a new state. For example, fences make their connections to
	 * the passed in state if possible, and wet concrete powder immediately returns
	 * its solidified counterpart. Note that this method should ideally consider
	 * only the specific face passed in.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.isValidPosition(worldIn, currentPos)) {
			if (facing == Direction.DOWN) {
				return Blocks.AIR.getDefaultState();
			}

			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}

		if (facing == Direction.UP && facingState.getBlock() == this) {
			return BlockInit.KELP.getDefaultState();
		} else {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
			return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

}
