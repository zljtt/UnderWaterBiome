package com.github.zljtt.underwaterbiome.Objects.Entity;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

public class EntityConch extends CreatureEntity implements IMob
{
	private Random ran= new Random();
//	protected final SwimmerPathNavigator waterNavigator;
	protected final ConchPathNavigator Navigator;
	public EntityConch(EntityType<? extends CreatureEntity> entityTypeIn, World worldIn) 
	{
		super(entityTypeIn, worldIn);
		this.stepHeight = 1.0F;
	      this.moveController = new EntityConch.MoveHelperController(this);
	      this.setPathPriority(PathNodeType.WATER, 0.0F);
//	      this.waterNavigator = new SwimmerPathNavigator(this, worldIn);
	      this.Navigator = new ConchPathNavigator(this, worldIn);
	}
	@Override
	protected void registerGoals() 
	{
//	    super.registerGoals();
	    this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
	    this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 0.5D));
	    this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
	    this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
	    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 2, true, false, this::shouldAttack));
	}
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(0.6D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.6F);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
	    this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
	}
	@Override
	   public boolean canBreatheUnderwater() {
		      return true;
		   }
	@Override
	   public CreatureAttribute getCreatureAttribute() {
		      return CreatureAttribute.WATER;
		   }
	@Override
	public boolean isPushedByWater() {
	      return !this.isSwimming();
	   }

	@Override
	public void travel(Vec3d p_213352_1_) 
	{
	      if (this.isServerWorld() && this.isInWater() && this.getAttackTarget()!=null&&this.getAttackTarget().isInWater()) 
	      {
	         this.moveRelative(0.01F, p_213352_1_);
	         this.move(MoverType.SELF, this.getMotion());
	         this.setMotion(this.getMotion().scale(0.9D));
	      } 
	      else 
	      {
	         super.travel(p_213352_1_);
	      }

	   }
	@Override
	public void updateSwimming() {
	      if (!this.world.isRemote) {
//	         if (this.isServerWorld() && this.isInWater() && this.getAttackTarget()!=null&&this.getAttackTarget().isInWater()) 
//	         {
//	            this.navigator = this.waterNavigator;
//	            this.setSwimming(true);
//	         } else {
//	            this.navigator = this.groundNavigator;
//	            this.setSwimming(false);
//	         }
	    	  this.navigator = this.Navigator;
	          this.setSwimming(false);
	      }

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
	
	@Override
	protected PathNavigator createNavigator(World worldIn) 
	{
	      return new GroundPathNavigator(this, worldIn);
	}
	@Override
	public void livingTick() 
	{
		if ( this.isJumping)
		{
       	 	world.addParticle(ParticleTypes.BUBBLE, this.posX+rand.nextDouble()/2-rand.nextDouble()/2, this.posY, this.posZ+rand.nextDouble()/2-rand.nextDouble()/2, 0.0D, 0.0D, 0.0D);
		}
//	      if (this.isInWater() && this.onGround && this.collidedVertically) 
//	      {
//	    	 this.setMotion(this.getMotion().getX(), +0.005 ,this.getMotion().getX());		
//	         this.onGround = false;
//	         this.isAirBorne = true;
//	      }	

	    	  
	    	  
	      
	    super.livingTick();
	}
	
	protected SoundEvent getSwimSound() 
	{
	      return SoundEvents.ENTITY_HOSTILE_SWIM;
	}
	public class ConchPathNavigator extends SwimmerPathNavigator 
	{
	    private final EntityConch entityIn;
	    private final World worldIn;
		public ConchPathNavigator(EntityConch entityIn, World worldIn) 
		{
			super(entityIn, worldIn);
			this.entityIn = entityIn;
			this.worldIn = worldIn;
		}
//		@Override
//		public Path getPathToPos(BlockPos pos, int p_179680_2_) 
//		{
//			if (this.world.getBlockState(pos).getBlock()==Blocks.WATER) {
//				BlockPos blockpos;
//				for(blockpos = pos.down(); blockpos.getY() > 0 && this.world.getBlockState(blockpos).getBlock()==Blocks.WATER; blockpos = blockpos.down()) {
//					;
//				}
//				
//				if (blockpos.getY() > 0) {
//					return super.getPathToPos(blockpos.up(), p_179680_2_);
//				}
//				
//				while(blockpos.getY() < this.world.getHeight() && this.world.getBlockState(blockpos).getBlock()==Blocks.WATER) {
//					blockpos = blockpos.up();
//				}
//
//				pos = blockpos;
//			}
//			
//			if (!this.world.getBlockState(pos).getMaterial().isSolid()) {
//		         return super.getPathToPos(pos, p_179680_2_);
//			} else {
//				BlockPos blockpos1;
//				for(blockpos1 = pos.up(); blockpos1.getY() < this.world.getHeight() && this.world.getBlockState(blockpos1).getMaterial().isSolid(); blockpos1 = blockpos1.up()) {
//		        	 ;
//				}
//
//				return super.getPathToPos(blockpos1, p_179680_2_);
//			}
//		}
		@Override
		public void tick() 
		{
		      ++this.totalTicks;
		      if (this.tryUpdatePath) {
		         this.updatePath();
		      }

		      if (!this.noPath()) 
		      {
		         if (this.canNavigate()) 
		         {
		            this.pathFollow();
		         } 
		         else if (this.currentPath != null && this.currentPath.getCurrentPathIndex() < this.currentPath.getCurrentPathLength()) 
		         {
		            Vec3d vec3d = this.currentPath.getVectorFromIndex(this.entityIn, this.currentPath.getCurrentPathIndex());
		            if (MathHelper.floor(this.entityIn.posX) == MathHelper.floor(vec3d.x) && MathHelper.floor(this.entityIn.posY) == MathHelper.floor(vec3d.y) && MathHelper.floor(this.entityIn.posZ) == MathHelper.floor(vec3d.z))
		            {
		               this.currentPath.setCurrentPathIndex(this.currentPath.getCurrentPathIndex() + 1);
		            }
		         }

		         DebugPacketSender.func_218803_a(this.worldIn, this.entityIn, this.currentPath, this.maxDistanceToWaypoint);
		         if (!this.noPath()) 
		         {
		            Vec3d vec3d1 = this.currentPath.getPosition(this.entityIn);
		            this.entityIn.getMoveHelper().setMoveTo(vec3d1.x, worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR,(int)Math.round(vec3d1.x), (int)Math.round(vec3d1.z))+1, vec3d1.z, this.speed);

//		            this.entity.getMoveHelper().setMoveTo(vec3d1.x, vec3d1.y, vec3d1.z, this.speed);
		         }
		      }
		   }

	}
	static class MoveHelperController extends MovementController 
	{
	      private final EntityConch entity;
	      public MoveHelperController(EntityConch entity) {
	          super(entity);
	          this.entity = entity;
	       }

	       public void tick() 
	       {
	          LivingEntity livingentity = this.entity.getAttackTarget();
	          if (livingentity!=null && livingentity.isInWater() && this.entity.isInWater()) 
	          {
	             if (livingentity != null && livingentity.posY> this.entity.posY+2) 
	             {
//	            	 this.entity.jump();
//	            	 this.action = MovementController.Action.JUMPING;
//	                this.entity.setMotion(this.entity.getMotion().add(0.0D, 0.002D, 0.0D));
	             }

	             if ((this.action != MovementController.Action.MOVE_TO&&this.action != MovementController.Action.JUMPING) || this.entity.getNavigator().noPath()) {
	                this.entity.setAIMoveSpeed(0.0F);
	                return;
	             }
	             double d0 = this.posX - this.entity.posX;
	             double d1 = this.posY - this.entity.posY;
	             double d2 = this.posZ - this.entity.posZ;
	             double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
	             d1 = d1 / d3;
	             float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
	             this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, f, 20.0F);
	             this.entity.renderYawOffset = this.entity.rotationYaw;
	             float f1 = (float)(this.speed * this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
	             float f2 = MathHelper.lerp(0.125F, this.entity.getAIMoveSpeed(), f1);
	             this.entity.setAIMoveSpeed(f2);
	             this.entity.setMotion(this.entity.getMotion().add((double)f2 * d0 * 0.005D, (double)f2 * d1 * 0.1D, (double)f2 * d2 * 0.005D));
//	             if(f2 * d1 > 0.1)
	          } 
	          else 
	          {
	             if (!this.entity.onGround) 
	             {
	                this.entity.setMotion(this.entity.getMotion().add(0.0D, -0.008D, 0.0D));
	             }

	             super.tick();
	          }

	       }
	}
}
