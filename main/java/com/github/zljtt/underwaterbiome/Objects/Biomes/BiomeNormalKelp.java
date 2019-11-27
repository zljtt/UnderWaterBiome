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
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.SeaGrassConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinStructure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;


public class BiomeNormalKelp extends BiomeOceanBase
{
	public BiomeNormalKelp(float depth, float scale,int color, String parent,int weight,Difficulty pollution) 
	{
	      super(new SurfaceBuilderConfig(
	    						  Blocks.SAND.getDefaultState(), 
	    						  Blocks.SAND.getDefaultState(), 
	    						  Blocks.SAND.getDefaultState())
	    		  , depth, scale, color, parent, weight, pollution,0.5F);
	      
//	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.KELP, IFeatureConfig.NO_FEATURE_CONFIG, Placement.TOP_SOLID_HEIGHTMAP, IPlacementConfig.NO_PLACEMENT_CONFIG));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureInit.KELP, IFeatureConfig.NO_FEATURE_CONFIG, Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED, new TopSolidWithNoiseConfig(80, 120D, 0.0D, Heightmap.Type.OCEAN_FLOOR_WG)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SEAGRASS, new SeaGrassConfig(80, 0.3D), Placement.TOP_SOLID_HEIGHTMAP, IPlacementConfig.NO_PLACEMENT_CONFIG));

	      DefaultBiomeFeatures.addSedimentDisks(this);
	      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.OCEAN_MONUMENT, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
	      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.OCEAN_RUIN, new OceanRuinConfig(OceanRuinStructure.Type.COLD, 0.3F, 0.9F), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));

//	      this.addCarver(GenerationStage.Carving.LIQUID, Biome.createCarver(WorldCarver.UNDERWATER_CAVE, new ProbabilityConfig(0.03F)));
	      
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SQUID, 3, 1, 4));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.COD, 15, 3, 6));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.TROPICAL_FISH, 15, 3, 6));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.DOLPHIN, 5, 1, 2));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.DROWNED, 2, 1, 1));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityInit.ENTITY_CONCH, 2, 1, 1));

   
	}

}
