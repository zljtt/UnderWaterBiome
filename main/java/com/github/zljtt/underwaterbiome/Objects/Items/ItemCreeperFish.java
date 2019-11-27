package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.Collection;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ItemCreeperFish extends ItemBase
{
	Random ran = new Random();
	public ItemCreeperFish(String name, Properties property, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, property
				, needBlueprint, type, difficulty);
	
		// TODO Auto-generated constructor stub
	}
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) 
	{
		if (ran.nextBoolean())explode(worldIn,  entityLiving);
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
	
	private void explode(World world, LivingEntity entityLiving) 
	{
	      if (!world.isRemote) {
	         world.createExplosion(entityLiving, DamageSource.causeMobDamage(entityLiving), entityLiving.posX, entityLiving.posY, entityLiving.posZ, 2F, false, Explosion.Mode.NONE);
	         this.spawnLingeringCloud(entityLiving);
	      }

	   }
	private void spawnLingeringCloud(LivingEntity entityLiving) {
	      Collection<EffectInstance> collection = entityLiving.getActivePotionEffects();
	      if (!collection.isEmpty()) {
	         AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(entityLiving.world, entityLiving.posX, entityLiving.posY, entityLiving.posZ);
	         areaeffectcloudentity.setRadius(2.5F);
	         areaeffectcloudentity.setRadiusOnUse(-0.5F);
	         areaeffectcloudentity.setWaitTime(10);
	         areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
	         areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());

	         for(EffectInstance effectinstance : collection) {
	            areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
	         }

	         entityLiving.world.addEntity(areaeffectcloudentity);
	      }

	   }

}
