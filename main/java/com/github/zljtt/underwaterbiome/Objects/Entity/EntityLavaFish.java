package com.github.zljtt.underwaterbiome.Objects.Entity;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Objects.Entity.Goals.GoToBlockGoal;
import com.github.zljtt.underwaterbiome.Utils.Interface.IFish;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityLavaFish extends EntityFishBase implements IMob,IFish
{
	private static final DataParameter<Boolean> IS_LAVA = EntityDataManager.createKey(EntityLavaFish.class, DataSerializers.BOOLEAN);

	public EntityLavaFish(EntityType<? extends EntityFishBase> entityTypeIn, World worldIn) 
	{
		super(entityTypeIn, worldIn);
		this.setIsLava(false);
	}
	@Override
	protected void registerGoals() 
	{
	    super.registerGoals();
	    this.goalSelector.addGoal(2, new GoToMagmaBlockGoal(this, 2.5D));
	    this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(1D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)1F);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	    this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}
	@Override
	public void tick() 
	{
		if (this.getHealth() <= this.getMaxHealth()/2F)
		{
			this.setIsLava(true);
			
		}
		if(this.getIsLava() && this.world.getBlockState(new BlockPos(this)).getBlock() == Blocks.BUBBLE_COLUMN)
		{
			this.addPotionEffect(new EffectInstance(Effects.REGENERATION,200,1));
			this.addPotionEffect(new EffectInstance(Effects.SPEED,200,1));
			this.inWater = true;
		}
		super.tick();
	}
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) 
	{
		if (source.getTrueSource() instanceof PlayerEntity)
		{
			if (!(((PlayerEntity)source.getTrueSource()).getHeldItemMainhand().getItem() instanceof PickaxeItem) && !this.getIsLava())
			{
				return false;
			}
		}
		if (source.isFireDamage())
		{
			return false;
		}
		return super.attackEntityFrom(source, amount);
	}
	
	public boolean getIsLava() 
	{
	      return this.dataManager.get(IS_LAVA);
	   }

	   public void setIsLava(boolean p_211137_1_) {
	      this.dataManager.set(IS_LAVA, p_211137_1_);
	   }
	   @Override
	   protected void registerData() 
	   {
	      super.registerData();
	      this.dataManager.register(IS_LAVA, false);
	   }
	
	
	
	
	
	
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) 
	{
	      float f = (float)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
	      float f1 = (float)this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).getValue();
	      if (entityIn instanceof LivingEntity) 
	      {
	         f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((LivingEntity)entityIn).getCreatureAttribute());
	         f1 += (float)EnchantmentHelper.getKnockbackModifier(this);
	      }
	      DamageSource source = DamageSource.causeMobDamage(this);
	      boolean flag = entityIn.attackEntityFrom(this.getIsLava()?source.setFireDamage(): source, f);
	      
	      if (flag) 
	      {
	         if (f1 > 0.0F && entityIn instanceof LivingEntity) 
	         {
	            ((LivingEntity)entityIn).knockBack(this, f1 * 0.5F, (double)MathHelper.sin(this.rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F))));
	            this.setMotion(this.getMotion().mul(0.6D, 1.0D, 0.6D));
	         }

	         if (entityIn instanceof PlayerEntity) 
	         {
	            PlayerEntity playerentity = (PlayerEntity)entityIn;
				IPlayerData cap_o =  playerentity.getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
				cap_o.increaseTemperature(f*15, true);
	            ItemStack itemstack = this.getHeldItemMainhand();
	            ItemStack itemstack1 = playerentity.isHandActive() ? playerentity.getActiveItemStack() : ItemStack.EMPTY;
	            if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.canDisableShield(itemstack1, playerentity, this) && itemstack1.isShield(playerentity)) {
	               float f2 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;
	               if (this.rand.nextFloat() < f2) 
	               {
	                  playerentity.getCooldownTracker().setCooldown(itemstack.getItem(), 100);
	                  this.world.setEntityState(playerentity, (byte)30);
	               }
	            }
	         }

	         this.applyEnchantments(this, entityIn);
	      }

	      return flag;
	   }
	
	static class GoToMagmaBlockGoal extends GoToBlockGoal
	{
		
		public GoToMagmaBlockGoal(EntityLavaFish entity, double speed) 
		{
			super(entity, Blocks.MAGMA_BLOCK, speed, 50);
		}
		
		@Override
		public boolean shouldExecute() 
		{
			if (entity instanceof EntityLavaFish)
			{
				return ((EntityLavaFish) entity).getIsLava() && super.shouldExecute();
			}
			return false;
		}
	}

}
