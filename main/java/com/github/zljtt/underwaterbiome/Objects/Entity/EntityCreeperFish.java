package com.github.zljtt.underwaterbiome.Objects.Entity;

import java.util.Collection;

import com.github.zljtt.underwaterbiome.Objects.Entity.Goals.SwellGoal;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.CreeperSwellGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityCreeperFish extends AbstractGroupFishEntity
{
	   private static final DataParameter<Integer> STATE0 = EntityDataManager.createKey(EntityCreeperFish.class, DataSerializers.VARINT);
	   private static final DataParameter<Boolean> IGNITED = EntityDataManager.createKey(EntityCreeperFish.class, DataSerializers.BOOLEAN);

	   private int lastActiveTime;
	   private int timeSinceIgnited;
	   private int fuseTime = 30;
	   private int explosionRadius = 2;
	   
	public EntityCreeperFish(EntityType<? extends AbstractGroupFishEntity> type, World worldIn) 
	{
		super(type, worldIn);
	}
	@Override
	protected void registerGoals() 
	{
	      this.goalSelector.addGoal(2, new SwellGoal(this));
	      this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
	      this.goalSelector.addGoal(5, new EntityCreeperFish.SwimGoal(this));
	      this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	      this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}
	@Override
	protected void registerAttributes() 
	{
	      super.registerAttributes();
	      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6F);
	}
	public void tick()
	{
	      if (this.isAlive())
	      {
	         this.lastActiveTime = this.timeSinceIgnited;

	         int i = this.getCreeperState();
	         if (i > 0 && this.timeSinceIgnited == 0)
	         {
	            this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
	         }

	         if(this.dataManager.get(IGNITED))
	         {
	        	 this.timeSinceIgnited += 2;
	         }
	         else
	         {
		         this.timeSinceIgnited += i; 
	         }
	         if (this.timeSinceIgnited < 0) 
	         {
	            this.timeSinceIgnited = 0;
	         }

	         if (this.timeSinceIgnited >= this.fuseTime)
	         {
	            this.timeSinceIgnited = this.fuseTime;
	            this.explode();
	         }
	      }

	      super.tick();
	   }
	@Override
	protected ItemStack getFishBucket() 
	{
		return null;
	}
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) 
	{
		Boolean can = super.attackEntityFrom(source, amount);
		if (!this.dead && this.isAlive() && !this.removed)
		{
			this.dataManager.set(IGNITED,true);
		}
		return can;
	}
	@OnlyIn(Dist.CLIENT)
	   public float getCreeperFlashIntensity(float partialTicks) 
	{
	      return MathHelper.lerp(partialTicks, (float)this.lastActiveTime, (float)this.timeSinceIgnited) / (float)(this.fuseTime - 2);
	   }
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand)
	{
	      return false;
	   }
	private void explode() 
	{
	      if (!this.world.isRemote) {
	         Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
	         float f = 1.0F;
	         this.dead = true;
	         this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius * f, explosion$mode);
	         this.remove();
	         this.spawnLingeringCloud();
	      }

	   }
	private void spawnLingeringCloud() {
	      Collection<EffectInstance> collection = this.getActivePotionEffects();
	      if (!collection.isEmpty()) {
	         AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.world, this.posX, this.posY, this.posZ);
	         areaeffectcloudentity.setRadius(2.5F);
	         areaeffectcloudentity.setRadiusOnUse(-0.5F);
	         areaeffectcloudentity.setWaitTime(10);
	         areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
	         areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());

	         for(EffectInstance effectinstance : collection) {
	            areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
	         }

	         this.world.addEntity(areaeffectcloudentity);
	      }

	   }
	@Override
	protected SoundEvent getDeathSound() 
	{
	      return SoundEvents.ENTITY_CREEPER_DEATH;
	}
	public void writeAdditional(CompoundNBT compound)
	{
	      super.writeAdditional(compound);
	      compound.putShort("Fuse", (short)this.fuseTime);
	      compound.putByte("ExplosionRadius", (byte)this.explosionRadius);
	   }
	public void readAdditional(CompoundNBT compound) 
	{
	      super.readAdditional(compound);
	      if (compound.contains("Fuse", 99)) 
	      {
	         this.fuseTime = compound.getShort("Fuse");
	      }

	      if (compound.contains("ExplosionRadius", 99)) {
	         this.explosionRadius = compound.getByte("ExplosionRadius");
	      }

	   }
	@Override
	protected SoundEvent getFlopSound() 
	{
		return SoundEvents.ENTITY_COD_FLOP;
	}
	protected void registerData() 
	{
	      super.registerData();
	      this.dataManager.register(STATE0, -1);
	      this.dataManager.register(IGNITED, false);
	   }
	protected boolean doSwim()
	{
		return true;	
	}
	
	public int getCreeperState() {
	      return this.dataManager.get(STATE0);
	   }

	   /**
	    * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
	    */
	   public void setCreeperState(int state) {
	      this.dataManager.set(STATE0, state);
	   }
	static class SwimGoal extends RandomSwimmingGoal 
	{
	      private final EntityCreeperFish fish;

	      public SwimGoal(EntityCreeperFish fish) {
	         super(fish, 1.0D, 40);
	         this.fish = fish;
	      }

	      /**
	       * Returns whether the EntityAIBase should begin execution.
	       */
	      public boolean shouldExecute() 
	      {
	         return this.fish.doSwim() && super.shouldExecute();
	      }
	   }

}
