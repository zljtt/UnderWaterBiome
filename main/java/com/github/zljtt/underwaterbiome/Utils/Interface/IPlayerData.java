package com.github.zljtt.underwaterbiome.Utils.Interface;

import com.github.zljtt.underwaterbiome.Capabilities.CapabilityPlayerData.KnowledgePoints;

public interface IPlayerData 
{
	double getTemperature();
	void setTemperature(double f);
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

	
}
