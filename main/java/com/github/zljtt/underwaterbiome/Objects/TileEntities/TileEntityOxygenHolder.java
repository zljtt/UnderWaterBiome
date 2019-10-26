package com.github.zljtt.underwaterbiome.Objects.TileEntities;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityOxygenProvider;
import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockOxygenHolder;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.IOxygen;

import net.minecraft.block.Blocks;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ObjectHolder;

public class TileEntityOxygenHolder extends TileEntity implements ITickableTileEntity
{
	@ObjectHolder("underwaterbiome:oxygen_holder")	
    public static TileEntityType<TileEntityOxygenHolder> TYPE;
	public static final float OXYGEN_HOLDER_MAX = 1000;

	public TileEntityOxygenHolder() 
	{
		super(TYPE);
	}

	
	@Override
	public void tick() 
	{	
		float rate = calculateAbsorbRate(this.getWorld(), this.getPos());
		IOxygen cap_o =  this.getCapability(CapabilityOxygenProvider.PARTICLE_CAP, null).orElse(null);
		float oxy = cap_o.getOxygenFloat();
		
		cap_o.addOxygen(Math.min(rate, OXYGEN_HOLDER_MAX-oxy));;
		if(rate!=0&& oxy!=OXYGEN_HOLDER_MAX)
		{
			setStateByOxy(this.getWorld(), this.getPos(), oxy);
		}
	}
	
//	@Override
//    public void read(CompoundNBT tag) 
//	{
//		IOxygen cap_o =  this.getCapability(CapabilityOxygenProvider.PARTICLE_CAP, null).orElse(null);
//		if (cap_o!=null)
//		{
//			cap_o.setOxygen(tag.getFloat("oxygen"));
//	    	System.out.print("read"+tag.getFloat("oxygen"));
//		}
//        super.read(tag);
//    }
//
//    @Override
//    public CompoundNBT write(CompoundNBT tag) 
//    {
//    	IOxygen cap_o =  this.getCapability(CapabilityOxygenProvider.PARTICLE_CAP, null).orElse(null);
//		if (cap_o!=null)
//		{
//			tag.putFloat("oxygen", cap_o.getOxygenFloat());
//	    	System.out.print("write"+cap_o.getOxygenFloat());
//		}
//  
//        return super.write(tag);
//    }
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) 
    {
    	this.read(pkt.getNbtCompound());
    	super.onDataPacket(net, pkt);
    }

	public static float calculateAbsorbRate(World world, BlockPos pos)
	{
		float water = 0;
		float air = 0;

		for (Direction direction: Reference.DIRECTIONS)
		{
			if (world.getBlockState(pos.offset(direction)).getBlock() ==Blocks.WATER)
			{
				water+=0.1;
			}
			if (world.getBlockState(pos.offset(direction)).getBlock() ==Blocks.AIR)
			{
				air+=0.3;
			}
		}
		return (water+air)/3;
	}
	public static void setStateByOxy(World world, BlockPos pos, float oxy)
	{
		int level = (int) Math.floor(10*oxy/OXYGEN_HOLDER_MAX);
		if (world.getBlockState(pos).getBlock() instanceof BlockOxygenHolder && level < 10)
		{
			Direction dir = world.getBlockState(pos).get(BlockOxygenHolder.FACING);
			world.setBlockState(pos, BlockInit.OXYGEN_HOLDER.getDefaultState().with(BlockOxygenHolder.FACING, dir).with(BlockOxygenHolder.OXYGEN_LEVEL, level));
		}
	}

//	public void increaseOxygen(float rate) 
//	{
//	}
}
