package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityOxygenProvider;
import com.github.zljtt.underwaterbiome.Handlers.EventHandler;
import com.github.zljtt.underwaterbiome.Handlers.OxygenHandler;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockBase;
import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityOxygenHolder;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.IOxygen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockOxygenHolder extends BlockBase
{

	public static final IntegerProperty OXYGEN_LEVEL = IntegerProperty.create("oxygen_level", 0, 9);
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	private Random rand = new Random();
	
	public BlockOxygenHolder(String name, Properties porperty, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, porperty, true, needBlueprint, type,  difficulty);
	    this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OXYGEN_LEVEL, 0));
	}
	
	
	@Override
	public boolean addDestroyEffects(BlockState state, World world, BlockPos pos, ParticleManager manager) 
	{
		TileEntity tile = world.getTileEntity(pos);
		float j= 0;
		if (tile instanceof TileEntityOxygenHolder)
		{
			IOxygen cap_o =  tile.getCapability(CapabilityOxygenProvider.PARTICLE_CAP, null).orElse(null);
			if (cap_o!=null)
			{
				j = cap_o.getOxygen();
			}
		}
		for (int i =0; i<j/10;i++)
		{
			world.addParticle(ParticleTypes.BUBBLE, (double)pos.getX()+rand.nextDouble()*2-rand.nextDouble()*0.5, (double)pos.getY()+rand.nextDouble()*2-rand.nextDouble()*0.5, (double)pos.getZ()+rand.nextDouble()*2-rand.nextDouble()*0.5, 0.0D, 0.0D, 0.0D);
		}
		return j!=0;
	}
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) 
	{
		if (OxygenHandler.chargeableItem().contains(player.getHeldItem(handIn).getItem()))
		{
			ItemStack stack = player.getHeldItem(handIn);
 			IOxygen cap_itemstack =  stack.getCapability(CapabilityOxygenProvider.PARTICLE_CAP, null).orElse(null);
 			
 			if (cap_itemstack!=null)
 			{
 				TileEntity tile = worldIn.getTileEntity(pos);
 				IOxygen cap_tile =  tile.getCapability(CapabilityOxygenProvider.PARTICLE_CAP, null).orElse(null);
 				if (cap_tile!=null)
 				{
 	 				int needed = EventHandler.getMaxOxygenValue(stack)-cap_itemstack.getOxygen();
 	 				int holder_left = (int)Math.floor(cap_tile.getOxygenFloat());
 	 				int d = Math.min(holder_left, needed);
 	 				cap_itemstack.addOxygen(d);
	 				cap_tile.minusOxygen(d);
	 				
 	 				System.out.println("oxy_left"+cap_tile.getOxygenFloat());
 	 				TileEntityOxygenHolder.setStateByOxy(worldIn, pos, cap_tile.getOxygenFloat());
 				}
 				
 			}

		}
		return false;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
	      return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(OXYGEN_LEVEL, 0);
	}
	@Override
    public boolean hasTileEntity(BlockState state) 
	{
        return true;
    }
	@Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
        return new TileEntityOxygenHolder();
    }
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder)
	{
        builder.add(FACING, OXYGEN_LEVEL);
	}

}
