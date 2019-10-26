package com.github.zljtt.underwaterbiome.Objects.Biomes;

import com.github.zljtt.underwaterbiome.Inits.FeatureInit;
import com.github.zljtt.underwaterbiome.Utils.Enum.Difficulty;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
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



public class BiomeRootyTentacle extends BiomeOceanBase
{
	public BiomeRootyTentacle(float depth, float scale,int color, String parent,int weight,Difficulty pollution) 
	{
	      super(new SurfaceBuilderConfig(
				  Blocks.SAND.getDefaultState(), 
				  Blocks.SAND.getDefaultState(), 
				  Blocks.SAND.getDefaultState()), depth, scale, color, parent, weight, pollution, 0.5F);
	      
	      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.OCEAN_RUIN, new OceanRuinConfig(OceanRuinStructure.Type.COLD, 0.3F, 0.9F), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
	      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(FeatureInit.TANTACLE_ROOT, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_TOP_SOLID, new FrequencyConfig(5)));

	      this.addCarver(GenerationStage.Carving.LIQUID, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.3F)));
	      
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SQUID, 3, 1, 4));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.COD, 15, 3, 6));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.PUFFERFISH, 15, 5, 10));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.DROWNED, 2, 1, 1));
	      
	}    
}
