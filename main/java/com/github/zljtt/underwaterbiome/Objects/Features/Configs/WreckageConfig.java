package com.github.zljtt.underwaterbiome.Objects.Features.Configs;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.world.gen.feature.IFeatureConfig;

public class WreckageConfig implements IFeatureConfig 
{
	public final int broken;
	public final int size;
	public final int difficulty;

		
	public WreckageConfig(int broken, int size, int difficulty) 
	{
			this.broken = broken;
			this.size = size;
			this.difficulty = difficulty;
	}
	
	public <T> Dynamic<T> serialize(DynamicOps<T> ops) 
	{
		return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(
				ops.createString("broken"), ops.createInt(this.broken),
				ops.createString("size"), ops.createInt(this.size),
				ops.createString("difficulty"), ops.createInt(this.difficulty)
				)));
	}
	
	public static <T> WreckageConfig deserialize(Dynamic<T> ops) 
	{
		int i = ops.get("broken").asInt(0);
		int j = ops.get("size").asInt(0);
		int l = ops.get("difficulty").asInt(0);
		return new WreckageConfig(i,j,l);
	}
}
