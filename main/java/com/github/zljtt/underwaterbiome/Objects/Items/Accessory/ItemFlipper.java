package com.github.zljtt.underwaterbiome.Objects.Items.Accessory;

import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.Event;

public class ItemFlipper extends ItemAccessoryBase
{

	public ItemFlipper(String name, Properties property, ObtainType ob, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, property, ob, needBlueprint, type, difficulty);
		// TODO Auto-generated constructor stub
	}


	@Override
	public AccessoryEntry<?> getType()
	{
		return AccessoryEntry.ON_TICK;
	}

	@Override
	public boolean getAccumulateable() 
	{
		return false;
	}
	@Override
	public <T extends Event> T editEffect(World worldIn, PlayerEntity player, T event) 
	{
//		System.out.println(player.getAIMoveSpeed()+"|"+player.getAttribute(player.SWIM_SPEED).getBaseValue()+"|"+player.getAttribute(player.SWIM_SPEED).getValue());
		if (player.getAIMoveSpeed()<0.12F && player.isInWater())
		{
			player.setAIMoveSpeed(player.getAIMoveSpeed()+0.001F);
		}
		if (player.getAIMoveSpeed()>0.13F && player.getAIMoveSpeed()<0.14F && player.isInWater())
		{
			player.setAIMoveSpeed(player.getAIMoveSpeed()+0.001F);

		}
//		player.setAIMoveSpeed(player.);
//		player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(new AttributeModifier("speed_add", maxDamage, Operation.MULTIPLY_BASE));
		return event;
	}

}
