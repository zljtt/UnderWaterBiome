package com.github.zljtt.underwaterbiome.Objects.Blocks.Base;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockBase extends Block implements INeedBluePrint
{
	
	BlueprintInfo info;
	public BlockBase(String name, Block.Properties porperty, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(porperty);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		this.info= new BlueprintInfo(needBlueprint, difficulty, type);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new BlockItem(this, new Item.Properties().group((BlockInit.blockGroup))).setRegistryName(this.getRegistryName()));

	}

	@Override
	public BlueprintInfo getBlueprintInfo() 
	{
		return info;
	}
}
