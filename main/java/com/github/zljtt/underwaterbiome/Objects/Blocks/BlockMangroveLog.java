package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedItem;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMangroveLog extends LogBlock implements INeedItem
{

	public BlockMangroveLog(String name, Properties pro) 
	{
		super(MaterialColor.WOOD, pro);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		BlockInit.BLOCKS.add(this);
//		ItemInit.ITEMS.add(new BlockItem(this, new Item.Properties().group((BlockInit.blockGroup))).setRegistryName(this.getRegistryName()));
	}
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
	{
		IPlayerData data = player.getCapability(CapabilityPlayerDataProvider.CAP).orElse(null);
		if (data!=null)
		{
			data.setNatureAnger(data.getNatureAnger()+1);
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	@Override
	public boolean needItem() {
		// TODO Auto-generated method stub
		return true;
	}

}
