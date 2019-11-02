package com.github.zljtt.underwaterbiome.Handlers;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemBlueprintFragment;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KnowledgePointHandler 
{
	Random ran = new Random();
	@SubscribeEvent
    public void onEnteringNewBiome(EntityEvent.EnteringChunk event)
    {
		if (event.getEntity() instanceof PlayerEntity && !event.getEntity().getEntityWorld().isRemote())
		{
			IPlayerData data = ((PlayerEntity)event.getEntity()).getCapability(CapabilityPlayerDataProvider.CAP).orElse(null);
			String biome = event.getEntity().getEntityWorld().getBiome(new BlockPos(event.getNewChunkX()*16,64,event.getNewChunkZ()*16)).getRegistryName().getPath();
			System.out.println(biome+"|"+data.getUnlockedBiomes());

			if (data!=null && (data.getUnlockedBiomes().size()==0||!data.getUnlockedBiomes().contains(biome)))
			{
				
				data.unlockBiome(biome);
				ITextComponent com0 = new TranslationTextComponent(I18n.format("message.biome_get_knowledge_1"));
				ITextComponent com1 = event.getEntity().getEntityWorld().getBiome(new BlockPos(event.getNewChunkX()*16,64,event.getNewChunkZ()*16)).getDisplayName().applyTextStyle(TextFormatting.LIGHT_PURPLE).applyTextStyle(TextFormatting.BOLD);
				ITextComponent com2 = new TranslationTextComponent(I18n.format("message.biome_get_knowledge_2"));
				ITextComponent com3 = new TranslationTextComponent(I18n.format("tooltip."+data.addRandomKnowledge(ran)));
				event.getEntity().sendMessage(com0.appendSibling(com1).appendSibling(com2).appendSibling(com3));	
			}
		}		

    }
	@SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent event)
    {
		Item item = event.getCrafting().getItem();
		IPlayerData data = event.getEntity().getCapability(CapabilityPlayerDataProvider.CAP).orElse(null);
		String id = Integer.toString(Item.getIdFromItem(item));
		if (data!=null && (data.getUsedItems().size()==0||!data.getUsedItems().contains(id)))
		{
			System.out.print(id);
			data.useItem(id);
			EventHandler.craft_time = data.getUsedItems().size();
			if (data.getUsedItems().size()%5==0)
			{
				ITextComponent com = new TranslationTextComponent(I18n.format("message.craft_get_knowledge"));
				if (!event.getEntityPlayer().getEntityWorld().isRemote())
				{
					event.getEntityPlayer().addItemStackToInventory(new ItemStack(ItemInit.BLUEPRINT_FRAGMENT));
					event.getPlayer().sendMessage(com);		
				}
			}	
		}
		if (item instanceof ItemBlueprintFragment)
		{
			event.getPlayer().addItemStackToInventory(new ItemStack(ItemInit.SPACESHIP_DRIVING_RECORDER));
		}
		if (item.equals(ItemInit.FISH_SKIN))
		{
			Item itemk;
			switch(event.getCrafting().getCount())
			{
			case 1:itemk = ItemInit.WOODEN_KNIFE;break;
			case 2:itemk = ItemInit.STONE_KNIFE;break;
			case 3:itemk = ItemInit.IRON_KNIFE;break;
			case 4:itemk = ItemInit.GOLDEN_KNIFE;break;
			case 5:itemk = ItemInit.DIAMOND_KNIFE;break;
			default:itemk = Items.AIR;
			}
			event.getPlayer().addItemStackToInventory(new ItemStack(itemk));
		}
		
    }

	@SubscribeEvent
    public void onRecieveKnowledgeBaseOnTime(PlayerTickEvent event)
    {
		if (!event.player.getEntityWorld().isRemote()&& event.phase == Phase.START)
		{
			int time = (int)(event.player.getEntityWorld().getDayTime() % 24000L);
//			System.out.println(time);
			if (time == 600)
			{
				event.player.sendMessage(new TranslationTextComponent(I18n.format("message.new_day")));		
				event.player.addItemStackToInventory(new ItemStack(ItemInit.BLUEPRINT_FRAGMENT));
			}
		}
		
    }
	
}
