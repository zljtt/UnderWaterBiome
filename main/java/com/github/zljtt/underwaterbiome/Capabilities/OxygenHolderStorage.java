package com.github.zljtt.underwaterbiome.Capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class OxygenHolderStorage  extends EnergyStorage  implements INBTSerializable<CompoundNBT>
{
	public OxygenHolderStorage(int capacity, int maxTransfer) 
	{
        super(capacity, maxTransfer);
    }
	public void setOxygen(int value)
	{
		this.energy= value;
	}
	
	@Override
    public CompoundNBT serializeNBT() 
	{
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("oxygen", getEnergyStored());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) 
    {
        setEnergy(nbt.getInt("oxygen"));
    }
	private void setEnergy(int int1) 
	{
		this.energy = 		int1;
	}
}
