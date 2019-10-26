package com.github.zljtt.underwaterbiome.Client.Models;


import com.github.zljtt.underwaterbiome.Objects.Entity.EntityRayBoss;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelRayBoss extends EntityModel<EntityRayBoss> 
{
	private final RendererModel bone;
	private final RendererModel right;
	private final RendererModel left;

	public ModelRayBoss() {
		textureWidth = 256;
		textureHeight = 256;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 3.1416F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 72, 0, -12.0F, -6.0F, -5.0435F, 6, 2, 22, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -8.0F, -4.0F, -15.0435F, 16, 2, 40, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, -4.0F, -35.0435F, 8, 2, 12, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 38, 96, -2.0F, -4.0F, -53.0435F, 4, 2, 18, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 72, -2.0F, -6.0F, -29.0435F, 4, 2, 24, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 14, -6.0F, -4.0F, -23.0435F, 12, 2, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 32, 72, 6.0F, -6.0F, -5.0435F, 6, 2, 22, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 66, 66, -6.0F, -8.0F, -7.0435F, 12, 4, 30, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 42, -10.0F, -2.0F, -5.0435F, 20, 2, 28, 0.0F, false));

		left = new RendererModel(this);
		left.setRotationPoint(0.0F, -4.0F, 0.0F);
		bone.addChild(left);
		left.cubeList.add(new ModelBox(left, 68, 42, 7.0F, 0.0F, 6.9565F, 28, 2, 8, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 72, 32, 7.0F, 0.0F, 0.9565F, 24, 2, 6, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 64, 106, 7.0F, 0.0F, -3.0435F, 18, 2, 4, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 0, 30, 7.0F, 0.0F, -7.0435F, 10, 2, 4, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 0, 60, 7.0F, 0.0F, -11.0435F, 6, 2, 4, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 0, 24, 7.0F, 0.0F, 18.9565F, 10, 2, 4, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 0, 98, 7.0F, 0.0F, 14.9565F, 22, 2, 4, 0.0F, false));

		right = new RendererModel(this);
		right.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(right);
		right.cubeList.add(new ModelBox(right, 68, 52, -35.0F, -4.0F, 6.9565F, 28, 2, 8, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 72, 24, -31.0F, -4.0F, 0.9565F, 24, 2, 6, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 0, 104, -25.0F, -4.0F, -3.0435F, 18, 2, 4, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 0, 48, -17.0F, -4.0F, -7.0435F, 10, 2, 4, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 0, 54, -13.0F, -4.0F, -11.0435F, 6, 2, 4, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 64, 100, -29.0F, -4.0F, 14.9565F, 22, 2, 4, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 0, 42, -17.0F, -4.0F, 18.9565F, 10, 2, 4, 0.0F, false));
	}

	@Override
	public void render(EntityRayBoss entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5*1.15F);
	}
	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
	@Override
	public void setRotationAngles(EntityRayBoss entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
		this.bone.rotateAngleX = headPitch * ((float)Math.PI / 720F);
	    this.bone.rotateAngleY = (netHeadYaw +180) * ((float)Math.PI / 180F);
	    
	    if (Entity.func_213296_b(entityIn.getMotion()) > 1.0E-7D) 
	    {
//	         this.tail_1.rotateAngleX += -0.05F + -0.05F * MathHelper.cos(ageInTicks * 0.3F);
	         this.right.rotateAngleZ = -0.11F * MathHelper.cos(ageInTicks * 0.3F)+0.05F;
	         this.left.rotateAngleZ = 0.11F * MathHelper.cos(ageInTicks * 0.3F)-0.05F;

	    }
	    else
	    {
	    	this.right.rotateAngleZ = -0.11F * MathHelper.cos(ageInTicks * 0.2F)+0.05F;
	         this.left.rotateAngleZ = 0.11F * MathHelper.cos(ageInTicks * 0.2F)-0.05F;
	    }
	}
}