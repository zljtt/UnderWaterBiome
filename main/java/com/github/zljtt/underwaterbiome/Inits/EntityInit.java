package com.github.zljtt.underwaterbiome.Inits;

import com.github.zljtt.underwaterbiome.Client.Renderers.RendererConch;
import com.github.zljtt.underwaterbiome.Client.Renderers.RendererConchSeaGrass;
import com.github.zljtt.underwaterbiome.Client.Renderers.RendererCreeperFish;
import com.github.zljtt.underwaterbiome.Client.Renderers.RendererDrownedBoss;
import com.github.zljtt.underwaterbiome.Client.Renderers.RendererFishSturgeon;
import com.github.zljtt.underwaterbiome.Client.Renderers.RendererLavaFish;
import com.github.zljtt.underwaterbiome.Client.Renderers.RendererRay;
import com.github.zljtt.underwaterbiome.Client.Renderers.RendererRayBoss;
import com.github.zljtt.underwaterbiome.Client.Renderers.RendererShark;
import com.github.zljtt.underwaterbiome.Client.Renderers.RendererWaterArrow;
import com.github.zljtt.underwaterbiome.Client.Renderers.RendererWaterSkeleton;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityConch;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityConchSeaGrass;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityCreeperFish;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityDrownedBoss;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityFishSturgeon;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityLavaFish;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityRay;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityRayBoss;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityShark;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityWaterArrow;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityWaterSkeleton;
import com.github.zljtt.underwaterbiome.Utils.Reference;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityInit 
{
	
	public static final EntityType<?> ENTITY_SHARK = build("shark", 2.0F, 1.5F, EntityType.Builder.create(EntityShark::new,EntityClassification.MONSTER).setUpdateInterval(2));	
	public static final EntityType<?> ENTITY_CONCH = build("conch", 0.6F, 0.4F, EntityType.Builder.create(EntityConch::new,EntityClassification.MONSTER).setUpdateInterval(2));	
	public static final EntityType<?> ENTITY_CONCH_SEA_GRASS = build("conch_sea_grass", 0.6F, 0.4F, EntityType.Builder.create(EntityConchSeaGrass::new,EntityClassification.MONSTER).setUpdateInterval(2));	
	public static final EntityType<?> ENTITY_FISH_STURGEON = build("fish_sturgeon", 1.0F, 0.8F, EntityType.Builder.create(EntityFishSturgeon::new,EntityClassification.WATER_CREATURE).setUpdateInterval(2));	
	public static final EntityType<?> ENTITY_RAY = build("ray", 2.0F, 0.7F, EntityType.Builder.create(EntityRay::new,EntityClassification.WATER_CREATURE).setUpdateInterval(2));	
	public static final EntityType<?> ENTITY_RAY_BOSS = build("ray_boss", 2.2F, 0.8F, EntityType.Builder.create(EntityRayBoss::new,EntityClassification.MONSTER).immuneToFire().setUpdateInterval(2));	
	public static final EntityType<?> ENTITY_DROWNED_BOSS = build("drowned_boss", 0.6F, 0.7F, EntityType.Builder.create(EntityDrownedBoss::new,EntityClassification.MONSTER).immuneToFire().setUpdateInterval(2));	
	public static final EntityType<?> ENTITY_WATER_SKELETON = build("water_skeleton", 0.6F, 1.99F, EntityType.Builder.create(EntityWaterSkeleton::new,EntityClassification.MONSTER).setUpdateInterval(2));	
	public static final EntityType<?> ENTITY_CREEPER_FISH = build("creeper_fish", 0.4F, 0.4F, EntityType.Builder.create(EntityCreeperFish::new,EntityClassification.MONSTER).setUpdateInterval(2));	
	public static final EntityType<?> ENTITY_LAVA_FISH = build("lava_fish", 0.9F, 0.7F, EntityType.Builder.create(EntityLavaFish::new,EntityClassification.WATER_CREATURE).setUpdateInterval(2));	

	public static final EntityType<?> ENTITY_WATER_ARROW = build("water_arrow", 0.5F, 0.5F, EntityType.Builder.<EntityWaterArrow>create(EntityWaterArrow::new,EntityClassification.MISC));	

	public static void init(RegistryEvent.Register<EntityType<?>> event)
	{
		event.getRegistry().registerAll(
				ENTITY_SHARK,
				ENTITY_CONCH,
				ENTITY_CONCH_SEA_GRASS,
				ENTITY_FISH_STURGEON,
				ENTITY_RAY,
				ENTITY_RAY_BOSS,
				ENTITY_WATER_ARROW,
				ENTITY_DROWNED_BOSS,
				ENTITY_WATER_SKELETON,
				ENTITY_CREEPER_FISH,
				ENTITY_LAVA_FISH
				);
	}
	@OnlyIn(Dist.CLIENT)
	public static void initRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityShark.class, new RendererShark.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityConch.class, new RendererConch.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityConchSeaGrass.class, new RendererConchSeaGrass.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityFishSturgeon.class, new RendererFishSturgeon.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityRay.class, new RendererRay.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityRayBoss.class, new RendererRayBoss.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityWaterArrow.class,  new RendererWaterArrow.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityDrownedBoss.class,  new RendererDrownedBoss.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityWaterSkeleton.class,  new RendererWaterSkeleton.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperFish.class,  new RendererCreeperFish.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityLavaFish.class,  new RendererLavaFish.RenderFactory());

	}
	public static EntityType<?> build(String name, float size1, float size2, Builder<?> builder) 
	{
		EntityType<?> type = builder.size(size1,size2).build(Reference.MODID+":"+name)
				.setRegistryName(new ResourceLocation(Reference.MODID,name));	
		return type;
	}


}
