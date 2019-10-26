package com.github.zljtt.underwaterbiome.Inits;

import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityMovingLightHigh;
import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityMovingLightLow;
import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityMovingLightMiddle;
import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityOxygenHolder;
import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityShipChest;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class TileEntityInit 
{
	
	public static final TileEntity OXYGEN_HOLDER = new TileEntityOxygenHolder();
	public static final TileEntity MOVING_LIGHT_HIGH = new TileEntityMovingLightHigh();
	public static final TileEntity MOVING_LIGHT_MIDDLE = new TileEntityMovingLightMiddle();
	public static final TileEntity MOVING_LIGHT_LOW = new TileEntityMovingLightLow();
	public static final TileEntity SHIP_CHEST = new TileEntityShipChest();

	
	public static void init(RegistryEvent.Register<TileEntityType<?>> event)
	{
        event.getRegistry().register(TileEntityType.Builder.create(TileEntityOxygenHolder::new, BlockInit.OXYGEN_HOLDER)
        		.build(null).setRegistryName(new ResourceLocation(Reference.MODID,"oxygen_holder")));
        
        event.getRegistry().register(TileEntityType.Builder.create(TileEntityMovingLightLow::new, BlockInit.MOVING_LIGHT_LOW)
        		.build(null).setRegistryName(new ResourceLocation(Reference.MODID,"moving_light_low")));
        
        event.getRegistry().register(TileEntityType.Builder.create(TileEntityMovingLightMiddle::new, BlockInit.MOVING_LIGHT_MIDDLE)
        		.build(null).setRegistryName(new ResourceLocation(Reference.MODID,"moving_light_middle")));
        
        event.getRegistry().register(TileEntityType.Builder.create(TileEntityMovingLightHigh::new, BlockInit.MOVING_LIGHT_HIGH)
        		.build(null).setRegistryName(new ResourceLocation(Reference.MODID,"moving_light_high")));
        
        event.getRegistry().register(TileEntityType.Builder.create(TileEntityShipChest::new, BlockInit.SHIP_CHEST)
        		.build(null).setRegistryName(new ResourceLocation(Reference.MODID,"ship_chest")));
	}
	
	
}
