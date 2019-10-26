package com.github.zljtt.underwaterbiome.Objects.Items;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemHeatingPad extends ItemBase
{

	public ItemHeatingPad(String name, Properties property, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, property,  needBlueprint, type,  difficulty);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		IPlayerData cap_o =  playerIn.getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
		if(cap_o!=null)
		{
			cap_o.increaseTemperature(100,false);
		}
		playerIn.getHeldItem(handIn).shrink(1);;
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
