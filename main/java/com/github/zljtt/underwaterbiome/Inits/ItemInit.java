package com.github.zljtt.underwaterbiome.Inits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Items.ItemBluePrint;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemBlueprintFragment;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemFuel;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemGravityGun;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemHeatingPad;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemPaintBrush;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemPsycheGun;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemStagnationGun;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemWaterBow;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemWaterPump;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemEggBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;

import net.minecraft.block.Block;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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
	public static final List<Item> ITEMS = new ArrayList<Item>();
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
	public static final Item WATER_BOW = new ItemWaterBow("water_bow", new Item.Properties()
			.group(ItemInit.itemGroup).maxStackSize(1),false,null);
	//Items-ingredient
	public static final Item SEAWEED_FRUIT = new ItemBase("seaweed_fruit", new Item.Properties()
			.group(ItemInit.itemGroup).food((new Food.Builder()).hunger(2+ran.nextInt()).saturation(0.1F).build()),false,null);
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
	
	
	//egg
	public static final Item SHARK_EGG = new ItemEggBase("shark_egg", EntityInit.ENTITY_SHARK, 0xB5FCF0, 0x127EFC);
	public static final Item CONCH_EGG = new ItemEggBase("conch_egg", EntityInit.ENTITY_CONCH, 0xCADB10, 0x105DDB);
	public static final Item CONCH_SEA_GRASS_EGG = new ItemEggBase("conch_sea_grass_egg", EntityInit.ENTITY_CONCH_SEA_GRASS, 0x0EB70B, 0x105DDB);
	public static final Item FISH_STURGEON_EGG = new ItemEggBase("fish_sturgeon_egg", EntityInit.ENTITY_FISH_STURGEON, 0x359BB5, 0x127EFC);
	public static final Item RAY_EGG = new ItemEggBase("ray_egg", EntityInit.ENTITY_RAY, 0xB4E4EB, 0xB8C1C2);

	//others
	public static Item BLUE_PRINT_TEMPERATURE_METER;
	public static final Item BLUEPRINT_FRAGMENT_CHEMISTRY = new ItemBlueprintFragment("blueprint_fragment_chemistry", new Item.Properties()
			.group(ItemInit.itemGroup),"chemistry");
	public static final Item BLUEPRINT_FRAGMENT_BIOLOGY = new ItemBlueprintFragment("blueprint_fragment_biology", new Item.Properties()
			.group(ItemInit.itemGroup),"biology");
	public static final Item BLUEPRINT_FRAGMENT_PHYSICS = new ItemBlueprintFragment("blueprint_fragment_physics", new Item.Properties()
			.group(ItemInit.itemGroup),"physics");
	public static final Item BLUEPRINT_FRAGMENT_OCCULT = new ItemBlueprintFragment("blueprint_fragment_occult", new Item.Properties()
			.group(ItemInit.itemGroup),"occult");
	//blueprint
//	public static final Item BLUE_PRINT_TEMPERATURE_METER = new ItemBluePrint("temperature_meter", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_PRESSURE_METER = new ItemBluePrint("pressure_meter", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_SINGLE_OXYGEN_TANK = new ItemBluePrint("single_oxygen_tank", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_DOUBLE_OXYGEN_TANK = new ItemBluePrint("double_oxygen_tank", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_TORCH_FAR = new ItemBluePrint("torch_far", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_TORCH_NEAR = new ItemBluePrint("torch_near", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_WATER_PUMP = new ItemBluePrint("water_pump", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_PSYCHE_GUN = new ItemBluePrint("psyche_gun", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_GRAVITY_GUN_ATTRACTIVE = new ItemBluePrint("gravity_gun_attractive", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_OXYGEN_HOLDER = new ItemBluePrint("oxygen_holder", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_ADVANCED_IRON_BLOCK = new ItemBluePrint("advanced_iron_block", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_FLUORESCENT_LAMP_OFF = new ItemBluePrint("fluorescent_lamp_off", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_CABIN_DOOR = new ItemBluePrint("cabin_door", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_PERPETUAL_MOTION_CORE = new ItemBluePrint("cabin_door", new Item.Properties()
//			.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_ = new ItemBluePrint("", new Item.Properties()
//	.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_ = new ItemBluePrint("", new Item.Properties()
//	.group(ItemInit.itemGroup).maxStackSize(1));
//	public static final Item BLUE_PRINT_ = new ItemBluePrint("", new Item.Properties()
//	.group(ItemInit.itemGroup).maxStackSize(1));
	

	//armor
//	public static final ArmorItem LIME_DIVING_HELMET = new ArmorBase("lime_diving_helmet",ListArmourMaterial.LIME, EquipmentSlotType.HEAD, new Item.Properties()
//			.group(ItemInit.itemGroup));
//	public static final ArmorItem LIME_DIVING_BACKPACK = new ArmorBase("lime_diving_backpack",ListArmourMaterial.LIME, EquipmentSlotType.CHEST, new Item.Properties()
//			.group(ItemInit.itemGroup));
	
	public static void register(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
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
			if (block instanceof INeedBluePrint && ((INeedBluePrint) block).getBlueprintInfo().getNeed() == true)
			{
				Item itemtoregister = new ItemBluePrint(block.getRegistryName().getPath(), new Item.Properties()
						.group(ItemInit.blueprintGroup).maxStackSize(1));
				event.getRegistry().register(itemtoregister);
				ItemInit.BLUEPRINT.add(itemtoregister);
				ItemInit.BLUEPRINT_MAP.put(new ItemStack(itemtoregister), ((INeedBluePrint) block).getBlueprintInfo());
			}
		}
		System.out.println("register blueprints: " + ItemInit.BLUEPRINT.toString());
	}
}
