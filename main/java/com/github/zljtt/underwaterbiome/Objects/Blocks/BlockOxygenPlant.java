package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterPlantBase;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
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

public class BlockOxygenPlant extends BlockWaterPlantBase 
{
	public BlockOxygenPlant(String name, Block.Properties p_i49501_2_) 
	{
		super(name, p_i49501_2_, Block.makeCuboidShape(2, 0, 2, 14, 14, 14), true);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) 
	{
		ItemEntity entity = new ItemEntity(worldIn, 
				pos.getX()+0.5F,
				pos.getY()+0.2F, 
				pos.getZ()+0.5F,
				new ItemStack(ItemInit.OXYGEN_FRUIT));
		worldIn.addEntity(entity);
		super.onBlockHarvested(worldIn, pos, state, player);
	}


	@Override
	public ResourceLocation getLootTable()
	{
		return new ResourceLocation(Reference.MODID,"blocks/oxygen_fruit_plant");
	}

}
