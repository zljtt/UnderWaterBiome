package com.github.zljtt.underwaterbiome.Objects.Messages;

import java.util.function.Supplier;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class MessageEntity extends MessageBase
{
	private final int data;
	Minecraft mc = Minecraft.getInstance();

	public MessageEntity(PacketBuffer buf) 
	{
        this.data = buf.readInt();
    }

	public MessageEntity(int data) 
	{
        this.data = data;
    }

    public void encode(PacketBuffer buf) 
    {
        buf.writeInt(data);
    }
    
	@SuppressWarnings("unchecked")
	public void handle(Supplier<Context> context) 
	{
		context.get().enqueueWork(() -> 
 	    {    	
	 	    PlayerEntity sender = context.get().getSender(); 
	 	    if (data ==0)
	 	    {
//	 	    	for(ServerPlayerEntity serverplayerentity : (Iterable<ServerPlayerEntity>)sender.getEntityWorld().getPlayers()) 
//				{
//		 		   	if (serverplayerentity.isCreative())
//		 	       	{
//		 			   serverplayerentity.abilities.isCreativeMode=false;
//		 			   serverplayerentity.setGameType(GameType.SURVIVAL);
//		 	        }
//		 	        else
//		 	        {
//		 	        	serverplayerentity.abilities.isCreativeMode=true;
//		 	        	serverplayerentity.setGameType(GameType.CREATIVE);
//
//		 	        }
//			    }
	 	        
	 	        this.mc.player.setReducedDebug(false);
	 	        this.mc.getRenderManager().setDebugBoundingBox(!this.mc.getRenderManager().isDebugBoundingBox());
	 	        this.mc.gameSettings.showDebugInfo = !this.mc.gameSettings.showDebugInfo;
	 	    }
	 	    if (data ==1)
	 	    {
	 	    	IPlayerData data = sender.getCapability(CapabilityPlayerDataProvider.CAP).orElse(null);
				if (data!=null)
				{
					data.getKnowledgePoints().add(1, 1, 1, 1);
					System.out.println(data.getKnowledgePoints().toString());
				}
	 	    }

	 	    
			
 	    	
 	    	

	    		

 	    });
    	context.get().setPacketHandled(true);
	}

}
