package com.github.zljtt.underwaterbiome.Handlers;

import com.github.zljtt.underwaterbiome.Client.LootTables.FunctionRandomBlueprint;
import com.github.zljtt.underwaterbiome.Client.LootTables.FunctionRandomBlueprintFragment;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;

public class LootTableHandler 
{
	   public static final ResourceLocation RESEARCH_STATION_DEFAULT = new ResourceLocation(Reference.MODID, "chests/default");

	   public static final ResourceLocation RESEARCH_STATION_AQUARIUM_0 = new ResourceLocation(Reference.MODID, "chests/research_station_aquarium_0");
	   public static final ResourceLocation RESEARCH_STATION_AQUARIUM_1 = new ResourceLocation(Reference.MODID, "chests/research_station_aquarium_1");
	   public static final ResourceLocation RESEARCH_STATION_AQUARIUM_2 = new ResourceLocation(Reference.MODID, "chests/research_station_aquarium_2");

	   public static final ResourceLocation RESEARCH_STATION_LAB_0 = new ResourceLocation(Reference.MODID, "chests/research_station_lab_0");
	   public static final ResourceLocation RESEARCH_STATION_LAB_1 = new ResourceLocation(Reference.MODID, "chests/research_station_lab_1");
	   public static final ResourceLocation RESEARCH_STATION_LAB_2 = new ResourceLocation(Reference.MODID, "chests/research_station_lab_2");

	   public static final ResourceLocation RESEARCH_STATION_STORAGE_EQUIPMENT = new ResourceLocation(Reference.MODID, "chests/research_station_storage_equipment");
	   public static final ResourceLocation RESEARCH_STATION_STORAGE_RESOURCE = new ResourceLocation(Reference.MODID, "chests/research_station_storage_resource");
	   public static final ResourceLocation RESEARCH_STATION_STORAGE_FOOD = new ResourceLocation(Reference.MODID, "chests/research_station_storage_food");

	   public static final ResourceLocation RESEARCH_STATION_SMELT_0 = new ResourceLocation(Reference.MODID, "chests/research_station_smelt_0");
	   public static final ResourceLocation RESEARCH_STATION_SMELT_1 = new ResourceLocation(Reference.MODID, "chests/research_station_smelt_1");

	   public static final ResourceLocation STARTER_1 = new ResourceLocation(Reference.MODID, "chests/starter_1");
	   public static final ResourceLocation STARTER_2 = new ResourceLocation(Reference.MODID, "chests/starter_2");
	   public static final ResourceLocation STARTER_3 = new ResourceLocation(Reference.MODID, "chests/starter_3");

	   public static void init() 
	   {		   
		   LootFunctionManager.registerFunction(new FunctionRandomBlueprint.Serializer());
		   LootFunctionManager.registerFunction(new FunctionRandomBlueprintFragment.Serializer());
	   }

}
