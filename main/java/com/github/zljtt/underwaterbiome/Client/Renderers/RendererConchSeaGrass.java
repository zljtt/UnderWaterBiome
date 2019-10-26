package com.github.zljtt.underwaterbiome.Client.Renderers;

import com.github.zljtt.underwaterbiome.Client.Models.ModelConchSeaGrass;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityConchSeaGrass;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererConchSeaGrass extends LivingRenderer<EntityConchSeaGrass, ModelConchSeaGrass>
{

	public RendererConchSeaGrass(EntityRendererManager manager) 
	{
		super(manager, new ModelConchSeaGrass(), 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityConchSeaGrass entity) 
	{
		return new ResourceLocation(Reference.MODID, "textures/entity/conch_sea_grass.png");
	}
	
	public static class RenderFactory implements IRenderFactory<EntityConchSeaGrass>
	{

		@Override
		public EntityRenderer<? super EntityConchSeaGrass> createRenderFor(EntityRendererManager manager) 
		{
			return new RendererConchSeaGrass(manager);
		}
		
	}
	@Override
	protected boolean canRenderName(EntityConchSeaGrass entity) 
	{
		return false;
	}

}
