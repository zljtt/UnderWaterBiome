package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.List;

import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ItemPsycheGun extends ItemBase 
{
	Minecraft mc = Minecraft.getInstance();
	public ItemPsycheGun(String name, Properties property, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, property,  needBlueprint, type,difficulty);
	}
	@SuppressWarnings("deprecation")
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		for(ItemEntity itementity : worldIn.getEntitiesWithinAABB(ItemEntity.class, playerIn.getBoundingBox().grow(4,0.5,4))) 
        {
			if (!itementity.removed && !itementity.getItem().isEmpty() && !itementity.cannotPickup()) 
			{
				playerIn.addItemStackToInventory(itementity.getItem());
				itementity.remove();
			}
        }	
        
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
//		tooltip.add(getItemByName().getName().applyTextStyle(TextFormatting.BLUE));
	}

}
