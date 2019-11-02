package com.github.zljtt.underwaterbiome.Inits;


import java.util.ArrayList;
import java.util.List;

import com.github.zljtt.underwaterbiome.Objects.Features.FeatureBone;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureDeadCoral;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureFloatingIsland;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureLavaBlock;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureLightingKelp;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureLime;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureMeandrina;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureReef;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureResearchStationWreckage;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureTantacleRoot;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureWaterGrass;
import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureInit 
{
	public static final List<Feature> FEATURES = new ArrayList<Feature>();

	public static final Feature<NoFeatureConfig> LIGHTING_KELP = new FeatureLightingKelp(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> TANTACLE_ROOT = new FeatureTantacleRoot(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> LAVA_BLOCK = new FeatureLavaBlock(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> MEANDRINA = new FeatureMeandrina(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> LIME = new FeatureLime(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> FLOATING_ISLAND = new FeatureFloatingIsland(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> REEF = new FeatureReef(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> WATER_GRASS = new FeatureWaterGrass(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> DEAD_CORAL = new FeatureDeadCoral(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> BONE = new FeatureBone(NoFeatureConfig::deserialize);

	public static final Feature<WreckageConfig> SPACECRAFT_WRECKAGE = new FeatureResearchStationWreckage(WreckageConfig::deserialize);

	
	public static void registerFeatures()
	{
		
		initFeature(LIGHTING_KELP, "lighting_kelp");
		initFeature(TANTACLE_ROOT, "tantacle_root");
		initFeature(LAVA_BLOCK, "lava_block");
		initFeature(MEANDRINA, "meandrina");
		initFeature(LIME, "lime");
		initFeature(FLOATING_ISLAND, "floating_island");
		initFeature(REEF, "reef");
		initFeature(WATER_GRASS, "water_grass");
		initFeature(SPACECRAFT_WRECKAGE, "spacecraft_wreckage");
		initFeature(DEAD_CORAL, "dead_coral");
		initFeature(BONE, "bone");

		
		
		ForgeRegistries.FEATURES.registerAll(FEATURES.toArray(new Feature[0]));
	}
	
	private static Feature initFeature(Feature feature, String name)
	{
		FEATURES.add(feature);
		feature.setRegistryName(new ResourceLocation(Reference.MODID,name));
		
		return feature;
	}
}
