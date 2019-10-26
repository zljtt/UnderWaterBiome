package com.github.zljtt.underwaterbiome.Client.Triggers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Capabilities.CapabilityPlayerData.KnowledgePoints;
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

public class TriggerNeedTechPoint implements ICriterionTrigger<TriggerNeedTechPoint.Instance>
{
    private static ResourceLocation ID;
    private final Map<PlayerAdvancements, TriggerNeedTechPoint.Listeners> listeners = Maps.newHashMap();

    public TriggerNeedTechPoint(String parString)
    {
        super();
        ID = new ResourceLocation(Reference.MODID,parString);
    }
    
    public TriggerNeedTechPoint(ResourceLocation parRL)
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
    public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<TriggerNeedTechPoint.Instance> listener)
    {
    	TriggerNeedTechPoint.Listeners myTriggerBase$listeners = (Listeners) listeners.get(playerAdvancementsIn);

        if (myTriggerBase$listeners == null)
        {
            myTriggerBase$listeners = new TriggerNeedTechPoint.Listeners(playerAdvancementsIn);
            listeners.put(playerAdvancementsIn, myTriggerBase$listeners);
        }

        myTriggerBase$listeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<TriggerNeedTechPoint.Instance> listener)
    {
    	TriggerNeedTechPoint.Listeners tameanimaltrigger$listeners = listeners.get(playerAdvancementsIn);

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
    public TriggerNeedTechPoint.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
    {
    	int chemistry = json.has("chemistry") ? JSONUtils.getInt(json, "chemistry") : 0;
    	int biology = json.has("biology") ? JSONUtils.getInt(json, "biology") : 0;
    	int physics = json.has("physics") ? JSONUtils.getInt(json, "physics") : 0;
    	int occult = json.has("occult") ? JSONUtils.getInt(json, "occult") : 0;

        return new TriggerNeedTechPoint.Instance(chemistry,biology,physics,occult);
    }

    public void trigger(ServerPlayerEntity player, KnowledgePoints point)
    {
        TriggerNeedTechPoint.Listeners tameanimaltrigger$listeners = listeners.get(player.getAdvancements());

        if (tameanimaltrigger$listeners != null)
        {
            tameanimaltrigger$listeners.trigger(point);

        }
    }

    public static class Instance extends CriterionInstance
    {
    	@Nullable
        private final Integer chemistry;
    	@Nullable
        private final Integer biology;
    	@Nullable
        private final Integer physics;
    	@Nullable
        private final Integer occult;
        public Instance(int chemistry,int biology, int physics,int occult)
        {
            super(TriggerNeedTechPoint.ID);
            this.chemistry = chemistry;
            this.biology = biology;
            this.physics = physics;
            this.occult = occult;
        }
        @Nullable
        public JsonElement serialize() 
        {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("chemistry", JSONUtils.fromJson(this.chemistry.toString()));
            jsonobject.add("biology", JSONUtils.fromJson(this.biology.toString()));
            jsonobject.add("physics", JSONUtils.fromJson(this.physics.toString()));
            jsonobject.add("occult", JSONUtils.fromJson(this.occult.toString()));

            return jsonobject;
        }

        public boolean test(KnowledgePoints point)
        {
//            System.out.println("--------start-test--------");
//            System.out.println("arguement for test: chemistry:"+chemistry+"|biology:"+biology+"|physics:"+physics+"|occult:"+occult);
            int file_che = this.chemistry!=null? this.chemistry:0;
            int file_bio = this.biology!=null? this.biology:0;
            int file_phy = this.physics!=null? this.physics:0;
            int file_occ = this.occult!=null? this.occult:0;
//            System.out.println("arguement from file: chemistry:"+file_che+"|biology:"+file_bio+"|physics:"+file_phy+"|occult:"+file_occ);
    		if (point.getChemistry() >= file_che && point.getBiology() >= file_bio && point.getPhysics() >= file_phy && point.getOccult() >= file_occ) 
            {
                 return true;
            }    		
			return false;        	
        }
    }

    static class Listeners
    {
        private final PlayerAdvancements playerAdvancements;
        private final Set<ICriterionTrigger.Listener<TriggerNeedTechPoint.Instance>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn)
        {
            playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty()
        {
            return listeners.isEmpty();
        }

        public void add(ICriterionTrigger.Listener<TriggerNeedTechPoint.Instance> listener)
        {
            listeners.add(listener);
        }

        public void remove(ICriterionTrigger.Listener<TriggerNeedTechPoint.Instance> listener)
        {
            listeners.remove(listener);
        }

        public void trigger(KnowledgePoints point)
        {
            List<ICriterionTrigger.Listener<TriggerNeedTechPoint.Instance>> list = null;

            for (ICriterionTrigger.Listener<TriggerNeedTechPoint.Instance> listener : listeners)
            {
                if (listener.getCriterionInstance().test(point))
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
                for (ICriterionTrigger.Listener<TriggerNeedTechPoint.Instance> listener1 : list)
                {
                    listener1.grantCriterion(playerAdvancements);
                }
            }
        }
    }
}