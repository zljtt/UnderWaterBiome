package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.function.Predicate;

import com.github.zljtt.underwaterbiome.Inits.EntityInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityWaterArrow;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;

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
import net.minecraft.world.World;

public class ItemWaterBow  extends BowItem implements INeedBluePrint
{
	BlueprintInfo info;

	public ItemWaterBow(String name, Item.Properties property, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
	    super(property);
	    setRegistryName(new ResourceLocation(Reference.MODID,name));
		this.info= new BlueprintInfo(needBlueprint, difficulty, type);
		ItemInit.ITEMS.add(this);
	}
	@Override
	public AbstractArrowEntity customeArrow(AbstractArrowEntity arrow) 
	{
	      EntityWaterArrow arrowentity = new EntityWaterArrow(arrow.getEntityWorld(), (LivingEntity)arrow.getShooter());
	      return arrowentity;
	}

	@Override
	public BlueprintInfo getBlueprintInfo() {
		// TODO Auto-generated method stub
		return info;
	}

}
