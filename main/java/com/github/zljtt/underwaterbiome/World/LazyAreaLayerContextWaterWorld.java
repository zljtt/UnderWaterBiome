package com.github.zljtt.underwaterbiome.World;

import com.github.zljtt.underwaterbiome.Utils.Interface.IContextExtendedWaterWorld;

import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.LazyArea;

public class LazyAreaLayerContextWaterWorld extends LazyAreaLayerContext implements IContextExtendedWaterWorld<LazyArea>
{
    private long worldSeed;

    public LazyAreaLayerContextWaterWorld(int maxCacheSize, long seed, long seedModifier)
    {
        super(maxCacheSize, seed, seedModifier);
        this.worldSeed = seed;
    }

    @Override
    public long getWorldSeed()
    {
        return this.worldSeed;
    }
}

