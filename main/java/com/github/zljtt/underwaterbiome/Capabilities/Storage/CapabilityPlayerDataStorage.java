package com.github.zljtt.underwaterbiome.Capabilities.Storage;

import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapabilityPlayerDataStorage implements IStorage<IPlayerData>
{
	@Override
    public INBT writeNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side)
    {
		
		CompoundNBT nbt = new CompoundNBT();
		nbt.putDouble("temperature", instance.getTemperature());
		nbt.putDouble("cold_prof", instance.getBaseColdProf());
		nbt.putDouble("heat_prof", instance.getBaseHeatProf());
		nbt.putFloat("reduce_oxy", instance.getReduceOxyConsumption());
		nbt.putBoolean("ignore_pressure", instance.getIgnorePressure());
		nbt.putInt("chemistry", instance.getKnowledgePoints().getChemistry());
		nbt.putInt("biology", instance.getKnowledgePoints().getBiology());
		nbt.putInt("physics", instance.getKnowledgePoints().getPhysics());
		nbt.putInt("occult", instance.getKnowledgePoints().getOccult());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side, INBT nbt)
    {
    	CompoundNBT com = (CompoundNBT)nbt;
        instance.setTemperature(com.getDouble("temperature"));
        instance.setBaseColdProf(com.getDouble("cold_prof"));
        instance.setBaseHeatProf(com.getDouble("heat_prof"));
        instance.setReduceOxyConsumption(com.getFloat("reduce_oxy"));
        instance.setIgnorePressure(com.getBoolean("ignore_pressure"));
        instance.getKnowledgePoints().setChemistry(com.getInt("chemistry"));
        instance.getKnowledgePoints().setBiology(com.getInt("biology"));
        instance.getKnowledgePoints().setPhysics(com.getInt("physics"));
        instance.getKnowledgePoints().setOccult(com.getInt("occult"));

    }
}
