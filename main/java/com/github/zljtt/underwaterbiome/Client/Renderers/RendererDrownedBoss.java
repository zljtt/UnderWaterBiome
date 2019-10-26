package com.github.zljtt.underwaterbiome.Client.Renderers;

import com.github.zljtt.underwaterbiome.Client.Models.ModelDrownedBoss;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityDrownedBoss;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererDrownedBoss extends LivingRenderer<EntityDrownedBoss,ModelDrownedBoss>
{

	public RendererDrownedBoss(EntityRendererManager manager) 
	{
	    super(manager, new ModelDrownedBoss(), 0);
	}

	
	@Override
	protected ResourceLocation getEntityTexture(EntityDrownedBoss entity) 
	{
		return  new ResourceLocation(Reference.MODID, "textures/entity/drowned_boss.png");
	}
	
	public static class RenderFactory implements IRenderFactory<EntityDrownedBoss>
	{

		@Override
		public EntityRenderer<? super EntityDrownedBoss> createRenderFor(EntityRendererManager manager) 
		{
			return new RendererDrownedBoss(manager);
		}
		
	}
	
	@Override
	protected boolean canRenderName(EntityDrownedBoss entity) 
	{
		return true;
	}


}
