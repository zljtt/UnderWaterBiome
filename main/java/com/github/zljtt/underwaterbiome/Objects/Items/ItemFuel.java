package com.github.zljtt.underwaterbiome.Objects.Items;

import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import com.github.zljtt.underwaterbiome.Objects.Items.Base.ItemBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemFuel extends ItemBase
{

	public ItemFuel(String name, Properties property, boolean needBlueprint,BlueprintType type, int... difficulty) 
	{
		super(name, property,needBlueprint, type,  difficulty);
	}
	@Override
	public int getBurnTime(ItemStack itemStack) 
	{
		return 1600;
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
//		if(!worldIn.isRemote())
//		{
//			Random rand = new Random();
//			WreckageConfig config = new WreckageConfig(20, 1, 0);
//			List<RoomInfo> room_list = FeatureResearchStationWreckage.generateStationMap(worldIn, rand, playerIn.getPosition(), config);
//			room_list.forEach(room_info->
//			{
//				System.out.println(room_info.toString());
//				room_info.room.generate(worldIn, room_info, config, rand);
//			});
//		}
//		playerIn.addPotionEffect(new EffectInstance(EffectInit.BREAK_SHELD, 999));
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
