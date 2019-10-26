package com.github.zljtt.underwaterbiome.Client.LootTables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class FunctionRandomBlueprint extends LootFunction
{
	private String type;
	private int difficulty;
	Random ran = new Random();
	protected FunctionRandomBlueprint(ILootCondition[] conditionsIn, String place,int difficulty) 
	{
		super(conditionsIn);
		this.type=place;
		this.difficulty = difficulty;
	}

	@Override
	protected ItemStack doApply(ItemStack itemstack, LootContext context) 
	{
		List<ItemStack> blueprint_list = new ArrayList<ItemStack>();
		ItemInit.BLUEPRINT_MAP.forEach((stack, info)->
		{
			for(int d : info.getRange())
			{
				if (d == difficulty && info.getType().getName().equals(type))
				{
					blueprint_list.add(stack);
				}
			}	
		});
		if (blueprint_list.size()>0)
		{
			int i = ran.nextInt(blueprint_list.size());
			return blueprint_list.get(i);
		}
		return ItemStack.EMPTY;
	}
	
	
	public static class Serializer extends LootFunction.Serializer<FunctionRandomBlueprint> 
	{
		public Serializer()
		{
			super(new ResourceLocation(Reference.MODID,"random_blueprint"), FunctionRandomBlueprint.class);
		}
		public void serialize(JsonObject object, FunctionRandomBlueprint functionClazz, JsonSerializationContext serializationContext) 
		{
		   	super.serialize(object, functionClazz, serializationContext);
		    object.add("type", serializationContext.serialize(functionClazz.type));
		    object.add("difficulty", serializationContext.serialize(functionClazz.difficulty));
		}

		public FunctionRandomBlueprint deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) 
		{
			String place = JSONUtils.deserializeClass(object, "type", deserializationContext, String.class);
			int difficulty = JSONUtils.deserializeClass(object, "difficulty", deserializationContext, Integer.class);
		    return new FunctionRandomBlueprint(conditionsIn, place, difficulty);
		}
	}
}

