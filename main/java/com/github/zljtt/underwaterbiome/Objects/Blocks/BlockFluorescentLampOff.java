package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class BlockFluorescentLampOff extends BlockBase
{

	public BlockFluorescentLampOff(String name, Properties porperty, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, porperty,  needBlueprint, type,  difficulty);
	}
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) 
	{
		if (!worldIn.isRemote)
		{
			if (player.getHeldItem(handIn)!=null&&player.getHeldItemMainhand()!=new ItemStack(Items.AIR))
			{
				if (player.getHeldItem(handIn).getItem()==Items.WATER_BUCKET)
				{
					player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
					worldIn.setBlockState(pos, BlockInit.FLUORESCENT_LAMP_ON.getDefaultState());
				}			
			}			
		}
		return false;			

	}
}
