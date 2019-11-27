package com.github.zljtt.underwaterbiome.Client.Models;


import com.github.zljtt.underwaterbiome.Objects.Entity.EntityConch;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityDrownedBoss;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.DrownedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelDrownedBoss extends EntityModel<EntityDrownedBoss> 
{
	public static int hasSheld= 3;

	private final RendererModel bone;
	private final RendererModel left;
	private final RendererModel right;
	private final RendererModel sheld1;
	private final RendererModel sheld2;
	private final RendererModel sheld3;

	public ModelDrownedBoss()
	{
		textureWidth = 64;
		textureHeight = 64;

		left = new RendererModel(this);
		left.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(left, 0.0F, 0.0F, -0.3491F);
		left.cubeList.add(new ModelBox(left, 17, 15, -11.0F, -6.0F, -3.0F, 3, 3, 3, 0.0F, true));

		right = new RendererModel(this);
		right.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(right, 0.0F, 0.0F, 0.3491F);
		right.cubeList.add(new ModelBox(right, 17, 15, 8.0F, -6.0F, -3.0F, 3, 3, 3, 0.0F, false));

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -5.0F, -11.0F, -5.0F, 10, 11, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 20, -4.0F, -9.0F, -4.0F, 8, 7, 8, 0.0F, false));
		
		sheld3 = new RendererModel(this);
		sheld3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(sheld3, 0.0F, 2.0944F, 0.0F);
		bone.addChild(sheld3);
		sheld3.cubeList.add(new ModelBox(sheld3, 21, 11, -3.0F, -9.0F, 7.0F, 6, 8, 1, 0.0F, false));
		sheld3.cubeList.add(new ModelBox(sheld3, 22, 16, -2.0F, -1.0F, 7.0F, 4, 1, 1, 0.0F, false));

		sheld2 = new RendererModel(this);
		sheld2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(sheld2, 0.0F, -2.0944F, 0.0F);
		bone.addChild(sheld2);
		sheld2.cubeList.add(new ModelBox(sheld2, 21, 11, -3.0F, -9.0F, 7.0F, 6, 8, 1, 0.0F, false));
		sheld2.cubeList.add(new ModelBox(sheld2, 22, 16, -2.0F, -1.0F, 7.0F, 4, 1, 1, 0.0F, false));

		sheld1 = new RendererModel(this);
		sheld1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(sheld1);
		sheld1.cubeList.add(new ModelBox(sheld1, 21, 11, -3.0F, -9.0F, 7.0F, 6, 8, 1, 0.0F, false));
		sheld1.cubeList.add(new ModelBox(sheld1, 22, 16, -2.0F, -1.0F, 7.0F, 4, 1, 1, 0.0F, false));
		}
	   
	   @Override
		public void render(EntityDrownedBoss entity, float f, float f1, float f2, float f3, float f4, float f5) {
			bone.render(f5);
			left.render(f5);
			right.render(f5);
		}
		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
		
		@Override
	   public void setRotationAngles(EntityDrownedBoss entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) 
	   {
			this.bone.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		    this.bone.rotateAngleY = (netHeadYaw) * ((float)Math.PI / 180F);
		    
		    this.sheld1.showModel = hasSheld >= 3;
		    this.sheld2.showModel = hasSheld >= 2;
		    this.sheld3.showModel = hasSheld >= 1;

		    this.sheld1.rotateAngleY = ageInTicks/20;
		    this.sheld2.rotateAngleY = -2.0944F+ageInTicks/20;
		    this.sheld3.rotateAngleY = 2.0944F+ageInTicks/20;

		    
		    bone.offsetY = 0.04F * MathHelper.cos(ageInTicks * 0.1F);
			right.offsetY =   -0.04F * MathHelper.cos(ageInTicks * 0.1F);
			left.offsetY =  -0.04F * MathHelper.cos(ageInTicks * 0.1F);

	   }
}