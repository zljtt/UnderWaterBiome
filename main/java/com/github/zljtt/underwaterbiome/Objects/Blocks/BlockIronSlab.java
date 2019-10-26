package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockIronSlab extends SlabBlock implements INeedBluePrint
{

	
	public BlockIronSlab(String name, Block.Properties porperty) 
	{
		super(porperty);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new BlockItem(this, new Item.Properties().group(BlockInit.blockGroup)).setRegistryName(this.getRegistryName()));

	}

	@Override
	public BlueprintInfo getBlueprintInfo() 
	{
		return new BlueprintInfo(true, new int[] {0,1}, BlueprintType.PHYSICS);
	}



}
