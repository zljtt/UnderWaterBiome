package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.List;

import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ItemSpaceshipDivingRecorder extends ItemBase
{

	public ItemSpaceshipDivingRecorder(String name, Properties property, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, property, needBlueprint, type, difficulty);
	}

}
