package com.github.zljtt.underwaterbiome.Objects.Items.Accessory;

import com.github.zljtt.underwaterbiome.Handlers.AccessoryHandler;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase.ObtainType;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event.Result;

public class ItemWitherFlower extends ItemAccessoryBase
{

	public ItemWitherFlower(String name, Properties property,ObtainType ob,  boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, property, ob, needBlueprint, type, difficulty);
	}

	@Override
	public AccessoryEntry getType() 
	{
		return AccessoryEntry.BEFORE_ATTACKED;
	}
	@Override
	public <T extends Event>T editEffect(World worldIn, PlayerEntity player, T eventIn)
	{
		LivingAttackEvent event = (LivingAttackEvent)eventIn;
		if (event.getSource().getTrueSource() instanceof IMob)
		{
			eventIn.setCanceled(true);
			int time = 0;
			if (player.isPotionActive(Effects.WITHER))
			{
				time = player.getActivePotionEffect(Effects.WITHER).getDuration();
			}
			AccessoryHandler.handleEffect(worldIn, player, new EffectInstance(Effects.WITHER, (int) (event.getAmount()*20F)+time, 0), event.getEntityLiving());
		}
		return eventIn;
		
	}

	@Override
	public boolean getAccumulateable()
	{
		return false;
	}

}
