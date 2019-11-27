 package com.github.zljtt.underwaterbiome.Handlers;

import com.github.zljtt.underwaterbiome.Capabilities.CapabilityOxygen;
import com.github.zljtt.underwaterbiome.Capabilities.CapabilityPlayerData;
import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityOxygenProvider;
import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Capabilities.Storage.CapabilityOxygenStorage;
import com.github.zljtt.underwaterbiome.Capabilities.Storage.CapabilityPlayerDataStorage;
import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityOxygenHolder;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.IOxygen;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler 
{
	public static final ResourceLocation OXYGEN = new ResourceLocation(Reference.MODID, "oxygen");
	public static final ResourceLocation PLAYER_DATA = new ResourceLocation(Reference.MODID, "player_data");
//	public static final ResourceLocation UNLOCKED_BIOME = new ResourceLocation(Reference.MODID, "unlocked_biome");

	public static void init()
	{
		CapabilityManager.INSTANCE.register(IPlayerData.class, new CapabilityPlayerDataStorage(),CapabilityPlayerData::new);
		CapabilityManager.INSTANCE.register(IOxygen.class, new CapabilityOxygenStorage(),CapabilityOxygen::new);
//		CapabilityManager.INSTANCE.register(IUnlockBiome.class, new CapabilityUnlockBiomeStorage(),CapabilityUnlockBiome::new);

	}
	
	@SubscribeEvent
    public void attachCapabilityPlayer(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof PlayerEntity)) return;
        event.addCapability(PLAYER_DATA, new CapabilityPlayerDataProvider());
//        event.addCapability(UNLOCKED_BIOME, new CapabilityUnlockBiomeProvider());
    }
	
    @SubscribeEvent
    public void attachCapabilityItem(AttachCapabilitiesEvent<ItemStack> event)
    {
        if (!(event.getObject() instanceof ItemStack)) return;
        if (OxygenHandler.breathableItem().contains(event.getObject().getItem()))
        {
            event.addCapability(OXYGEN, new CapabilityOxygenProvider());
        }

    }
    @SubscribeEvent
    public void attachCapabilityTileEntity(AttachCapabilitiesEvent<TileEntity> event)
    {
        if (!(event.getObject() instanceof TileEntity)) return;
        if (event.getObject() instanceof TileEntityOxygenHolder)
        {
            event.addCapability(OXYGEN, new CapabilityOxygenProvider());
        }

    }
//    @SubscribeEvent
//    public void attachCapabilityBlock(AttachCapabilitiesEvent<TileEntity> event)
//    {
//        if (!(event.getObject() instanceof TileEntity)) return;
//
//        event.addCapability(PARTICLEBLOCK, new ParticleBlockProvider());
//
//    }
//    @SubscribeEvent
//    public void attachCapabilityPlayer(AttachCapabilitiesEvent<EntityPlayer> event)
//    {
//        if (!(event.getObject() instanceof EntityPlayer)) return;
//
//        event.addCapability(PLAYERINFO, new PlayerInfoProvider());
//
//    }
}
