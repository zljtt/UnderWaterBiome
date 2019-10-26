package com.github.zljtt.underwaterbiome.Objects.Messages;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public abstract class MessageBase
{
	
	public MessageBase() 
	{
    }


	abstract public void encode(PacketBuffer buf);

	abstract public void handle(Supplier<NetworkEvent.Context> context);
}
