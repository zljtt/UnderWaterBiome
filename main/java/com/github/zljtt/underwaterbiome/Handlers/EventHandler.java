package com.github.zljtt.underwaterbiome.Handlers;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.lwjgl.glfw.GLFW;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityOxygenProvider;
import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.EffectInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Biomes.BiomeLavaRange;
import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockInvisible;
import com.github.zljtt.underwaterbiome.Objects.Features.FeatureStarter;
import com.github.zljtt.underwaterbiome.Objects.Items.ItemSpaceshipDivingRecorder;
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
import net.minecraft.command.impl.TimeCommand;
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
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
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
	BlockPos timer_block;
	static int craft_time = 0;
	boolean timer_3= false;

	//game setting
	public static BlockPos rayTraceBlockPos;

	public static int getMaxOxygenValue(ItemStack stack)
	{
		return BreathableItem.getByItem(stack.getItem()).getMaxOxygen();
	}
	@SubscribeEvent
    public void onPlayerLogIn(PlayerEvent.PlayerLoggedInEvent event)
    {
		timer_3 = false;
    }	
//	
	@SubscribeEvent
    public void onAfterLoginSync(PlayerTickEvent event)
    {
		PlayerEntity player = (PlayerEntity) event.player;
		if(!event.player.world.isRemote && mc.player!=null && player!=null  && player.world.getWorldType() instanceof WorldTypeWaterWorld
				&& !player.isCreative() && timer_3 == false)
		{
			IPlayerData data = player.getCapability(CapabilityPlayerDataProvider.CAP).orElse(null);
			if (data!=null && !data.getPosEscape().equals(new BlockPos(0,0,0)))
			{
				System.out.println("login: "+data.getPosEscape());
				System.out.println("login: "+data.getUsedItems());
				timer_block = data.getPosEscape();
				craft_time = data.getUsedItems().size();
				timer_3 = true;
			}		
		}
		
    }
	
	@SubscribeEvent
    public void onPlayerFirstJoin(PlayerTickEvent event)
    {
		if(mc.player!=null&& event.player!=null  && event.player.world.getWorldType() instanceof WorldTypeWaterWorld
				&& !event.player.isCreative())
		{
			
			IPlayerData data = event.player.getCapability(CapabilityPlayerDataProvider.CAP).orElse(null);
			if (event.phase == Phase.START && data!=null && !data.getChecked())
			{		
				if (!event.player.world.isRemote)
				{
					mc.getIntegratedServer().getPlayerList().setCommandsAllowedForAll(true);
					mc.player.sendChatMessage("/gamerule doLimitedCrafting true");	
					
					event.player.addItemStackToInventory(new ItemStack(ItemInit.SPACESHIP_DRIVING_RECORDER));
					BlockPos pos = FeatureStarter.generate(event.player.getPosition(), event.player.world);
					timer_block = pos;
					data.setPosEscape(pos);
					System.out.println(timer_block);
				}
				
				
				timer2_1=30;
				data.setChecked(true);
				
			}
			if (event.phase == Phase.END && timer2_1 > -5 && timer2_1 <= 0)
			{
				--timer2_1;
				mc.getIntegratedServer().getPlayerList().setCommandsAllowedForAll(false);
				data.setPosEscape(timer_block);
				if (event.player.world.isRemote)
				{
					data.setPosEscape(timer_block);
					System.out.println(timer_block);
				}
			}
			

		}
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
		if (event.getEntityPlayer()!=null && current_stack.getItem() instanceof ItemSpaceshipDivingRecorder)
		{
 			IPlayerData data =  event.getEntityPlayer().getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
 			if (data!=null && timer_block!=null)
 			{
// 				BlockPos pos = data.getPosEscape();
 				BlockPos pos = timer_block;

 				ITextComponent com = new StringTextComponent(I18n.format("tooltip.location_of_base")+pos.getX()+" "+pos.getY()+" "+pos.getZ())
 						.applyTextStyle(TextFormatting.BLUE);
 				event.getToolTip().add(com);
 				
 			}
 			if (data!=null)
 			{
 				ITextComponent com = new StringTextComponent(I18n.format("tooltip.have_crafting")+craft_time)
 						.applyTextStyle(TextFormatting.BLUE);
 				event.getToolTip().add(com);
 			}
 			
		}
		if (OxygenHandler.breathableItem().contains(current_stack.getItem()))
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
    public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event)
    {        
		IPlayerData data =  event.getEntityPlayer().getCapability(CapabilityPlayerDataProvider.CAP, null).orElse(null);
		IPlayerData olddata = (IPlayerData) event.getOriginal().getCapability(CapabilityPlayerDataProvider.CAP, null);        
		        
		data.setBaseColdProf(olddata.getBaseColdProf());;
		data.setBaseHeatProf(olddata.getBaseHeatProf());
		data.setChecked(olddata.getChecked());
		data.setIgnorePressure(olddata.getIgnorePressure());
		data.setKnowledgePoints(olddata.getKnowledgePoints());
		data.setPosEscape(olddata.getPosEscape());
		data.setReduceOxyConsumption(olddata.getReduceOxyConsumption());
		data.setTemperature(olddata.getTemperature());
		data.setUnlockedBiomes(olddata.getUnlockedBiomes());
		data.setUsedItem(olddata.getUsedItems());
    }
	
}
