package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.List;

import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;

public class BlockIron extends BlockBase
{
	public static final EnumProperty<PaintColor> PAINT = EnumProperty.create("paint",PaintColor.class);

	public BlockIron(String name, Properties porperty, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, porperty,true,  needBlueprint, type,  difficulty);
		this.setDefaultState(this.stateContainer.getBaseState().with(PAINT,PaintColor.NONE));
	}
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) 
	{
		ITextComponent com = new TranslationTextComponent(I18n.format("tooltip.iron_block"));
		tooltip.add(com.applyTextStyle(TextFormatting.RED));

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	      builder.add(PAINT);
	   }
	
	
	
	public enum PaintColor implements IStringSerializable 
	{
		NONE("no"),
		ORANGE("orange"),
		BLUE("blue");
		
		private final String name;

		   private PaintColor(String name) 
		   {
		      this.name = name;
		   }
		@Override
		public String getName() 
		{
			return name;
		}
		
	}

}
