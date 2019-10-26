package com.github.zljtt.underwaterbiome.Objects.Messages;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class MessageEventHandler extends MessageBase
{
	private final int data;

	public MessageEventHandler(PacketBuffer buf) 
	{
        this.data = buf.readInt();
    }

	public MessageEventHandler(int data) 
	{
        this.data = data;
    }

    public void encode(PacketBuffer buf) 
    {
        buf.writeInt(data);
    }
    
	public void handle(Supplier<Context> context) 
	{
		context.get().enqueueWork(() -> 
 	    {    	
 	        PlayerEntity sender = context.get().getSender(); 
 	      	
	    		

 	    });
    	context.get().setPacketHandled(true);
	}

}
