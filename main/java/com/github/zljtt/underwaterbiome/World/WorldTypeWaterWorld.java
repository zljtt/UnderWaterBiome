package com.github.zljtt.underwaterbiome.World;


import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class WorldTypeWaterWorld extends WorldType
{

	public WorldTypeWaterWorld() 
	{
		super("water_world");
	}
	@Override
	public ChunkGenerator<?> createChunkGenerator(World world) 
	{
		if (world.getDimension().getType() == DimensionType.OVERWORLD)
    	{
	        OverworldGenSettings overworldGenSettings = new OverworldGenSettings();
	        OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings();
	        biomeProviderSettings.setWorldInfo(world.getWorldInfo());
	        biomeProviderSettings.setGeneratorSettings(overworldGenSettings);
	        return new OverworldChunkGenerator(world, new BiomeProviderWaterWorld(biomeProviderSettings), overworldGenSettings);
    	}
    	else
    	{
    		return super.createChunkGenerator(world);
    	}
    }
}
