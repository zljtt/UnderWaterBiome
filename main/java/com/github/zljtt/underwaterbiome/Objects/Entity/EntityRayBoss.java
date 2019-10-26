package com.github.zljtt.underwaterbiome.Objects.Entity;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.ServerBossInfo;
import net.minecraft.world.World;

public class EntityRayBoss extends EntityRay
{
	 private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
	public EntityRayBoss(EntityType<? extends EntityFishBase> entityTypeIn, World worldIn) 
	{
		super(entityTypeIn, worldIn);
	}
	@Override
	protected void registerGoals() 
	{
	    super.registerGoals();
	    this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 2.5D, false));
	    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 2, true, false, this::shouldAttack));
	}
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
	    this.getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(2D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)2F);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
	    this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}
	@Override
	public void tick() 
	{
		if (this.collided)
		{
			for (Direction d : Reference.DIRECTIONS)
			{
				BlockPos pos = this.getPosition().offset(d);
				if (this.getEntityWorld().getBlockState(pos).isSolid()&&!this.getEntityWorld().getBlockState(pos).getBlock().equals(Blocks.SAND))
				{
					this.getEntityWorld().setBlockState(pos, Blocks.SAND.getDefaultState());
				}
			}
        }
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		super.tick();
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
}
