package com.github.zljtt.underwaterbiome.Capabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Handlers.EventHandler;
import com.github.zljtt.underwaterbiome.Handlers.TemperatureHandler;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.util.math.BlockPos;

public class CapabilityPlayerData implements IPlayerData
{
	public double temperature = 0;
	public double cold_prof = 0;
	public double heat_prof = 0;
	public float oxygen_reduce = 0;
	public BlockPos escape = new BlockPos(0,0,0);

	public KnowledgePoints knowledge = new KnowledgePoints(0,0,0,0);
	public boolean ignore_pressure = false;
	public boolean checked = false;

	public List<String> unlocked_biome = new ArrayList<String>();
	public List<String> used_item = new ArrayList<String>();
	
	@Override
	public List<String> getUnlockedBiomes() 
	{
		return unlocked_biome;
	}
	@Override
	public void setUnlockedBiomes(List<String> print) 
	{
		unlocked_biome.clear();
		unlocked_biome = print;
	}
	
	@Override
	public void unlockBiome(String print) 
	{
		unlocked_biome.add(print);
	}
	
	@Override
	public void lockAllBiome() 
	{
		unlocked_biome.clear();;
	}
	@Override
	public void setUsedItem(List<String> print) 
	{
		used_item.clear();
		used_item = print;
	}
	@Override
	public List<String> getUsedItems() {
		// TODO Auto-generated method stub
		return used_item;
	}
	@Override
	public void useItem(String print) {
		used_item.add(print);		
	}
	
	@Override
	public boolean getChecked() {
		return checked;
	}

	@Override
	public void setChecked(boolean f) {
		checked = f;		
	}
	
	
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
		if (temperature > TemperatureHandler.temp_bound/2 && !canOverheat)
		{
			temperature = TemperatureHandler.temp_bound/2;
		}
		else if (temperature > TemperatureHandler.temp_bound*3/2)
		{
			temperature = TemperatureHandler.temp_bound*3/2;
		}
		else 
			reduceTemperature(-value);	
	}

	@Override
	public void reduceTemperature(double value) 
	{
		if (temperature < - TemperatureHandler.temp_bound*3/2)
		{
			temperature = - TemperatureHandler.temp_bound*3/2;
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
	@Override
	public String addRandomKnowledge(Random ran)
	{
		int k = ran.nextInt(4);
		switch(k)
		{
		case 0:
			knowledge.add(1, 0, 0, 0);return "chemistry";
		case 1:
			knowledge.add(0, 1, 0, 0);return "biology";
		case 2:
			knowledge.add(0, 0, 1, 0);return "physics";
		case 3:
			knowledge.add(0, 0, 0, 1);return "occult";
		default:
			knowledge.add(0, 0, 0, 1);return "occult";
		}				
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

	@Override
	public BlockPos getPosEscape() {
		return escape==null?new BlockPos(0,0,0):escape;
	}

	@Override
	public void setPosEscape(BlockPos f) {
		escape = f;		
	}


	
	
}
