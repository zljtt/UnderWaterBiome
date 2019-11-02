package com.github.zljtt.underwaterbiome.Objects.Items.Base;

import java.util.ArrayList;
import java.util.List;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.Event;

public abstract class ItemAccessoryBase extends Item implements INeedBluePrint
{
	BlueprintInfo info;
	String name;
	ObtainType obtain_type;
	public ItemAccessoryBase(String name, Item.Properties property, ObtainType ob, boolean needBlueprint, BlueprintType type, int... difficulty) 
	{
		super(property);
		this.obtain_type = ob;
		this.name = name;
		setRegistryName(new ResourceLocation(Reference.MODID,"accessory/"+name));
		this.info= new BlueprintInfo(needBlueprint, difficulty, type);
		ItemInit.ITEMS.add(this);
		if (ob == ObtainType.CHEST)ItemInit.ACCESSORY_CHEST.add(this);
		else if (ob == ObtainType.CRAFTING)ItemInit.ACCESSORY_CRAFTING.add(this);
		else if (ob == ObtainType.ENTITY_DROP)ItemInit.ACCESSORY_ENTITY_DROP.add(this);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		ITextComponent string0 = new TranslationTextComponent(String.format("tooltip.in_inventory")).applyTextStyle(TextFormatting.GOLD);
		ITextComponent string1 = new TranslationTextComponent(String.format("accessory."+this.name));

		tooltip.add(string0.appendSibling(string1));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public abstract AccessoryEntry<?> getType();
	public abstract boolean getAccumulateable();
	@Override
	public BlueprintInfo getBlueprintInfo() 
	{
		return info;
	}
	public <T extends Event>T editEffect(World worldIn, PlayerEntity player, T event)
	{
		return event;
	}
	@Override
	public String getTranslationKey() 
	{
		return "item.underwaterbiome."+name;
	}
	public enum ObtainType
	{
		ENTITY_DROP,
		CRAFTING,
		CHEST;
	}
	public static <T extends Event>T testAccessoryAndEdit(World worldIn, PlayerEntity player, T event, AccessoryEntry<?> type)
	{
		List<Item> n = new ArrayList<Item>();
		for (ItemStack stack:player.inventory.mainInventory)
		{
			if (stack.getItem() instanceof ItemAccessoryBase && ((ItemAccessoryBase) stack.getItem()).getType() == type
					&& !n.contains(stack.getItem()))
			{
				ItemAccessoryBase item = (ItemAccessoryBase) stack.getItem();
				System.out.println(item.name);
				event = item.editEffect(worldIn, player, event);
				if(!item.getAccumulateable()) n.add(stack.getItem());
			}
		}
		return event;
		
	}
	public static boolean isMeleeDamage(Item item)
	{
		return item instanceof SwordItem 
				|| item instanceof ToolItem
				|| item instanceof AxeItem
				|| item instanceof PickaxeItem
				|| item == Items.AIR;
	}

}
