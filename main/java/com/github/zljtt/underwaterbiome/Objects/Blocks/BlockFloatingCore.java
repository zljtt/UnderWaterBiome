package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockBase;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedItem;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockFloatingCore extends BlockBase
{

	public BlockFloatingCore(String name, Properties porperty, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, porperty, true, needBlueprint, type, difficulty);

	}
}
