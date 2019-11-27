package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

public class BlockLivingRoot extends BlockBase
{

	public BlockLivingRoot(String name, Properties porperty, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, porperty,true , needBlueprint, type,  difficulty);
	}

}
