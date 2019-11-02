package com.github.zljtt.underwaterbiome.Client.LootTables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class FunctionRandomAccessory extends LootFunction
{
	Random ran = new Random();
	int chance;
	String obtain;
	protected FunctionRandomAccessory(ILootCondition[] conditionsIn, String obtain, int chance) 
	{
		super(conditionsIn);
		this.chance = chance;
		this.obtain = obtain;
	}

	@Override
	protected ItemStack doApply(ItemStack itemstack, LootContext context) 
	{
		if (obtain == "chest" && ran.nextInt(100)<chance && ItemInit.ACCESSORY_CHEST!=null)
		{
			return new ItemStack(ItemInit.ACCESSORY_CHEST.get(ran.nextInt(ItemInit.ACCESSORY_CHEST.size())));
		}
		else return ItemStack.EMPTY;
	}
	
	
	public static class Serializer extends LootFunction.Serializer<FunctionRandomAccessory> 
	{
		public Serializer()
		{
			super(new ResourceLocation(Reference.MODID,"random_accessory"), FunctionRandomAccessory.class);
		}
		public void serialize(JsonObject object, FunctionRandomAccessory functionClazz, JsonSerializationContext serializationContext) 
		{
		   	super.serialize(object, functionClazz, serializationContext);
		    object.add("obtain", serializationContext.serialize(functionClazz.obtain));
		    object.add("chance", serializationContext.serialize(functionClazz.chance));

		}

		public FunctionRandomAccessory deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) 
		{
			String obtain = JSONUtils.deserializeClass(object, "obtain", deserializationContext, String.class);
			int chance = JSONUtils.deserializeClass(object, "chance", deserializationContext, Integer.class);
		    return new FunctionRandomAccessory(conditionsIn, obtain,chance);
		}
	}
}

