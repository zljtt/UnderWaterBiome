package com.github.zljtt.underwaterbiome.Objects.Messages;

import java.util.function.Supplier;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class MessageCraftReturn extends MessageBase
{
	private final ItemStack data;
	Minecraft mc = Minecraft.getInstance();

	public MessageCraftReturn(PacketBuffer buf) 
	{
        this.data = buf.readItemStack();
    }

	public MessageCraftReturn(ItemStack data) 
	{
        this.data = data;
    }

    public void encode(PacketBuffer buf) 
    {
        buf.writeItemStack(data);
    }
    
	public void handle(Supplier<Context> context) 
	{
		context.get().enqueueWork(() -> 
 	    {    	
	 	    PlayerEntity sender = context.get().getSender(); 
	 	   sender.addItemStackToInventory(data);
 	    });
    	context.get().setPacketHandled(true);
	}

}
