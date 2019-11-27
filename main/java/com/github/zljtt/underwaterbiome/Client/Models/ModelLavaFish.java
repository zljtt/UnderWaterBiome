package com.github.zljtt.underwaterbiome.Client.Models;


import com.github.zljtt.underwaterbiome.Objects.Entity.EntityFishSturgeon;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityLavaFish;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelLavaFish extends EntityModel<EntityLavaFish> 
{
	private final RendererModel bone;
	private final RendererModel bone2;
	private final RendererModel bone3;
	private final RendererModel bone4;
	private final RendererModel bone5;
	private final RendererModel bone6;
	private final RendererModel bone7;
	private final RendererModel bone8;
	private final RendererModel bone9;
	private final RendererModel bone10;
	private final RendererModel bone11;
	private final RendererModel tail3;
	private final RendererModel tail1;
	private final RendererModel tail2;
	private final RendererModel bone13;

	public ModelLavaFish() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 20, -1.0F, -14.0F, 3.0F, 2, 6, 2, 0.0F, false));

		bone2 = new RendererModel(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -3.0F, -9.0F, -4.0F, 6, 7, 13, 0.0F, false));

		bone3 = new RendererModel(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone3, 0.0873F, 0.0F, 0.0F);
		bone.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 31, -2.0F, -7.0F, -6.0F, 4, 2, 4, 0.0F, false));

		bone4 = new RendererModel(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone4, 0.0873F, 0.0F, 0.0F);
		bone.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 31, -2.0F, -4.0F, -6.0F, 4, 2, 4, 0.0F, false));

		bone5 = new RendererModel(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, 0.6981F, 0.0F, -0.2618F);
		bone.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 37, 37, -1.0F, -12.0F, 2.0F, 2, 5, 2, 0.0F, false));

		bone6 = new RendererModel(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone6, 0.6981F, 0.0F, 0.2618F);
		bone.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 37, -1.0F, -12.0F, 2.0F, 2, 5, 2, 0.0F, false));

		bone7 = new RendererModel(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone7, 0.2618F, 0.0F, 0.0F);
		bone.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 14, 36, -1.0F, -13.0F, 0.0F, 2, 6, 2, 0.0F, false));

		bone8 = new RendererModel(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 18, 20, -1.0F, -14.0F, 0.0F, 2, 6, 2, 0.0F, false));

		bone9 = new RendererModel(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone9, -0.2618F, 0.0F, 0.0F);
		bone.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 0, 0, -1.0F, -14.0F, 3.0F, 2, 6, 2, 0.0F, false));

		bone10 = new RendererModel(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 0, 20, -2.0F, -10.0F, -2.0F, 4, 1, 10, 0.0F, false));

		bone11 = new RendererModel(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone11, 0.0F, -0.4363F, 0.0F);
		bone.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 13, 5, 3.0F, -8.0F, 3.0F, 5, 4, 2, 0.0F, false));

		tail3 = new RendererModel(this);
		tail3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(tail3, -0.0873F, 0.0F, 0.0F);
		bone.addChild(tail3);
		tail3.cubeList.add(new ModelBox(tail3, 25, 0, -2.0F, -9.0F, 8.0F, 4, 5, 6, 0.0F, false));

		tail1 = new RendererModel(this);
		tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(tail1, 0.0F, 0.6981F, 0.0F);
		bone.addChild(tail1);
		tail1.cubeList.add(new ModelBox(tail1, 21, 24, -8.0F, -9.0F, 11.0F, 2, 7, 7, 0.0F, false));

		tail2 = new RendererModel(this);
		tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(tail2, 0.0F, -0.6981F, 0.0F);
		bone.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 21, 24, 6.0F, -9.0F, 11.0F, 2, 7, 7, 0.0F, false));

		bone13 = new RendererModel(this);
		bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone13, 0.0F, 0.4363F, 0.0F);
		bone.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 23, 31, -8.0F, -8.0F, 3.0F, 5, 4, 2, 0.0F, false));
	}
	@Override
	public void render(EntityLavaFish entity, float f, float f1, float f2, float f3, float f4, float f5) 
	{
		bone.render(f5);
		
	}
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(EntityLavaFish entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
//		this.bone.rotateAngleX = headPitch * ((float)Math.PI / 640F);
//
//	    this.bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 640F);

	    
	    {
	    	 this.tail1.rotateAngleY = 0.6981F-0.05F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail2.rotateAngleY = -0.6981F-0.05F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail3.rotateAngleY = -0.05F * MathHelper.cos(ageInTicks * 0.3F);
	    }
//	      this.field_78148_b.rotateAngleX = ((float)Math.PI / 2F);
//	      this.field_78149_c.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//	      this.field_78146_d.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//	      this.field_78147_e.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//	      this.field_78144_f.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}
