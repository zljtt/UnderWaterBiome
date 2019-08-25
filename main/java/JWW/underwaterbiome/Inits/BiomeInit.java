package JWW.underwaterbiome.Inits;


import java.util.ArrayList;
import java.util.List;

import JWW.underwaterbiome.World.Biomes.BiomeLightingSeaweed;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;

public class BiomeInit 
{
	public static final List<Biome> BIOMES = new ArrayList<Biome>();

	public static final Biome LIGHTING_SEAWEED = new BiomeLightingSeaweed();
	
	public static void registerBiomes(RegistryEvent.Register<Biome> event)
	{
		
		initBiome(event, LIGHTING_SEAWEED, "lighting_seaweed", BiomeType.COOL, Type.OCEAN);

	}
	
	private static Biome initBiome(RegistryEvent.Register<Biome> event, Biome biome, String name, BiomeType biomeType, Type... types)
	{
		BIOMES.add(biome);
		biome.setRegistryName(name);
		event.getRegistry().register(LIGHTING_SEAWEED);		
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		return biome;
	}
}
