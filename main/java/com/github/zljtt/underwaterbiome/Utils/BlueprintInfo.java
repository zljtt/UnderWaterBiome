package com.github.zljtt.underwaterbiome.Utils;

public class BlueprintInfo 
{
	private int[] range;
	private Boolean need;
	private BlueprintType type;

	public BlueprintInfo(Boolean need, int[] range, BlueprintType type) 
	{
		this.setType(type);
		this.setNeed(need);
		this.setRange(range);
	}
	
	public int[] getRange() {
		return range;
	}

	public void setRange(int[] range) {
		this.range = range;
	}

	public Boolean getNeed() {
		return need;
	}

	public void setNeed(Boolean need) {
		this.need = need;
	}

	public BlueprintType getType() {
		return type;
	}

	public void setType(BlueprintType type) {
		this.type = type;
	}

	public static enum BlueprintType
	{
		CHEMISTRY("c"),
		BIOLOGY("b"),
		PHYSICS("p"),//
		OCCULT("o"),
		CHEMISTRY_BIOLOGY_OCCULT("cbo"),//
		BIOLOGY_PHYSICS("bp"),//
		CHEMISTRY_PHYSICS("cp"),//
		PHYSICS_OCCULT("po"),//
		ALL("a");
		
		String name;
		BlueprintType(String name)
		{
			this.name = name;
		}
		public String getName()
		{
			return name;
		}
	}
}
