package com.github.zljtt.underwaterbiome.Objects.Biomes;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.EntityInit;
import com.github.zljtt.underwaterbiome.Inits.FeatureInit;
import com.github.zljtt.underwaterbiome.Utils.Enum.Difficulty;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.BlockWithContextConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SeaGrassConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinConfig;
import net.minecraft.world.gen.feature.structure.OceanRuinStructure;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;



public class BiomeDeadCoral extends BiomeOceanBase
{
    Random rand =  new Random();

	public BiomeDeadCoral(float depth, float scale,int color, String parent, int weight,Difficulty pollution) 
	{
	      super(new SurfaceBuilderConfig(
	    						  Blocks.SAND.getDefaultState(), 
	    						  Blocks.SAND.getDefaultState(), 
	    						  Blocks.SAND.getDefaultState()), depth, scale, color, parent, weight, pollution,0.7F);
	      
	      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.OCEAN_RUIN, new OceanRuinConfig(OceanRuinStructure.Type.COLD, 0.3F, 0.9F), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));

	      
	      this.addCarver(GenerationStage.Carving.LIQUID, Biome.createCarver(WorldCarver.UNDERWATER_CAVE, new ProbabilityConfig(0.4F)));

	      //coral
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureInit.DEAD_CORAL, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_TOP_SOLID, new FrequencyConfig(4)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureInit.BONE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(1)));
	      //
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SEAGRASS, new SeaGrassConfig(80, 0.8D), Placement.TOP_SOLID_HEIGHTMAP, IPlacementConfig.NO_PLACEMENT_CONFIG));
	      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(FeatureInit.LIME, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(3)));
	      //underground
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SIMPLE_BLOCK, new BlockWithContextConfig(Blocks.SEA_PICKLE.getDefaultState().with(SeaPickleBlock.PICKLES, Integer.valueOf(rand.nextInt(4) + 1)), new BlockState[]{Blocks.STONE.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}), Placement.CARVING_MASK, new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.02F)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SIMPLE_BLOCK, new BlockWithContextConfig(Blocks.SEAGRASS.getDefaultState(), new BlockState[]{Blocks.STONE.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}), Placement.CARVING_MASK, new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.1F)));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SIMPLE_BLOCK, new BlockWithContextConfig(BlockInit.LIME.getStateWithRandomDirection(), new BlockState[]{Blocks.STONE.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}), Placement.CARVING_MASK, new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.03F)));

	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SQUID, 3, 1, 4));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.COD, 15, 3, 6));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.TROPICAL_FISH, 15, 5, 10));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.DOLPHIN, 2, 1, 2));
	      this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityInit.ENTITY_FISH_STURGEON, 2, 1, 1));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityInit.ENTITY_WATER_SKELETON, 2, 1, 1));


	      
	}    
}
