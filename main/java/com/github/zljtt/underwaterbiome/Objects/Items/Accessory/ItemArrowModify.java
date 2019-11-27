package com.github.zljtt.underwaterbiome.Objects.Items.Accessory;

import java.util.List;

import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemAccessoryBase;
import com.github.zljtt.underwaterbiome.Utils.AccessoryEntry;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.Event;

public class ItemArrowModify extends ItemAccessoryBase
{

	public ItemArrowModify(String name, Properties property, ObtainType ob, boolean needBlueprint, BlueprintType type,
			int... difficulty) {
		super(name, property, ob, needBlueprint, type, difficulty);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AccessoryEntry<?> getType() 
	{
		return AccessoryEntry.ON_TICK;
	}
	@Override
	public <T extends Event> T editEffect(World worldIn, PlayerEntity player, T event) 
	{
		AxisAlignedBB box = player.getBoundingBox().grow(30, 4.0D, 30);
		List<AbstractArrowEntity> arrow = worldIn.getEntitiesWithinAABB(AbstractArrowEntity.class, box);
		if (arrow.size()>0)
		{
			arrow.forEach(entity->
			{
				if (entity.isInWater()&&!entity.onGround &&(entity.getMotion().x>0 || entity.getMotion().z>0))
				{
					entity.setMotion(entity.getMotion().scale(1.6F));
				}
			});

		}
		return event;
	}
	@Override
	public boolean getAccumulateable() {
		// TODO Auto-generated method stub
		return false;
	}

}
