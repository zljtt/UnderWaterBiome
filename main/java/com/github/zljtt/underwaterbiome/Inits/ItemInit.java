package com.github.zljtt.underwaterbiome.Inits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Items.ItemBluePrint;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemBlueprintFragment;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemCreeperFish;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemElementBow;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemElementSword;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemFuel;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemGasBottle;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemGravityGun;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemHeatingPad;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemKnife;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemPaintBrush;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemPsycheGun;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemSpaceshipDivingRecorder;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemStagnationGun;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemSummon;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemWaterPump;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemArrowModify;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemAttackAmplifer;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemBloodthirstySoul;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemFlipper;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemLargeSilt;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemMagicThrough;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemMagicalGun;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemPoisonProtection;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemPoisonWeakness;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemPrecisePart;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemStickySkin;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemUndeadKiller;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemVenomContainer;
import com.github.zljtt.underwaterbiome.Objects.Items.Accessory.ItemWitherFlower;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase.ObtainType;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemElementBow.ElementType;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemEggBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedItem;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class ItemInit 
{
	/* for an Item 
	 * 1. create reference here
	 * 2. make texture
	 * 3. make item model
	 * (4. make blueprint model)
	 * <5. make advancement for the recipe>
	 * 6. put both blueprint and item into language file
	*/
	
	static Random ran = new Random();
	public static ItemGroup itemGroup = new ItemGroup(Reference.MODID+"_items") 
	{
        @Override
        public ItemStack createIcon() 
        {
            return new ItemStack(ItemInit.SEAWEED_FRUIT);
        }
    };
    public static ItemGroup blueprintGroup = new ItemGroup(Reference.MODID+"_blueprints") 
	{
        @Override
        public ItemStack createIcon() 
        {
            return new ItemStack(ItemInit.BLUE_PRINT_TEMPERATURE_METER);
        }
    };
    public static ItemGroup accessoryGroup = new ItemGroup(Reference.MODID+"_accessory") 
	{
        @Override
        public ItemStack createIcon() 
        {
            return new ItemStack(ItemInit.MAGICAL_THROUGH);
        }
    };
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final List<Item> ACCESSORY_CRAFTING = new ArrayList<Item>();
	public static final List<Item> ACCESSORY_ENTITY_DROP = new ArrayList<Item>();
	public static final List<Item> ACCESSORY_CHEST = new ArrayList<Item>();

	public static final List<Item> BLUEPRINT = new ArrayList<Item>();
	public static final Map<ItemStack, BlueprintInfo> BLUEPRINT_MAP = new HashMap<ItemStack, BlueprintInfo>();

	//Items-equipment
	public static final Item DOUBLE_OXYGEN_TANK = new ItemBase("double_oxygen_tank", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),true,BlueprintType.BIOLOGY_PHYSICS,0,1);
	public static final Item SINGLE_OXYGEN_TANK = new ItemBase("single_oxygen_tank", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),true,BlueprintType.BIOLOGY_PHYSICS,1,2);
	public static final Item MEASURING_DEVICE = new ItemBase("measuring_device", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),false, null);
	public static final Item TEMPERATURE_METER = new ItemBase("temperature_meter", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),true,BlueprintType.PHYSICS_OCCULT,0,1);
	public static final Item PRESSURE_METER = new ItemBase("pressure_meter", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),true,BlueprintType.BIOLOGY_PHYSICS,1,2);
	public static final Item WATER_PUMP = new ItemWaterPump("water_pump", new Item.Properties()
			.group(ItemInit.itemGroup),true,BlueprintType.CHEMISTRY_BIOLOGY_OCCULT,0,1);
	public static final Item TORCH_FAR = new ItemBase("torch_far", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),true,BlueprintType.ALL,0,1,2,3,4);
	public static final Item TORCH_NEAR = new ItemBase("torch_near", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),true,BlueprintType.CHEMISTRY_BIOLOGY_OCCULT,0,1);
	public static final Item PSYCHE_GUN = new ItemPsycheGun("psyche_gun", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),true,BlueprintType.ALL,0,1,2,3,4);
	public static final Item STAGNATION_GUN = new ItemStagnationGun("stagnation_gun", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),0,true,BlueprintType.ALL,0,1,2,3,4);
	public static final Item STAGNATION_GUN_TIME = new ItemStagnationGun("stagnation_gun_time", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),1,true,BlueprintType.ALL,0,1,2,3,4);
	public static final Item STAGNATION_GUN_POISON = new ItemStagnationGun("stagnation_gun_poison", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),2,true,BlueprintType.ALL,0,1,2,3,4);
	public static final Item GRAVITY_GUN_ATTRACTIVE = new ItemGravityGun("gravity_gun_attractive", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1), true,BlueprintType.ALL,0,1,2,3,4);
	public static final Item GRAVITY_GUN_REPULSIVE = new ItemGravityGun("gravity_gun_repulsive", new Item.Properties()
			.maxStackSize(1),false,null);
	public static final Item HEATING_PAD = new ItemHeatingPad("heating_pad", new Item.Properties()
			.group(ItemInit.itemGroup),true,BlueprintType.CHEMISTRY,0,1);
	public static final Item DECIPHER_DEVICE = new ItemBase("decipher_device", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),true,BlueprintType.CHEMISTRY_PHYSICS,0,1);
	public static final Item PAINT_BRUSH = new ItemPaintBrush("paint_brush", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),true,BlueprintType.CHEMISTRY_PHYSICS,0,1);
	public static final Item SPACESHIP_DRIVING_RECORDER = new ItemSpaceshipDivingRecorder("spaceship_driving_recorder", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),false,null);
	public static final Item WATER_TORCH = new WallOrFloorItem(BlockInit.WATER_TORCH, BlockInit.WALL_WATER_TORCH, (new Item.Properties())
			.group(ItemInit.itemGroup)).setRegistryName(new ResourceLocation(Reference.MODID,"water_torch"));
	public static final Item WOODEN_KNIFE = new ItemKnife("wooden_knife",ItemTier.WOOD, 1, -1.9F, (new Item.Properties())
			.group(ItemInit.itemGroup).maxStackSize(1));
	public static final Item STONE_KNIFE = new ItemKnife("stone_knife",ItemTier.STONE, 1, -1.9F, (new Item.Properties())
			.group(ItemInit.itemGroup).maxStackSize(1));
	public static final Item IRON_KNIFE = new ItemKnife("iron_knife",ItemTier.IRON, 1, -1.9F, (new Item.Properties())
			.group(ItemInit.itemGroup).maxStackSize(1));
	public static final Item GOLDEN_KNIFE = new ItemKnife("golden_knife",ItemTier.GOLD, 1, -1.9F, (new Item.Properties())
			.group(ItemInit.itemGroup).maxStackSize(1));
	public static final Item DIAMOND_KNIFE = new ItemKnife("diamond_knife",ItemTier.DIAMOND, 1, -1.9F, (new Item.Properties())
			.group(ItemInit.itemGroup).maxStackSize(1));
	public static final Item WATER_SWORD = new ItemElementSword("water_sword",ElementType.WATER, ItemTier.IRON, 4, -2.5F, (new Item.Properties())
			.group(ItemInit.itemGroup).maxStackSize(1));
	public static final Item LAVA_SWORD = new ItemElementSword("lava_sword",ElementType.LAVA,ItemTier.IRON, 4, -2.5F, (new Item.Properties())
			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item WATER_BOW = new ItemElementBow("water_bow",(new Item.Properties())
//			.group(ItemInit.itemGroup).maxStackSize(1));
	public static final Item LAVA_BOW = new ItemElementBow("lava_bow",ElementType.LAVA,(new Item.Properties())
			.group(ItemInit.itemGroup).maxStackSize(1));
	
	//Items-ingredient
	public static final Item SEAWEED_FRUIT = new ItemBase("seaweed_fruit", new Item.Properties()
			.group(ItemInit.itemGroup).food((new Food.Builder()).hunger(2+ran.nextInt()).saturation(0.1F).build()),false,null);
	public static final Item CONICAL_FLASK = new ItemBase("conical_flask", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item LIME_POWDER = new ItemBase("lime_powder", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item CHAMELEON_SKIN = new ItemBase("chameleon_skin", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item PERPETUAL_MOTION_CORE = new ItemBase("perpetual_motion_core", new Item.Properties()
			.group(ItemInit.itemGroup),true,BlueprintType.ALL,0,1,2,3,4);
	public static final Item PLANT_EXTRACT = new ItemBase("plant_extract", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item LIGHTING_EXTRACT = new ItemBase("lighting_extract", new Item.Properties()
			.group(ItemInit.itemGroup),true,BlueprintType.CHEMISTRY_BIOLOGY_OCCULT,0,1);
	public static final Item FLOATING_EXTRACT = new ItemBase("floating_extract", new Item.Properties()
			.group(ItemInit.itemGroup),true,BlueprintType.ALL,0,1,2,3,4);
	public static final Item VENOM_EXTRACT = new ItemBase("venom_extract", new Item.Properties()
			.group(ItemInit.itemGroup),true, BlueprintType.CHEMISTRY_BIOLOGY_OCCULT,1,2);
	public static final Item FUEL = new ItemFuel("fuel", new Item.Properties()
			.group(ItemInit.itemGroup),true, BlueprintType.CHEMISTRY_BIOLOGY_OCCULT,1,2);
	public static final Item PLANT_FIBER = new ItemBase("plant_fiber", new Item.Properties()
			.group(ItemInit.itemGroup),false, null);
	public static final Item PRECISION_PART = new ItemBase("precision_part", new Item.Properties()
			.group(ItemInit.itemGroup),false, null);
	public static final Item FISH_SKIN = new ItemBase("fish_skin", new Item.Properties()
			.group(ItemInit.itemGroup),false, null);
	public static final Item SHARK_FIN = new ItemBase("shark_fin", new Item.Properties()
			.group(ItemInit.itemGroup),false, null);
	public static final Item SILT = new ItemBase("silt", new Item.Properties()
			.group(ItemInit.itemGroup),false, null);
	public static final Item CORAL_STICK = new ItemBase("coral_stick", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item NEEDLE = new ItemBase("needle", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),false,null);
	public static final Item THREAD = new ItemBase("thread", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),false,null);
	public static final Item NEEDLE_AND_THREAD = new ItemBase("needle_and_thread", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),false,null);
	public static final Item SHRIMP = new ItemBase("shrimp", new Item.Properties()
			.group(ItemInit.itemGroup).food((new Food.Builder()).hunger(2+ran.nextInt()).saturation(0.3F).build()),false,null);
	public static final Item CRAB = new ItemBase("crab", new Item.Properties()
			.group(ItemInit.itemGroup).food((new Food.Builder()).hunger(2+ran.nextInt()).saturation(0.3F).build()),false,null);
	public static final Item CREEPER_FISH = new ItemCreeperFish("creeper_fish", new Item.Properties()
			.group(ItemInit.itemGroup).food((new Food.Builder()).hunger(2+ran.nextInt()).saturation(0.3F).setAlwaysEdible().build()),false,null);
	public static final Item BAIT = new ItemBase("bait", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item FIRE_ELEMENT = new ItemBase("fire_element", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item WATER_ELEMENT = new ItemBase("water_element", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item METHANE_BOTTLE = new ItemGasBottle("methane_bottle", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item WATER_BAR = new ItemBase("water_bar", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item LAVA_BAR = new ItemBase("lava_bar", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item OBSIDIAN_STICK = new ItemBase("obsidian_stick", new Item.Properties()
			.group(ItemInit.itemGroup),false,null);
	public static final Item OXYGEN_FRUIT = new ItemBase("oxygen_fruit", new Item.Properties()
			.group(ItemInit.itemGroup).food(new Food.Builder().setAlwaysEdible().effect(new EffectInstance(Effects.WATER_BREATHING, 200), 1F).build()),false,null);
	//accessory
	//loot
	public static final Item LARGE_SILT = new ItemLargeSilt("large_silt", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.ENTITY_DROP, false, null, null);//conch-finished
	public static final Item UNDEAD_KILLER = new ItemUndeadKiller("undead_killer", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.ENTITY_DROP,false, null);//skeleton-finished
	public static final Item POISON_PROTECTION = new ItemPoisonProtection("poison_protection", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.ENTITY_DROP,false, null);//skeleton-finished
	public static final Item POISON_WEAKNESS = new ItemPoisonWeakness("poison_weakness", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.ENTITY_DROP,false, null);//conch-finished
	public static final Item WITHER_FLOWER = new ItemWitherFlower("wither_flower", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.ENTITY_DROP,false, null);//drowned_boss-finished
	public static final Item STICKY_SKIN = new ItemStickySkin("sticky_skin", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.ENTITY_DROP,false, null);//fish-finished
	//craft
	public static final Item VENPM_CONTAINER = new ItemVenomContainer("venom_container", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.CRAFTING,true, BlueprintType.CHEMISTRY_BIOLOGY_OCCULT, 1,2);//crafting-finished
	public static final Item MAGICAL_GUN = new ItemMagicalGun("magical_gun", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.CRAFTING,true, BlueprintType.PHYSICS_OCCULT,0,1,2);//craft-finished
	public static final Item MAGICAL_THROUGH = new ItemMagicThrough("magical_through", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.CRAFTING,true, BlueprintType.OCCULT,0,1,2);//craft-finished
	public static final Item ATTACK_AMPLIFER = new ItemAttackAmplifer("attack_amplifer", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.CRAFTING,true, BlueprintType.ALL, 0,1,2);//fish-finished
	public static final Item ARROW_MODIFY = new ItemArrowModify("arrow_modify", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.CRAFTING,true, BlueprintType.PHYSICS, 1,2);//fish-finished
//	public static final Item FLIPPER = new ItemFlipper("flipper", new Item.Properties()
//			.group(ItemInit.accessoryGroup),ObtainType.CRAFTING,true, BlueprintType.ALL,0,1,2);
	//chest
	public static final Item PRECISE_PART = new ItemPrecisePart("precise_part", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.CHEST,false, null);// chest-finished
	public static final Item BLOODTHIRSTY_SOUL = new ItemBloodthirstySoul("bloodthirsty_soul", new Item.Properties()
			.group(ItemInit.accessoryGroup),ObtainType.CHEST,false, null);// chest-finished
	//summon
	public static final Item DROWNED_BOSS_SUMMON = new ItemSummon("drowned_boss_summon", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),EntityInit.ENTITY_DROWNED_BOSS, false, null);
	public static final Item RAY_BOSS_SUMMON = new ItemSummon("ray_boss_summon", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),EntityInit.ENTITY_RAY_BOSS, false, null);
	
	
	
	//egg
	public static final Item SHARK_EGG = new ItemEggBase("shark_egg", EntityInit.ENTITY_SHARK, 0xB5FCF0, 0x127EFC);
	public static final Item CONCH_EGG = new ItemEggBase("conch_egg", EntityInit.ENTITY_CONCH, 0xCADB10, 0x105DDB);
	public static final Item CONCH_SEA_GRASS_EGG = new ItemEggBase("conch_sea_grass_egg", EntityInit.ENTITY_CONCH_SEA_GRASS, 0x0EB70B, 0x105DDB);
	public static final Item FISH_STURGEON_EGG = new ItemEggBase("fish_sturgeon_egg", EntityInit.ENTITY_FISH_STURGEON, 0x359BB5, 0x127EFC);
	public static final Item RAY_EGG = new ItemEggBase("ray_egg", EntityInit.ENTITY_RAY, 0xB4E4EB, 0xB8C1C2);
	public static final Item WATER_SKELETON_EGG = new ItemEggBase("water_skeleton_egg", EntityInit.ENTITY_WATER_SKELETON, 0x59DF59, 0xDBDBDB);
	public static final Item CREEPER_FISH_EGG = new ItemEggBase("creeper_fish_egg", EntityInit.ENTITY_CREEPER_FISH, 0x6EBE5F, 0x5FBCBE);
	public static final Item LAVA_FISH_EGG = new ItemEggBase("lava_fish_egg", EntityInit.ENTITY_LAVA_FISH, 0xED6922, 0x000000);


	//others
	public static Item BLUE_PRINT_TEMPERATURE_METER;
	public static final Item BLUEPRINT_FRAGMENT = new ItemBlueprintFragment("blueprint_fragment", new Item.Properties()
			.group(ItemInit.itemGroup),"all");
	public static final Item BLUEPRINT_FRAGMENT_CHEMISTRY = new ItemBlueprintFragment("blueprint_fragment_chemistry", new Item.Properties()
			.group(ItemInit.itemGroup),"chemistry");
	public static final Item BLUEPRINT_FRAGMENT_BIOLOGY = new ItemBlueprintFragment("blueprint_fragment_biology", new Item.Properties()
			.group(ItemInit.itemGroup),"biology");
	public static final Item BLUEPRINT_FRAGMENT_PHYSICS = new ItemBlueprintFragment("blueprint_fragment_physics", new Item.Properties()
			.group(ItemInit.itemGroup),"physics");
	public static final Item BLUEPRINT_FRAGMENT_OCCULT = new ItemBlueprintFragment("blueprint_fragment_occult", new Item.Properties()
			.group(ItemInit.itemGroup),"occult");

	//armor
//	public static final ArmorItem LIME_DIVING_HELMET = new ArmorBase("lime_diving_helmet",ListArmourMaterial.LIME, EquipmentSlotType.HEAD, new Item.Properties()
//			.group(ItemInit.itemGroup));
//	public static final ArmorItem LIME_DIVING_BACKPACK = new ArmorBase("lime_diving_backpack",ListArmourMaterial.LIME, EquipmentSlotType.CHEST, new Item.Properties()
//			.group(ItemInit.itemGroup));
	
	public static void register(RegistryEvent.Register<Item> event)
	{
		//add additional item
		ITEMS.add(WATER_TORCH);

		for(Item item: ItemInit.ITEMS)
		{
			if (item instanceof INeedBluePrint && ((INeedBluePrint) item).getBlueprintInfo().getNeed() == true)
			{
				Item itemtoregister = new ItemBluePrint(item.getRegistryName().getPath(), new Item.Properties()
						.group(ItemInit.blueprintGroup).maxStackSize(1));
				event.getRegistry().register(itemtoregister);
				ItemInit.BLUEPRINT.add(itemtoregister);
				ItemInit.BLUEPRINT_MAP.put(new ItemStack(itemtoregister), ((INeedBluePrint) item).getBlueprintInfo());
				if (item.equals(ItemInit.TEMPERATURE_METER))BLUE_PRINT_TEMPERATURE_METER = itemtoregister;			
			}
		}
		for(Block block : BlockInit.BLOCKS)
		{
			
			if (block instanceof INeedItem && ((INeedItem) block).needItem())
			{
				ITEMS.add(new BlockItem(block, new Item.Properties().group((BlockInit.blockGroup))).setRegistryName(block.getRegistryName()));
			}
			if (block instanceof INeedBluePrint && ((INeedBluePrint) block).getBlueprintInfo().getNeed() == true)
			{ 
				Item itemtoregister = new ItemBluePrint(block.getRegistryName().getPath(), new Item.Properties()
						.group(ItemInit.blueprintGroup).maxStackSize(1));
				event.getRegistry().register(itemtoregister);
				ItemInit.BLUEPRINT.add(itemtoregister);
				ItemInit.BLUEPRINT_MAP.put(new ItemStack(itemtoregister), ((INeedBluePrint) block).getBlueprintInfo());
			}
		}
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
		System.out.println("register blueprints: " + ItemInit.BLUEPRINT.toString());
	}
}
