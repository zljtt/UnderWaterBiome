package com.github.zljtt.underwaterbiome.Objects.Items;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Handlers.TriggerHandler;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.advancements.Advancement;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemBluePrint extends Item 
{
	public int rarity;
	Random ran = new Random();
	public ItemBluePrint(String name, Properties property) 
	{
		super(property);
		this.setRegistryName(new ResourceLocation(Reference.MODID, "blueprint/"+name));
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		if (playerIn instanceof ServerPlayerEntity)
		{		
			ServerPlayerEntity player = (ServerPlayerEntity) playerIn;
			Advancement advancement_recipe = worldIn.getServer().getAdvancementManager().getAdvancement(new ResourceLocation(Reference.MODID,"recipes/"+getStringByRegistryName()));
			System.out.println(advancement_recipe.toString());
			if (player.getAdvancements().getProgress(advancement_recipe).isDone())
			{
				ITextComponent com = new TranslationTextComponent(I18n.format("message.already_have_blueprint"));
				ITextComponent com2 = new TranslationTextComponent(I18n.format("message.change_to_fragment"));
				playerIn.sendMessage(com.appendSibling(getItemByName().getName()).applyTextStyle(TextFormatting.BLUE).appendSibling(com2));
				playerIn.setHeldItem(handIn, new ItemStack(Items.AIR));
				playerIn.addItemStackToInventory(new ItemStack(ItemInit.BLUEPRINT_FRAGMENT, 2+ran.nextInt(3)));
			}
			else 
			{
				TriggerHandler.UNLOCKED_BLUE_PRINT.trigger((ServerPlayerEntity)playerIn, getStringByRegistryName());
				ITextComponent com = new TranslationTextComponent(I18n.format("message.unlock_blueprint"));
				playerIn.sendMessage(com.appendSibling(getItemByName().getName()));
				playerIn.setHeldItem(handIn, new ItemStack(Items.AIR));
			}				
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	public String getStringByRegistryName()
	{
		String[] list = this.getRegistryName().getPath().split("blueprint/");	
		return list[1];
		
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(getItemByName().getName().applyTextStyle(TextFormatting.BLUE));
	}
	
	public Item getItemByName()
	{
		for (Item item0:ItemInit.ITEMS)
		{
			if(this.getStringByRegistryName().equals(item0.getRegistryName().getPath()))
			return item0;
		}
		return Items.AIR;
	}
	@Override
	public String getTranslationKey() 
	{
		return "item.underwaterbiome.blueprint";
	}
	

}
