package com.github.zljtt.underwaterbiome.Client.Renderers;

import com.github.zljtt.underwaterbiome.Client.Models.ModelConch;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityConch;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererConch extends LivingRenderer<EntityConch, ModelConch>
{

	public RendererConch(EntityRendererManager manager) 
	{
		super(manager, new ModelConch(), 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityConch entity) 
	{
		return new ResourceLocation(Reference.MODID, "textures/entity/conch.png");
	}
	
	public static class RenderFactory implements IRenderFactory<EntityConch>
	{

		@Override
		public EntityRenderer<? super EntityConch> createRenderFor(EntityRendererManager manager) 
		{
			return new RendererConch(manager);
		}
		
	}
	@Override
	protected boolean canRenderName(EntityConch entity) 
	{
		return false;
	}

}
