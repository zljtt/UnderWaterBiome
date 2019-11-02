package com.github.zljtt.underwaterbiome.Objects.Items.Accessory;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Handlers.AccessoryHandler;
import com.github.zljtt.underwaterbiome.Handlers.NetworkingHandler;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase.ObtainType;
import com.github.zljtt.underwaterbiome.Objects.Messages.MessageParticleEffect;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.IGun;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class ItemMagicThrough extends ItemAccessoryBase
{
	Random ran = new Random();
	public ItemMagicThrough(String name, Properties property, ObtainType ob, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, property, ob, needBlueprint, type, difficulty);
	}

	@Override
	public AccessoryEntry getType() 
	{
		return AccessoryEntry.ON_ATTACK;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Event> T editEffect(World worldIn, PlayerEntity player, T target) 
	{
		if (target instanceof LivingDamageEvent)
		{
			LivingDamageEvent event = (LivingDamageEvent)target;
			if(event.getSource().isMagicDamage())
			{
				if (ran.nextInt(100)<20)
				{
					NetworkingHandler.sendToServer(new MessageParticleEffect("enchant"));
					((LivingDamageEvent) target).setAmount(((LivingDamageEvent) target).getAmount()+10);
				}
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
