package com.github.zljtt.underwaterbiome.Handlers;

import java.util.HashSet;
import java.util.Set;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityOxygenProvider;
import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Enum.BreathableItem;
import com.github.zljtt.underwaterbiome.Utils.Interface.IOxygen;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OxygenHandler 
{
	@SubscribeEvent
    public void onPlayerOutOfBreath(LivingAttackEvent event)
    {
		if (event.getEntity() instanceof PlayerEntity)
		{
			if (event.getSource().equals(DamageSource.DROWN))
			{
				PlayerEntity entity = (PlayerEntity)(event.getEntity());
								
				if(entity.inventory.hasAny(breathableItem()))
				{
					// looping every slots
					for (ItemStack stack : entity.inventory.mainInventory)
					{
						if 	(breathableItem().contains(stack.getItem()))
						{
							IOxygen cap_o =  stack.getCapability(CapabilityOxygenProvider.PARTICLE_CAP, null).orElse(null);
							if (!cap_o.equals(null))
							{
								//reduce the oxygen in one tank and cancel the damage
								if (cap_o.getOxygen()>0)
								{
									float b = getBreathingUnderPressure(entity, entity.world.getSeaLevel());
									cap_o.minusOxygen(Math.min(b, cap_o.getOxygen()));
									event.setCanceled(true);
									break;
								}
							}
						}
					}
				}
			}
		}
    }
	public static float getBreathingUnderPressure(PlayerEntity player, int sealevel)
	{
		double offest = sealevel- player.posY;
		IPlayerData cap_o =  player.getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
		float consumption  = 0;
		if  (offest>40 && !cap_o.getIgnorePressure())
		{
			player.addPotionEffect(new EffectInstance(EffectInit.HIGH_PRESSURE,Integer.MAX_VALUE,1));
			consumption= 2 - cap_o.getReduceOxyConsumption();
		}
		else if  (offest>25 && !cap_o.getIgnorePressure())
		{
			player.addPotionEffect(new EffectInstance(EffectInit.HIGH_PRESSURE,Integer.MAX_VALUE,0));
			consumption= 1.5F - cap_o.getReduceOxyConsumption();
		}
		else if  (offest>0)
		{
			player.removePotionEffect(EffectInit.HIGH_PRESSURE);
			consumption= 1 - cap_o.getReduceOxyConsumption();
		}
		else
		{
			player.removePotionEffect(EffectInit.HIGH_PRESSURE);
			consumption= 0;
		}
		return Math.max(consumption, 0);
	}
	public static Set<Item> breathableItem()
	{
		Set<Item> breath_item= new HashSet<Item>();
		for (BreathableItem item: Reference.BREATHABLEITEM)
		{
			breath_item.add(item.getBreathItem());
		}
		return breath_item;
	}
	public static Set<Item> chargeableItem()
	{
		Set<Item> charge_item= new HashSet<Item>();
		for (BreathableItem item: Reference.BREATHABLEITEM)
		{
			if (item.getCanCharge())
			{
				charge_item.add(item.getBreathItem());
			}
		}
		return charge_item;
	}

}
