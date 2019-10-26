package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWaterPump extends ItemBase
{
	public static final int MAX_WATER_ONCE = 500;

	private Map<BlockPos, BlockState> waterlogged = new HashMap<BlockPos, BlockState>();
	public ItemWaterPump(String name, Properties property, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, property, needBlueprint, type,  difficulty);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		BlockPos pos = playerIn.getPosition();

		if (worldIn.getBlockState(playerIn.getPosition()).getBlock().equals(Blocks.WATER))
		{
			waterlogged.clear();
			List<BlockPos> water = removeWater(new ArrayList<BlockPos>(), pos, worldIn);
			
			if (water.size() < MAX_WATER_ONCE && water.size()>0 && waterlogged.size()>0)
			{
				water.forEach(pos0->worldIn.setBlockState(pos0, Blocks.AIR.getDefaultState(),3));
				waterlogged.forEach((pos0,state)->worldIn.setBlockState(pos0, state));
				playerIn.getHeldItem(handIn).shrink(1);
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	private List<BlockPos> removeWater(List<BlockPos> water,BlockPos pos, World world)
	{
		for (Direction direction: Reference.DIRECTIONS)
		{
			BlockState state = world.getBlockState(pos.offset(direction));
			if (water.size() < MAX_WATER_ONCE)
			{ 
				BlockPos pos_new = pos.offset(direction);
				if ((state.getBlock()==Blocks.WATER ||state.getBlock() instanceof ILiquidContainer) && ! water.contains(pos_new))
				{
					water.add(pos_new);
					return removeWater(water, pos_new, world);
				}
				else if (state.has(BlockStateProperties.WATERLOGGED) 
						&& state.get(BlockStateProperties.WATERLOGGED) == true
						&& !waterlogged.containsKey(pos_new)
						&& state.getBlock()!=BlockInit.CABIN_DOOR)
				{
					BlockState state0 = state.with(BlockStateProperties.WATERLOGGED, false);
					waterlogged.put(pos_new, state0);	
				}

			}
		}
		return water;
	}

}
