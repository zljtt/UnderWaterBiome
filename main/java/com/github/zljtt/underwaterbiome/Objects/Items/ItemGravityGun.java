package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.List;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemGravityGun extends ItemBase 
{
	Minecraft mc = Minecraft.getInstance();
	public ItemGravityGun(String name, Properties property, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, property,  needBlueprint, type, difficulty);
	}
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) 
	{
		if (entity!=null)
		{
			BlockPos pos_target = entity.getPosition();
//	    	ItemEntity itementity0 = (ItemEntity) this.mc.pointedEntity;
		    Vec3d vec3d1 = player.getLook(0);
			for(LivingEntity living_entity : entity.world.getEntitiesWithinAABB(LivingEntity.class, (new AxisAlignedBB(pos_target).grow(1)))) 
			{
				if (stack.getItem().getRegistryName().getPath().equals("gravity_gun_attractive")&&!(living_entity instanceof PlayerEntity))
				{
					living_entity.addVelocity(-vec3d1.x, -vec3d1.y, -vec3d1.z);
				}
				else if(stack.getItem().getRegistryName().getPath().equals("gravity_gun_repulsive")&&!(living_entity instanceof PlayerEntity))
				{
					living_entity.addVelocity(vec3d1.x, vec3d1.y, vec3d1.z);
				}
			}
			return true;
		}	
		return false;
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{	
		if (playerIn.getHeldItem(handIn).getItem().getRegistryName().getPath().equals("gravity_gun_attractive"))
		{
			playerIn.setHeldItem(handIn, new ItemStack(ItemInit.GRAVITY_GUN_REPULSIVE));
		}
		else if(playerIn.getHeldItem(handIn).getItem().getRegistryName().getPath().equals("gravity_gun_repulsive"))
		{
			playerIn.setHeldItem(handIn, new ItemStack(ItemInit.GRAVITY_GUN_ATTRACTIVE));
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		if (stack.getItem().getRegistryName().getPath().equals("gravity_gun_attractive"))
		{
			tooltip.add((new TranslationTextComponent(I18n.format("tooltip.gravity_gun_attractive")).applyTextStyle(TextFormatting.DARK_GREEN)));
		}
		else if(stack.getItem().getRegistryName().getPath().equals("gravity_gun_repulsive"))
		{
			tooltip.add((new TranslationTextComponent(I18n.format("tooltip.gravity_gun_repulsive")).applyTextStyle(TextFormatting.DARK_RED)));
		}

	}

}
