package com.github.zljtt.underwaterbiome.Objects.Effects;

import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class EffectWaterCurse extends EffectBase
{
	public EffectWaterCurse(String name, EffectType typeIn, int liquidColorIn) 
	{
		super(name, typeIn, liquidColorIn);
	}
	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) 
	{
		if (entityLivingBaseIn instanceof PlayerEntity)
		{
			if(!entityLivingBaseIn.isInWaterOrBubbleColumn()&&!((PlayerEntity)entityLivingBaseIn).isCreative())
			{
				entityLivingBaseIn.attackEntityFrom(Reference.WATER_CURSE, 1);
			}
		}
	}
	@Override
	public boolean isReady(int duration, int amplifier) 
	{
	       int k = 25 >> amplifier;
	       if (k > 0) 
	       {
	          return duration % k == 0;
	       } 
	       else 
	       {
	          return true;
	       }
	}
	
}
