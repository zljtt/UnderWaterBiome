package com.github.zljtt.underwaterbiome.Capabilities.Storage;

import java.util.ArrayList;
import java.util.List;

import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
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
		nbt.putBoolean("checked", instance.getChecked());
		nbt.putInt("esc_x", instance.getPosEscape().getX());
		nbt.putInt("esc_y", instance.getPosEscape().getY());
		nbt.putInt("esc_z", instance.getPosEscape().getZ());

		nbt.putInt("chemistry", instance.getKnowledgePoints().getChemistry());
		nbt.putInt("biology", instance.getKnowledgePoints().getBiology());
		nbt.putInt("physics", instance.getKnowledgePoints().getPhysics());
		nbt.putInt("occult", instance.getKnowledgePoints().getOccult());
		nbt.putInt("rest", instance.getRestFree());

		nbt.putInt("nature_anger", instance.getNatureAnger());

		//
		String tag = "";
		if (instance.getUnlockedBiomes().size()>0)tag = instance.getUnlockedBiomes().get(0);
		if (instance.getUnlockedBiomes().size()>1)
		{
			for (int x=1; x<instance.getUnlockedBiomes().size();x++)
			{
				tag += " ";
				tag += instance.getUnlockedBiomes().get(x);
			}
		}
		nbt.putString("unlocked_biomes", tag);
		//
		String tag2 = "";
		if (instance.getUsedItems().size()>0)tag2 =instance.getUsedItems().get(0);
		if (instance.getUsedItems().size()>1)
		{
			for (int x=1; x<instance.getUsedItems().size();x++)
			{
				tag2 += " ";
				tag2 += instance.getUsedItems().get(x);
			}
		}
		nbt.putString("used_items", tag2);

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
        instance.setChecked(com.getBoolean("checked"));
        instance.setPosEscape(new BlockPos(com.getInt("esc_x"),com.getInt("esc_y"),com.getInt("esc_z")));

        instance.getKnowledgePoints().setChemistry(com.getInt("chemistry"));
        instance.getKnowledgePoints().setBiology(com.getInt("biology"));
        instance.getKnowledgePoints().setPhysics(com.getInt("physics"));
        instance.getKnowledgePoints().setOccult(com.getInt("occult"));
        instance.setRestFree(com.getInt("rest"));

        instance.setNatureAnger(com.getInt("nature_anger"));;

        List<String> list = new ArrayList<String>();
    	for(String str: com.getString("unlocked_biomes").split(" "))
    	{
    		if (str!=null ||str!="")list.add(str);
    	}
    	System.out.println("list1:"+list);
        instance.setUnlockedBiomes(list);
        
        List<String> list2 = new ArrayList<String>();
    	for(String str: com.getString("used_items").split(" "))
    	{
    		if (str!=null ||str!="")list2.add(str);
    	}
        instance.setUsedItem(list2);
    	System.out.println("list2:"+list2);

    }
}
