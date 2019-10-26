package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockFloatingCore extends Block
{

	public BlockItem BLOCKITEM;
	public BlockFloatingCore(String name, Properties porperty) 
	{
		
		super(porperty);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		this.BLOCKITEM = (BlockItem) new BlockItem(this, new Item.Properties().group((BlockInit.blockGroup)));
		
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(BLOCKITEM.setRegistryName(this.getRegistryName()));

	}

}
