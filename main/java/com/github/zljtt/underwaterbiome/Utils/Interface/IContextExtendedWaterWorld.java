package com.github.zljtt.underwaterbiome.Utils.Interface;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;

public interface IContextExtendedWaterWorld <R extends IArea> extends IExtendedNoiseRandom<R>
{
    long getWorldSeed();
}
