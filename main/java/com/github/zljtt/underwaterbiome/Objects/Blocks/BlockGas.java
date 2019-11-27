package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockGas extends BlockBase
{
	public static final IntegerProperty GAS = IntegerProperty.create("gas", 0, 2);

	public BlockGas(String name, Properties porperty, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, porperty, true, needBlueprint, type, difficulty);
	    this.setDefaultState(this.stateContainer.getBaseState().with(GAS, 2));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		builder.add(GAS);
		super.fillStateContainer(builder);
	}
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		double d0 = (double)pos.getX()+rand.nextDouble();
	    double d1 = (double)pos.getY() + 1D+rand.nextDouble();
	    double d2 = (double)pos.getZ()+rand.nextDouble();
		if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.WATER && rand.nextInt(3) < stateIn.get(GAS))
		{
		      worldIn.addParticle(ParticleTypes.BUBBLE, d0, d1, d2, 0.0D, 0.2D, 0.0D);
		}
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) 
	{
		Item item = player.getHeldItem(handIn).getItem();
		if (item.equals(Items.FLINT_AND_STEEL) || item.equals(Items.TORCH)|| item.equals(ItemInit.WATER_TORCH))
		{
			worldIn.createExplosion(player, pos.getX(), pos.getY(), pos.getZ(), 2F, false, Explosion.Mode.DESTROY);
		}
		else if(item.equals(Items.GLASS_BOTTLE) || item.equals(ItemInit.CONICAL_FLASK))
		{
			player.getHeldItem(handIn).shrink(1);
			ItemEntity entity = new ItemEntity(worldIn, 
					pos.getX()+0.5F,
					pos.getY()+1.1F, 
					pos.getZ()+0.5F,
					new ItemStack(ItemInit.METHANE_BOTTLE));
			worldIn.addEntity(entity);
			if (state.get(GAS) ==0)
			{
				worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
			}
			else
				worldIn.setBlockState(pos, state.with(GAS,state.get(GAS)-1));

		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
	
	

}
