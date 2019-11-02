package com.github.zljtt.underwaterbiome.Objects.Items.Accessory;

import com.github.zljtt.underwaterbiome.Handlers.AccessoryHandler;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase.ObtainType;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.IGun;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class ItemPrecisePart extends ItemAccessoryBase
{

	public ItemPrecisePart(String name, Properties property, ObtainType ob, boolean needBlueprint, BlueprintType type,
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
	public <T extends Event> T editEffect(World worldIn, PlayerEntity player, T target) 
	{
		if (target instanceof LivingDamageEvent)
		{
			if(player.getHeldItemMainhand().getItem() instanceof IGun)
			{
				((LivingDamageEvent) target).setAmount(((LivingDamageEvent) target).getAmount()+2);
				System.out.println("precise"+((LivingDamageEvent) target).getAmount());
				return target;
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
