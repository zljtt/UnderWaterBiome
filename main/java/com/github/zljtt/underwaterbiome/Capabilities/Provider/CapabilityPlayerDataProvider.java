package com.github.zljtt.underwaterbiome.Capabilities.Provider;

import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityPlayerDataProvider implements ICapabilitySerializable<INBT>
{
	@CapabilityInject(IPlayerData.class)
    public static final Capability<IPlayerData> CAP = null;

    private IPlayerData instance = CAP.getDefaultInstance();


    @Override
    public void deserializeNBT(INBT nbt)
    {
    	CAP.getStorage().readNBT(CAP, this.instance, null, nbt);
    }

	
	@Override
	public INBT serializeNBT() 
	{
        return CAP.getStorage().writeNBT(CAP, this.instance, null);
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		return cap == CAP? LazyOptional.of(() -> instance).cast():LazyOptional.empty();
	}

}
