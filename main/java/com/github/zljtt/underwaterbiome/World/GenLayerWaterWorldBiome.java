package com.github.zljtt.underwaterbiome.World;


import java.util.ArrayList;
import java.util.Iterator;

import com.github.zljtt.underwaterbiome.Inits.BiomeInit;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeOceanBase;
import com.google.common.collect.Lists;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum GenLayerWaterWorldBiome implements IAreaTransformer0, IDimOffset0Transformer
{
	INSTANCE; 
    @SuppressWarnings("deprecation")
	@Override
    public int apply(INoiseRandom context, int x, int z)
    {
        return Registry.BIOME.getId(getRandomOceanBiome(context, BiomeInit.CORAL_REEF));
    }
    
    private int totalBiomesWeight = 0;
    private ArrayList<WeightedBiomeEntry> oceanBiomes = Lists.newArrayList();

    
    
    final WeightedBiomeEntry ROOTY_TANTACLE = createWeightedBiomeEntry(((BiomeOceanBase)BiomeInit.ROOTY_TANTACLE).getWeight(),BiomeInit.ROOTY_TANTACLE);
    final WeightedBiomeEntry NORMAL_SEAWEED = createWeightedBiomeEntry(((BiomeOceanBase)BiomeInit.NORMAL_SEAWEED).getWeight(),BiomeInit.NORMAL_SEAWEED);
    final WeightedBiomeEntry LIGHTING_SEAWEED = createWeightedBiomeEntry(((BiomeOceanBase)BiomeInit.LIGHTING_SEAWEED).getWeight(),BiomeInit.LIGHTING_SEAWEED);
    final WeightedBiomeEntry BROKEN_CANYAN = createWeightedBiomeEntry(((BiomeOceanBase)BiomeInit.BROKEN_CANYAN).getWeight(),BiomeInit.BROKEN_CANYAN);
    final WeightedBiomeEntry FLOATING_ISLAND = createWeightedBiomeEntry(((BiomeOceanBase)BiomeInit.FLOATING_ISLAND).getWeight(),BiomeInit.FLOATING_ISLAND);
    final WeightedBiomeEntry LAVA_RANGE = createWeightedBiomeEntry(((BiomeOceanBase)BiomeInit.LAVA_RANGE).getWeight(),BiomeInit.LAVA_RANGE);
    final WeightedBiomeEntry CORAL_REEF = createWeightedBiomeEntry(((BiomeOceanBase)BiomeInit.CORAL_REEF).getWeight(),BiomeInit.CORAL_REEF);
    final WeightedBiomeEntry CORAL_TREE = createWeightedBiomeEntry(((BiomeOceanBase)BiomeInit.CORAL_TREE).getWeight(),BiomeInit.CORAL_TREE);

//    final WeightedBiomeEntry DEEP_OCEAN = createWeightedBiomeEntry(10,Biomes.DEEP_OCEAN);
//    final WeightedBiomeEntry LUKEWARM_OCEAN = createWeightedBiomeEntry(10,Biomes.LUKEWARM_OCEAN);

    public Biome getRandomOceanBiome(INoiseRandom context, Biome fallback)
    {
    	if (this.totalBiomesWeight == 0)
            return fallback;

        int weight = context.random(this.totalBiomesWeight);
        Iterator<WeightedBiomeEntry> iterator = this.oceanBiomes.iterator();
        WeightedBiomeEntry item;
        do
        {
            item = iterator.next();
            weight -= item.weight;
        }
        while (weight >= 0);
        return item.biome;
    }
    private WeightedBiomeEntry createWeightedBiomeEntry(int weight, Biome biome)
    {
    	WeightedBiomeEntry entry = new WeightedBiomeEntry(weight, biome);
    	totalBiomesWeight += weight;
    	oceanBiomes.add(entry);
    	return entry;
    }
    
    
    public static class WeightedBiomeEntry
    {
        public final int weight;
        public final Biome biome;

        public WeightedBiomeEntry(int weight, Biome biome)
        {
            this.weight = weight;
            this.biome = biome;
        }
    }
}
