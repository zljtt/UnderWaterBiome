package com.github.zljtt.underwaterbiome.Client.Renderers;

import com.github.zljtt.underwaterbiome.Client.Models.ModelConch;
import com.github.zljtt.underwaterbiome.Client.Models.ModelLavaFish;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityConch;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityLavaFish;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererLavaFish extends LivingRenderer<EntityLavaFish, ModelLavaFish>
{

	ResourceLocation lava  = new ResourceLocation(Reference.MODID, "textures/entity/lava_fish.png");
	ResourceLocation obsi = new ResourceLocation(Reference.MODID, "textures/entity/obsidian_fish.png");
	public RendererLavaFish(EntityRendererManager manager) 
	{
		super(manager, new ModelLavaFish(), 0F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLavaFish entity) 
	{
		return entity.getIsLava()?lava:obsi;
	}
	public static class RenderFactory implements IRenderFactory<EntityLavaFish>
	{

		@Override
		public EntityRenderer<? super EntityLavaFish> createRenderFor(EntityRendererManager manager) 
		{
			return new RendererLavaFish(manager);
		}
		
	}
	@Override
	protected boolean canRenderName(EntityLavaFish entity) 
	{
		return false;
	}

}
