package com.github.zljtt.underwaterbiome.Utils.Interface;

import java.util.List;
public interface IBluePrint 
{
	List<String> getUnlockedBluePrint();

	void setUnlockedBluePrint(List<String> print);

	void unlockBluePrint(String print);

	void lockAllBluePrint();

}
