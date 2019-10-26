package com.github.zljtt.underwaterbiome.Objects.Biomes;

import com.github.zljtt.underwaterbiome.Inits.EntityInit;
import com.github.zljtt.underwaterbiome.Inits.FeatureInit;
import com.github.zljtt.underwaterbiome.Utils.Enum.Difficulty;
import com.google.common.collect.Lists;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinStructure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;



public class BiomeFloatingland extends BiomeOceanBase 
{
	public BiomeFloatingland(float depth, float scale,int color, String parent,int weight,Difficulty pollution) 
	{
	      super(new SurfaceBuilderConfig(
	    						  Blocks.STONE.getDefaultState(), 
	    						  Blocks.STONE.getDefaultState(), 
	    						  Blocks.SAND.getDefaultState()), depth, scale, color, parent, weight, pollution,0.5F);
	      
	      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.OCEAN_RUIN, new OceanRuinConfig(OceanRuinStructure.Type.COLD, 0.3F, 0.9F), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
	      this.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, 
	    		  Biome.createDecoratedFeature(FeatureInit.FLOATING_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_PASSTHROUGH, new ChanceConfig(5)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureInit.WATER_GRASS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.TOP_SOLID_HEIGHTMAP, IPlacementConfig.NO_PLACEMENT_CONFIG));
	      
	      this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(Blocks.GRAVEL.getDefaultState(), 6, 2, Lists.newArrayList(Blocks.SANDSTONE.getDefaultState(), Blocks.STONE.getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(1)));

	      this.addCarver(GenerationStage.Carving.LIQUID, Biome.createCarver(WorldCarver.UNDERWATER_CAVE, new ProbabilityConfig(0.3F)));

	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SQUID, 3, 1, 4));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.COD, 15, 3, 6));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SALMON, 15, 5, 10));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityInit.ENTITY_RAY, 3, 1, 1));

	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.DROWNED, 5, 1, 1));
	      
	      
	}    
}
