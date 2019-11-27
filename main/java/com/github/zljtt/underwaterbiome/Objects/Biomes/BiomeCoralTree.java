package com.github.zljtt.underwaterbiome.Objects.Biomes;

import com.github.zljtt.underwaterbiome.Inits.EntityInit;
import com.github.zljtt.underwaterbiome.Inits.FeatureInit;
import com.github.zljtt.underwaterbiome.Utils.Enum.Difficulty;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinStructure;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;



public class BiomeCoralTree extends BiomeOceanBase
{
	public BiomeCoralTree(float depth, float scale,int color, String parent, int weight,Difficulty pollution) 
	{
	      super(new SurfaceBuilderConfig(
	    						  Blocks.SAND.getDefaultState(), 
	    						  Blocks.SAND.getDefaultState(), 
	    						  Blocks.SANDSTONE.getDefaultState()), depth, scale, color, parent, weight, pollution,0.7F);
	      
	      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.OCEAN_RUIN, new OceanRuinConfig(OceanRuinStructure.Type.COLD, 0.3F, 0.9F), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));

	      
	      this.addCarver(GenerationStage.Carving.LIQUID, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.4F)));
	      DefaultBiomeFeatures.addSedimentDisks(this);
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.CORAL_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_TOP_SOLID, new FrequencyConfig(5)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureInit.KELP, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_TOP_SOLID, new FrequencyConfig(5)));

	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.PUFFERFISH, 15, 3, 6));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.TROPICAL_FISH, 15, 5, 10));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.DOLPHIN, 15, 1, 2));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityInit.ENTITY_FISH_STURGEON, 10, 1, 1));


	      
	}    
}
