package com.github.zljtt.underwaterbiome.Utils;


import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class DummyRecipe extends SpecialRecipe
{
    public DummyRecipe(ResourceLocation idIn) 
    {
		super(idIn);
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean matches(final CraftingInventory inv, final World worldIn) 
    {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(final CraftingInventory inv) 
    {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(final int width, final int height) 
    {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() 
    {
        return ItemStack.EMPTY;
    }

	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return IRecipeSerializer.CRAFTING_SHAPELESS;
	}
}
