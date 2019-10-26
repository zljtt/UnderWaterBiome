package com.github.zljtt.underwaterbiome.Utils.Enum;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum BreathableItem 
{
	
	SINGLE_OXYGEN_TANK(ItemInit.SINGLE_OXYGEN_TANK, 250,true),
	DOUBLE_OXYGEN_TANK(ItemInit.DOUBLE_OXYGEN_TANK, 500,true);

	private Item breath_item;
	private ItemStack breath_itemstack;
	private int max_oxygen;
	private boolean canCharge;

	private BreathableItem(Item item, int max_oxy, boolean cancharge)
	{
		this.setBreathItem(item);
		this.setMaxOxygen(max_oxy);
		this.setBreathItemStack(new ItemStack(breath_item));
		this.canCharge = cancharge;
	}
	
	
	public static BreathableItem getByItem(Item item)
	{
		if (item.equals(ItemInit.SINGLE_OXYGEN_TANK))return SINGLE_OXYGEN_TANK;
		else if (item.equals(ItemInit.DOUBLE_OXYGEN_TANK))return DOUBLE_OXYGEN_TANK;
		else return null;

	}
	public boolean getCanCharge() 
	{
		return canCharge;
	}
	public int getMaxOxygen() 
	{
		return max_oxygen;
	}

	public void setMaxOxygen(int max_oxygen) 
	{
		this.max_oxygen = max_oxygen;
	}

	public Item getBreathItem() {
		return breath_item;
	}

	public void setBreathItem(Item breathitem) {
		this.breath_item = breathitem;
	}

	public ItemStack getBreathItemStack() {
		return breath_itemstack;
	}

	public void setBreathItemStack(ItemStack breathitemStack) {
		this.breath_itemstack = breathitemStack;
	}
}
