package com.github.zljtt.underwaterbiome.Client.Models;


import com.github.zljtt.underwaterbiome.Objects.Entity.EntityShark;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelShark extends EntityModel<EntityShark> 
{
	private final RendererModel bone;
	private final RendererModel center;
	private final RendererModel tail_1;
	private final RendererModel tail_2;
	private final RendererModel tail_3;
	private final RendererModel tail_3_2;
	private final RendererModel tail_3_1;
	private final RendererModel body_1;
	private final RendererModel head;
	private final RendererModel body_2;
	private final RendererModel head_1_1;
	private final RendererModel head_1_2;
	private final RendererModel head_2;
	private final RendererModel fins;
	private final RendererModel fin_left;
	private final RendererModel fin_right;
	private final RendererModel fin_up;

	public ModelShark() {
		textureWidth = 256;
		textureHeight = 256;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);

		center = new RendererModel(this);
		center.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(center);
			//tail
			tail_1 = new RendererModel(this);
			tail_1.setRotationPoint(0.0F, -6.0F, 25.0F);
			center.addChild(tail_1);
			tail_1.cubeList.add(new ModelBox(tail_1, 69, 239, -9.0F, 4.0F, -4.0F, 18, 2, 15, 0.0F, false));
			tail_1.cubeList.add(new ModelBox(tail_1, 88, 0, -9.0F, -13.0F, -4.0F, 18, 17, 22, 0.0F, false));
	
			tail_2 = new RendererModel(this);
			tail_2.setRotationPoint(0.0F, -6.0F, 25.0F);
			center.addChild(tail_2);
			tail_2.cubeList.add(new ModelBox(tail_2, 57, 120, -6.0F, -9.0F, 13.0F, 12, 11, 17, 0.0F, false));
	
			tail_3 = new RendererModel(this);
			tail_3.setRotationPoint(0.0F, -6.0F, 25.0F);
			center.addChild(tail_3);
	
				tail_3_2 = new RendererModel(this);
				tail_3_2.setRotationPoint(-0.5F, -4.0F, 37.5F);
				setRotationAngle(tail_3_2, -0.6981F, 0.0F, 0.0F);
				tail_3.addChild(tail_3_2);
				tail_3_2.cubeList.add(new ModelBox(tail_3_2, 115, 120, -1.5F, -20.0F, -10.5F, 3, 30, 7, 0.0F, false));
		
				tail_3_1 = new RendererModel(this);
				tail_3_1.setRotationPoint(-0.5F, -4.0F, 37.5F);
				setRotationAngle(tail_3_1, 0.6981F, 0.0F, 0.0F);
				tail_3.addChild(tail_3_1);
				tail_3_1.cubeList.add(new ModelBox(tail_3_1, 0, 64, -1.5F, -7.0F, -10.5F, 3, 17, 6, 0.0F, false));
			//body
			body_1 = new RendererModel(this);
			body_1.setRotationPoint(0.0F, -3.0F, 0.0F);
			center.addChild(body_1);
			body_1.cubeList.add(new ModelBox(body_1, 0, 0, -12.0F, -21.0F, -18.0F, 24, 24, 44, 0.0F, false));
			body_1.cubeList.add(new ModelBox(body_1, 0, 211, -12.0F, 3.0F, -18.0F, 24, 1, 44, 0.0F, false));
			//head
			head = new RendererModel(this);
			head.setRotationPoint(0.0F, -7.0F, -22.0F);
			center.addChild(head);
	
				body_2 = new RendererModel(this);
				body_2.setRotationPoint(0.0F, -3.5F, 10.5F);
				head.addChild(body_2);
				body_2.cubeList.add(new ModelBox(body_2, 0, 64, -10.0F, -11.5F, -17.5F, 20, 23, 27, 0.0F, false));
		
				head_1_1 = new RendererModel(this);
				head_1_1.setRotationPoint(0.0F, 4.0F, 0.0F);
				setRotationAngle(head_1_1, 0.3491F, 0.0F, 0.0F);
				head.addChild(head_1_1);
				head_1_1.cubeList.add(new ModelBox(head_1_1, 70, 90, -10.0F, -19.2085F, -22.5526F, 20, 6, 22, 0.0F, false));
		
				head_1_2 = new RendererModel(this);
				head_1_2.setRotationPoint(0.0F, 4.0F, 0.0F);
				setRotationAngle(head_1_2, 2.8798F, 0.0F, 0.0F);
				head.addChild(head_1_2);
				head_1_2.cubeList.add(new ModelBox(head_1_2, 0, 114, -10.0F, -5.2117F, 3.1822F, 20, 4, 17, 0.0F, false));
		
				head_2 = new RendererModel(this);
				head_2.setRotationPoint(0.0F, 4.0F, 0.0F);
				head.addChild(head_2);
				head_2.cubeList.add(new ModelBox(head_2, 105, 41, -9.0F, -12.0F, -20.0F, 18, 12, 23, 0.0F, false));
		//fins
		fins = new RendererModel(this);
		fins.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(fins);

			fin_left = new RendererModel(this);
			fin_left.setRotationPoint(0.0F, 0.0F, 0.0F);
			setRotationAngle(fin_left, 0.0F, 0.0F, 0.6109F);
			fins.addChild(fin_left);
			fin_left.cubeList.add(new ModelBox(fin_left, 0, 0, -17.0F, -7.0F, -7.0F, 8, 18, 8, 0.0F, false));
			fin_left.cubeList.add(new ModelBox(fin_left, 0, 135, -16.0F, -7.0F, -6.0F, 5, 27, 6, 0.0F, false));
	
			fin_right = new RendererModel(this);
			fin_right.setRotationPoint(0.0F, 0.0F, 0.0F);
			setRotationAngle(fin_right, 0.0F, 0.0F, -0.6109F);
			fins.addChild(fin_right);
			fin_right.cubeList.add(new ModelBox(fin_right, 0, 0, 10.0F, -7.0F, -7.0F, 8, 18, 8, 0.0F, false));
			fin_right.cubeList.add(new ModelBox(fin_right, 0, 135, 11.0F, -7.0F, -6.0F, 5, 27, 6, 0.0F, false));
	
			fin_up = new RendererModel(this);
			fin_up.setRotationPoint(0.0F, 0.0F, 0.0F);
			setRotationAngle(fin_up, -0.7854F, 0.0F, 0.0F);
			fins.addChild(fin_up);
			fin_up.cubeList.add(new ModelBox(fin_up, 134, 76, -2.0F, -37.0F, -17.0F, 3, 21, 10, 0.0F, false));
	}

	
	@Override
	public void render(EntityShark entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
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
	public void setRotationAngles(EntityShark entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
		this.bone.rotateAngleX = headPitch * ((float)Math.PI / 720F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 640F);

	    this.bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 720F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 680F);

	    
	    if (Entity.func_213296_b(entityIn.getMotion()) > 1.0E-7D) {
	         this.tail_1.rotateAngleY = -0.05F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail_2.rotateAngleY = -0.1F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail_3.rotateAngleY = -0.2F * MathHelper.cos(ageInTicks * 0.3F);
	         this.fin_right.rotateAngleZ = -0.6109F-0.05F * MathHelper.cos(ageInTicks * 0.3F);
	         this.fin_left.rotateAngleZ = 0.6109F+0.05F * MathHelper.cos(ageInTicks * 0.3F);
	      }
	    else 
	    {
	    	this.tail_1.rotateAngleY = -0.05F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail_2.rotateAngleY = -0.07F * MathHelper.cos(ageInTicks * 0.3F);
	         this.tail_3.rotateAngleY = -0.1F * MathHelper.cos(ageInTicks * 0.3F);
	         this.fin_right.rotateAngleZ = -0.6109F-0.03F * MathHelper.cos(ageInTicks * 0.3F);
	         this.fin_left.rotateAngleZ = 0.6109F+0.03F * MathHelper.cos(ageInTicks * 0.3F);
	    }
//	      this.field_78148_b.rotateAngleX = ((float)Math.PI / 2F);
//	      this.field_78149_c.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//	      this.field_78146_d.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//	      this.field_78147_e.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//	      this.field_78144_f.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}
