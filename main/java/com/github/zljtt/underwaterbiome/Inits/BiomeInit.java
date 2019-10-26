package com.github.zljtt.underwaterbiome.Inits;


import java.util.ArrayList;
import java.util.List;

import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeBrokenCanyon;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeCoralReef;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeCoralTree;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeFloatingland;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeLavaRange;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeLightingKelp;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeNormalKelp;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeRootyTentacle;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Enum.Difficulty;

import net.minecraft.util.ResourceLocation;
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
	
	public static final Biome CORAL_REEF = new BiomeCoralReef(-0.6F, 0.01F, 0x12c3dd,(String)null, 3, Difficulty.EASY);//5
		public static final Biome CORAL_TREE = new BiomeCoralTree(-0.8F, 0.01F, 0x12c3dd,"coral_reef", 2, Difficulty.NORMAL);//5
		
	public static final Biome NORMAL_SEAWEED = new BiomeNormalKelp(-1.6F, 0.1F, 0x0AAA8E,(String)null, 5, Difficulty.NORMAL);//5
		public static final Biome LIGHTING_SEAWEED = new BiomeLightingKelp(-1.9F, 0.1F, 0x0AAA70,"normal_seaweed", 5,Difficulty.DIFFICULT);//5
		
	public static final Biome FLOATING_ISLAND = new BiomeFloatingland(-1.8F, 0.2F, 0x53A1FC,(String)null, 4, Difficulty.NORMAL);//
	
	public static final Biome BROKEN_CANYAN = new BiomeBrokenCanyon(-1F, 0.1F, 0x125C89,(String)null, 5, Difficulty.NORMAL);//5
		public static final Biome LAVA_RANGE = new BiomeLavaRange(-1.9F, 0.02F, 0x65599A,"broken_canyon", 2, Difficulty.DIFFICULT);//5
		public static final Biome ROOTY_TANTACLE = new BiomeRootyTentacle(-1.5F, 0.07F, 0xDFFF7F,"broken_canyon", 2, Difficulty.DIFFICULT);//5


	public static void registerBiomes(RegistryEvent.Register<Biome> event)
	{
		initBiome(event, CORAL_REEF, "coral_reef", BiomeType.WARM, Type.OCEAN);
		initBiome(event, CORAL_TREE, "coral_tree", BiomeType.WARM, Type.OCEAN);
		initBiome(event, LIGHTING_SEAWEED, "lighting_seaweed", BiomeType.WARM, Type.OCEAN);
		initBiome(event, NORMAL_SEAWEED, "normal_seaweed", BiomeType.WARM, Type.OCEAN);
		
		initBiome(event, ROOTY_TANTACLE, "rooty_tantacle", BiomeType.COOL, Type.OCEAN);
		initBiome(event, FLOATING_ISLAND, "floating_island", BiomeType.COOL, Type.OCEAN);
		initBiome(event, BROKEN_CANYAN, "broken_canyan", BiomeType.COOL, Type.OCEAN);
		initBiome(event, LAVA_RANGE, "lava_range", BiomeType.COOL, Type.OCEAN);
		


	}
	
	private static Biome initBiome(RegistryEvent.Register<Biome> event, Biome biome, String name, BiomeType biomeType, Type... types)
	{
		BIOMES.add(biome);
		biome.setRegistryName(new ResourceLocation(Reference.MODID,name));
		event.getRegistry().register(biome);	
		
		
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		return biome;
	}
}
