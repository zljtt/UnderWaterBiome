package com.github.zljtt.underwaterbiome.Objects.Biomes;

import com.github.zljtt.underwaterbiome.Inits.FeatureInit;
import com.github.zljtt.underwaterbiome.Objects.Features.Configs.WreckageConfig;
import com.github.zljtt.underwaterbiome.Utils.Enum.Difficulty;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BiomeOceanBase extends Biome
{
	private int weight;
	private int pollution;
	protected BiomeOceanBase(SurfaceBuilderConfig config, float depth, float scale,int color, String parent, int weight, Difficulty pollution,float temp) 
	{
		super((new Biome.Builder())
	    		  .surfaceBuilder(SurfaceBuilder.DEFAULT, config)
	    		  .precipitation(Biome.RainType.RAIN)
	    		  .temperature(temp)
	    		  .downfall(0.5F)
	    		  .category(Biome.Category.OCEAN)
	    		  .depth(depth)
	    		  .scale(scale)
	    		  .waterColor(color)
	    		  .waterFogColor(color)
	    		  .parent(parent));
		
		this.weight=weight;
		this.pollution = pollution.getDifficulty();
		
	    this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(FeatureInit.SPACECRAFT_WRECKAGE, new WreckageConfig(this.getPollution()*10+25, this.getPollution(), this.getPollution()), Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(25)));

	    //normal ocean setting
	      DefaultBiomeFeatures.addOres(this);
	      DefaultBiomeFeatures.addStoneVariants(this);
	      DefaultBiomeFeatures.addMonsterRooms(this);
	      this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, Biome.createDecoratedFeature(Feature.MINESHAFT, new MineshaftConfig((double)0.004F, MineshaftStructure.Type.NORMAL), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
	   
	}
	public int getWeight()
	{
		return weight;
	}
	public int getPollution()
	{
		return  pollution;
	}

}
