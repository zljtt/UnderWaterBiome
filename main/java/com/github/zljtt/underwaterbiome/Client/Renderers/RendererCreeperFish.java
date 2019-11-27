package com.github.zljtt.underwaterbiome.Client.Renderers;

import com.github.zljtt.underwaterbiome.Client.Models.ModelConch;
import com.github.zljtt.underwaterbiome.Client.Models.ModelCreeperFish;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityConch;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityCreeperFish;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererCreeperFish extends LivingRenderer<EntityCreeperFish, ModelCreeperFish>
{

	public RendererCreeperFish(EntityRendererManager manager) 
	{
		super(manager, new ModelCreeperFish(), 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCreeperFish entity) 
	{
		return new ResourceLocation(Reference.MODID, "textures/entity/creeper_fish.png");
	}
	
	public static class RenderFactory implements IRenderFactory<EntityCreeperFish>
	{

		@Override
		public EntityRenderer<? super EntityCreeperFish> createRenderFor(EntityRendererManager manager) 
		{
			return new RendererCreeperFish(manager);
		}
		
	}
	@Override
	protected boolean canRenderName(EntityCreeperFish entity) 
	{
		return false;
	}

}
