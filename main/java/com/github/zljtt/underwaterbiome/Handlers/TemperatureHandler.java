package com.github.zljtt.underwaterbiome.Handlers;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeLavaRange;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TemperatureHandler 
{
	boolean canHeat =false;
	int timer = 100;

	public static int temp_bound = 200;
	@SubscribeEvent
    public void onPlayerCalculateTempRate(PlayerTickEvent event)
    {
		if (event.phase == TickEvent.Phase.START)
		{
			PlayerEntity player = event.player; 
			IPlayerData cap_o =  player.getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
			
			if (!player.world.isRemote && player.isInWater() && cap_o!=null && !player.isCreative()&&!player.isSpectator())
			{
				double bottom_offset 	= Math.max(player.posY - player.world.getHeight(Heightmap.Type.OCEAN_FLOOR, (int)player.posX, (int)player.posZ), 0);
	    		double top_offset 		= Math.max(player.world.getSeaLevel() - player.posY,0);
	    		Biome biome 			= player.world.getBiome(player.getPosition());
	    		
//	    		cap_o.setTemperatureRate(biome.getTemperature(player.getPosition())-0.5+(player.getFoodStats().getFoodLevel()-10)/15);//x=10 y=0 /x=20 y=1
				canHeat = (biome instanceof BiomeLavaRange && bottom_offset < 10)
						||player.world.getBlockState(player.getPosition()).getBlock() == Blocks.BUBBLE_COLUMN;
				
				Item chest = event.player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem();
				Item feet = event.player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem();
				Item head = event.player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
				Item legs = event.player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem();
				
				//increase temp by biome
    			double add_biome = biome.getTemperature(player.getPosition())-0.5;
				cap_o.increaseTemperature(add_biome,false);
				
				if (canHeat)
	    		{
					double increaseTemp = 2 - bottom_offset/5;
	    			double heat_prof_total = Reference.HEAT_PROF_ITEM.getOrDefault(chest, 0)
	    					+ Reference.HEAT_PROF_ITEM.getOrDefault(feet, 0)
	    					+ Reference.HEAT_PROF_ITEM.getOrDefault(head, 0)
	    					+ Reference.HEAT_PROF_ITEM.getOrDefault(legs, 0);	   
	    			double heat_prof_total0 = (heat_prof_total+cap_o.getBaseHeatProf())/10;
					cap_o.increaseTemperature(Math.max(increaseTemp - heat_prof_total0, 0), true);//x=10 y = 0 / x=0 y=2
	    		}
	    		else
	    		{
	    			
	    			//reduce temp by pressure
					if (top_offset>10)
					{
//						if (!cap_o.getIgnorePressure())
						{
							cap_o.reduceTemperature((top_offset-10)/30);
						}
					}
					else
					{
						cap_o.increaseTemperature((10-top_offset)/30,false);
					}
	    			//add temp by cold_prof
					double cold_prof_total = Reference.COLD_PROF_ITEM.getOrDefault(chest, 0)
	    					+ Reference.COLD_PROF_ITEM.getOrDefault(feet, 0)
	    					+ Reference.COLD_PROF_ITEM.getOrDefault(head, 0)
	    					+ Reference.COLD_PROF_ITEM.getOrDefault(legs, 0);
	    			cap_o.increaseTemperature((cold_prof_total+cap_o.getBaseColdProf())/10,false);		
	    		}	
			}
			else if (!player.world.isRemote && cap_o!=null && !player.isCreative()&&!player.isSpectator())
			{
				cap_o.increaseTemperature(1/3, false);
			}

		}
		
    }
	@SubscribeEvent
    public void onPlayerSufferColdOrHeatDamage(PlayerTickEvent event)
    {
		PlayerEntity player = event.player; 
		IPlayerData cap_o =  player.getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
		if (!player.world.isRemote && cap_o!=null && !player.isCreative() && !player.isSpectator())
		{
			double temp = cap_o.getTemperature();
			
			
			//heat
			if (temp>=temp_bound/2+temp_bound-1)
			{
				player.addPotionEffect(new EffectInstance(EffectInit.SCALD,Integer.MAX_VALUE,1));	
				timer=120;
			}
			else if (temp>temp_bound/2+1)
			{
				player.removePotionEffect(EffectInit.SCALD);
				timer+=1;
				if (timer >=120)
				{
					player.addPotionEffect(new EffectInstance(EffectInit.SCALD,110,0));
					timer = 0;
				}
			}
			else 
			{
				timer=120;
				player.removePotionEffect(EffectInit.SCALD);
				
			}

			//cold
			if (temp<=(-temp_bound/2-temp_bound+1))
			{
					player.addPotionEffect(new EffectInstance(EffectInit.FROST_BITE,Integer.MAX_VALUE,1));
			}
			else if (temp< -temp_bound/2)			
			{
				player.removePotionEffect(EffectInit.FROST_BITE);
				timer+=1;
				if (timer >=120)
				{
					player.addPotionEffect(new EffectInstance(EffectInit.FROST_BITE,110,0));
					timer = 0;
				}
			}
			else
			{
					timer=120;
					player.removePotionEffect(EffectInit.FROST_BITE);
			}
		}
		if (player.isCreative())
		{
			player.removePotionEffect(EffectInit.SCALD);
			player.removePotionEffect(EffectInit.FROST_BITE);
		}
    }
}
