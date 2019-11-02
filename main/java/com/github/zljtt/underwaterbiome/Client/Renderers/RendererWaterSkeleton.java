package com.github.zljtt.underwaterbiome.Client.Renderers;

import com.github.zljtt.underwaterbiome.Client.Models.ModelConch;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityConch;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityWaterSkeleton;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererWaterSkeleton extends LivingRenderer<EntityWaterSkeleton, SkeletonModel<EntityWaterSkeleton>>
{

	public RendererWaterSkeleton(EntityRendererManager manager) 
	{
		super(manager, new SkeletonModel<EntityWaterSkeleton>(), 0F);
		this.addLayer(new HeldItemLayer<>(this));
	    this.addLayer(new BipedArmorLayer<>(this, new SkeletonModel<EntityWaterSkeleton>(0.5F, true), new SkeletonModel<EntityWaterSkeleton>(1.0F, true)));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWaterSkeleton entity) 
	{
		return new ResourceLocation(Reference.MODID, "textures/entity/skeleton.png");
	}
	
	public static class RenderFactory implements IRenderFactory<EntityWaterSkeleton>
	{

		@Override
		public EntityRenderer<? super EntityWaterSkeleton> createRenderFor(EntityRendererManager manager) 
		{
			return new RendererWaterSkeleton(manager);
		}
		
	}
	@Override
	protected boolean canRenderName(EntityWaterSkeleton entity) 
	{
		return false;
	}

}
