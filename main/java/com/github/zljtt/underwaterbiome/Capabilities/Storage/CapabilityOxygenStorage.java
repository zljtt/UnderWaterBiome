package com.github.zljtt.underwaterbiome.Capabilities.Storage;

import com.github.zljtt.underwaterbiome.Utils.Interface.IOxygen;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapabilityOxygenStorage implements IStorage<IOxygen>
{
	@Override
    public INBT writeNBT(Capability<IOxygen> capability, IOxygen instance, Direction side)
    {
		
		CompoundNBT nbt = new CompoundNBT();
		nbt.putFloat("Oxygen", instance.getOxygenFloat());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IOxygen> capability, IOxygen instance, Direction side, INBT nbt)
    {
    	CompoundNBT com = (CompoundNBT)nbt;
        instance.setOxygen(com.getFloat("Oxygen"));

    }
}
