package com.github.zljtt.underwaterbiome.Crafting;

import java.util.List;

import com.github.zljtt.underwaterbiome.Handlers.CraftingHandler;
import com.github.zljtt.underwaterbiome.Handlers.NetworkingHandler;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemKnife;
import com.github.zljtt.underwaterbiome.Objects.Messages.MessageCraftReturn;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.google.common.collect.Lists;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

public class FishSkinRecipe extends SpecialRecipe
{

	public FishSkinRecipe(ResourceLocation l) 
	{
		super(l);
	}

	@Override
	public boolean matches(CraftingInventory inv, World worldIn) 
	{
		 ItemStack knife = ItemStack.EMPTY;
	     int list = 0;

	      for(int i = 0; i < inv.getSizeInventory(); ++i)
	      {
	         ItemStack itemstack1 = inv.getStackInSlot(i);
	         if (!itemstack1.isEmpty()) 
	         {
	            if (itemstack1.getItem() instanceof ItemKnife)
	            {
	               if (!knife.isEmpty())
	               {
	                  return false;
	               }

	               knife = itemstack1;
	            }
	            else 
	            {
	            	Item item = itemstack1.getItem();
	            	if(item == Items.PUFFERFISH||item == Items.COD||item == Items.TROPICAL_FISH||item == Items.SALMON)
	            	{
	            		list+=1;
	            	}
	            	else return false;
	            }
	         }
	      }
   		System.out.println(!knife.isEmpty() && list==2);
	    return !knife.isEmpty() && list==2;
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv) 
	{
		System.out.print("result");
		ItemStack knife = ItemStack.EMPTY;
		int count_skin = 0;
	      for(int i = 0; i < inv.getSizeInventory(); ++i)
	      {
	         ItemStack itemstack1 = inv.getStackInSlot(i);
	         if (itemstack1.getItem() instanceof ItemKnife) 
	         {
	            knife = itemstack1; 
	         }
	      }
	      if (!knife.isEmpty())
	      {
	    	  if (knife.getItem().equals(ItemInit.WOODEN_KNIFE))count_skin = 1;
	    	  else if (knife.getItem().equals(ItemInit.STONE_KNIFE))count_skin = 2;
	    	  else if (knife.getItem().equals(ItemInit.IRON_KNIFE))count_skin = 3;
	    	  else if (knife.getItem().equals(ItemInit.GOLDEN_KNIFE))count_skin = 4;
	    	  else if (knife.getItem().equals(ItemInit.DIAMOND_KNIFE))count_skin = 5;
	    	  NetworkingHandler.sendToServer(new MessageCraftReturn(knife));
	  		return new ItemStack(ItemInit.FISH_SKIN, count_skin);
	      }
	      else
	    	  return ItemStack.EMPTY;
	}

	@Override
	public boolean canFit(int width, int height) 
	{
		return width * height >= 2;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return CraftingHandler.CRAFTING_FISH_SKIN;
	}

}
