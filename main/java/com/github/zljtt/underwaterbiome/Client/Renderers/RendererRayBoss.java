package com.github.zljtt.underwaterbiome.Client.Renderers;

import com.github.zljtt.underwaterbiome.Client.Models.ModelRayBoss;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityRayBoss;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererRayBoss extends LivingRenderer<EntityRayBoss,ModelRayBoss>
{

	public RendererRayBoss(EntityRendererManager manager) 
	{
		super(manager, new ModelRayBoss(), 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRayBoss entity) 
	{
		return new ResourceLocation(Reference.MODID, "textures/entity/ray_boss.png");
	}
	
	public static class RenderFactory implements IRenderFactory<EntityRayBoss>
	{

		@Override
		public EntityRenderer<? super EntityRayBoss> createRenderFor(EntityRendererManager manager) 
		{
			return new RendererRayBoss(manager);
		}
		
	}
	@Override
	protected boolean canRenderName(EntityRayBoss entity) 
	{
		return true;
	}

}
