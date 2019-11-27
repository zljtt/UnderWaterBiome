package com.github.zljtt.underwaterbiome.Objects.Features;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FeatureMangrove extends AbstractTreeFeature<NoFeatureConfig>
{
	private static final BlockState MANGROVE_LEAF(IWorldGenerationReader worldIn, BlockPos pos)
	{
		if (worldIn.hasBlockState(pos, state->state.getBlock()==Blocks.WATER))
		{
			return BlockInit.MANGROVE_LEAF.getDefaultState().with(BlockStateProperties.WATERLOGGED, true);
		}
		else return BlockInit.MANGROVE_LEAF.getDefaultState().with(BlockStateProperties.WATERLOGGED, false);
	}
	private static final BlockState MANGROVE_LOG = BlockInit.MANGROVE_LOG.getDefaultState();

	public FeatureMangrove(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49920_1_, boolean doBlockNofityOnPlace) 
	{
		super(p_i49920_1_, doBlockNofityOnPlace);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	protected boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position,
//			MutableBoundingBox p_208519_5_) 
//	{
//		// TODO Auto-generated method stub
//		return false;
//	}
	private void crossSection(IWorldGenerationReader worldIn, BlockPos pos, float p_208529_3_, MutableBoundingBox p_208529_4_, Set<BlockPos> changedBlocks) {
	      int i = (int)((double)p_208529_3_ + 0.618D);

	      for(int j = -i; j <= i; ++j) {
	         for(int k = -i; k <= i; ++k) {
	            if (Math.pow((double)Math.abs(j) + 0.5D, 2.0D) + Math.pow((double)Math.abs(k) + 0.5D, 2.0D) <= (double)(p_208529_3_ * p_208529_3_)) {
	               BlockPos blockpos = pos.add(j, 0, k);
	               if (isAirOrLeaves(worldIn, blockpos)||worldIn.hasBlockState(blockpos, state->state.getBlock()==Blocks.WATER)) 
	               {
	                  this.setLogState(changedBlocks, worldIn, blockpos, MANGROVE_LEAF(worldIn,blockpos), p_208529_4_);
	               }
	            }
	         }
	      }

	   }

	   private float treeShape(int p_208527_1_, int p_208527_2_) {
	      if ((float)p_208527_2_ < (float)p_208527_1_ * 0.3F) {
	         return -1.0F;
	      } else {
	         float f = (float)p_208527_1_ / 2.0F;
	         float f1 = f - (float)p_208527_2_;
	         float f2 = MathHelper.sqrt(f * f - f1 * f1);
	         if (f1 == 0.0F) {
	            f2 = f;
	         } else if (Math.abs(f1) >= f) {
	            return 0.0F;
	         }

	         return f2 * 0.5F;
	      }
	   }

	   private float foliageShape(int y) {
	      if (y >= 0 && y < 5) {
	         return y != 0 && y != 4 ? 3.0F : 2.0F;
	      } else {
	         return -1.0F;
	      }
	   }

	   private void foliageCluster(IWorldGenerationReader worldIn, BlockPos pos, MutableBoundingBox p_202393_3_, Set<BlockPos> changedBlocks) {
	      for(int i = 0; i < 5; ++i) {
	         this.crossSection(worldIn, pos.up(i), this.foliageShape(i), p_202393_3_, changedBlocks);
	      }

	   }

	   private int makeLimb(Set<BlockPos> p_208523_1_, IWorldGenerationReader worldIn, BlockPos p_208523_3_, BlockPos p_208523_4_, boolean p_208523_5_, MutableBoundingBox box) {
	      if (!p_208523_5_ && Objects.equals(p_208523_3_, p_208523_4_)) {
	         return -1;
	      } else {
	         BlockPos blockpos = p_208523_4_.add(-p_208523_3_.getX(), -p_208523_3_.getY(), -p_208523_3_.getZ());
	         int i = this.getGreatestDistance(blockpos);
	         float f = (float)blockpos.getX() / (float)i;
	         float f1 = (float)blockpos.getY() / (float)i;
	         float f2 = (float)blockpos.getZ() / (float)i;

	         for(int j = 0; j <= i; ++j) {
	            BlockPos blockpos1 = p_208523_3_.add((double)(0.5F + (float)j * f), (double)(0.5F + (float)j * f1), (double)(0.5F + (float)j * f2));
	            if (p_208523_5_) {
	               this.setLogState(p_208523_1_, worldIn, blockpos1, MANGROVE_LOG.with(LogBlock.AXIS, this.getLoxAxis(p_208523_3_, blockpos1)), box);
	            } else if (!doSupport(worldIn, blockpos1)) {
	               return j;
	            }
	         }

	         return -1;
	      }
	   }

	   private int getGreatestDistance(BlockPos posIn) {
	      int i = MathHelper.abs(posIn.getX());
	      int j = MathHelper.abs(posIn.getY());
	      int k = MathHelper.abs(posIn.getZ());
	      if (k > i && k > j) {
	         return k;
	      } else {
	         return j > i ? j : i;
	      }
	   }

	   private Direction.Axis getLoxAxis(BlockPos p_197170_1_, BlockPos p_197170_2_) {
	      Direction.Axis direction$axis = Direction.Axis.Y;
	      int i = Math.abs(p_197170_2_.getX() - p_197170_1_.getX());
	      int j = Math.abs(p_197170_2_.getZ() - p_197170_1_.getZ());
	      int k = Math.max(i, j);
	      if (k > 0) {
	         if (i == k) {
	            direction$axis = Direction.Axis.X;
	         } else if (j == k) {
	            direction$axis = Direction.Axis.Z;
	         }
	      }

	      return direction$axis;
	   }

	   private void makeFoliage(IWorldGenerationReader worldIn, int p_208525_2_, BlockPos pos, List<FeatureMangrove.FoliageCoordinates> p_208525_4_, MutableBoundingBox p_208525_5_, Set<BlockPos> changedBlocks) {
	      for(FeatureMangrove.FoliageCoordinates FeatureMangrove$foliagecoordinates : p_208525_4_) {
	         if (this.trimBranches(p_208525_2_, FeatureMangrove$foliagecoordinates.getBranchBase() - pos.getY())) {
	            this.foliageCluster(worldIn, FeatureMangrove$foliagecoordinates, p_208525_5_, changedBlocks);
	         }
	      }

	   }

	   private boolean trimBranches(int p_208522_1_, int p_208522_2_) {
	      return (double)p_208522_2_ >= (double)p_208522_1_ * 0.2D;
	   }

	   private void makeTrunk(Set<BlockPos> p_208526_1_, IWorldGenerationReader p_208526_2_, BlockPos p_208526_3_, int p_208526_4_, MutableBoundingBox p_208526_5_) {
	      this.makeLimb(p_208526_1_, p_208526_2_, p_208526_3_, p_208526_3_.up(p_208526_4_), true, p_208526_5_);
	   }

	   private void makeBranches(Set<BlockPos> p_208524_1_, IWorldGenerationReader p_208524_2_, int p_208524_3_, BlockPos p_208524_4_, List<FeatureMangrove.FoliageCoordinates> p_208524_5_, MutableBoundingBox p_208524_6_) {
	      for(FeatureMangrove.FoliageCoordinates FeatureMangrove$foliagecoordinates : p_208524_5_) {
	         int i = FeatureMangrove$foliagecoordinates.getBranchBase();
	         BlockPos blockpos = new BlockPos(p_208524_4_.getX(), i, p_208524_4_.getZ());
	         if (!blockpos.equals(FeatureMangrove$foliagecoordinates) && this.trimBranches(p_208524_3_, i - p_208524_4_.getY())) {
	            this.makeLimb(p_208524_1_, p_208524_2_, blockpos, FeatureMangrove$foliagecoordinates, true, p_208524_6_);
	         }
	      }

	   }

	   private void makeRoots(Set<BlockPos> build, IWorldGenerationReader reader, BlockPos pos, MutableBoundingBox box, Random ran)
	   {
		   int ran0 = 3+ran.nextInt(3);
		   int i = 0;
		   for(int k=0; i<ran0 && k<10;k++)
		   {
			   int x_offset = pos.getX()-3+ran.nextInt(7);
			   int z_offset = pos.getZ()-3+ran.nextInt(7);
			   BlockPos pos0 = new BlockPos(x_offset,pos.getY(),z_offset);
			   BlockPos height = reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos0);
			   if( (pos.getY()-height.getY())<10 && (pos.getY()-height.getY())>0)
			   {
				   this.makeLimb(build, reader, pos, height, true, box);
				   i++;
			   }
		   }
//		   System.out.println("making root: "+i);
		    
	   }
	   public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox box) {
	      Random random = new Random(rand.nextLong());
	      int buttom_offset = 3 + random.nextInt(3);
	      position = position.up(buttom_offset);
	      int i = this.checkLocation(changedBlocks, worldIn, position, 5 + random.nextInt(12), box);
	      if (i == -1)
	      {
	         return false;
	      } 
	      else 
	      {
	         int j = (int)((double)i * 0.618D);
	         if (j >= i) {
	            j = i - 1;
	         }

	         int k = (int)(1.382D + Math.pow(1.0D * (double)i / 13.0D, 2.0D));
	         if (k < 1) {
	            k = 1;
	         }

	         int l = position.getY() + j;
	         int i1 = i - 5;
	         List<FeatureMangrove.FoliageCoordinates> list = Lists.newArrayList();
	         list.add(new FeatureMangrove.FoliageCoordinates(position.up(i1), l));

	         for(; i1 >= 0; --i1) 
	         {
	            float f = this.treeShape(i, i1);
	            if (!(f < 0.0F)) 
	            {
	               for(int j1 = 0; j1 < k; ++j1) 
	               {
	                  double d2 = 1.0D * (double)f * ((double)random.nextFloat() + 0.328D);
	                  double d3 = (double)(random.nextFloat() * 2.0F) * Math.PI;
	                  double d4 = d2 * Math.sin(d3) + 0.5D;
	                  double d5 = d2 * Math.cos(d3) + 0.5D;
	                  BlockPos blockpos = position.add(d4, (double)(i1 - 1), d5);
	                  BlockPos blockpos1 = blockpos.up(5);
	                  if (this.makeLimb(changedBlocks, worldIn, blockpos, blockpos1, false, box) == -1) 
	                  {
	                     int k1 = position.getX() - blockpos.getX();
	                     int l1 = position.getZ() - blockpos.getZ();
	                     double d6 = (double)blockpos.getY() - Math.sqrt((double)(k1 * k1 + l1 * l1)) * 0.381D;
	                     int i2 = d6 > (double)l ? l : (int)d6;
	                     BlockPos blockpos2 = new BlockPos(position.getX(), i2, position.getZ());
	                     if (this.makeLimb(changedBlocks, worldIn, blockpos2, blockpos, false, box) == -1) 
	                     {
	                        list.add(new FeatureMangrove.FoliageCoordinates(blockpos, blockpos2.getY()));
	                     }
	                  }
	               }
	            }
	         }

	         this.makeFoliage(worldIn, i, position, list, box, changedBlocks);
	         this.makeTrunk(changedBlocks, worldIn, position, j, box);
	         this.makeBranches(changedBlocks, worldIn, i, position, list, box);
	         this.makeRoots(changedBlocks, worldIn ,  position, box,random);
	         return true;
	      }
	   }

	   private int checkLocation(Set<BlockPos> p_208528_1_, IWorldGenerationReader p_208528_2_, BlockPos pos, int ran, MutableBoundingBox p_208528_5_) 
	   {
		   int i = this.makeLimb(p_208528_1_, p_208528_2_, pos, pos.up(ran - 1), false, p_208528_5_);
	       if (i == -1) 
	       {
	            return ran;
	         } 
	       else 
	       {
	            return i < 6 ? -1 : i;
	      }
	   }

	   static class FoliageCoordinates extends BlockPos {
	      private final int branchBase;

	      public FoliageCoordinates(BlockPos pos, int p_i45635_2_) {
	         super(pos.getX(), pos.getY(), pos.getZ());
	         this.branchBase = p_i45635_2_;
	      }

	      public int getBranchBase() {
	         return this.branchBase;
	      }
	   }
	   protected static boolean doSupport(IWorldGenerationBaseReader reader, BlockPos pos) {
		      if (!(reader instanceof net.minecraft.world.IWorldReader))
		      {
		    	  return reader.hasBlockState(pos, (state) -> 
			      {
			         Block block = state.getBlock();
			         return state.isAir()||block ==Blocks.WATER || state.isIn(BlockTags.LEAVES) || block == Blocks.GRASS_BLOCK || Block.isDirt(block) || block.isIn(BlockTags.LOGS) || block.isIn(BlockTags.SAPLINGS) || block == Blocks.VINE;
			      });
		      }
		      else return reader.hasBlockState(pos, state -> state.getBlock() ==Blocks.WATER||state.canBeReplacedByLogs((net.minecraft.world.IWorldReader)reader, pos)); 
		   }

}
