//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class  extends ModelBase {
	private final ModelRenderer left;
	private final ModelRenderer right;
	private final ModelRenderer bone;
	private final ModelRenderer sheld3;
	private final ModelRenderer sheld2;
	private final ModelRenderer sheld1;

	public () {
		textureWidth = 64;
		textureHeight = 64;

		left = new ModelRenderer(this);
		left.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(left, 0.0F, 0.0F, -0.3491F);
		left.cubeList.add(new ModelBox(left, 17, 15, -11.0F, -6.0F, -3.0F, 3, 3, 3, 0.0F, true));

		right = new ModelRenderer(this);
		right.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(right, 0.0F, 0.0F, 0.3491F);
		right.cubeList.add(new ModelBox(right, 17, 15, 8.0F, -6.0F, -3.0F, 3, 3, 3, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -5.0F, -11.0F, -5.0F, 10, 11, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 20, -4.0F, -9.0F, -4.0F, 8, 7, 8, 0.0F, false));

		sheld3 = new ModelRenderer(this);
		sheld3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(sheld3, 0.0F, 2.0944F, 0.0F);
		bone.addChild(sheld3);
		sheld3.cubeList.add(new ModelBox(sheld3, 21, 11, -3.0F, -9.0F, 7.0F, 6, 8, 1, 0.0F, false));
		sheld3.cubeList.add(new ModelBox(sheld3, 22, 16, -2.0F, -1.0F, 7.0F, 4, 1, 1, 0.0F, false));

		sheld2 = new ModelRenderer(this);
		sheld2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(sheld2, 0.0F, -2.0944F, 0.0F);
		bone.addChild(sheld2);
		sheld2.cubeList.add(new ModelBox(sheld2, 21, 11, -3.0F, -9.0F, 7.0F, 6, 8, 1, 0.0F, false));
		sheld2.cubeList.add(new ModelBox(sheld2, 22, 16, -2.0F, -1.0F, 7.0F, 4, 1, 1, 0.0F, false));

		sheld1 = new ModelRenderer(this);
		sheld1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(sheld1);
		sheld1.cubeList.add(new ModelBox(sheld1, 21, 11, -3.0F, -9.0F, 7.0F, 6, 8, 1, 0.0F, false));
		sheld1.cubeList.add(new ModelBox(sheld1, 22, 16, -2.0F, -1.0F, 7.0F, 4, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		left.render(f5);
		right.render(f5);
		bone.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}