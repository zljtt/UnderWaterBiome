package com.github.zljtt.underwaterbiome.Objects.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.ServerBossInfo;
import net.minecraft.world.World;

public class EntityDrownedBoss extends EntityFishBase implements IMob
{
	private final static int MAX_SUMMON = 5;
	public boolean hasSheld= false;
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
	List<DrownedEntity> list =  new ArrayList<DrownedEntity>();
	   private int delayCounter;
		Random ran = new Random();

	public EntityDrownedBoss(EntityType<? extends EntityDrownedBoss> entityTypeIn, World worldIn) 
	{
		super(entityTypeIn, worldIn);

	}
	@Override
	protected void registerGoals() 
	{
	    super.registerGoals();
	    this.goalSelector.addGoal(2, new EntityDrownedBoss.FollowDrownedGoal(this, 1.0));
	    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 2, true, false, this::shouldAttack));
	    this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setCallsForHelp(DrownedEntity.class));

	}
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(1D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)1F);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
	    this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}
	@Override
	public void tick() 
	{
		super.tick();
		if (list.size()<MAX_SUMMON && this.delayCounter<0)
		{
			DrownedEntity entity =  EntityType.DROWNED.create(world);
			entity.setPosition(this.posX,this.posY,this.posZ);
			world.addEntity(entity);
			list.add(entity);
			for (int i = 0;i<100;i++)
			{
				world.addParticle(ParticleTypes.SMOKE, 
						this.posX+ran.nextDouble()*3-ran.nextDouble()*3, 
						this.posY+ran.nextDouble()*3-ran.nextDouble()*3, 
						this.posZ+ran.nextDouble()*3-ran.nextDouble()*3, 
						ran.nextDouble()-ran.nextDouble(), ran.nextDouble()-ran.nextDouble(), ran.nextDouble()-ran.nextDouble());
			}
			this.delayCounter = 50;
		}

		for (int i = 0; i<list.size();i++)
		{
			if (list.get(i) == null || !list.get(i).isAlive())
			{
				System.out.println("remove"+list.get(i));
       		 	list.remove(i);
       			this.delayCounter = 110;
			}
		}
		if (list.size()<MAX_SUMMON)
		{
			hasSheld = false;
		}
		else hasSheld = true;
		this.delayCounter--;
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());

	}
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) 
	{
		return hasSheld?false:super.attackEntityFrom(source, amount);
	}
	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		super.readAdditional(compound);
	      if (this.hasCustomName()) 
	      {
	         this.bossInfo.setName(this.getDisplayName());
	      }
	 }

	@Override
	 public void setCustomName(@Nullable ITextComponent name) 
	 {
	      super.setCustomName(name);
	      this.bossInfo.setName(this.getDisplayName());
	 }
	 @Override
	 public void addTrackingPlayer(ServerPlayerEntity player)
	 {
	      super.addTrackingPlayer(player);
	      this.bossInfo.addPlayer(player);
	      player.addPotionEffect(new EffectInstance(EffectInit.WATER_CURSE,Integer.MAX_VALUE));
	 }
	 @Override
	 public void removeTrackingPlayer(ServerPlayerEntity player) 
	 {
	      super.removeTrackingPlayer(player);
	      this.bossInfo.removePlayer(player);
	      player.removePotionEffect(EffectInit.WATER_CURSE);
	 }
	 @Override
	 public boolean addPotionEffect(EffectInstance p_195064_1_) {
	      return false;
	   }
	 @Override
	 public boolean isNonBoss() {
	      return false;
	   }
	 
	
	 static class FollowDrownedGoal extends Goal 
	 {
		   private final EntityDrownedBoss entity;
		   private DrownedEntity entity_to_follow;
		   private final double moveSpeed;
		   private int delayCounter;
		   public FollowDrownedGoal(EntityDrownedBoss animal, double speed) {
		      this.entity = animal;
		      this.moveSpeed = speed;
//		      this.entity_to_follow = EntityType.DROWNED.create(animal.world);
		   }

		   /**
		    * Returns whether the EntityAIBase should begin execution.
		    */
		   public boolean shouldExecute() 
		   {
		         DrownedEntity entity_ = null;
		         double d0 = Double.MAX_VALUE;
		         List<DrownedEntity> list = this.entity.world.getEntitiesWithinAABB(DrownedEntity.class, this.entity.getBoundingBox().grow(8.0D, 4.0D, 8.0D));
		         if (list==null) return false;
		         for(DrownedEntity animalentity1 : list) 
		         {
		        	 if (animalentity1!=null && animalentity1.isAlive())
		        	 {
		        		 double d1 = this.entity.getDistanceSq(animalentity1);
			               if (!(d1 > d0)) 
			               {
			                  d0 = d1;
			                  entity_ = animalentity1;
			               }
		        	 }
		         }

		         if (entity_ == null) 
		         {
		            return false;
		         } 
		         else if (d0 < 9.0D)
		         {
		            return false;
		         } 
		         else 
		         {
		            this.entity_to_follow = entity_;
		            return true;
		         }
		      
		   }

		   /**
		    * Returns whether an in-progress EntityAIBase should continue executing
		    */
		   public boolean shouldContinueExecuting() 
		   {
		      if (!this.entity_to_follow.isAlive())
		      {
		         return false;
		      } else 
		      {
		         double d0 = this.entity.getDistanceSq(this.entity_to_follow);
		         return !(d0 < 9.0D) && !(d0 > 256.0D);
		      }
		   }

		   public void startExecuting() 
		   {
		      this.delayCounter = 0;
		   }

		   public void resetTask() {
		      this.entity_to_follow = null;
		   }

		   /**
		    * Keep ticking a continuous task that has already been started
		    */
		   public void tick() {
		      if (--this.delayCounter <= 0) 
		      {
		         this.delayCounter = 10;
		         this.entity.getNavigator().tryMoveToEntityLiving(this.entity_to_follow, this.moveSpeed);
		      }
		   }
	 }


}
