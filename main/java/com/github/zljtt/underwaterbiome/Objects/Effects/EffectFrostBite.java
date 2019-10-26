package com.github.zljtt.underwaterbiome.Objects.Effects;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class EffectFrostBite extends EffectBase
{
	int timer1 = 0;
	int next = 0;
	Random ran = new Random();
	public static int cold_posibility=40;
	
	public EffectFrostBite(String name, EffectType typeIn, int liquidColorIn) 
	{
		super(name, typeIn, liquidColorIn);
	}
	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) 
	{
		System.out.println(timer1+"/"+next);
			if (timer1 >= next)
			{
				entityLivingBaseIn.attackEntityFrom(Reference.FROST_BITE, 1);
				next = ran.nextInt(cold_posibility)+cold_posibility*2;
				timer1=0;
			}
			timer1+=1;
	}
	@Override
	public boolean isReady(int duration, int amplifier) 
	{
//	       int k = 50 >> amplifier;
//	       if (k > 0) 
//	       {
//	          return duration % k == 0;
//	       } 
//	       else 
	       {
	          return true;
	       }
	}
	
}
