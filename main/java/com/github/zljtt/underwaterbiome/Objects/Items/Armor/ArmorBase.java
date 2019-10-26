package com.github.zljtt.underwaterbiome.Objects.Items.Armor;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.util.ResourceLocation;

public class ArmorBase extends ArmorItem
{

	public ArmorBase(String name, IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) 
	{
		super(materialIn, slot, builder);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		ItemInit.ITEMS.add(this);	
	}

}
