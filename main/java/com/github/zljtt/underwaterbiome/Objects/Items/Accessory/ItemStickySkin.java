package com.github.zljtt.underwaterbiome.Objects.Items.Accessory;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class ItemStickySkin extends ItemAccessoryBase
{
	Random ran = new Random();
	public ItemStickySkin(String name, Properties property, ObtainType ob, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, property, ob, needBlueprint, type, difficulty);
		// TODO Auto-generated constructor stub
	}


	@Override
	public AccessoryEntry<?> getType()
	{
		return AccessoryEntry.ON_KNOCKBACK;
	}

	@Override
	public boolean getAccumulateable() 
	{
		return false;
	}
	@Override
	public <T extends Event> T editEffect(World worldIn, PlayerEntity player, T event) 
	{
		if(ran.nextInt(100)<50)
		{
			System.out.println("knockback");
			event.setCanceled(true);
		}
		return event;
	}

}
