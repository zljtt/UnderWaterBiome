package com.github.zljtt.underwaterbiome.Objects.Entity;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Objects.Entity.EntityConch.ConchPathNavigator;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityWaterSkeleton extends DrownedEntity implements IMob
{

	@SuppressWarnings("unchecked")
	public EntityWaterSkeleton(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super((EntityType<? extends DrownedEntity>) type, worldIn);
		this.stepHeight = 1.0F;
	    this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.inventoryHandsDropChances[EquipmentSlotType.MAINHAND.getIndex()] = 0.0F;

//	    this.waterNavigator = new SwimmerPathNavigator(this, worldIn);
	}
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(0.4D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.4F);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	    this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
	}
	public CreatureAttribute getCreatureAttribute() {
	      return CreatureAttribute.UNDEAD;
	   }

	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) 
	{
	         int i = this.rand.nextInt(16);
	         if (i < 10) 
	         {
	            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
	            this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.IRON_CHESTPLATE));

	         }
	         else 
	         {
	            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
	            this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
	         }

	   }
	@Override
	public boolean addPotionEffect(EffectInstance p_195064_1_) 
	{
		if (p_195064_1_.getEffectName().equals(new EffectInstance(Effects.POISON).getEffectName())
				||p_195064_1_.getEffectName().equals(new EffectInstance(Effects.WEAKNESS).getEffectName()))
		{
			return false;
		}
		return super.addPotionEffect(p_195064_1_);
	}


}
