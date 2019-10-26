package com.github.zljtt.underwaterbiome.Objects.Entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class EntityConchSeaGrass extends EntityConch
{

	public EntityConchSeaGrass(EntityType<EntityConchSeaGrass> entityTypeIn, World worldIn) 
	{
		super(entityTypeIn, worldIn);
		
	}
	@Override
	protected void registerGoals() 
	{
	    this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
	    this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 0.5D));
	    this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
	    this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
	    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 2, true, false, this::shouldAttack));
	}
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(0.6D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.6F);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
	    this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
	}
	@Override
	public boolean attackEntityAsMob(Entity entityIn) 
	{
		if (entityIn!=null && entityIn instanceof LivingEntity)
		{
			LivingEntity entity = (LivingEntity) entityIn;
			entity.addPotionEffect(new EffectInstance(Effects.POISON,60));
		}
		return super.attackEntityAsMob(entityIn);
	}
	static class PoisonAttackGoal extends MeleeAttackGoal
	{

		public PoisonAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) 
		{
			super(creature, speedIn, useLongMemory);
		}
		
	}

}
