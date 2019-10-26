package com.github.zljtt.underwaterbiome.Client.Renderers;

import com.github.zljtt.underwaterbiome.Client.Models.ModelRay;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityRay;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererRay extends LivingRenderer<EntityRay,ModelRay>
{

	public RendererRay(EntityRendererManager manager) 
	{
		super(manager, new ModelRay(), 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRay entity) 
	{
		return new ResourceLocation(Reference.MODID, "textures/entity/ray.png");
	}
	
	public static class RenderFactory implements IRenderFactory<EntityRay>
	{

		@Override
		public EntityRenderer<? super EntityRay> createRenderFor(EntityRendererManager manager) 
		{
			return new RendererRay(manager);
		}
		
	}
	@Override
	protected boolean canRenderName(EntityRay entity) 
	{
		return false;
	}

}
