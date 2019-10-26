package com.github.zljtt.underwaterbiome.Capabilities;

import java.util.ArrayList;
import java.util.List;

import com.github.zljtt.underwaterbiome.Utils.Interface.IBluePrint;

public class CapabilityBluePrint implements IBluePrint
{
	public List<String> unlocked_blueprints = new ArrayList<String>();

	@Override
	public List<String> getUnlockedBluePrint() 
	{
		return unlocked_blueprints;
	}
	@Override
	public void setUnlockedBluePrint(List<String> print) 
	{
		unlocked_blueprints.clear();
		unlocked_blueprints = print;
	}
	
	@Override
	public void unlockBluePrint(String print) 
	{
		unlocked_blueprints.add(print);
	}
	
	@Override
	public void lockAllBluePrint() 
	{
		unlocked_blueprints.clear();;
	}


	
	
}
