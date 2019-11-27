package com.github.zljtt.underwaterbiome.Objects.Entity.Goals;

import java.util.EnumSet;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GoToBlockGoal extends Goal 
{
    protected final CreatureEntity entity;
    private double pos_x;
    private double pos_y;
    private double pos_z;
    private final double speedIn;
    private final World world;
    private final Block block;
    private final int test_time;

    public GoToBlockGoal(CreatureEntity entity, Block block, double speed, int time) {
       this.entity = entity;
       this.speedIn = speed;
       this.block = block;
       this.world = entity.world;
       this.test_time = time;
       this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() 
    {
       if (!this.entity.isInWater()) 
       {
          return false;
       } 
       else 
       {
          Vec3d vec3d = this.getVec3d();
          if (vec3d == null)
          {
             return false;
          } 
          else 
          {
             this.pos_x = vec3d.x;
             this.pos_y = vec3d.y;
             this.pos_z = vec3d.z;
             return true;
          }
       }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
       return !this.entity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
       this.entity.getNavigator().tryMoveToXYZ(this.pos_x, this.pos_y, this.pos_z, this.speedIn);
    }

    @Nullable
    private Vec3d getVec3d() 
    {
       Random random = this.entity.getRNG();
       BlockPos blockpos = new BlockPos(this.entity.posX, this.entity.getBoundingBox().minY, this.entity.posZ);

       for(int i = 0; i < test_time; ++i) 
       {
//           BlockPos blockpos1 = this.world.block;
          BlockPos blockpos1 = blockpos.add(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);
          if (this.world.getBlockState(blockpos1).getBlock() == block) 
          {
             return new Vec3d((double)blockpos1.getX(), (double)blockpos1.getY(), (double)blockpos1.getZ());
          }
       }

       return null;
    }
 }
