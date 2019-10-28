package com.github.zljtt.underwaterbiome.World;

import java.util.function.LongFunction;

import com.google.common.collect.ImmutableList;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.VoroniZoomLayer;
import net.minecraft.world.gen.layer.ZoomLayer;

public class LayerUtilWaterWorld 
{
	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createBiomeFactory(IAreaFactory<T> landFactory, LongFunction<C> contextFactory)
    {
        IAreaFactory<T> biomeFactory = GenLayerWaterWorldBiome.INSTANCE.apply(contextFactory.apply(200L));
        // magnify the biome layer
        biomeFactory = LayerUtil.repeat(1000L, ZoomLayer.FUZZY, biomeFactory, 2, contextFactory);
        return biomeFactory;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> ImmutableList<IAreaFactory<T>> createAreaFactories(WorldType worldType, OverworldGenSettings settings, LongFunction<C> contextFactory)
    {
        int biomeSize = 2;

        IAreaFactory<T> oceanFactory = GenLayerWaterWorldBiome.INSTANCE.apply(contextFactory.apply(1L));

        // Allocate the biomes
        IAreaFactory<T> biomesFactory = createBiomeFactory(oceanFactory, contextFactory);
        
        // Zoom more based on the biome size
        for (int i = 0; i < biomeSize; ++i)
        {
            biomesFactory = ZoomLayer.NORMAL.apply(contextFactory.apply((long)(1000 + i)), biomesFactory);
        }

        biomesFactory = SmoothLayer.INSTANCE.apply(contextFactory.apply(1000L), biomesFactory);

        // Finish biomes with Voroni zoom
        IAreaFactory<T> voroniZoomBiomesFactory = VoroniZoomLayer.INSTANCE.apply(contextFactory.apply(10L), biomesFactory);
        return ImmutableList.of(biomesFactory, voroniZoomBiomesFactory, biomesFactory);
    }

    public static Layer[] createGenLayers(long seed, WorldType worldType, OverworldGenSettings settings)
    {
        ImmutableList<IAreaFactory<LazyArea>> factoryList = createAreaFactories(worldType, settings, (seedModifier) ->
        {
            return new LazyAreaLayerContextWaterWorld(1, seed, seedModifier);
        });
        Layer biomesLayer = new Layer(factoryList.get(0));
        Layer voroniZoomBiomesLayer = new Layer(factoryList.get(1));
        Layer biomesLayer2 = new Layer(factoryList.get(2));
        return new Layer[]{biomesLayer, voroniZoomBiomesLayer, biomesLayer2};
    }
}
