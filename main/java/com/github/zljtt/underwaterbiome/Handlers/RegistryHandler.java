 package com.github.zljtt.underwaterbiome.Handlers;

import com.github.zljtt.underwaterbiome.Gui.GuiOverlay;
import com.github.zljtt.underwaterbiome.Inits.BiomeInit;
import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import com.github.zljtt.underwaterbiome.Inits.EntityInit;
import com.github.zljtt.underwaterbiome.Inits.FeatureInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Inits.TileEntityInit;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.potion.Effect;
import net.minecraft.resources.ResourcePackInfo.Priority;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHandler 
{
	static Minecraft mc = Minecraft.getInstance();

	@SubscribeEvent//(priority = EventPriority.HIGHEST)
	public static void registerBlock(final RegistryEvent.Register<Block> event)
	{ 
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void registerItem(final RegistryEvent.Register<Item> event)
	{
		ItemInit.register(event);
	}
	
	@SubscribeEvent
	public static void registerTileEntity(final RegistryEvent.Register<TileEntityType<?>> event)
	{ 
		TileEntityInit.init(event);
	}
	@SubscribeEvent
	public static void registerEntity(final RegistryEvent.Register<EntityType<?>> event)
	{ 
		EntityInit.init(event);
	}
	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> event)
	{		
    	FeatureInit.registerFeatures();
		BiomeInit.registerBiomes(event);
	}
	@SubscribeEvent
	public static void registerEffects(final RegistryEvent.Register<Effect> event)
	{		
		event.getRegistry().registerAll(EffectInit.EFFECTS.toArray(new Effect[0]));
	}
	@SubscribeEvent
	public static void registerRecipes(final RegistryEvent.Register<IRecipeSerializer<?>> event)
	{		
		CraftingHandler.register(event);
	}

	
	public static void preInitRegistries()
	{    
		CapabilityHandler.init();
		
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        LootTableHandler.init();
		NetworkingHandler.init();	
		TriggerHandler.init();
        MinecraftForge.EVENT_BUS.register(new GuiOverlay());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new KnowledgePointHandler());
        MinecraftForge.EVENT_BUS.register(new TemperatureHandler());
        MinecraftForge.EVENT_BUS.register(new OxygenHandler());
        MinecraftForge.EVENT_BUS.register(new AccessoryHandler());

       
	}
	
	public static void initRegistries()
	{
		
	}
	public static void postInitRegistries() 
	{
	}	
	public static void serverInitRegistries(FMLServerStartingEvent event)
	{
	}
	public static void clientRegistries() 
	{
		EntityInit.initRender();		
	}
}
