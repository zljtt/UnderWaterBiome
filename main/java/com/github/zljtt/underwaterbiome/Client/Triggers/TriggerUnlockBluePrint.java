package com.github.zljtt.underwaterbiome.Client.Triggers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

public class TriggerUnlockBluePrint implements ICriterionTrigger<TriggerUnlockBluePrint.Instance>
{
    private static ResourceLocation ID;
    private final Map<PlayerAdvancements, TriggerUnlockBluePrint.Listeners> listeners = Maps.newHashMap();

    public TriggerUnlockBluePrint(String parString)
    {
        super();
        ID = new ResourceLocation(Reference.MODID,parString);
    }
    
    public TriggerUnlockBluePrint(ResourceLocation parRL)
    {
        super();
        ID = parRL;
    }
    
    @Override
    public ResourceLocation getId()
    {
        return ID;
    }

    @Override
    public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<TriggerUnlockBluePrint.Instance> listener)
    {
    	TriggerUnlockBluePrint.Listeners myTriggerBase$listeners = (Listeners) listeners.get(playerAdvancementsIn);

        if (myTriggerBase$listeners == null)
        {
            myTriggerBase$listeners = new TriggerUnlockBluePrint.Listeners(playerAdvancementsIn);
            listeners.put(playerAdvancementsIn, myTriggerBase$listeners);
        }

        myTriggerBase$listeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<TriggerUnlockBluePrint.Instance> listener)
    {
    	TriggerUnlockBluePrint.Listeners tameanimaltrigger$listeners = listeners.get(playerAdvancementsIn);

        if (tameanimaltrigger$listeners != null)
        {
            tameanimaltrigger$listeners.remove(listener);

            if (tameanimaltrigger$listeners.isEmpty())
            {
                listeners.remove(playerAdvancementsIn);
            }
        }
    }

    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn)
    {
        listeners.remove(playerAdvancementsIn);
    }
    
    @Override
    public TriggerUnlockBluePrint.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
    {
    	String recipe = json.has("blueprint") ? JSONUtils.getString(json, "blueprint") : null;
        return new TriggerUnlockBluePrint.Instance(recipe);
    }

    public void trigger(ServerPlayerEntity player, String item)
    {
        TriggerUnlockBluePrint.Listeners tameanimaltrigger$listeners = listeners.get(player.getAdvancements());

        if (tameanimaltrigger$listeners != null)
        {
//        	IBluePrint print = player.getCapability(CapabilityBluePrintProvider.CAP).orElse(null);
//            tameanimaltrigger$listeners.trigger(print.getUnlockedBluePrint());
            tameanimaltrigger$listeners.trigger(item);

        }
    }

    public static class Instance extends CriterionInstance
    {
    	@Nullable
        private final String recipe;
        public Instance(String recipe)
        {
            super(TriggerUnlockBluePrint.ID);
            this.recipe = recipe;
        }
        @Nullable
        public JsonElement serialize() 
        {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("blueprint", JSONUtils.fromJson(this.recipe));
            return jsonobject;
        }

        public boolean test(String item)
        {
        	if(item!=null)
        	{
//                System.out.println(recipe);
        		if (this.recipe != null && this.recipe.equals(item)) 
                {
//        	         System.out.println("test true");
                     return true;
                } 
        		else if (this.recipe == null)
        		{
//                    System.out.println("test false due to null recipe: "+item.toString()+" and "+this.recipe.toString());
        		}
        		else
                {
//                     System.out.println("test false due to wrong recipe: "+item.toString()+" and "+this.recipe.toString());
                }
        		
        	}
			return false;

        	
        }
    }

    static class Listeners
    {
        private final PlayerAdvancements playerAdvancements;
        private final Set<ICriterionTrigger.Listener<TriggerUnlockBluePrint.Instance>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn)
        {
            playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty()
        {
            return listeners.isEmpty();
        }

        public void add(ICriterionTrigger.Listener<TriggerUnlockBluePrint.Instance> listener)
        {
            listeners.add(listener);
        }

        public void remove(ICriterionTrigger.Listener<TriggerUnlockBluePrint.Instance> listener)
        {
            listeners.remove(listener);
        }

        public void trigger(String item)
        {
            List<ICriterionTrigger.Listener<TriggerUnlockBluePrint.Instance>> list = null;

            for (ICriterionTrigger.Listener<TriggerUnlockBluePrint.Instance> listener : listeners)
            {
                if (listener.getCriterionInstance().test(item))
                {
                    if (list == null)
                    {
                        list = Lists.newArrayList();
                    }
                    
                    list.add(listener);
                }
            }

            if (list != null)
            {
                for (ICriterionTrigger.Listener<TriggerUnlockBluePrint.Instance> listener1 : list)
                {
                    listener1.grantCriterion(playerAdvancements);
                }
            }
        }
    }
}