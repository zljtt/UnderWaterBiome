package com.github.zljtt.underwaterbiome.Client.Models;


import com.github.zljtt.underwaterbiome.Objects.Entity.EntityConchSeaGrass;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelConchSeaGrass extends EntityModel<EntityConchSeaGrass> 
{
	private final RendererModel bone;
	private final RendererModel shell;
	private final RendererModel body;

	public ModelConchSeaGrass() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone, 0.0F, -2.3562F, 0.0F);

		shell = new RendererModel(this);
		shell.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(shell, 0.3491F, 0.0F, 0.3491F);
		bone.addChild(shell);
		shell.cubeList.add(new ModelBox(shell, 0, 0, -6.0F, -3.0F, -4.0F, 10, 2, 10, 0.0F, false));
		shell.cubeList.add(new ModelBox(shell, 0, 12, -5.0F, -5.0F, -3.0F, 8, 2, 8, 0.0F, false));
		shell.cubeList.add(new ModelBox(shell, 18, 22, -4.0F, -4.0F, 5.0F, 7, 1, 1, 0.0F, false));
		shell.cubeList.add(new ModelBox(shell, 24, 12, -4.0F, -7.0F, -1.0F, 6, 1, 5, 0.0F, false));
		shell.cubeList.add(new ModelBox(shell, 24, 18, -3.0F, -6.0F, -2.0F, 5, 1, 1, 0.0F, false));
		shell.cubeList.add(new ModelBox(shell, 0, 22, -4.0F, -6.0F, -1.0F, 6, 1, 6, 0.0F, false));
		shell.cubeList.add(new ModelBox(shell, 7, 29, -3.0F, -8.0F, 0.0F, 4, 1, 3, 0.0F, false));

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(body);
		body.cubeList.add(new ModelBox(body, 19, 24, -3.0F, -3.0F, -2.0F, 5, 3, 5, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 30, 0, -2.0F, -2.0F, 3.0F, 5, 2, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 29, -4.0F, -2.0F, -3.0F, 1, 2, 5, 0.0F, false));
	}
	
	@Override
	public void render(EntityConchSeaGrass entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) 
	{
		bone.render(scale);
	}
	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) 
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}


}
