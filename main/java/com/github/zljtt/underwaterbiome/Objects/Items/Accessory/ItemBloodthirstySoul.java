package com.github.zljtt.underwaterbiome.Objects.Items.Accessory;

import com.github.zljtt.underwaterbiome.Handlers.AccessoryHandler;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase.ObtainType;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class ItemBloodthirstySoul extends ItemAccessoryBase
{

	public ItemBloodthirstySoul(String name, Properties property, ObtainType ob, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, property, ob, needBlueprint, type, difficulty);
	}

	@Override
	public AccessoryEntry getType() 
	{
		return AccessoryEntry.ON_ATTACK;
	}
	@Override
	public <T extends Event>T editEffect(World worldIn, PlayerEntity player, T event)
	{
		if (event instanceof LivingDamageEvent)
		{
			if (ItemLargeSilt.isMeleeDamage(player.getHeldItemMainhand().getItem()))
			{
				player.heal(((LivingDamageEvent) event).getAmount()/10);
			}
		}
		return event;
	}
	
	@Override
	public boolean getAccumulateable()
	{
		return false;
	}

}
