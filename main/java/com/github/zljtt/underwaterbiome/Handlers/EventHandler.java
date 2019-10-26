package com.github.zljtt.underwaterbiome.Handlers;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.lwjgl.glfw.GLFW;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityOxygenProvider;
import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeLavaRange;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockInvisible;
import com.github.zljtt.underwaterbiome.Objects.Messages.MessageDebug;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Enum.BreathableItem;
import com.github.zljtt.underwaterbiome.Utils.Enum.LightingItem;
import com.github.zljtt.underwaterbiome.Utils.Interface.IOxygen;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;
import com.github.zljtt.underwaterbiome.World.WorldTypeWaterWorld;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class EventHandler 
{
	Minecraft mc = Minecraft.getInstance();
	Random ran = new Random();

	boolean timer2 = false;
	int timer2_1 = 0;
	int timer3 = 0;
	int temp_state = 0;
	boolean canHeat =false;
	//game setting
	public static int temp_bound = 200;
	public static BlockPos rayTraceBlockPos;

	public static int getMaxOxygenValue(ItemStack stack)
	{
		return BreathableItem.getByItem(stack.getItem()).getMaxOxygen();
	}
	@SubscribeEvent
    public void onPlayerLogIn(PlayerEvent.PlayerLoggedInEvent event)
    {
		if (mc.player != null && event.getEntity()!=null 
				&& event.getEntity().getEntityWorld().getWorldInfo().getGameType() == GameType.SURVIVAL
				&& event.getEntity().getEntityWorld().getWorldType() instanceof WorldTypeWaterWorld)
		{
			for(ServerPlayerEntity serverplayerentity : (Iterable<ServerPlayerEntity>)event.getEntity().getEntityWorld().getPlayers()) 
			{
		         	mc.getIntegratedServer().getPlayerList().setCommandsAllowedForAll(true);
					mc.player.sendChatMessage("/gamerule doLimitedCrafting true");	
					this.timer2 = true;
		    }
		}
    }	
	@SubscribeEvent
    public void onAfterLogin(PlayerTickEvent event)
    {
		if(timer2 && event.player.world.isRemote)
		{
			if (timer2_1 >= 20 	&& mc.player != null && event.player!=null && event.player.getEntityWorld().getWorldInfo().getGameType() == GameType.SURVIVAL)
			{
//				for(ServerPlayerEntity serverplayerentity : (Iterable<ServerPlayerEntity>)event.player.getEntityWorld().getPlayers()) 
				{
				    mc.getIntegratedServer().getPlayerList().setCommandsAllowedForAll(false);
				}
				this.timer2 = false;

			}
			timer2_1+=1;			
		}
		
    }
	public static BlockPos rayTrace(Entity entity, double distance, float frame) 
	{
	      Vec3d vec3d = entity.getEyePosition(frame);
	      Vec3d vec3d1 = entity.getLook(frame);
	      Vec3d vec3d2 = vec3d.add(vec3d1.x * distance, vec3d1.y * distance, vec3d1.z * distance);
	      RayTraceResult context = entity.world.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity));  
		  BlockPos pos_hit = ((BlockRayTraceResult)context).getPos().add(-Math.round(vec3d1.x),-Math.round(vec3d1.y),-Math.round(vec3d1.z));
		  rayTraceBlockPos = pos_hit;
//		  System.out.println(vec3d1.toString());
	      return pos_hit;
	}
	@SubscribeEvent
    public void onCheckingRecipe(PlayerTickEvent event)
    {
		IPlayerData data = event.player.getCapability(CapabilityPlayerDataProvider.CAP).orElse(null);
		if (data!=null && event.player instanceof ServerPlayerEntity)
		{
			TriggerHandler.NEED_TECHPOINT.trigger((ServerPlayerEntity)event.player, data.getKnowledgePoints());
		}
    }	
	@SubscribeEvent
    public void onDiggingSpeed(net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed event)
    {
		float f = event.getOriginalSpeed();
		if (event.getPlayer().areEyesInFluid(FluidTags.WATER) && !EnchantmentHelper.hasAquaAffinity(event.getPlayer())) 
		{
	         f *= 5.0F;
	    }
	    if (!event.getPlayer().onGround) 
	    {
	         f *= 5.0F;
	    }
		System.out.print(f);
		event.setNewSpeed(f);
    }
	@SubscribeEvent
    public void onLighting(PlayerTickEvent event)
    {		
        if (event.phase == PlayerTickEvent.Phase.START && !event.player.world.isRemote && LightingItem.getHoldingLightItem(event.player)!=LightingItem.NO_LIGHT) 
        {
        	World world = event.player.getEntityWorld();
    		BlockPos pos = event.player.getPosition().up();
    		Block block = world.getBlockState(pos).getBlock();
//    	    Entity entity = this.mc.getRenderViewEntity();

//    		if (LightingItem.getHoldingLightItem(event.player).isDistance())
//    		{
////    		    rayTraceBlock = event.player.func_213324_a(8.0D, 0.0F, false);
//    		    BlockPos pos_new = rayTrace(event.player,8.0, 0.0F);
////                BlockPos pos_new = getDistancedPos(pos_hit, pos, world);
//                System.out.println(" | "+pos_new.toString());
//        		Block block_hit = world.getBlockState(pos_new).getBlock();
//        		if ((block_hit==Blocks.WATER || block_hit==Blocks.AIR) && block_hit!=BlockInit.MOVING_LIGHT_HIGH && block!=BlockInit.MOVING_LIGHT_LOW)
//        		{
//        			world.setBlockState(pos_new, LightingItem.getStateForLight(world,  pos_new, event.player).with(BlockInvisible.DISTANCED, true));
//        		}
//    		}
//    		if (!LightingItem.getHoldingLightItem(event.player).isDistance())
    		{
    			if ((block==Blocks.WATER || block==Blocks.AIR) && !(block instanceof BlockInvisible))
        		{
        			world.setBlockState(pos, LightingItem.getStateForLight(world,  pos,event.player));
//        			System.out.println("Light DIRECT at "+pos.toString());
        		}
    		}
    		
    		
    		
//    		else
//    		{
//    			BlockPos pos2 = pos;
//    			double distance_final=5;
//    			for (int x = -1;x<2;x++)
//    			{
//    				for (int y = -1;y<3;y++)
//    				{
//    					for (int z = -1;z<2;z++)
//    					{
//    			    		Block block0 = world.getBlockState(pos.add(x, y, z)).getBlock();
//    						double distance0 = pos.distanceSq(pos.getX()+x, pos.getY()+y, pos.getZ()+z, true);
//    						if ((block0==Blocks.WATER || block0==Blocks.AIR)&& block0!=BlockInit.MOVING_LIGHT && distance0 < distance_final-1)
//    						{
//    							distance_final = distance0;
//    							pos2 = pos.add(x, y, z);
//    						}
//    					}
//    				}
//    			}
//        		Block block1 = world.getBlockState(pos2).getBlock();
//    			if (distance_final!=5 && (block1==Blocks.WATER || block1==Blocks.AIR) && block1!=BlockInit.MOVING_LIGHT)
//    			{
//    				world.setBlockState(pos2, BlockInit.MOVING_LIGHT.getStateForLight(world,  pos2, event.player));
//        			System.out.println("Light INDIRECT at "+pos2.toString()+" with distance of "+distance_final);
//    			}
//
//    		}   	    
    	}	
    }
	@SubscribeEvent
    public void onTimer(PlayerTickEvent event)
    {	
    }
	@SubscribeEvent
    public void onPlayerSufferColdOrHeatDamage(PlayerTickEvent event)
    {
		PlayerEntity player = event.player; 
		IPlayerData cap_o =  player.getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
		if (!player.world.isRemote && cap_o!=null && !player.isCreative() && !player.isSpectator())
		{
			double temp = cap_o.getTemperature();
			
			
			//heat
			if (temp>=temp_bound/2+temp_bound-1)
			{
				if (temp_state!=2)
				{
					player.removePotionEffect(EffectInit.SCALD);
					player.addPotionEffect(new EffectInstance(EffectInit.SCALD_II,Integer.MAX_VALUE,1));	
				}
				temp_state = 2;
			}
			else if (temp>temp_bound/2)
			{
				player.removePotionEffect(EffectInit.SCALD_II);
				timer3+=1;
				if (timer3 >=110)
				{
					player.addPotionEffect(new EffectInstance(EffectInit.SCALD,120));
					timer3 = 0;
				}
				temp_state = 1;
			}
			else 
			{
					player.removePotionEffect(EffectInit.SCALD);
					player.removePotionEffect(EffectInit.SCALD_II);
				temp_state = 0;
				
			}

			//cold
			if (temp<=(-temp_bound/2-temp_bound+1))
			{
				if (temp_state!=-2)
				{
					player.removePotionEffect(EffectInit.FROST_BITE);
					player.addPotionEffect(new EffectInstance(EffectInit.FROST_BITE_II,Integer.MAX_VALUE,1));
				}
				temp_state = -2;
			}
			else if (temp< -temp_bound/2)			
			{
					player.removePotionEffect(EffectInit.FROST_BITE_II);
				timer3+=1;
				if (timer3 >=110)
				{
					player.addPotionEffect(new EffectInstance(EffectInit.FROST_BITE,120));
					timer3 = 0;
				}
				temp_state = -1;
			}
			else
			{
					player.removePotionEffect(EffectInit.FROST_BITE);
					player.removePotionEffect(EffectInit.FROST_BITE_II);	
				temp_state = 0;

			}


		}
		else
		{
			player.removePotionEffect(EffectInit.FROST_BITE);
			player.removePotionEffect(EffectInit.FROST_BITE_II);	
			temp_state = 0;
		}
    }
	@SubscribeEvent
    public void onPlayerCalculateTempRate(PlayerTickEvent event)
    {
		if (event.phase == TickEvent.Phase.START)
		{
			PlayerEntity player = event.player; 
			IPlayerData cap_o =  player.getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
			
			if (!player.world.isRemote && player.isInWater() && cap_o!=null && !player.isCreative()&&!player.isSpectator())
			{
				double bottom_offset 	= Math.max(player.posY - player.world.getHeight(Heightmap.Type.OCEAN_FLOOR, (int)player.posX, (int)player.posZ), 0);
	    		double top_offset 		= Math.max(player.world.getSeaLevel() - player.posY,0);
	    		Biome biome 			= player.world.getBiome(player.getPosition());
	    		
//	    		cap_o.setTemperatureRate(biome.getTemperature(player.getPosition())-0.5+(player.getFoodStats().getFoodLevel()-10)/15);//x=10 y=0 /x=20 y=1
				canHeat = (biome instanceof BiomeLavaRange && bottom_offset < 10)
						||player.world.getBlockState(player.getPosition()).getBlock() == Blocks.BUBBLE_COLUMN;
				
				Item chest = event.player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem();
				Item feet = event.player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem();
				Item head = event.player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
				Item legs = event.player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem();

				if (canHeat)
	    		{
					double increaseTemp = 2 - bottom_offset/5;
	    			double heat_prof_total = Reference.HEAT_PROF_ITEM.getOrDefault(chest, 0)
	    					+ Reference.HEAT_PROF_ITEM.getOrDefault(feet, 0)
	    					+ Reference.HEAT_PROF_ITEM.getOrDefault(head, 0)
	    					+ Reference.HEAT_PROF_ITEM.getOrDefault(legs, 0);	   
	    			double heat_prof_total0 = (heat_prof_total+cap_o.getBaseHeatProf())/10;
					cap_o.increaseTemperature(Math.max(increaseTemp - heat_prof_total0, 0), true);//x=10 y = 0 / x=0 y=2
	    		}
	    		else
	    		{
	    			//increase temp by biome
	    			double add_biome = biome.getTemperature(player.getPosition())-0.5;
					cap_o.increaseTemperature(add_biome,false);
	    			//reduce temp by pressure
					if (top_offset>10)
					{
//						if (!cap_o.getIgnorePressure())
						{
							cap_o.reduceTemperature((top_offset-10)/30);
						}
					}
					else
					{
						cap_o.increaseTemperature((10-top_offset)/30,false);
					}
	    			//add temp by cold_prof
					double cold_prof_total = Reference.COLD_PROF_ITEM.getOrDefault(chest, 0)
	    					+ Reference.COLD_PROF_ITEM.getOrDefault(feet, 0)
	    					+ Reference.COLD_PROF_ITEM.getOrDefault(head, 0)
	    					+ Reference.COLD_PROF_ITEM.getOrDefault(legs, 0);
	    			cap_o.increaseTemperature((cold_prof_total+cap_o.getBaseColdProf())/10,false);		
	    		}	
			}
			else if (!player.world.isRemote && cap_o!=null && !player.isCreative()&&!player.isSpectator())
			{
				cap_o.increaseTemperature(1/3, false);
			}

		}
		
    }
		
	@SubscribeEvent
    public void onPlayerHoldFloat(PlayerTickEvent event)
    {
		if ((event.player.getHeldItemMainhand().getItem()==BlockInit.FLOATING_CORE.BLOCKITEM
			||event.player.getHeldItemOffhand().getItem()==BlockInit.FLOATING_CORE.BLOCKITEM)
				&&event.player.isInWater()
				&&event.player.getPosition().getY()<(event.player.getEntityWorld().getSeaLevel())
				&&event.player.getMotion().getY()<0.5)
		{
			event.player.setMotion(event.player.getMotion().getX(), event.player.getMotion().getY()+0.01, event.player.getMotion().getZ());
		}
		
    }
	@SubscribeEvent
    public void onPlayerOutOfBreath(LivingAttackEvent event)
    {
		if (event.getEntity() instanceof PlayerEntity)
		{
			if (event.getSource().equals(DamageSource.DROWN))
			{
				PlayerEntity entity = (PlayerEntity)(event.getEntity());
								
				if(entity.inventory.hasAny(EventHandler.breathableItem()))
				{
					// looping every slots
					for (ItemStack stack : entity.inventory.mainInventory)
					{
						if 	(EventHandler.breathableItem().contains(stack.getItem()))
						{
							IOxygen cap_o =  stack.getCapability(CapabilityOxygenProvider.PARTICLE_CAP, null).orElse(null);
							if (!cap_o.equals(null))
							{
								//reduce the oxygen in one tank and cancel the damage
								if (cap_o.getOxygen()>0)
								{
									float b = getBreathingUnderPressure(entity, entity.world.getSeaLevel());
									cap_o.minusOxygen(Math.min(b, cap_o.getOxygen()));
									event.setCanceled(true);
									break;
								}
							}
						}
					}
				}
			}
		}
    }
	@SubscribeEvent
    public void onPlayerPressKey(InputEvent.KeyInputEvent event)
    {
		if (event.getKey()==GLFW.GLFW_KEY_P)
		{
			NetworkingHandler.sendToServer(new MessageDebug(0));

//			this.mc.worldRenderer.loadRenderers();
		}
    }
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event)
	{
		ItemStack current_stack = event.getItemStack();
		if (EventHandler.breathableItem().contains(current_stack.getItem()))
		{
	 			IOxygen cap_o =  current_stack.getCapability(CapabilityOxygenProvider.PARTICLE_CAP, null).orElse(null);
	 			if (cap_o!=null)
	 			{
	 				ITextComponent com = new StringTextComponent(I18n.format("tooltip.oxygen_left")
	 						+cap_o.getOxygen()+"/"+EventHandler.getMaxOxygenValue(current_stack)).applyTextStyle(TextFormatting.BLUE);
					event.getToolTip().add(com);			
	 			}
		}
		if (Reference.COLD_PROF_ITEM.containsKey(current_stack.getItem()))
		{
			Integer prof = Reference.COLD_PROF_ITEM.get(current_stack.getItem());
			if(prof!=0)
			{
				String plus = prof>0?"+":"";
				ITextComponent string = new StringTextComponent(plus+prof.toString()+I18n.format("tooltip.cold_prof")).applyTextStyle(TextFormatting.BLUE);
				event.getToolTip().add(string);	
			}						
		}
		if (Reference.HEAT_PROF_ITEM.containsKey(current_stack.getItem()))
		{
			Integer prof = Reference.HEAT_PROF_ITEM.get(current_stack.getItem());
			if(prof!=0)
			{
				String plus = prof>0?"+":"";
				ITextComponent string = new StringTextComponent(plus+prof.toString()+I18n.format("tooltip.heat_prof")).applyTextStyle(TextFormatting.BLUE);
				event.getToolTip().add(string);		
			}			
		}
	}
	public static Set<Item> breathableItem()
	{
		Set<Item> breath_item= new HashSet<Item>();
		for (BreathableItem item: Reference.BREATHABLEITEM)
		{
			breath_item.add(item.getBreathItem());
		}
		return breath_item;
	}
	public static Set<Item> chargeableItem()
	{
		Set<Item> charge_item= new HashSet<Item>();
		for (BreathableItem item: Reference.BREATHABLEITEM)
		{
			if (item.getCanCharge())
			{
				charge_item.add(item.getBreathItem());
			}
		}
		return charge_item;
	}
	public static float getBreathingUnderPressure(PlayerEntity player, int sealevel)
	{
		double offest = sealevel- player.posY;
		IPlayerData cap_o =  player.getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
		float consumption  = 0;
		if  (offest>40 && !cap_o.getIgnorePressure())
		{
			player.addPotionEffect(new EffectInstance(EffectInit.HIGH_PRESSURE,Integer.MAX_VALUE,1));
			consumption= 2 - cap_o.getReduceOxyConsumption();
		}
		else if  (offest>25 && !cap_o.getIgnorePressure())
		{
			player.addPotionEffect(new EffectInstance(EffectInit.HIGH_PRESSURE,Integer.MAX_VALUE,0));
			consumption= 1.5F - cap_o.getReduceOxyConsumption();
		}
		else if  (offest>0)
		{
			player.removePotionEffect(EffectInit.HIGH_PRESSURE);
			consumption= 1 - cap_o.getReduceOxyConsumption();
		}
		else
		{
			player.removePotionEffect(EffectInit.HIGH_PRESSURE);
			consumption= 0;
		}
		return Math.max(consumption, 0);
	}
}
