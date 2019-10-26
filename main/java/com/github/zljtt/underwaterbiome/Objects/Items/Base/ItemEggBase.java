package com.github.zljtt.underwaterbiome.Objects.Items.Base;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;

public class ItemEggBase extends SpawnEggItem
{

	public ItemEggBase(String name, EntityType<?> typeIn, int primaryColorIn, int secondaryColorIn)
	{
		super(typeIn, primaryColorIn, secondaryColorIn, new Item.Properties().group(ItemInit.itemGroup));
		
		this.setRegistryName(new ResourceLocation(Reference.MODID,name));
		ItemInit.ITEMS.add(this);
	}

}
