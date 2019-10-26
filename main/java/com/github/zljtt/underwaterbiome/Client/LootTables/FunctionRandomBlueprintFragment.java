package com.github.zljtt.underwaterbiome.Client.LootTables;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class FunctionRandomBlueprintFragment extends LootFunction
{
	Random ran = new Random();
	protected FunctionRandomBlueprintFragment(ILootCondition[] conditionsIn) 
	{
		super(conditionsIn);
	}

	@Override
	protected ItemStack doApply(ItemStack itemstack, LootContext context) 
	{
		int num = ran.nextInt(2)+1;
		Item item = Reference.BLUEPRINT_FRAGMENTS[ran.nextInt(4)];
		return new ItemStack(item, num);
	}
	
	
	public static class Serializer extends LootFunction.Serializer<FunctionRandomBlueprintFragment> 
	{
		public Serializer()
		{
			super(new ResourceLocation(Reference.MODID,"random_blueprint_fragment"), FunctionRandomBlueprintFragment.class);
		}
		public void serialize(JsonObject object, FunctionRandomBlueprintFragment functionClazz, JsonSerializationContext serializationContext) 
		{
		   	super.serialize(object, functionClazz, serializationContext);
		}

		public FunctionRandomBlueprintFragment deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) 
		{
		    return new FunctionRandomBlueprintFragment(conditionsIn);
		}
	}
}

