package com.github.zljtt.underwaterbiome.Capabilities.Storage;

import java.util.ArrayList;
import java.util.List;

import com.github.zljtt.underwaterbiome.Utils.Interface.IBluePrint;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapabilityBluePrintStorage implements IStorage<IBluePrint>
{
	@Override
    public INBT writeNBT(Capability<IBluePrint> capability, IBluePrint instance, Direction side)
    {
		
		CompoundNBT nbt = new CompoundNBT();
		String tag = "";
		for (String string : instance.getUnlockedBluePrint())
		{
			tag += " ";
			tag += string;
		}
		nbt.putString("unlocked_blueprints", tag);
        return nbt;
    }

    @Override
    public void readNBT(Capability<IBluePrint> capability, IBluePrint instance, Direction side, INBT nbt)
    {
    	CompoundNBT com = (CompoundNBT)nbt;
    	List<String> list = new ArrayList<String>();
    	for(String str: com.getString("unlocked_blueprints").split(" "))
    	{
    		list.add(str);
    	}
        instance.setUnlockedBluePrint(list);

    }
}
