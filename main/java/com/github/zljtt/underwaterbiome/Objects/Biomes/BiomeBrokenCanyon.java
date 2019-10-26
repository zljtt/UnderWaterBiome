package com.github.zljtt.underwaterbiome.Objects.Biomes;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.EntityInit;
import com.github.zljtt.underwaterbiome.Inits.FeatureInit;
import com.github.zljtt.underwaterbiome.Utils.Enum.Difficulty;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.BlockWithContextConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinStructure;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;



public class BiomeBrokenCanyon extends BiomeOceanBase
{
	public BiomeBrokenCanyon(float depth, float scale,int color, String parent, int weight,Difficulty pollution) 
	{
	      super(new SurfaceBuilderConfig(
	    						  Blocks.SAND.getDefaultState(), 
	    						  Blocks.SAND.getDefaultState(), 
	    						  Blocks.SAND.getDefaultState()), depth, scale, color, parent, weight,pollution,0.5F);
	      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.OCEAN_RUIN, new OceanRuinConfig(OceanRuinStructure.Type.COLD, 0.3F, 0.9F), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));

	      this.addCarver(GenerationStage.Carving.LIQUID, Biome.createCarver(WorldCarver.UNDERWATER_CANYON, new ProbabilityConfig(0.3F)));
	      this.addCarver(GenerationStage.Carving.LIQUID, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.2F)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureInit.REEF, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(1)));
	      this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(Feature.SIMPLE_BLOCK, new BlockWithContextConfig(Blocks.SEAGRASS.getDefaultState(), new BlockState[]{Blocks.STONE.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}), Placement.CARVING_MASK, new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.1F)));
	      this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(Feature.SIMPLE_BLOCK, new BlockWithContextConfig(BlockInit.LIME.getStateWithRandomDirection(), new BlockState[]{Blocks.STONE.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}), Placement.CARVING_MASK, new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.04F)));
	      

	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SQUID, 3, 1, 4));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.COD, 15, 3, 6));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SALMON, 15, 5, 10));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.DROWNED, 5, 1, 1));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityInit.ENTITY_SHARK, 1, 1, 1));
	      
	      
	}    
	
}
