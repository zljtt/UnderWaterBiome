package com.github.zljtt.underwaterbiome.Capabilities;

import com.github.zljtt.underwaterbiome.Handlers.EventHandler;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

public class CapabilityPlayerData implements IPlayerData
{
	public double temperature = 0;
	public double cold_prof = 0;
	public double heat_prof = 0;
	public float oxygen_reduce = 0;

	public KnowledgePoints knowledge = new KnowledgePoints(0,0,0,0);
	public boolean ignore_pressure = false;

	@Override
	public double getTemperature() {
		return temperature;
	}

	@Override
	public void setTemperature(double f) {
		temperature = f;		
	}
	
	@Override
	public void setIgnorePressure(boolean f) {
		ignore_pressure = f;		
	}
	
	@Override
	public boolean getIgnorePressure() {
		return ignore_pressure;		
	}

	@Override
	public void increaseTemperature(double value, boolean canOverheat) 
	{
		if (temperature > EventHandler.temp_bound/2 && !canOverheat)
		{
			temperature = EventHandler.temp_bound/2;
		}
		else if (temperature > EventHandler.temp_bound*3/2)
		{
			temperature = EventHandler.temp_bound*3/2;
		}
		else 
			reduceTemperature(-value);	
	}

	@Override
	public void reduceTemperature(double value) 
	{
		if (temperature < - EventHandler.temp_bound*3/2)
		{
			temperature = - EventHandler.temp_bound*3/2;
		}
		else 
			temperature -= value;

	}


	@Override
	public KnowledgePoints getKnowledgePoints() 
	{
		return knowledge;
	}

	@Override
	public void setKnowledgePoints(KnowledgePoints point) 
	{
		knowledge = point;		
	}

	
	public class KnowledgePoints
	{
		public int chemistry;
		public int physics;
		public int biology;
		public int occult;
		
		public KnowledgePoints(int chemistry,int biology, int physics,int occult)
		{
			this.chemistry = chemistry;
            this.biology = biology;
            this.physics = physics;
            this.occult = occult;
		}
		
		public int getChemistry() {
			return chemistry;
		}

		
		public void setChemistry(int f) {
			chemistry=f;		
		}

		
		public void addChemistry(int value) {
			chemistry+=value;		
		}

		
		public int getPhysics() {
			return physics;
		}

		
		public void setPhysics(int f) {
			physics=f;		
		}

		
		public void addPhysics(int value) {
			physics+=value;		
		}

		
		public int getOccult() {
			return occult;
		}

		
		public void setOccult(int f) {
			occult=f;		
		}

		
		public void addOccult(int value) {
			occult+=value;		
		}

		
		public int getBiology() {
			return biology;
		}

		
		public void setBiology(int f) {
			biology=f;		
		}

		
		public void addBiology(int value) {
			biology+=value;		
		}
		
		@Override
		public String toString() 
		{
			return "{Chemistry:"+chemistry+"|Biology"+biology+"|Physics:"+physics+"|Occult"+occult+"}";
		}
		public void add(int chemistry,int biology, int physics,int occult)
		{
			this.addChemistry(chemistry);
			this.addBiology(biology);
			this.addPhysics(physics);
			this.addOccult(occult);
		}

	}


	@Override
	public double getBaseColdProf() {
		return cold_prof;
	}

	@Override
	public void setBaseColdProf(double f) {
		cold_prof =f;
	}

	@Override
	public double getBaseHeatProf() {
		// TODO Auto-generated method stub
		return heat_prof;
	}

	@Override
	public void setBaseHeatProf(double f) {
		// TODO Auto-generated method stub
		heat_prof=f;
	}

	@Override
	public float getReduceOxyConsumption() {
		// TODO Auto-generated method stub
		return oxygen_reduce;
	}

	@Override
	public void setReduceOxyConsumption(float f) {
		oxygen_reduce=f;		
	}


	
	
}
