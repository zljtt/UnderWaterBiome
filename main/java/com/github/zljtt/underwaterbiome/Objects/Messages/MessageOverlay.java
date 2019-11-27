package com.github.zljtt.underwaterbiome.Objects.Messages;

import java.util.function.Supplier;

import com.github.zljtt.underwaterbiome.Capabilities.CapabilityPlayerData.KnowledgePoints;
import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityOxygenProvider;
import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Gui.GuiOverlay;
import com.github.zljtt.underwaterbiome.Handlers.EventHandler;
import com.github.zljtt.underwaterbiome.Handlers.OxygenHandler;
import com.github.zljtt.underwaterbiome.Handlers.TriggerHandler;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Interface.IOxygen;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class MessageOverlay extends MessageBase
{
	private final int data;

	public MessageOverlay(PacketBuffer buf) 
	{
        this.data = buf.readInt();
    }

	public MessageOverlay(int data) 
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
 	        if (data == 0)
 	        {
 	        // oxygen meter
 	 	        int oxy_max_total = 0;
 		    	int oxy_total = 0;
 		    	int count = 0;
 		    	if (!sender.world.isRemote)
 		    	{
 		    		for (ItemStack stack : sender.inventory.mainInventory)
 		 	    	{
 		 	    		if 	(OxygenHandler.breathableItem().contains(stack.getItem()))
 		 	    		{
 		 	    			count+=1;
 		 	    			IOxygen cap_o =  stack.getCapability(CapabilityOxygenProvider.PARTICLE_CAP, null).orElse(null);
 		 	    			if (!cap_o.equals(null))
 		 	    			{
 		 	    				oxy_max_total+=EventHandler.getMaxOxygenValue(stack);
 		 	    				oxy_total+=cap_o.getOxygen();
 		 	    			}
 		 	    		}
 		 	    	}
 		 	    	GuiOverlay.show_oxy = count!=0;
 		 	        GuiOverlay.oxy_percentage = (float)oxy_total/oxy_max_total;
 		 	        //pressure measure
 		 	        if (sender.inventory.hasItemStack(new ItemStack(ItemInit.MEASURING_DEVICE))
 		 	        		||sender.inventory.hasItemStack(new ItemStack(ItemInit.PRESSURE_METER)))
 		 	        {
 			    		GuiOverlay.show_depth = true;
 			    		GuiOverlay.depth_offset = sender.world.getSeaLevel() - sender.posY;
 		 	        }
 		 	        else
 		 	        {
 		 	        	GuiOverlay.show_depth = false;
 		 	        }
 		 	        //temperature
 		 	       if (sender.inventory.hasItemStack(new ItemStack(ItemInit.MEASURING_DEVICE))
 		 	    		   ||sender.inventory.hasItemStack(new ItemStack(ItemInit.TEMPERATURE_METER)))
 			        {
 			    		GuiOverlay.show_temp = true;
 			    		IPlayerData cap_p =  sender.getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
 			    		if (cap_p!=null)	
 			    		{
 				    		GuiOverlay.temp=cap_p.getTemperature();
 			    		}
 			        }
 			        else
 			        {
 			        	GuiOverlay.show_temp = false;
 			        }
 		    	}
 	 	    		
 	        }
 	        else if (data == 1)
 	        {
 	        	IPlayerData cap_o =  sender.getCapability(CapabilityPlayerDataProvider.CAP).orElse(null);
 	 	        if (cap_o!=null)
 		      	{
 		    		KnowledgePoints point = cap_o.getKnowledgePoints();	   
 		    		GuiOverlay.knowledge = point;	
 		    		GuiOverlay.rest = cap_o.getRestFree();	
 		      	}
 	        }
        	IPlayerData cap_o =  sender.getCapability(CapabilityPlayerDataProvider.CAP).orElse(null);
        	if(cap_o!=null && cap_o.getRestFree()>0 && data>=2 && data<=5)
        	{
        		if (!sender.getEntityWorld().isRemote())
        		{
        			cap_o.setRestFree(cap_o.getRestFree()-1);
            		switch(data)
            		{
            		case 2:cap_o.getKnowledgePoints().addChemistry(1);break;
            		case 3:cap_o.getKnowledgePoints().addBiology(1);break;
            		case 4:cap_o.getKnowledgePoints().addPhysics(1);break;
            		case 5:cap_o.getKnowledgePoints().addOccult(1);break;
            		}
        		}
        		if (sender instanceof ServerPlayerEntity)
        		{
        			TriggerHandler.NEED_TECHPOINT.trigger((ServerPlayerEntity)sender, cap_o.getKnowledgePoints());
        		}
        		
        	}
        	

    		
 	        
	    		

 	    });
    	context.get().setPacketHandled(true);
	}

}
