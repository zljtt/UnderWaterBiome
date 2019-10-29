package com.github.zljtt.underwaterbiome.Handlers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.github.zljtt.underwaterbiome.Client.Triggers.TriggerNeedTechPoint;
import com.github.zljtt.underwaterbiome.Client.Triggers.TriggerUnlockBluePrint;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class TriggerHandler 
{
//	private static Method CriterionRegister;
	public static final TriggerUnlockBluePrint UNLOCKED_BLUE_PRINT = new TriggerUnlockBluePrint("unlocked_blue_print");
	public static final TriggerNeedTechPoint NEED_TECHPOINT = new TriggerNeedTechPoint("have_knowledge");

    public static final ICriterionTrigger<?>[] TRIGGER_ARRAY = new ICriterionTrigger[] 
    		{
    				UNLOCKED_BLUE_PRINT,
    				NEED_TECHPOINT
            };
    
//    @SuppressWarnings("unchecked")
//	public static <T extends ICriterionInstance> ICriterionTrigger<T> registerAdvancementTrigger(ICriterionTrigger<T> trigger) 
//    {
//    	CriteriaTriggers.register(trigger);
//		if(CriterionRegister == null) 
//		{
//			for (Method m:CriteriaTriggers.class.getDeclaredMethods())
//			{
//				System.out.print(m.getName());
//			}
//			try {
//				CriterionRegister = ObfuscationReflectionHelper.findMethod(CriteriaTriggers.class, "register", ICriterionTrigger.class);
//			}catch(Exception e) {System.out.println("no method 1");}
//			CriterionRegister.setAccessible(true);
//		}
//		try 
//		{
//			System.out.println("Succeeded to register trigger " + trigger.getId());
//			trigger = (ICriterionTrigger<T>) CriterionRegister.invoke(null, trigger);
//		} 
//		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
//		{
//			System.out.println("Failed to register trigger " + trigger.getId());
//			e.printStackTrace();
//		}
//		return trigger;
//	}

	public static void init() 
	{
		 for (ICriterionTrigger<?> trigger: TriggerHandler.TRIGGER_ARRAY)
		 {
		    	CriteriaTriggers.register(trigger);
//			 registerAdvancementTrigger(trigger);
	     }		
	}
	

}
