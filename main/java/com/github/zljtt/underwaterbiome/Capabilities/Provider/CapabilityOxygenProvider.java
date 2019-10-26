package com.github.zljtt.underwaterbiome.Capabilities.Provider;

import com.github.zljtt.underwaterbiome.Utils.Interface.IOxygen;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityOxygenProvider implements ICapabilitySerializable<INBT>
{
	@CapabilityInject(IOxygen.class)
    public static final Capability<IOxygen> PARTICLE_CAP = null;

    private IOxygen instance = PARTICLE_CAP.getDefaultInstance();


    @Override
    public void deserializeNBT(INBT nbt)
    {
    	PARTICLE_CAP.getStorage().readNBT(PARTICLE_CAP, this.instance, null, nbt);
    }

	
	@Override
	public INBT serializeNBT() 
	{
        return PARTICLE_CAP.getStorage().writeNBT(PARTICLE_CAP, this.instance, null);
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		return cap == PARTICLE_CAP? LazyOptional.of(() -> instance).cast():LazyOptional.empty();
	}

}
