package com.github.zljtt.underwaterbiome.Objects.Items.Accessory;

import com.github.zljtt.underwaterbiome.Handlers.AccessoryHandler;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase.ObtainType;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event.Result;

public class ItemPoisonProtection extends ItemAccessoryBase
{

	public ItemPoisonProtection(String name, Properties property, ObtainType ob, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, property, ob, needBlueprint, type, difficulty);
	}

	@Override
	public AccessoryEntry getType() 
	{
		return AccessoryEntry.ON_PLAYER_CHECK_EFFECT;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Event> T editEffect(World worldIn, PlayerEntity player, T target) 
	{
		if (target instanceof PotionApplicableEvent)
		{
			PotionApplicableEvent event = (PotionApplicableEvent)target;
			if(event.getPotionEffect().getEffectName().equals(Effects.POISON.getName()))
			{
				PotionApplicableEvent event_return = new PotionApplicableEvent(event.getEntityLiving(), event.getPotionEffect());
				event_return.setResult(Result.DENY);
				return (T) event_return;
			}
		}
		return target;
	}

	@Override
	public boolean getAccumulateable()
	{
		return false;
	}

}
