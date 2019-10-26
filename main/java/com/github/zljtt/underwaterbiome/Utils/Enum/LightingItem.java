package com.github.zljtt.underwaterbiome.Utils.Enum;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockInvisible;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public enum LightingItem 
{
	NO_LIGHT(Items.AIR,false,0),
	JACK_O_LANTERN(Items.JACK_O_LANTERN,false,1),
	REDSTONE_LAMP(Items.REDSTONE_LAMP,false,1),
	SEA_LANTERN(Items.SEA_LANTERN,false,1),
//	MAGMA_BLOCK(Items.MAGMA_BLOCK,false,1),
	SEA_PICKLE(Items.SEA_PICKLE,false,1),
	LANTERN(Items.SEA_PICKLE,false,1),
//	END_ROD(Items.END_ROD,false,1),
	TORCH_NEAR(ItemInit.TORCH_NEAR,false,2),
	TORCH_FAR(ItemInit.TORCH_FAR,false,3);

	private Item item;
	private boolean isDistance;
	private int light_value;
	private LightingItem(Item item,boolean isDistance,int light_level)
	{
		this.item = item;
		this.setDistance(isDistance);
		this.setLight_value(light_level);
	}
	public static LightingItem getByItem(Item item)
	{
		if(item != Items.AIR)
		{
			for(LightingItem light: LightingItem.values())
			{
				if (light.item ==item)
					return light;
			}
		}
		
		return NO_LIGHT;
	}
	public static BlockState getStateForLight(World world, BlockPos pos, LivingEntity entity)
	{
	      BlockState blockstate;
	      if (getHoldingLightItem(entity).light_value == 1)
	      {
	    	  blockstate=BlockInit.MOVING_LIGHT_LOW.getDefaultState();
	      }
	      else if (getHoldingLightItem(entity).light_value ==2)
	      {
	    	  blockstate=BlockInit.MOVING_LIGHT_MIDDLE.getDefaultState();
	      }
	      else if (getHoldingLightItem(entity).light_value ==3)
	      {
	    	  blockstate=BlockInit.MOVING_LIGHT_HIGH.getDefaultState();
	      }
	      else
	      {
	    	  blockstate=BlockInit.MOVING_LIGHT_LOW.getDefaultState();
	      }
	      IFluidState ifluidstate = world.getFluidState(pos);
	      
		return blockstate.with(BlockInvisible.WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}
		
		
	public static LightingItem getHoldingLightItem(LivingEntity theEntityLiving) 
	{
		for (LightingItem value : LightingItem.values())
		{
			if (value != NO_LIGHT &&
					(value.item == theEntityLiving.getHeldItemMainhand().getItem() || value.item == theEntityLiving.getHeldItemOffhand().getItem()))
			{
				return value;
			}
		}
		return NO_LIGHT;
	}
	public boolean isDistance() {
		return isDistance;
	}
	public void setDistance(boolean isDistance) {
		this.isDistance = isDistance;
	}
	public int getLight_value() {
		return light_value;
	}
	public void setLight_value(int light_value) {
		this.light_value = light_value;
	}
	
//
//	public static int lightValueToPlace(LivingEntity theEntityLiving) 
//	{
//		Item stack_off = theEntityLiving.getHeldItemOffhand().getItem();
//		Item stack_main = theEntityLiving.getHeldItemMainhand().getItem();
//
//		System.out.println("Light value 1:"+Math.max(getByItem(stack_off).light_value, getByItem(stack_main).light_value));
//		return ;
//	}
	
}
