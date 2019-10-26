package com.github.zljtt.underwaterbiome.Utils.Enum;

import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum ListArmourMaterial implements IArmorMaterial
{
	LIME("lime", 10, new int[]{2, 3, 5, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Items.IRON_INGOT, 0.0F);

	private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	private String name;
	private SoundEvent sound;
	private int enchantability,durability;
	private int[] damageReductionAmount;
	private Item repairItem;
	private float toughness;
	
	private ListArmourMaterial(String name,int durability,int[] damageReductionAmount,int enchantability, SoundEvent sound,Item repair_item, float toughness)
	{
		this.damageReductionAmount  = damageReductionAmount;
		this.durability  =durability;
		this.enchantability = enchantability;
		this.repairItem = repair_item;
		this.name = name;
		this.toughness = toughness;
		this.sound = sound;
	}
	@Override
	public int getDurability(EquipmentSlotType slotIn) 
	{
		return this.durability * MAX_DAMAGE_ARRAY[slotIn.getIndex()];
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) 
	{
		return this.damageReductionAmount[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() 
	{
		return enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() 
	{
		return sound ;
	}

	@Override
	public Ingredient getRepairMaterial() 
	{
		return Ingredient.fromItems(repairItem);
	}

	@Override
	public String getName() 
	{
		return Reference.MODID+name;
	}

	@Override
	public float getToughness() 
	{
		return this.toughness;
	}

}
