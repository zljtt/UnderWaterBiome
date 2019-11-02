package com.github.zljtt.underwaterbiome.Objects.Entity;

import com.github.zljtt.underwaterbiome.Utils.Interface.IFish;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityRay extends EntityFishBase implements IMob,IFish
{
	public EntityRay(EntityType<? extends EntityFishBase> entityTypeIn, World worldIn) 
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
	    this.getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(1D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)1F);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	    this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}
	
	static class MoveHelperController extends MovementController {
	      private final EntityRay fish;

	      MoveHelperController(EntityRay entityShark) {
	         super(entityShark);
	         this.fish = entityShark;
	      }

	      public void tick() 
	      {
	         if (this.fish.areEyesInFluid(FluidTags.WATER)) 
	         {
	        	 if (!this.fish.collidedVertically)
		         {
			         this.fish.setMotion(this.fish.getMotion().add(0.0D, -0.005D, 0.0D));
		         }
	            else
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
//	         else 
//	         {
//		         this.fish.setMotion(0.0D, -0.005D, 0.0D);
////		         this.fish.setAIMoveSpeed(0.0F);
//	         }
	         if (!this.fish.onGround) 
             {
                this.fish.setMotion(this.fish.getMotion().add(0.0D, -0.008D, 0.0D));
                super.tick();
             }
	      }
	   }


}
