package com.github.zljtt.underwaterbiome.Objects.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

public class EntityFishSturgeon extends EntityFishBase implements IMob
{
	public EntityFishSturgeon(EntityType<? extends EntityFishBase> entityTypeIn, World worldIn) 
	{
		super(entityTypeIn, worldIn);
	}
	@Override
	protected void registerGoals() 
	{
	    super.registerGoals();
	    this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 2.5D, false));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(2D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)2F);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	    this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}


}
