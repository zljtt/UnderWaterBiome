package com.github.zljtt.underwaterbiome.Objects.Entity.Goals;

import java.util.EnumSet;

import com.github.zljtt.underwaterbiome.Objects.Entity.EntityCreeperFish;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.CreeperEntity;

public class SwellGoal  extends Goal {
	   private final EntityCreeperFish fish;
	   private LivingEntity target;

	   public SwellGoal(EntityCreeperFish entitycreeperIn) {
	      this.fish = entitycreeperIn;
	      this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	   }

	   /**
	    * Returns whether the EntityAIBase should begin execution.
	    */
	   public boolean shouldExecute() {
	      LivingEntity livingentity = this.fish.getAttackTarget();
	      return this.fish.getCreeperState() > 0||livingentity != null && this.fish.getDistanceSq(livingentity) < 4.0D;
	   }

	   /**
	    * Execute a one shot task or start executing a continuous task
	    */
	   public void startExecuting() {
	      this.fish.getNavigator().clearPath();
	      this.target = this.fish.getAttackTarget();
	   }

	   /**
	    * Reset the task's internal state. Called when this task is interrupted by another one
	    */
	   public void resetTask() {
	      this.target = null;
	   }

	   /**
	    * Keep ticking a continuous task that has already been started
	    */
	   public void tick() {
	      if (this.target == null) 
	      {
	         this.fish.setCreeperState(-1);
	      } 
	      else if (this.fish.getDistanceSq(this.target) > 25.0D) 
	      {
	         this.fish.setCreeperState(-1);
	      } 
	      else if (!this.fish.getEntitySenses().canSee(this.target)) 
	      {
	         this.fish.setCreeperState(-1);
	      }
	      else 
	      {
	         this.fish.setCreeperState(1);
	      }
	   }
	}