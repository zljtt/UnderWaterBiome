package com.github.zljtt.underwaterbiome.Objects.Entity;

import com.github.zljtt.underwaterbiome.Utils.Interface.IFish;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class EntityShark extends EntityFishBase implements IMob,IFish
{
	private static final DataParameter<Integer> HUNGER = EntityDataManager.createKey(EntityShark.class, DataSerializers.VARINT);

	public EntityShark(EntityType<? extends EntityFishBase> entityTypeIn, World worldIn) 
	{
		super(entityTypeIn, worldIn);
	}
	@Override
	protected void registerGoals() 
	{
	    super.registerGoals();
	    this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 2D, false));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 2, true, false, this::shouldAttack));
	    this.targetSelector.addGoal(3, new EatFishGoal(this, AbstractFishEntity.class, false));
	}
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(1.2D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)1.5F);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	    this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void livingTick() 
	{
    	  this.setHunger(this.getHunger()+1);
	      if (!this.world.isRemote && this.isAlive() && !this.dead && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) 
	      {
	          for(ItemEntity itementity : this.world.getEntitiesWithinAABB(ItemEntity.class, this.getBoundingBox().grow(1.0D, 1.0D, 1.0D))) 
	          {
	             if (!itementity.removed && !itementity.getItem().isEmpty() && !itementity.cannotPickup()
	            		 && (itementity.getItem().getItem() == Items.PUFFERFISH 
	            		 || itementity.getItem().getItem() == Items.TROPICAL_FISH
	            		 || itementity.getItem().getItem() == Items.COD
	            		 || itementity.getItem().getItem() == Items.SALMON)) 
				{
	            	 this.setHunger(0);
	            	 itementity.remove();
	             }
	          }
	       }	
    	this.world.getProfiler().endSection();

	      super.livingTick();
	}
	protected SoundEvent getSwimSound() 
	{
	      return SoundEvents.ENTITY_HOSTILE_SWIM;
	}
	
	public int getHunger() {
	      return this.dataManager.get(HUNGER);
	   }

	   public void setHunger(int p_211137_1_) {
	      this.dataManager.set(HUNGER, p_211137_1_);
	   }

	   protected void registerData() {
	      super.registerData();
	      this.dataManager.register(HUNGER, 0);
	   }
	
	@SuppressWarnings("rawtypes")
	static class EatFishGoal extends NearestAttackableTargetGoal 
	{
		private final EntityShark shark;
		@SuppressWarnings("unchecked")
		public EatFishGoal(MobEntity p_i50313_1_, Class p_i50313_2_, boolean p_i50313_3_) 
		{
			super(p_i50313_1_, p_i50313_2_, p_i50313_3_);
			this.shark=(EntityShark) p_i50313_1_;
			// TODO Auto-generated constructor stub
		}

		 public boolean shouldExecute() 
		 {
			 return shark.dataManager.get(HUNGER)>600 && super.shouldExecute();
		 }
	}



}
