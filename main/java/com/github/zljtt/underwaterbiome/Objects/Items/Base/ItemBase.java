package com.github.zljtt.underwaterbiome.Objects.Items.Base;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemBase extends Item implements INeedBluePrint
{
	BlueprintInfo info;
	String name;
	public ItemBase(String name, Item.Properties property, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(property);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		this.name = name;
		this.info= new BlueprintInfo(needBlueprint, difficulty, type);
		ItemInit.ITEMS.add(this);

	}

	@Override
	public BlueprintInfo getBlueprintInfo() 
	{
		return info;
	}

}
