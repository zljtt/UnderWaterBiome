package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.ArrayList;
import java.util.List;

import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ItemStagnationGun extends ItemBase 
{
	int level;
	public ItemStagnationGun(String name, Properties property, int level, boolean needBlueprint, BlueprintType type, int... difficulty) 
	{
		super(name, property,  needBlueprint, type,difficulty);
		this.level = level;

	}
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) 
	{
		
		return super.hitEntity(stack, target, attacker);
	}
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) 
	{
		if (entity!=null &&entity instanceof LivingEntity && entity.isAlive() && entity.isNonBoss())
		{
			if (level == 0)
			{
				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS,60,999));
			}
			if (level == 1)
			{
				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS,120,999));
			}
			if (level == 2)
			{
				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS,60,999));
				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.POISON,60,1));
			}
			
		}
		return true;
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
//		tooltip.add(getItemByName().getName().applyTextStyle(TextFormatting.BLUE));
	}

}
