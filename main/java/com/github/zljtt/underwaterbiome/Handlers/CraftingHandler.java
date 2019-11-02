package com.github.zljtt.underwaterbiome.Handlers;

import java.util.ArrayList;
import java.util.List;

import com.github.zljtt.underwaterbiome.Crafting.FishSkinRecipe;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.item.crafting.ArmorDyeRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingHandler 
{
	public static final IRecipeSerializer<?> CRAFTING_FISH_SKIN =new SpecialRecipeSerializer<>(FishSkinRecipe::new)
			.setRegistryName(new ResourceLocation(Reference.MODID,"crafting_fish_skin"));

	public static void register( RegistryEvent.Register<IRecipeSerializer<?>> event)
	{
		event.getRegistry().register(CRAFTING_FISH_SKIN);
	}


}
