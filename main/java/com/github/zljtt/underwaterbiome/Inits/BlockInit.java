package com.github.zljtt.underwaterbiome.Inits;

import java.util.ArrayList;
import java.util.List;

import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockCabinDoor;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockFishCatcher;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockFloatingCore;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockFluorescentLampOff;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockFluorescentLampOn;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockGas;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockInvisible;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockIron;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockIronSlab;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockLifeRing;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockLime;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockLivingRoot;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockMangroveLeaf;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockMangroveLog;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockMeandrina;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockOxygenHolder;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockOxygenPlant;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockShipChest;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockShipDoor;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockWallWaterTorch;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockWaterGrass;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockWaterTorch;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockBase;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterLoggedBase;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterPlantBase;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockKelps.BlockKelp;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockKelps.BlockKelpLight;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockKelps.BlockKelpTop;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ToolType;

public class BlockInit 
{
	public static ItemGroup blockGroup = new ItemGroup(Reference.MODID+"_blocks") 
	{
        @Override
        public ItemStack createIcon() 
        {
            return new ItemStack(BlockInit.FLUORESCENT_LAMP_ON);
        }
    };
    
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Blocks
//	public static final BlockLifeRing LIFE_RING = new BlockLifeRing("life_ring",Block.Properties
//			.create(Material.MISCELLANEOUS)
//			.sound(SoundType.SLIME)
//			.hardnessAndResistance(0.5F, 1F), true, false, null);
	
	public static final BlockGas GAS = new BlockGas("gas",Block.Properties
			.create(Material.ICE)
			.sound(SoundType.STONE)
			.hardnessAndResistance(1F, 1F), false, null, null);
	
//	public static final BlockWaterPlantBase LAVA_GRASS = new BlockWaterPlantBase("lava_grass",Block.Properties
//			.create(Material.ROCK)
//			.sound(SoundType.PLANT)
//			.hardnessAndResistance(0.5F, 6F),Block.makeCuboidShape(2, 0, 2, 14, 8, 14), true);
	
	public static final BlockWaterPlantBase OXYGEN_FRUIT = new BlockOxygenPlant("oxygen_fruit_plant",Block.Properties
			.create(Material.ROCK)
			.sound(SoundType.PLANT)
			.hardnessAndResistance(0F));
	
//	public static final BlockBase STALACTITE = new BlockWaterLoggedBase("stalactite",Block.Properties
//			.create(Material.ROCK)
//			.sound(SoundType.STONE)
//			.hardnessAndResistance(3F,3F), true, false, null, null);
	
	public static final BlockBase FIRE_CORE = new BlockBase("fire_core",Block.Properties
			.create(Material.ROCK)
			.sound(SoundType.STONE)
			.hardnessAndResistance(3F,0F).lightValue(9).harvestLevel(2), true, false, null, null);
	
	public static final BlockBase WATER_CORE = new BlockBase("water_core",Block.Properties
			.create(Material.ROCK)
			.sound(SoundType.STONE)
			.hardnessAndResistance(3F,0F).harvestLevel(2), true,false, null, null);
	
	public static final BlockFishCatcher FISH_CATCHER = new BlockFishCatcher("fish_catcher",Block.Properties
			.create(Material.WOOD)
			.hardnessAndResistance(2.0F)
			.sound(SoundType.WOOD).tickRandomly(), false, null);
	
	
	public static final BlockMangroveLog MANGROVE_LOG = new BlockMangroveLog("mangrove_log",Block.Properties
			.create(Material.WOOD)
			.hardnessAndResistance(2.0F)
			.sound(SoundType.WOOD));
	
	public static final BlockMangroveLeaf MANGROVE_LEAF = new BlockMangroveLeaf("mangrove_leaf",Block.Properties
			.create(Material.LEAVES)
			.hardnessAndResistance(0.2F)
			.tickRandomly()
			.sound(SoundType.PLANT));
	
	public static final BlockWaterTorch WATER_TORCH = new BlockWaterTorch("water_torch",Block.Properties
			.create(Material.MISCELLANEOUS)
			.doesNotBlockMovement()
			.hardnessAndResistance(0.0F)
			.lightValue(14)
			.sound(SoundType.WOOD), true, BlueprintType.CHEMISTRY, 1,2);
	
	public static final BlockWallWaterTorch WALL_WATER_TORCH = new BlockWallWaterTorch("wall_water_torch", Block.Properties
			.create(Material.MISCELLANEOUS)
			.doesNotBlockMovement()
			.hardnessAndResistance(0.0F)
			.lightValue(14)
			.sound(SoundType.WOOD)
			.lootFrom(WATER_TORCH), false, null);
			
	
	public static final BlockInvisible MOVING_LIGHT_LOW = new BlockInvisible("moving_light_low",Block.Properties
			.create(Material.AIR)
			.lightValue(9));
	
	public static final BlockInvisible MOVING_LIGHT_MIDDLE = new BlockInvisible("moving_light_middle",Block.Properties
			.create(Material.AIR)
			.lightValue(12));
	
	public static final BlockInvisible MOVING_LIGHT_HIGH = new BlockInvisible("moving_light_high",Block.Properties
			.create(Material.AIR)
			.lightValue(20));
	
	public static final BlockLivingRoot LIVING_ROOT = new BlockLivingRoot("living_root",Block.Properties
			.create(Material.WOOD)
			.sound(SoundType.WOOD)
			.hardnessAndResistance(1F).harvestTool(ToolType.AXE), false, null);
	
	public static final BlockFloatingCore FLOATING_CORE = new BlockFloatingCore("floating_core",Block.Properties
			.create(Material.ROCK)
			.sound(SoundType.STONE)
			.hardnessAndResistance(2F,6F).harvestLevel(2), false, null, null);
	
	public static final BlockCabinDoor CABIN_DOOR = new BlockCabinDoor("cabin_door",Block.Properties
			.create(Material.MISCELLANEOUS)
			.sound(SoundType.METAL)
			.hardnessAndResistance(5F,6F).harvestTool(ToolType.PICKAXE), true, BlueprintType.PHYSICS, 1,2);
	
	public static final BlockShipChest SHIP_CHEST = new BlockShipChest("ship_chest",Block.Properties
			.create(Material.IRON)
			.sound(SoundType.METAL)
			.hardnessAndResistance(5F,6F).harvestTool(ToolType.PICKAXE), true, BlueprintType.PHYSICS, 1,2);
	
	public static final SlabBlock IRON_SLAB =new BlockIronSlab("iron_slab",Block.Properties
			.create(Material.IRON, MaterialColor.IRON)
			.sound(SoundType.METAL)
			.hardnessAndResistance(5.0F, 6.0F));
	
	public static final BlockShipDoor SHIP_DOOR = new BlockShipDoor("ship_door",Block.Properties
			.create(Material.IRON, MaterialColor.IRON)
			.sound(SoundType.METAL)
			.hardnessAndResistance(5.0F, 6.0F),true, BlueprintType.PHYSICS, 1,2);
	
	public static final BlockBase IRON_BLOCK = new BlockIron("advanced_iron_block",Block.Properties
			.create(Material.IRON, MaterialColor.IRON)
			.sound(SoundType.METAL)
			.hardnessAndResistance(5.0F, 6.0F), true, BlueprintType.PHYSICS, 0,1,2);
	
	public static final BlockBase LIME_STONE = new BlockBase("lime_stone",Block.Properties
			.create(Material.ROCK)
			.sound(SoundType.STONE)
			.hardnessAndResistance(1.5F,6F).harvestTool(ToolType.PICKAXE).harvestLevel(1),true, false, null, null);
	
	public static final BlockBase POLLUTED_SAND = new BlockBase("polluted_sand",Block.Properties
			.create(Material.SAND, MaterialColor.SAND)
			.sound(SoundType.SAND)
			.hardnessAndResistance(0.5F),true,false, null);
	
	public static final BlockBase REEF = new BlockBase("reef",Block.Properties
			.create(Material.ROCK)
			.sound(SoundType.STONE)
			.hardnessAndResistance(1F,8F).harvestTool(ToolType.PICKAXE),true,false, null);
	
	public static final BlockFluorescentLampOn FLUORESCENT_LAMP_ON = new BlockFluorescentLampOn("fluorescent_lamp_on", Block.Properties
			.create(Material.ROCK)
	        .sound(SoundType.STONE)
	        .hardnessAndResistance(1.0f)
	        .lightValue(15), false, null);

	public static final BlockFluorescentLampOff FLUORESCENT_LAMP_OFF = new BlockFluorescentLampOff("fluorescent_lamp_off", Block.Properties
			.create(Material.ROCK)
	        .sound(SoundType.STONE)
	        .hardnessAndResistance(1.0f), true, BlueprintType.CHEMISTRY_BIOLOGY_OCCULT,1,2);
	
	public static final BlockKelpTop KELP_TOP = new BlockKelpTop("kelp_top", Block.Properties
			.create(Material.OCEAN_PLANT).doesNotBlockMovement().tickRandomly()
			.sound(SoundType.WET_GRASS)
			.hardnessAndResistance(0));
	
	public static final BlockKelpLight KELP_LIGHT = new BlockKelpLight("kelp_light",(BlockKelpTop)KELP_TOP, Block.Properties
			.create(Material.OCEAN_PLANT, MaterialColor.GREEN).doesNotBlockMovement()
			.sound(SoundType.WET_GRASS)
			.hardnessAndResistance(0)
			.lightValue(10));
	
	public static final BlockKelp KELP = new BlockKelp("kelp",(BlockKelpTop)KELP_TOP, Block.Properties
			.create(Material.OCEAN_PLANT).doesNotBlockMovement()
			.sound(SoundType.WET_GRASS)
			.hardnessAndResistance(0));
	
	public static final BlockMeandrina MEANDRINA = new BlockMeandrina("meandrina",Block.Properties
			.create(Material.OCEAN_PLANT)
			.sound(SoundType.CORAL)
			.hardnessAndResistance(0.5F, 6.0F));
	
	public static final BlockLime LIME = new BlockLime("lime",Block.Properties
			.create(Material.ROCK)
			.sound(SoundType.STONE)
			.hardnessAndResistance(0.5F,6F).harvestTool(ToolType.PICKAXE).harvestLevel(1),false, null);
	
	public static final BlockOxygenHolder OXYGEN_HOLDER = new BlockOxygenHolder("oxygen_holder",Block.Properties
			.create(Material.IRON)
			.sound(SoundType.METAL)
			.hardnessAndResistance(2F,6F).harvestTool(ToolType.PICKAXE), true, BlueprintType.ALL, 0,1,2,3,4);
	
	public static final BlockWaterPlantBase WATER_GRASS_RED = new BlockWaterGrass("water_grass_red",Block.Properties
					.create(Material.OCEAN_PLANT)
					.sound(SoundType.WET_GRASS)
					.hardnessAndResistance(0F));
	
	public static final BlockWaterPlantBase WATER_GRASS_GREEN = new BlockWaterGrass("water_grass_green",Block.Properties
			.create(Material.OCEAN_PLANT)
			.sound(SoundType.WET_GRASS)
			.hardnessAndResistance(0F));
	
	
}
