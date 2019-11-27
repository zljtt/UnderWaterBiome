package com.github.zljtt.underwaterbiome.Gui;
import java.text.DecimalFormat;

import com.github.zljtt.underwaterbiome.Capabilities.CapabilityPlayerData.KnowledgePoints;
import com.github.zljtt.underwaterbiome.Handlers.EventHandler;
import com.github.zljtt.underwaterbiome.Handlers.NetworkingHandler;
import com.github.zljtt.underwaterbiome.Handlers.TemperatureHandler;
import com.github.zljtt.underwaterbiome.Objects.Messages.MessageOverlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.AdvancementsScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;;

public class GuiOverlay
{

	public static int timer = 0;
	private final int tex_width=5,tex_height=32,bar_width=3;
	private Minecraft mc = Minecraft.getInstance();
	public static float oxy_percentage;
	public static double depth_offset;
	public static int pollution;
	public static double temp;
	public static float health = 0;
	public static float last_hit = 0;

	public static KnowledgePoints knowledge;
	public static int rest;

	public static boolean show_oxy = false;
	public static boolean show_depth = false;
	public static boolean show_temp = false;
	public static boolean show_polution = false;
	public static boolean show_battle_info = false;
	DecimalFormat decimalFormat=new DecimalFormat("0.0");

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
			NetworkingHandler.sendToServer(new MessageOverlay(1));
			int posX = (event.getGui().width - 244)/2-4;
			int posY = (event.getGui().height - 140)/2;
			mc.ingameGUI.blit(posX-25, posY, 0, 0, 25, 47);
			mc.ingameGUI.blit(posX-51, posY+47, 25, 0, 51, 24);
			if (knowledge!=null)
			{
				String k1 = I18n.format("gui.chemistry");
				String k2 = I18n.format("gui.biology");
				String k3 = I18n.format("gui.physics");
				String k4 = I18n.format("gui.occult");
				String k5 = I18n.format("gui.rest");
				int word = mc.fontRenderer.getStringWidth(k5+String.valueOf(rest));
				int k = 51-(51-word)/2;
				mc.fontRenderer.drawString(k1+knowledge.getChemistry(), 	posX-22, posY+5, 0x000000);//4
				mc.fontRenderer.drawString(k2+knowledge.getBiology(), 		posX-22, posY+15, 0x000000);
				mc.fontRenderer.drawString(k3+knowledge.getPhysics(), 		posX-22, posY+25, 0x000000);
				mc.fontRenderer.drawString(k4+knowledge.getOccult(), 		posX-22, posY+35, 0x000000);
				mc.fontRenderer.drawString(k5+String.valueOf(rest), 		posX-k+1, posY+54, 0x000000);
				if (EventHandler.timer_4 >= 0)
				{
					EventHandler.timer_4-=1;
				}
			}

			
		}
		
    }
	@SubscribeEvent(priority = EventPriority.LOW)
    public void GUIOverlay(RenderGameOverlayEvent event)
    {
		if (!event.isCancelable() && show_battle_info)
		{
			if (health != 0)
			{
				String str = decimalFormat.format(health);
				mc.fontRenderer.drawString(str, 
						event.getWindow().getScaledWidth()/2-mc.fontRenderer.getStringWidth(str)/2, 
						event.getWindow().getScaledHeight()/2, 0x000000);
			}
			if (last_hit != 0 && timer < 60)
			{
				String str = decimalFormat.format(last_hit);
				mc.fontRenderer.drawString(str, 
						event.getWindow().getScaledWidth()/2-mc.fontRenderer.getStringWidth(str)/2, 
						event.getWindow().getScaledHeight()/2+10, 0x000000);
			}
			timer +=1;
		}
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
