package com.github.zljtt.underwaterbiome.Objects.Entity;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Inits.EntityInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public class EntityWaterArrow extends ArrowEntity
{

	public EntityWaterArrow(EntityType<? extends ArrowEntity> entitytype, World world) {
	      super(entitytype, world);
	   }

	@SuppressWarnings("unchecked")
	public EntityWaterArrow(World worldIn, double x, double y, double z) 
	   {
	      super((EntityType<? extends ArrowEntity>) EntityInit.ENTITY_WATER_ARROW, worldIn);
	   }

	@SuppressWarnings("unchecked")
	public EntityWaterArrow(World worldIn, LivingEntity shooter) 
	   {
	      super((EntityType<? extends ArrowEntity>) EntityInit.ENTITY_WATER_ARROW, worldIn);
	   }
	   

	@Override
	protected ItemStack getArrowStack() 
	{
		return new ItemStack(Items.ARROW);
	}
	@Override
	protected float getWaterDrag() 
	{
	      return 1F;
	}

}
