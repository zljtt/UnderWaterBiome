package com.github.zljtt.underwaterbiome.Objects.Items;

import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.item.ItemStack;

public class ItemGasBottle extends ItemBase
{

	public ItemGasBottle(String name, Properties property, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, property, needBlueprint, type, difficulty);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getBurnTime(ItemStack itemStack) 
	{
		return 1600;
	}
}
