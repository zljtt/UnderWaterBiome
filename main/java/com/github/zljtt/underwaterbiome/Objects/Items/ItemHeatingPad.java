package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.Random;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHeatingPad extends ItemBase
{
	Random rand = new Random();
	public ItemHeatingPad(String name, Properties property, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, property,  needBlueprint, type,  difficulty);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		IPlayerData cap_o =  playerIn.getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
		if(cap_o!=null)
		{
			cap_o.increaseTemperature(100,false);
			BlockPos pos = new BlockPos(playerIn.posX,playerIn.posY,playerIn.posZ);
			double d0 = (double)pos.getX()+rand.nextDouble()-rand.nextDouble();
		      double d1 = (double)pos.getY() + 0.7D+rand.nextDouble()-rand.nextDouble();
		      double d2 = (double)pos.getZ()+rand.nextDouble()-rand.nextDouble();
			for (int i = 0; i<10;i++)
			{
			      worldIn.addParticle(ParticleTypes.HEART, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
		playerIn.getHeldItem(handIn).shrink(1);;
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
