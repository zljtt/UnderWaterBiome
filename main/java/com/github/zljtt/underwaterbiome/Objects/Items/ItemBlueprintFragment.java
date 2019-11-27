package com.github.zljtt.underwaterbiome.Objects.Items;

import java.util.List;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBlueprintFragment extends ItemBase
{
	String knowledge; 
	Random ran = new Random();
	public ItemBlueprintFragment(String name, Properties property, String knowledge) 
	{
		super(name, property, false, null, null);
		this.knowledge = knowledge;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
			ITextComponent string = new StringTextComponent(I18n.format("tooltip."+knowledge));
			tooltip.add(string.applyTextStyle(TextFormatting.YELLOW));
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		IPlayerData data = playerIn.getCapability(CapabilityPlayerDataProvider.CAP).orElse(null);
		if (data!=null)
		{
			switch(knowledge)
			{
			case "chemistry":
				data.getKnowledgePoints().add(1, 0, 0, 0);break;
			case "biology":
				data.getKnowledgePoints().add(0, 1, 0, 0);break;
			case "physics":
				data.getKnowledgePoints().add(0, 0, 1, 0);break;
			case "occult":
				data.getKnowledgePoints().add(0, 0, 0, 1);break;
			case "all":
				data.setRestFree(data.getRestFree()+1);break;
			}
			if (!playerIn.isCreative())
			{
				playerIn.getHeldItem(handIn).shrink(1);
			}
			BlockPos pos = playerIn.getPosition();
		    for (int i = 0; i<20;i++)
			{
		    	double d0 = (double)pos.getX()-1D+ran.nextDouble();
			    double d1 = (double)pos.getY()+1+ran.nextDouble()*1.2;
			    double d2 = (double)pos.getZ()-1D+ran.nextDouble();
		    	playerIn.getEntityWorld().addParticle(ParticleTypes.ENCHANT, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	@Override
	public String getTranslationKey() 
	{
		return "item.underwaterbiome.blueprint_fragment";
	}
}
