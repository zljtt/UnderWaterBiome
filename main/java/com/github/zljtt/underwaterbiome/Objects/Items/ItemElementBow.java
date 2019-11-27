package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.List;
import java.util.function.Predicate;

import com.github.zljtt.underwaterbiome.Inits.EntityInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityWaterArrow;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemElementBow  extends BowItem
{
	ElementType type;
	
	public ItemElementBow(String name, ElementType type, Item.Properties property) 
	{
	    super(property);
	    setRegistryName(new ResourceLocation(Reference.MODID,name));
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
	
	public static enum ElementType
	{
		LAVA,
		WATER
	}

}
