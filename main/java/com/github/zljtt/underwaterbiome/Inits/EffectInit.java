package com.github.zljtt.underwaterbiome.Inits;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.github.zljtt.underwaterbiome.Objects.Effects.EffectBase;
import com.github.zljtt.underwaterbiome.Objects.Effects.EffectFrostBite;
import com.github.zljtt.underwaterbiome.Objects.Effects.EffectScald;
import com.github.zljtt.underwaterbiome.Objects.Effects.EffectStun;
import com.github.zljtt.underwaterbiome.Objects.Effects.EffectWaterCurse;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class EffectInit 
{
	public static final List<Effect> EFFECTS = new ArrayList<Effect>();
	public static final Effect FROST_BITE = new EffectFrostBite("frost_bite", EffectType.HARMFUL, 0x53A1FC);
	public static final Effect SCALD = new EffectScald("scald", EffectType.HARMFUL, 0xE55010);
//	public static final Effect FROST_BITE_II = new EffectFrostBite("frost_bite_high", EffectType.HARMFUL, 0x53A1FC);
//	public static final Effect SCALD_II = new EffectScald("scald_high", EffectType.HARMFUL, 0xE55010);
	public static final Effect WATER_CURSE = new EffectWaterCurse("water_curse", EffectType.HARMFUL, 0x125C89);
	public static final Effect HIGH_PRESSURE = new EffectBase("high_pressure", EffectType.HARMFUL, Color.TRANSLUCENT);

}
