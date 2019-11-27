package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.List;
import java.util.function.Consumer;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemKnife extends SwordItem
{

	public ItemKnife(String name, IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
		this.setRegistryName(new ResourceLocation(Reference.MODID,name));
		ItemInit.ITEMS.add(this);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(new TranslationTextComponent("tooltip.ignore_armer_to_fish").applyTextStyle(TextFormatting.BLUE));
		tooltip.add(new TranslationTextComponent("tooltip.unbreakable").applyTextStyle(TextFormatting.GRAY));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) 
	{
		return 0;
	}
	
	

}
