package com.github.zljtt.underwaterbiome.Client.Renderers;

import com.github.zljtt.underwaterbiome.Client.Models.ModelFishSturgeon;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityFishSturgeon;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererFishSturgeon extends LivingRenderer<EntityFishSturgeon,ModelFishSturgeon>
{

	public RendererFishSturgeon(EntityRendererManager manager) 
	{
		super(manager, new ModelFishSturgeon(), 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFishSturgeon entity) 
	{
		return new ResourceLocation(Reference.MODID, "textures/entity/sturgeon.png");
	}
	
	public static class RenderFactory implements IRenderFactory<EntityFishSturgeon>
	{

		@Override
		public EntityRenderer<? super EntityFishSturgeon> createRenderFor(EntityRendererManager manager) 
		{
			return new RendererFishSturgeon(manager);
		}
		
	}
	@Override
	protected boolean canRenderName(EntityFishSturgeon entity) 
	{
		return false;
	}

}
