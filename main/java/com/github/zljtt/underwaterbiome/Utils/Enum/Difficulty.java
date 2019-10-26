package com.github.zljtt.underwaterbiome.Utils.Enum;

public enum Difficulty 
{
	EASY(0),
	NORMAL(1),
	DIFFICULT(2);
	
	int pollution;
	Difficulty(int pollution)
	{
		this.pollution =pollution;
	}
	public int getDifficulty()
	{
		return pollution;
	}
}

