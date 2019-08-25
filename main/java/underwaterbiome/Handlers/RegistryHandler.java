 package underwaterbiome.Handlers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import underwaterbiome.Inits.BiomeInit;
import underwaterbiome.Inits.BlockInit;
import underwaterbiome.Inits.ItemInit;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHandler 
{
	static Minecraft mc = Minecraft.getInstance();

	@SubscribeEvent
	public static void registerItem(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	@SubscribeEvent
	public static void registerBlock(final RegistryEvent.Register<Block> event)
	{ 
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
//		TileEntityHandler.registerTileEntities();
	}
	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> event)
	{		
		BiomeInit.registerBiomes(event);
	}
	@SubscribeEvent
	public static void registerModels(final ModelRegistryEvent event)
	{		
	}

	
	public static void preInitRegistries()
	{        
//        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    
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
}
