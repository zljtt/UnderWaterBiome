package com.github.zljtt.underwaterbiome.Utils;

import java.util.HashMap;
import java.util.Map;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Enum.BreathableItem;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;

public class Reference  
{
	public static final String MODID = "underwaterbiome";
	public static final String NAME = "Underwater Biome";
	public static final String VERSION = "MOD 0.1.0 - MC 1.14";
	
	public static final Direction[] DIRECTIONS = new Direction[] {Direction.NORTH,Direction.EAST,Direction.SOUTH,Direction.WEST,Direction.DOWN,Direction.UP};
	public static final Direction[] HORIZONTAL_DIRECTIONS = new Direction[] {Direction.NORTH,Direction.EAST,Direction.SOUTH,Direction.WEST};
	public static final Item[] BLUEPRINT_FRAGMENTS = new Item[] {ItemInit.BLUEPRINT_FRAGMENT_CHEMISTRY,ItemInit.BLUEPRINT_FRAGMENT_BIOLOGY,ItemInit.BLUEPRINT_FRAGMENT_PHYSICS,ItemInit.BLUEPRINT_FRAGMENT_OCCULT};

	public static Map<Item, Integer> COLD_PROF_ITEM = new HashMap<Item, Integer>();
	public static Map<Item, Integer> HEAT_PROF_ITEM = new HashMap<Item, Integer>();
	public static Map<String, Integer> TECH_POINT_MAP = new HashMap<String, Integer>();
	public static final DamageSource FROST_BITE = new DamageSource("frost_bite"); 
	public static final DamageSource SCALD = new DamageSource("scald");
	public static final DamageSource WATER_CURSE = new DamageSource("water_curse");

	public static final BreathableItem[] BREATHABLEITEM = new BreathableItem[] {BreathableItem.DOUBLE_OXYGEN_TANK,BreathableItem.SINGLE_OXYGEN_TANK};

	public static void init()
	{
//		BREATHABLEITEM =  ;
		
		initColdProfMap(COLD_PROF_ITEM);
		initHeatProfMap(HEAT_PROF_ITEM);
		initTechPointMap(TECH_POINT_MAP);

	}
	private static void initTechPointMap(Map<String, Integer> map) 
	{
		map.put("advanced_fuel", 5);
		map.put("advanced_fuel", 5);
		map.put("advanced_fuel", 5);
		map.put("advanced_fuel", 5);
		map.put("advanced_fuel", 5);
		map.put("advanced_fuel", 5);
		map.put("advanced_fuel", 5);
		map.put("advanced_fuel", 5);
		map.put("advanced_fuel", 5);
		map.put("advanced_fuel", 5);

	}
	public static void initColdProfMap(Map<Item, Integer> map)
	{
		map.put(Items.IRON_BOOTS, -1);
		map.put(Items.IRON_CHESTPLATE, -1);
		map.put(Items.IRON_HELMET, -1);
		map.put(Items.IRON_LEGGINGS, -1);
		
		map.put(Items.GOLDEN_BOOTS, -2);
		map.put(Items.GOLDEN_CHESTPLATE, -2);
		map.put(Items.GOLDEN_HELMET, -2);
		map.put(Items.GOLDEN_LEGGINGS, -2);
		
		map.put(Items.DIAMOND_BOOTS, 3);
		map.put(Items.DIAMOND_CHESTPLATE, 3);
		map.put(Items.DIAMOND_HELMET, 3);
		map.put(Items.DIAMOND_LEGGINGS, 3);
		
		map.put(Items.CHAINMAIL_BOOTS, 0);
		map.put(Items.CHAINMAIL_CHESTPLATE, 0);
		map.put(Items.CHAINMAIL_HELMET, 0);
		map.put(Items.CHAINMAIL_LEGGINGS, 0);
		
		map.put(Items.LEATHER_BOOTS, 2);
		map.put(Items.LEATHER_CHESTPLATE, 2);
		map.put(Items.LEATHER_HELMET, 2);
		map.put(Items.LEATHER_LEGGINGS, 2);
	}
	public static void initHeatProfMap(Map<Item, Integer> map)
	{
		map.put(Items.IRON_BOOTS, -1);
		map.put(Items.IRON_CHESTPLATE, -1);
		map.put(Items.IRON_HELMET, -1);
		map.put(Items.IRON_LEGGINGS, -1);
		
		map.put(Items.GOLDEN_BOOTS, -2);
		map.put(Items.GOLDEN_CHESTPLATE, -2);
		map.put(Items.GOLDEN_HELMET, -2);
		map.put(Items.GOLDEN_LEGGINGS, -2);
		
		map.put(Items.DIAMOND_BOOTS, 3);
		map.put(Items.DIAMOND_CHESTPLATE, 3);
		map.put(Items.DIAMOND_HELMET, 3);
		map.put(Items.DIAMOND_LEGGINGS, 3);
		
		map.put(Items.CHAINMAIL_BOOTS, 0);
		map.put(Items.CHAINMAIL_CHESTPLATE, 0);
		map.put(Items.CHAINMAIL_HELMET, 0);
		map.put(Items.CHAINMAIL_LEGGINGS, 0);
		
		map.put(Items.LEATHER_BOOTS, 0);
		map.put(Items.LEATHER_CHESTPLATE, 0);
		map.put(Items.LEATHER_HELMET, 0);
		map.put(Items.LEATHER_LEGGINGS, 0);
	}

}
