package com.github.zljtt.underwaterbiome.Objects.Entity;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.FindWaterGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFishBase extends WaterMobEntity
{

	@SuppressWarnings("unchecked")
	public EntityFishBase(EntityType<? extends EntityFishBase> entityTypeIn, World worldIn) 
	{
		super((EntityType<? extends WaterMobEntity>) entityTypeIn, worldIn);
	    this.moveController = new MoveHelperController(this);

	}
	
	@Override
	protected void registerGoals() 
	{
	    super.registerGoals();
	    this.goalSelector.addGoal(0, new FindWaterGoal(this));
//	    this.goalSelector.addGoal(2, new SwimGoal(this));
//	    this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
	    this.goalSelector.addGoal(3, new RandomSwimmingGoal(this,1.0D, 40));
//	    this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
//	    this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));

	}
	@Override
	protected PathNavigator createNavigator(World worldIn) 
	{
	      return new SwimmerPathNavigator(this, worldIn);
	}
	@Override
	public void livingTick() 
	{
	      if(!this.isInWater() && this.onGround && this.collidedVertically) 
	      {
	         this.setMotion(this.getMotion().add((double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.05F)));
	         this.onGround = false;
	         this.isAirBorne = true;
	      }		
	      super.livingTick();
	}
	public boolean shouldAttack(@Nullable LivingEntity entity) 
	{
	      if (entity != null) 
	      {
	         return entity.isInWater();
	      } else 
	      {
	         return false;
	      }
	   }
	
	static class MoveHelperController extends MovementController {
	      private final EntityFishBase fish;

	      MoveHelperController(EntityFishBase entityShark) {
	         super(entityShark);
	         this.fish = entityShark;
	      }

	      public void tick() 
	      {
	         if (this.fish.areEyesInFluid(FluidTags.WATER)) 
	         {
	            this.fish.setMotion(this.fish.getMotion().add(0.0D, 0.005D, 0.0D));
	         }

	         if (this.action == MovementController.Action.MOVE_TO && !this.fish.getNavigator().noPath()) 
	         {
	            double d0 = this.posX - this.fish.posX;
	            double d1 = this.posY - this.fish.posY;
	            double d2 = this.posZ - this.fish.posZ;
	            double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
	            d1 = d1 / d3;
	            float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.fish.rotationPitch = this.limitAngle(this.fish.rotationPitch, f, 5.0F);
	            this.fish.rotationYaw = this.limitAngle(this.fish.rotationYaw, f, 10.0F);
//	            this.fish.rotationPitch = this.limitAngle(this.fish.rotationPitch, f, 10.0F);
	            
	            this.fish.renderYawOffset = this.fish.rotationYaw;
	            float f1 = (float)(this.speed * this.fish.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
	            this.fish.setAIMoveSpeed(MathHelper.lerp(0.125F, this.fish.getAIMoveSpeed(), f1));
	            this.fish.setMotion(this.fish.getMotion().add(0.0D, (double)this.fish.getAIMoveSpeed() * d1 * 0.1D, 0.0D));
	         } 
	         else 
	         {
	            this.fish.setAIMoveSpeed(0.0F);
	         }
	      }
	   }
	
	

}
