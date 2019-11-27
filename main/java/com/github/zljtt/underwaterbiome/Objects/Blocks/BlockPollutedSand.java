package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedItem;

import net.minecraft.block.SandBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockPollutedSand extends SandBlock implements INeedItem
{

	public BlockPollutedSand(String  name, Properties properties) 
	{
		super(14406560, properties);
		this.setRegistryName(new ResourceLocation(Reference.MODID,name));
		BlockInit.BLOCKS.add(this);
	}

	@Override
	public boolean needItem() {
		// TODO Auto-generated method stub
		return true;
	}

}
