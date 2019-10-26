package com.github.zljtt.underwaterbiome.Client.Models;


import com.github.zljtt.underwaterbiome.Objects.Entity.EntityFishSturgeon;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelFishSturgeon extends EntityModel<EntityFishSturgeon> 
{
	private final RendererModel bone;
	private final RendererModel body;
	private final RendererModel head;
	private final RendererModel head_3;
	private final RendererModel head_2;
	private final RendererModel head_1;
	private final RendererModel tail_3;
	private final RendererModel tail_3_2;
	private final RendererModel tail_3_1;
	private final RendererModel tail_2;
	private final RendererModel tail_1;
	private final RendererModel fin;
	private final RendererModel fin_2;
	private final RendererModel fin_1;

	public ModelFishSturgeon() 
	{
		textureWidth = 128;
		textureHeight = 128;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 20.0F, 0.0F);

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 0, -6.0F, -5.0F, -4.0F, 12, 9, 18, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 27, -5.0F, -6.0F, -4.0F, 10, 1, 17, 0.0F, false));

		head = new RendererModel(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(head);

		head_3 = new RendererModel(this);
		head_3.setRotationPoint(0.0F, 1.0F, -8.0F);
		setRotationAngle(head_3, -1.2217F, 0.0F, 0.0F);
		head.addChild(head_3);
		head_3.cubeList.add(new ModelBox(head_3, 0, 45, -5.0F, -6.0F, -5.0F, 10, 13, 3, 0.0F, false));

		head_2 = new RendererModel(this);
		head_2.setRotationPoint(0.0F, 1.0F, -11.5F);
		head.addChild(head_2);
		head_2.cubeList.add(new ModelBox(head_2, 60, 11, -4.0F, -4.0F, 1.5F, 8, 5, 4, 0.0F, false));
		head_2.cubeList.add(new ModelBox(head_2, 60, 20, -4.0F, -3.0F, -1.5F, 8, 4, 3, 0.0F, false));
		head_2.cubeList.add(new ModelBox(head_2, 24, 59, -5.0F, -4.0F, 5.5F, 10, 7, 2, 0.0F, false));

		head_1 = new RendererModel(this);
		head_1.setRotationPoint(0.0F, 3.0F, -11.5F);
		setRotationAngle(head_1, -0.1745F, 0.0F, 0.0F);
		head.addChild(head_1);
		head_1.cubeList.add(new ModelBox(head_1, 42, 0, -5.0F, -2.0F, -2.5F, 10, 2, 9, 0.0F, false));

		tail_3 = new RendererModel(this);
		tail_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(tail_3);

		tail_3_2 = new RendererModel(this);
		tail_3_2.setRotationPoint(0.0F, 2.0F, 28.0F);
		setRotationAngle(tail_3_2, -0.6981F, 0.0F, 0.0F);
		tail_3.addChild(tail_3_2);
		tail_3_2.cubeList.add(new ModelBox(tail_3_2, 0, 0, -1.0F, -13.0F, -4.0F, 2, 12, 4, 0.0F, false));

		tail_3_1 = new RendererModel(this);
		tail_3_1.setRotationPoint(0.0F, 2.0F, 28.0F);
		setRotationAngle(tail_3_1, -2.618F, 0.0F, 0.0F);
		tail_3.addChild(tail_3_1);
		tail_3_1.cubeList.add(new ModelBox(tail_3_1, 0, 27, -1.0F, -9.0F, -1.0F, 2, 12, 3, 0.0F, false));

		tail_2 = new RendererModel(this);
		tail_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(tail_2);
		tail_2.cubeList.add(new ModelBox(tail_2, 49, 49, -3.0F, -2.0F, 21.0F, 6, 5, 7, 0.0F, false));
		tail_2.cubeList.add(new ModelBox(tail_2, 42, 11, -2.0F, -3.0F, 21.0F, 4, 1, 4, 0.0F, false));

		tail_1 = new RendererModel(this);
		tail_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(tail_1);
		tail_1.cubeList.add(new ModelBox(tail_1, 37, 27, -5.0F, -4.0F, 13.0F, 10, 8, 9, 0.0F, false));
		tail_1.cubeList.add(new ModelBox(tail_1, 26, 45, -4.0F, -5.0F, 13.0F, 8, 1, 7, 0.0F, false));

		fin = new RendererModel(this);
		fin.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(fin);

		fin_2 = new RendererModel(this);
		fin_2.setRotationPoint(0.0F, -7.0F, 7.5F);
		setRotationAngle(fin_2, -0.6109F, 0.0F, 0.0F);
		fin.addChild(fin_2);
		fin_2.cubeList.add(new ModelBox(fin_2, 0, 61, -1.0F, -5.0F, -1.5F, 2, 8, 3, 0.0F, false));

		fin_1 = new RendererModel(this);
		fin_1.setRotationPoint(0.0F, 6.0F, 16.5F);
		setRotationAngle(fin_1, 0.3491F, 0.0F, 0.0F);
		fin.addChild(fin_1);
		fin_1.cubeList.add(new ModelBox(fin_1, 10, 61, -1.0F, -3.0F, -1.5F, 2, 6, 3, 0.0F, false));
	}


	
	@Override
	public void render(EntityFishSturgeon entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		// TODO Auto-generated method stub
		bone.render(scale);
	}

	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) 
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(EntityFishSturgeon entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
		this.bone.rotateAngleX = headPitch * ((float)Math.PI / 640F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 540F);

	    this.bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 640F);
		this.head.rotateAngleY = headPitch * ((float)Math.PI / 540F);

	    
	    if (Entity.func_213296_b(entityIn.getMotion()) > 1.0E-7D) {
//	         this.tail_1.rotateAngleX += -0.05F + -0.05F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail_1.rotateAngleY = -0.05F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail_2.rotateAngleY = -0.09F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail_3.rotateAngleY = -0.13F * MathHelper.cos(ageInTicks * 0.3F);
	      }
	    else
	    {
	    	 this.tail_1.rotateAngleY = -0.02F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail_2.rotateAngleY = -0.06F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail_3.rotateAngleY = -0.10F * MathHelper.cos(ageInTicks * 0.3F);
	    }
//	      this.field_78148_b.rotateAngleX = ((float)Math.PI / 2F);
//	      this.field_78149_c.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//	      this.field_78146_d.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//	      this.field_78147_e.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//	      this.field_78144_f.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}
