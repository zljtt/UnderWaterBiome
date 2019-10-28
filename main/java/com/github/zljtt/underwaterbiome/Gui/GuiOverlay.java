package com.github.zljtt.underwaterbiome.Gui;
import com.github.zljtt.underwaterbiome.Capabilities.CapabilityPlayerData.KnowledgePoints;
import com.github.zljtt.underwaterbiome.Handlers.EventHandler;
import com.github.zljtt.underwaterbiome.Handlers.NetworkingHandler;
import com.github.zljtt.underwaterbiome.Handlers.TemperatureHandler;
import com.github.zljtt.underwaterbiome.Objects.Messages.MessageOverlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.AdvancementsScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;;

public class GuiOverlay
{
	private final int tex_width=5,tex_height=32,bar_width=3;
	private Minecraft mc = Minecraft.getInstance();
	public static float oxy_percentage;
	public static double depth_offset;
	public static int pollution;
	public static double temp;
	
	public static KnowledgePoints knowledge;

	public static boolean show_oxy = false;
	public static boolean show_depth = false;
	public static boolean show_temp = false;
	public static boolean show_polution = false;

	public GuiOverlay() 
	{
		super();
	}
	
	@SubscribeEvent
    public void onPlayerAdvancementsGUI(GuiScreenEvent event)
    {        		
		if (event.getGui() instanceof AdvancementsScreen)
		{
			mc.getTextureManager().bindTexture(new ResourceLocation("underwaterbiome:textures/gui/part_window.png"));
//			mc.getTextureManager().bindTexture(new ResourceLocation("underwaterbiome:textures/gui/advancements/part_window.png"));
			NetworkingHandler.sendToServer(new MessageOverlay(1));
			int posX = (event.getGui().width - 244)/2-4;
			int posY = (event.getGui().height - 140)/2;
			mc.ingameGUI.blit(posX-25, posY, 0, 0, 25, 47);
			if (knowledge!=null)
			{
				String k1 = I18n.format("gui.chemistry");
				String k2 = I18n.format("gui.biology");
				String k3 = I18n.format("gui.physics");
				String k4 = I18n.format("gui.occult");
				mc.fontRenderer.drawString(k1+knowledge.getChemistry(), 	posX-19, posY+5, 0x000000);//4
				mc.fontRenderer.drawString(k2+knowledge.getBiology(), 		posX-19, posY+15, 0x000000);
				mc.fontRenderer.drawString(k3+knowledge.getPhysics(), 		posX-19, posY+25, 0x000000);
				mc.fontRenderer.drawString(k4+knowledge.getOccult(), 		posX-19, posY+35, 0x000000);

				
//				mc.fontRenderer.drawString(k, (event.getGui().width-mc.fontRenderer.getStringWidth(k)) / 2,
//						(posY+5), 
//						0xB5FCF0);
			}
			
//			System.out.print("height"+event.getGui().height+"width"+event.getGui().width );
		}
		
    }
	@SubscribeEvent(priority = EventPriority.LOW)
    public void GUIOverlay(RenderGameOverlayEvent event)
    {
		if (!event.isCancelable() && event.getType() == ElementType.EXPERIENCE)
		{		
			if (!mc.player.isCreative() && !mc.player.isSpectator())
			{
				mc.getTextureManager().bindTexture(new ResourceLocation("underwaterbiome:textures/gui/bars.png"));
				NetworkingHandler.sendToServer(new MessageOverlay(0));
				int posX = event.getWindow().getScaledWidth()/2 - 100;
				int posY = event.getWindow().getScaledHeight()  - 32;
				if (show_oxy)
				{
					int amount = Math.round(oxy_percentage*30);
					mc.ingameGUI.blit(posX, posY, 25, 0, tex_width, tex_height);
					mc.ingameGUI.blit(posX+1, posY+32-(1+amount), 1, 32-(1+amount), bar_width, amount);
					posX-=5;
				}
				if (show_depth)
				{
					int amount = 0  ;
					if  (depth_offset>40)
					{
						amount =  (int) Math.round(20+(depth_offset-45)/(64-45)*10);
					}
					else if  (depth_offset>25)
					{
						amount =  (int) Math.round(10+(depth_offset-25)/(45-25)*10);
					}
					else if  (depth_offset>0)
					{
						amount =  (int) Math.round(0+depth_offset/25*10);
					}
					mc.ingameGUI.blit(posX, posY, 25, 0, tex_width, tex_height);
					mc.ingameGUI.blit(posX+1, posY+32-(1+amount), 1+tex_width, 32-(1+amount), bar_width, amount);
					posX-=5;
				}
				if (show_temp)
				{
					int amount = (int) Math.round(30*(temp+TemperatureHandler.temp_bound*3/2)/(3*TemperatureHandler.temp_bound));//x=-300,y=0/x=0,y=15/x=300,y=30

					mc.ingameGUI.blit(posX, posY, 30, 0, tex_width, tex_height);
					mc.ingameGUI.blit(posX+1, posY+32-(1+amount), 1+tex_width*3, 32-(1+amount), bar_width, amount);
					posX-=5;
				}
			}
		}
    }
}
