package com.github.zljtt.underwaterbiome.Objects.Items.Accessory;

import com.github.zljtt.underwaterbiome.Handlers.AccessoryHandler;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase.ObtainType;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;

public class ItemVenomContainer  extends ItemAccessoryBase
{

	public ItemVenomContainer(String name, Properties property,ObtainType ob,  boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, property, ob, needBlueprint, type, difficulty);
	}

	@Override
	public AccessoryEntry getType() 
	{
		return AccessoryEntry.ON_PLAYER_ADD_EFFECT;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Event> T editEffect(World worldIn, PlayerEntity player, T event) 
	{
		if (event instanceof PotionEvent)
		{
			PotionEvent potion_event = ((PotionEvent)event);
			EffectInstance old_effect = potion_event.getPotionEffect();
			if(old_effect.getEffectName() == Effects.POISON.getName())
			{
				EffectInstance new_effect = new EffectInstance(Effects.POISON, old_effect.getDuration(),old_effect.getAmplifier()+1);
				return (T) new PotionEvent(potion_event.getEntityLiving(), new_effect);
			}
		}
		return event;
	}

	@Override
	public boolean getAccumulateable() 
	{
		// TODO Auto-generated method stub
		return false;
	}

}
