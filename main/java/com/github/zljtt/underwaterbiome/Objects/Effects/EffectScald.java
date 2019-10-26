package com.github.zljtt.underwaterbiome.Objects.Effects;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class EffectScald extends EffectBase
{
	int timer1 = 0;
	int next = 0;
	Random ran = new Random();
	public static int heat_posibility=40;
	
	public EffectScald(String name, EffectType typeIn, int liquidColorIn) 
	{
		super(name, typeIn, liquidColorIn);
	}
	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) 
	{
			if (timer1 >= next)
			{
				entityLivingBaseIn.attackEntityFrom(Reference.SCALD, 1);
				next = ran.nextInt(heat_posibility)+heat_posibility*2;
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
