package com.github.zljtt.underwaterbiome.Client.Models;


import com.github.zljtt.underwaterbiome.Objects.Entity.EntityCreeperFish;
import com.github.zljtt.underwaterbiome.Objects.Entity.EntityFishSturgeon;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelCreeperFish extends EntityModel<EntityCreeperFish> 
{
	private final RendererModel bone;
	private final RendererModel body1;
	private final RendererModel body2;
	private final RendererModel side1;
	private final RendererModel side2;
	private final RendererModel bone2;
	private final RendererModel bone4;
	private final RendererModel tail1;
	private final RendererModel bone6;
	private final RendererModel bone5;
	private final RendererModel body3;
	private final RendererModel side4;
	private final RendererModel bone3;

	public ModelCreeperFish() 
	{
		textureWidth = 32;
		textureHeight = 32;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);

		body1 = new RendererModel(this);
		body1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(body1);
		body1.cubeList.add(new ModelBox(body1, 0, 0, -1.0F, -7.0F, -2.0F, 4, 4, 4, 0.0F, false));

		body2 = new RendererModel(this);
		body2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(body2, 0.0F, 0.0F, 0.0873F);
		bone.addChild(body2);
		body2.cubeList.add(new ModelBox(body2, 6, 8, -3.0F, -6.0F, -1.0F, 2, 1, 2, 0.0F, false));

		side1 = new RendererModel(this);
		side1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(side1, 0.4363F, 0.0F, 0.0F);
		bone.addChild(side1);
		side1.cubeList.add(new ModelBox(side1, 14, 8, 0.0F, -4.0F, 3.0F, 2, 2, 1, 0.0F, false));

		side2 = new RendererModel(this);
		side2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(side2, -0.4363F, 0.0F, 0.0F);
		bone.addChild(side2);
		side2.cubeList.add(new ModelBox(side2, 14, 8, 0.0F, -4.0F, -4.0F, 2, 2, 1, 0.0F, false));

		bone2 = new RendererModel(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 12, 0, 3.0F, -4.0F, -1.0F, 1, 1, 2, 0.0F, false));

		bone4 = new RendererModel(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, 3.0F, -6.0F, -2.0F, 1, 2, 1, 0.0F, false));

		tail1 = new RendererModel(this);
		tail1.setRotationPoint(-5.0F, 0.0F, 0.0F);
		setRotationAngle(tail1, 0.0F, 0.0F, 0.5236F);
		bone.addChild(tail1);
		tail1.cubeList.add(new ModelBox(tail1, 0, 8, 5.3301F, -12.5F, -1.0F, 1, 4, 2, 0.0F, false));

		bone6 = new RendererModel(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.5236F);
		bone.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 12, 12, 6.0F, -2.0F, -1.0F, 1, 3, 2, 0.0F, false));

		bone5 = new RendererModel(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.0873F);
		bone.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 0, 0, -2.0F, -5.0F, -1.0F, 2, 1, 2, 0.0F, false));

		body3 = new RendererModel(this);
		body3.setRotationPoint(0.0F, 24.0F, 0.0F);
		body3.cubeList.add(new ModelBox(body3, 4, 12, 3.0F, -6.0F, -1.0F, 2, 2, 2, 0.0F, false));

		side4 = new RendererModel(this);
		side4.setRotationPoint(0.0F, 24.0F, 0.0F);
		side4.cubeList.add(new ModelBox(side4, 12, 0, 3.0F, -7.0F, -1.0F, 1, 1, 2, 0.0F, false));

		bone3 = new RendererModel(this);
		bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, 3.0F, -6.0F, 1.0F, 1, 2, 1, 0.0F, false));
	}


	
	@Override
	public void render(EntityCreeperFish entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		// TODO Auto-generated method stub
		bone.render(scale);
		body3.render(scale);
		side4.render(scale);
		bone3.render(scale);
	}

	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) 
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(EntityCreeperFish entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
		this.bone.rotateAngleY = (netHeadYaw -90) * ((float)Math.PI / 180F);
		this.body3.rotateAngleY = (netHeadYaw -90) * ((float)Math.PI / 180F);
		this.side4.rotateAngleY = (netHeadYaw -90) * ((float)Math.PI / 180F);
		this.bone3.rotateAngleY = (netHeadYaw -90) * ((float)Math.PI / 180F);

		float f = 1.0F;
	    if (!entityIn.isInWater()) 
	    {
	         f = 1.5F;
	    }
	      this.tail1.rotateAngleY = -f * 0.1F * MathHelper.sin(0.4F * ageInTicks);
	      this.bone6.rotateAngleY = -f * 0.1F * MathHelper.sin(0.4F * ageInTicks);

//		this.bone.rotateAngleX = headPitch * ((float)Math.PI / 640F);
//		this.head.rotateAngleX = headPitch * ((float)Math.PI / 540F);
//
//	    this.bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 640F);
//		this.head.rotateAngleY = headPitch * ((float)Math.PI / 540F);

	    
//	    if (Entity.func_213296_b(entityIn.getMotion()) > 1.0E-7D) {
////	         this.tail_1.rotateAngleX += -0.05F + -0.05F * MathHelper.cos(ageInTicks * 0.3F);
//	         this.tail_1.rotateAngleY = -0.05F * MathHelper.cos(ageInTicks * 0.3F);
//	         this.tail_2.rotateAngleY = -0.09F * MathHelper.cos(ageInTicks * 0.3F);
//	         this.tail_3.rotateAngleY = -0.13F * MathHelper.cos(ageInTicks * 0.3F);
//	      }
//	    else
//	    {
//	    	 this.tail_1.rotateAngleY = -0.02F * MathHelper.cos(ageInTicks * 0.3F);
//	         this.tail_2.rotateAngleY = -0.06F * MathHelper.cos(ageInTicks * 0.3F);
//	         this.tail_3.rotateAngleY = -0.10F * MathHelper.cos(ageInTicks * 0.3F);
	 }
//	      this.field_78148_b.rotateAngleX = ((float)Math.PI / 2F);
//	      this.field_78149_c.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//	      this.field_78146_d.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//	      this.field_78147_e.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//	      this.field_78144_f.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
}

