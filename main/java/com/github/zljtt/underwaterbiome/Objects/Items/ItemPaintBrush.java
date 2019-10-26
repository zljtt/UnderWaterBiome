package com.github.zljtt.underwaterbiome.Objects.Items;

import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockIron;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockIron.PaintColor;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;

public class ItemPaintBrush extends ItemBase
{

	public ItemPaintBrush(String name, Properties property, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, property,  needBlueprint, type,  difficulty);
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		BlockState state = context.getWorld().getBlockState(context.getPos());
		if(state.getBlock() instanceof BlockIron)
		{
			ItemStack blue = new ItemStack(Items.BLUE_DYE);
			ItemStack orange = new ItemStack(Items.ORANGE_DYE);

			PlayerInventory i = context.getPlayer().inventory;
			if (i.hasItemStack(blue))
			{
				int slot = i.getSlotFor(blue);
				if (!context.getPlayer().isCreative())
					i.setInventorySlotContents(slot, i.decrStackSize(slot, 1));
				context.getWorld().setBlockState(context.getPos(), state.with(BlockIron.PAINT, PaintColor.BLUE));
			}
			else if (i.hasItemStack(orange))
			{
				int slot = i.getSlotFor(orange);
				if (!context.getPlayer().isCreative())
					i.setInventorySlotContents(slot, i.decrStackSize(slot, 1));
				context.getWorld().setBlockState(context.getPos(), state.with(BlockIron.PAINT, PaintColor.ORANGE));
			}
		}
		return super.onItemUse(context);
	}

}
