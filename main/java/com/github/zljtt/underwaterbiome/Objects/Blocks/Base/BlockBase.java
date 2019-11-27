package com.github.zljtt.underwaterbiome.Objects.Blocks.Base;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedItem;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeBlock;

public class BlockBase extends Block implements INeedBluePrint, INeedItem
{
	
	BlueprintInfo info;
	boolean needitem;
	public BlockBase(String name, Block.Properties porperty, boolean needitem, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(porperty);
		this.needitem = needitem;
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		this.info= new BlueprintInfo(needBlueprint, difficulty, type);
		
		BlockInit.BLOCKS.add(this);
//		Item item = new BlockItem(this, new Item.Properties().group((BlockInit.blockGroup))).setRegistryName(this.getRegistryName());
//		ItemInit.ITEMS.add(item);
//		System.out.println("addBlockItem"+((BlockItem) item).getBlock().toString());
	}

	@Override
	public BlueprintInfo getBlueprintInfo() 
	{
		return info;
	}
	@Override
	public boolean needItem() {
		// TODO Auto-generated method stub
		return needitem;
	}
}
