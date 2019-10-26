package com.github.zljtt.underwaterbiome.Client.Renderers;

import com.github.zljtt.underwaterbiome.Client.Models.ModelShark;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityShark;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererShark extends LivingRenderer<EntityShark,ModelShark>
{

	public RendererShark(EntityRendererManager manager) 
	{
		super(manager, new ModelShark(), 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityShark entity) 
	{
		return new ResourceLocation(Reference.MODID, "textures/entity/shark.png");
	}
	
	public static class RenderFactory implements IRenderFactory<EntityShark>
	{

		@Override
		public EntityRenderer<? super EntityShark> createRenderFor(EntityRendererManager manager) 
		{
			return new RendererShark(manager);
		}
		
	}
	@Override
	protected boolean canRenderName(EntityShark entity) 
	{
		return false;
	}

}
