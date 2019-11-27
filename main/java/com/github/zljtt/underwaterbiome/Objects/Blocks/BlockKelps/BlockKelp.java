package com.github.zljtt.underwaterbiome.Objects.Blocks.BlockKelps;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterPlantBase;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTables;

public class BlockKelp extends BlockWaterPlantBase {
	private final BlockKelpTop top;
	Random ran = new Random();
	public BlockKelp(String name, BlockKelpTop p_i49501_1_, Block.Properties p_i49501_2_) 
	{
		super(name, p_i49501_2_, Block.makeCuboidShape(0, 0, 0, 16, 16, 16), false);
		this.top = p_i49501_1_;
	}

	/**
	 * Update the provided state given the provided neighbor facing and neighbor
	 * state, returning a new state. For example, fences make their connections to
	 * the passed in state if possible, and wet concrete powder immediately returns
	 * its solidified counterpart. Note that this method should ideally consider
	 * only the specific face passed in.
	 */
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos)) 
		{
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
			if (worldIn.getWorld() !=null)
				this.tick(stateIn, worldIn.getWorld(), currentPos, ran);
		}

		if (facing == Direction.UP) {
			Block block = facingState.getBlock();
			if (block != this && block != this.top) {
				return this.top.randomAge(worldIn);
			}
		}
		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		Block block = blockstate.getBlock();
		return block != Blocks.MAGMA_BLOCK && (block == this || block == BlockInit.KELP_LIGHT
				|| blockstate.func_224755_d(worldIn, blockpos, Direction.UP));
	}
	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) 
	{
		if (!state.isValidPosition(worldIn, pos)) 
		{
			worldIn.destroyBlock(pos, true);
		}
	    super.tick(state, worldIn, pos, random);

	}

	@Override
	public ResourceLocation getLootTable()
	{
		return new ResourceLocation(Reference.MODID,"blocks/kelp");
	}

}
