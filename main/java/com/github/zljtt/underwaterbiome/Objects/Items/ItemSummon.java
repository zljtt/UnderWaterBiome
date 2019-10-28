package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.List;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.EntityInit;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSummon extends ItemBase
{
	EntityType entity;
	public ItemSummon(String name, Properties property, EntityType entity, boolean needBlueprint, BlueprintType type, int... difficulty) 
	{
		super(name, property, needBlueprint, type, difficulty);
		this.entity = entity;
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		Random ran = new Random();
		for (int x = 0; x < 100; x++)
		{
			BlockPos pos = new BlockPos(playerIn.posX-16+ran.nextInt(32),
					playerIn.posY-16+ran.nextInt(32),
					playerIn.posZ-16+ran.nextInt(32));
			if (worldIn.getBlockState(pos).getBlock().equals(Blocks.WATER))
			{
				Entity entity_in_room =  entity.create(worldIn.getWorld());
				entity_in_room.setPosition(pos.getX(), pos.getY(), pos.getZ());
				worldIn.addEntity(entity_in_room);
				playerIn.setHeldItem(handIn, new ItemStack(Items.AIR));	
				if (worldIn.isRemote)
				{
					StringTextComponent com = new StringTextComponent(I18n.format("message.summon_success"));
					ITextComponent com0 = entity.getName();
					playerIn.sendMessage(com0.applyTextStyle(TextFormatting.DARK_RED).appendSibling(com));
				}
				return super.onItemRightClick(worldIn, playerIn, handIn);
			}
		}
		playerIn.sendMessage(new StringTextComponent(I18n.format("message.summon_fail")));
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(entity.getName().applyTextStyle(TextFormatting.DARK_RED));
	}

}
