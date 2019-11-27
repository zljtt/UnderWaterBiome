package com.github.zljtt.underwaterbiome.Handlers;


import java.util.function.Supplier;

import com.github.zljtt.underwaterbiome.Objects.Messages.MessageBase;
import com.github.zljtt.underwaterbiome.Objects.Messages.MessageCraftReturn;
import com.github.zljtt.underwaterbiome.Objects.Messages.MessageDebug;
import com.github.zljtt.underwaterbiome.Objects.Messages.MessageEventHandler;
import com.github.zljtt.underwaterbiome.Objects.Messages.MessageOverlay;
import com.github.zljtt.underwaterbiome.Objects.Messages.MessageParticleEffect;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkingHandler
{
		private static final String PROTOCOL_VERSION = "underwater";
		private static SimpleChannel INSTANCE;

	 	public static void init()
		{
	 		int id = 0;
			INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Reference.MODID,"underwaterbiome_simplechannel"), 
				    () -> PROTOCOL_VERSION, 
				    PROTOCOL_VERSION::equals, 
				    PROTOCOL_VERSION::equals);
			
			INSTANCE.registerMessage(id++, MessageCraftReturn.class, MessageCraftReturn::encode, MessageCraftReturn::new, MessageCraftReturn::handle);
			INSTANCE.registerMessage(id++, MessageOverlay.class, MessageOverlay::encode, MessageOverlay::new, MessageOverlay::handle);
			INSTANCE.registerMessage(id++, MessageDebug.class, MessageDebug::encode, MessageDebug::new, MessageDebug::handle);
			INSTANCE.registerMessage(id++, MessageEventHandler.class, MessageEventHandler::encode, MessageEventHandler::new, MessageEventHandler::handle);
			INSTANCE.registerMessage(id++, MessageParticleEffect.class, MessageParticleEffect::encode, MessageParticleEffect::new, MessageParticleEffect::handle);
		}
	 	
		public static void sendToServer(MessageBase message)
		{
			INSTANCE.sendToServer(message);
		}
		public static void sendToClient(Supplier<ServerPlayerEntity> player, MessageBase message)
		{
			INSTANCE.send(PacketDistributor.PLAYER.with(player), message);
		}

}
