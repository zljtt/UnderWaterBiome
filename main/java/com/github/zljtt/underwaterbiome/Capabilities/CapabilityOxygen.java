package com.github.zljtt.underwaterbiome.Capabilities;

import com.github.zljtt.underwaterbiome.Utils.Interface.IOxygen;

public class CapabilityOxygen implements IOxygen
{
	public float oxygen;
	public int max_oxygen;

	@Override
	public float getOxygenFloat() {
		// TODO Auto-generated method stub
		return oxygen;
	}
	@Override
	public int getOxygen() {
		// TODO Auto-generated method stub
		return (int) Math.floor(oxygen);
	}

	@Override
	public void setOxygen(float value) {
		oxygen=value;		
	}
	@Override
	public void addOxygen(float value) 
	{	
		oxygen+=value;		
	}
	@Override
	public void minusOxygen(float value) 
	{	
		oxygen-=value;		
	}

	
	
}
