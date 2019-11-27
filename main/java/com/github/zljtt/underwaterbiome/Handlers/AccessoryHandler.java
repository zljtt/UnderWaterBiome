package com.github.zljtt.underwaterbiome.Handlers;

import com.github.zljtt.underwaterbiome.Gui.GuiOverlay;
import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityFishBase;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemKnife;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.Interface.IFish;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionExpiryEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AccessoryHandler 
{
	public static int timer = 0;
	
	@SubscribeEvent
	public void onPlayerTick(LivingKnockBackEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			event = ItemAccessoryBase.testAccessoryAndEdit(event.getEntityLiving().world, (PlayerEntity)event.getEntityLiving(), event, AccessoryEntry.ON_KNOCKBACK);
		}
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		if (event.phase == TickEvent.Phase.START)
		{
			timer+=1;
			if (timer%200 == 0 && ItemAccessoryBase.testAccessory(event.player, ItemInit.ATTACK_AMPLIFER))
			{
				event.player.addPotionEffect(new EffectInstance(EffectInit.TEMP_STRENGTH, 60));
			}
			
			event = ItemAccessoryBase.testAccessoryAndEdit(event.player.world, event.player, event, AccessoryEntry.ON_TICK);
			if (timer>999999)timer=0;
		}
	}
	
	@SubscribeEvent
	public void onPlayerBeforeAttacked(LivingAttackEvent event)
	{
		//suffer damage
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player  = (PlayerEntity) event.getEntityLiving();
			event = ItemAccessoryBase.testAccessoryAndEdit(player.world, player, event, AccessoryEntry.BEFORE_ATTACKED);
		}
		//deal damage
		if (event.getSource().getTrueSource() instanceof PlayerEntity)
		{
			PlayerEntity player  = (PlayerEntity) event.getSource().getTrueSource();
			event = ItemAccessoryBase.testAccessoryAndEdit(player.world, player, event, AccessoryEntry.BEFORE_ATTACKING);
		}
	}

	@SubscribeEvent
	public void onPlayerDamageOrAttack(LivingDamageEvent event)
    {
		//suffer damage
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player  = (PlayerEntity) event.getEntityLiving();
			event = ItemAccessoryBase.testAccessoryAndEdit(player.world, player, event, AccessoryEntry.ON_SUFFER_DAMAGE);
		}
		//deal damage
		if (event.getSource().getTrueSource() instanceof PlayerEntity)
		{
			PlayerEntity player  = (PlayerEntity) event.getSource().getTrueSource();
			event = ItemAccessoryBase.testAccessoryAndEdit(player.world, player, event, AccessoryEntry.ON_ATTACK);
			GuiOverlay.last_hit = event.getAmount();
			GuiOverlay.timer=0;
			if (isFish(event.getEntity()) && player.getHeldItemMainhand().getItem() instanceof ItemKnife)
			{
				event.setAmount(event.getAmount()+2);
			}
			//test weapon
			Item item = player.getHeldItemMainhand().getItem();
			if (item.equals(ItemInit.LAVA_SWORD))
			{
				event.getSource().setFireDamage();
			}
			if (item.equals(ItemInit.WATER_SWORD))
			{
				AccessoryHandler.handleEffect(player.getEntityWorld(), player, new EffectInstance(Effects.SLOWNESS, 30, 1), ((LivingDamageEvent)event).getEntityLiving());
			}
			if (event.getSource() instanceof IndirectEntityDamageSource 
					&& ((IndirectEntityDamageSource)event.getSource()).getImmediateSource() instanceof AbstractArrowEntity)
			{
				if (item.equals(ItemInit.LAVA_BOW))
				{
					event.getSource().setFireDamage();
				}
//				if (item.equals(ItemInit.WATER_BOW))
//				{
//					AccessoryHandler.handleEffect(player.getEntityWorld(), player, new EffectInstance(Effects.SLOWNESS, 60, 1), ((LivingDamageEvent)event).getEntityLiving());
//				}
			}
		}
		
    }
	@SubscribeEvent
	public void onHeal(LivingHealEvent event)
    {
		
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player  = (PlayerEntity) event.getEntityLiving();
			event = ItemAccessoryBase.testAccessoryAndEdit(player.world, player, event, AccessoryEntry.ON_HEALING);
		}
    }
	@SubscribeEvent
	public void onEffectCheck(PotionEvent.PotionApplicableEvent event)
    {
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player  = (PlayerEntity) event.getEntityLiving();
			Result result = ItemAccessoryBase.testAccessoryAndEdit(player.world, player, event, AccessoryEntry.ON_PLAYER_CHECK_EFFECT).getResult();
		    event.setResult(result);
		}
    }
	@SubscribeEvent
	public void onEffectExpiry(PotionEvent.PotionExpiryEvent event)
    {
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player  = (PlayerEntity) event.getEntityLiving();
			ItemAccessoryBase.testAccessoryAndEdit(player.world, player, event, AccessoryEntry.ON_PLAYER_REMOVE_EFFECT);
		}
    }
	@SubscribeEvent
	public void onEffectRemove(PotionEvent.PotionRemoveEvent event)
    {
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player  = (PlayerEntity) event.getEntityLiving();
			ItemAccessoryBase.testAccessoryAndEdit(player.world, player, event, AccessoryEntry.ON_PLAYER_REMOVE_EFFECT);
		}
    }
	
	public static void handleEffect(World worldIn,PlayerEntity player,EffectInstance effect, LivingEntity entity)
	{
		EffectInstance effect_after = ItemAccessoryBase.testAccessoryAndEdit(player.world, player, new PotionEvent(entity, effect), AccessoryEntry.ON_PLAYER_ADD_EFFECT).getPotionEffect();
		entity.addPotionEffect(effect_after);
	}
	
	public static boolean isFish(Entity en)
	{
		return en instanceof AbstractFishEntity || en instanceof DolphinEntity || en instanceof SquidEntity || en instanceof IFish;
	}
}
