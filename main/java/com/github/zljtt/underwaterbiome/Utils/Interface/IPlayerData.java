package com.github.zljtt.underwaterbiome.Utils.Interface;

import java.util.List;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Capabilities.CapabilityPlayerData.KnowledgePoints;

import net.minecraft.util.math.BlockPos;

public interface IPlayerData 
{
	double getTemperature();
	void setTemperature(double f);
	BlockPos getPosEscape();
	void setPosEscape(BlockPos f);
	double getBaseColdProf();
	void setBaseColdProf(double f);
	double getBaseHeatProf();
	void setBaseHeatProf(double f);
	void reduceTemperature(double value);
	KnowledgePoints getKnowledgePoints();
	void setKnowledgePoints(KnowledgePoints point);
	void increaseTemperature(double value, boolean canOverheat);
	boolean getIgnorePressure();
	void setIgnorePressure(boolean f);
	float getReduceOxyConsumption();
	void setReduceOxyConsumption(float f);
	String addRandomKnowledge(Random ran);
	boolean getChecked();
	void setChecked(boolean f);
	void setUnlockedBiomes(List<String> print);

	void unlockBiome(String print);

	void lockAllBiome();
	
	void setUsedItem(List<String> print);

	void useItem(String print);

	List<String> getUsedItems();
	List<String> getUnlockedBiomes();

	
}
