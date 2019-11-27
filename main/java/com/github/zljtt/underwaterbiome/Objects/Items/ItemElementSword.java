package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.List;
import java.util.function.Consumer;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemElementBow.ElementType;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemElementSword extends SwordItem
{
	ElementType type;

	public ItemElementSword(String name, ElementType type, IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
		this.setRegistryName(new ResourceLocation(Reference.MODID,name));
		ItemInit.ITEMS.add(this);
		this.type = type;
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		ITextComponent string = null;
		switch(type)
		{
		case LAVA :string = new StringTextComponent(I18n.format("tooltip.lava_weapon"));break;
		case WATER :string = new StringTextComponent(I18n.format("tooltip.water_weapon"));break;
		}
		tooltip.add(string.applyTextStyle(TextFormatting.BLUE));
	}

}
