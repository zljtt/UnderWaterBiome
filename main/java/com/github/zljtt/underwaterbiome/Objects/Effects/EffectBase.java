package com.github.zljtt.underwaterbiome.Objects.Effects;

import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class EffectBase extends Effect
{
	String name;
//	int map_loc_x;
//	int map_loc_y;
	public EffectBase(String name, EffectType typeIn, int liquidColorIn) 
	{
		super(typeIn, liquidColorIn);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		EffectInit.EFFECTS.add(this);
		this.name = name;
//		this.map_loc_x=x;
//		this.map_loc_y=y;
	}
	
	@Override
	public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha) 
	{
		gui.blit(x, y, 18, 18, 0, 0);
	}
}
