package xenoform.hailstorm.entity.blizzard;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelBlizzard - T3DK Created using Tabula 7.0.0
 */
public class ModelBlizzard extends ModelBase {
	public ModelRenderer Head;
	public ModelRenderer CloudlayerLow;
	public ModelRenderer CloudlayerMedium;
	public ModelRenderer CloudlayerHigh;
	public ModelRenderer RainBlocks;
	public ModelRenderer CloudlayerHigh_1;
	public ModelRenderer PlateHigh;
	public ModelRenderer PlateLow;
	public ModelRenderer shape5;
	public ModelRenderer shape5_1;
	public ModelRenderer shape5_2;
	public ModelRenderer shape5_3;
	public ModelRenderer shape5_4;
	public ModelRenderer shape5_5;
	public ModelRenderer shape5_6;
	public ModelRenderer shape5_7;
	public ModelRenderer shape5_8;
	public ModelRenderer shape5_9;
	public ModelRenderer shape5_10;
	public ModelRenderer shape5_11;
	public ModelRenderer shape5_12;

	public ModelBlizzard() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.CloudlayerLow = new ModelRenderer(this, 0, 0);
		this.CloudlayerLow.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.CloudlayerLow.addBox(-7.0F, 0.0F, -7.0F, 14, 1, 14, 0.0F);
		this.shape5_6 = new ModelRenderer(this, 0, 48);
		this.shape5_6.setRotationPoint(-12.0F, -1.8F, -5.2F);
		this.shape5_6.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_6, 0.0F, 0.0F, 0.2617993877991494F);
		this.shape5_9 = new ModelRenderer(this, 0, 48);
		this.shape5_9.setRotationPoint(12.0F, -1.8F, -5.3F);
		this.shape5_9.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_9, 0.0F, 0.0F, 0.2617993877991494F);
		this.CloudlayerHigh_1 = new ModelRenderer(this, 0, 0);
		this.CloudlayerHigh_1.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.CloudlayerHigh_1.addBox(-12.5F, 0.0F, -12.5F, 25, 3, 25, 0.0F);
		this.shape5_3 = new ModelRenderer(this, 0, 48);
		this.shape5_3.setRotationPoint(6.0F, -1.8F, -5.3F);
		this.shape5_3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_3, 0.0F, 0.0F, 0.2617993877991494F);
		this.RainBlocks = new ModelRenderer(this, 0, 0);
		this.RainBlocks.setRotationPoint(0.0F, 4.1F, 0.0F);
		this.RainBlocks.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.Head = new ModelRenderer(this, 0, 48);
		this.Head.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.Head.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 8, 0.0F);
		this.shape5_2 = new ModelRenderer(this, 0, 48);
		this.shape5_2.setRotationPoint(-7.5F, -1.8F, 0.0F);
		this.shape5_2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_2, 0.0F, 0.0F, 0.2617993877991494F);
		this.shape5_11 = new ModelRenderer(this, 0, 48);
		this.shape5_11.setRotationPoint(12.0F, -1.8F, 5.3F);
		this.shape5_11.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_11, 0.0F, 0.0F, 0.2617993877991494F);
		this.shape5_7 = new ModelRenderer(this, 0, 48);
		this.shape5_7.setRotationPoint(-12.0F, -1.8F, 5.3F);
		this.shape5_7.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_7, 0.0F, 0.0F, 0.2617993877991494F);
		this.shape5 = new ModelRenderer(this, 0, 48);
		this.shape5.setRotationPoint(-6.0F, -1.8F, -5.3F);
		this.shape5.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5, 0.0F, 0.0F, 0.2617993877991494F);
		this.CloudlayerMedium = new ModelRenderer(this, 0, 0);
		this.CloudlayerMedium.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.CloudlayerMedium.addBox(-10.0F, 0.0F, -10.0F, 20, 3, 20, 0.0F);
		this.shape5_4 = new ModelRenderer(this, 0, 48);
		this.shape5_4.setRotationPoint(7.5F, -1.8F, 0.0F);
		this.shape5_4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_4, 0.0F, 0.0F, 0.2617993877991494F);
		this.shape5_5 = new ModelRenderer(this, 0, 48);
		this.shape5_5.setRotationPoint(6.0F, -1.8F, 5.3F);
		this.shape5_5.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_5, 0.0F, 0.0F, 0.2617993877991494F);
		this.CloudlayerHigh = new ModelRenderer(this, 0, 0);
		this.CloudlayerHigh.setRotationPoint(0.0F, -5.0F, 0.0F);
		this.CloudlayerHigh.addBox(-15.0F, 0.0F, -15.0F, 30, 5, 30, 0.0F);
		this.shape5_12 = new ModelRenderer(this, 0, 48);
		this.shape5_12.setRotationPoint(0.0F, -1.8F, 7.5F);
		this.shape5_12.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_12, 0.0F, 0.0F, 0.2617993877991494F);
		this.PlateHigh = new ModelRenderer(this, 0, 0);
		this.PlateHigh.setRotationPoint(0.0F, 8.5F, 0.0F);
		this.PlateHigh.addBox(-3.0F, 0.0F, -3.0F, 6, 1, 6, 0.0F);
		this.shape5_8 = new ModelRenderer(this, 0, 48);
		this.shape5_8.setRotationPoint(-13.5F, -1.8F, 0.0F);
		this.shape5_8.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_8, 0.0F, 0.0F, 0.2617993877991494F);
		this.shape5_1 = new ModelRenderer(this, 0, 48);
		this.shape5_1.setRotationPoint(-6.0F, -1.8F, 5.3F);
		this.shape5_1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_1, 0.0F, 0.0F, 0.2617993877991494F);
		this.PlateLow = new ModelRenderer(this, 0, 0);
		this.PlateLow.setRotationPoint(0.0F, 10.0F, 0.0F);
		this.PlateLow.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
		this.shape5_10 = new ModelRenderer(this, 0, 48);
		this.shape5_10.setRotationPoint(13.5F, -1.8F, 0.0F);
		this.shape5_10.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape5_10, 0.0F, 0.0F, 0.2617993877991494F);
		this.RainBlocks.addChild(this.shape5_6);
		this.RainBlocks.addChild(this.shape5_9);
		this.RainBlocks.addChild(this.shape5_3);
		this.Head.addChild(this.RainBlocks);
		this.RainBlocks.addChild(this.shape5_2);
		this.RainBlocks.addChild(this.shape5_11);
		this.RainBlocks.addChild(this.shape5_7);
		this.RainBlocks.addChild(this.shape5);
		this.RainBlocks.addChild(this.shape5_4);
		this.RainBlocks.addChild(this.shape5_5);
		this.RainBlocks.addChild(this.shape5_12);
		this.Head.addChild(this.PlateHigh);
		this.RainBlocks.addChild(this.shape5_8);
		this.RainBlocks.addChild(this.shape5_1);
		this.Head.addChild(this.PlateLow);
		this.RainBlocks.addChild(this.shape5_10);
		this.CloudlayerLow.addChild(this.CloudlayerMedium);
		this.CloudlayerMedium.addChild(this.CloudlayerHigh);
		this.CloudlayerHigh.addChild(this.CloudlayerHigh_1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.Head.render(f5);
		this.CloudlayerLow.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {

		this.Head.rotateAngleX = (float) Math.toRadians(headPitch);
		this.Head.rotateAngleY = (float) Math.toRadians(netHeadYaw);

		this.shape5.rotationPointY = MathHelper.cos(0.1962F * ageInTicks) * 1.4F;
		this.shape5_1.rotationPointY = MathHelper.cos(0.1862F * ageInTicks) * 1.4F;
		this.shape5_2.rotationPointY = MathHelper.cos(0.1762F * ageInTicks) * 1.4F;
		this.shape5_3.rotationPointY = MathHelper.cos(0.1662F * ageInTicks) * 1.4F;
		this.shape5_4.rotationPointY = MathHelper.cos(0.1562F * ageInTicks) * 1.4F;
		this.shape5_5.rotationPointY = MathHelper.cos(0.1462F * ageInTicks) * 1.4F;
		this.shape5_6.rotationPointY = MathHelper.cos(0.1362F * ageInTicks) * 1.4F;
		this.shape5_7.rotationPointY = MathHelper.cos(0.1262F * ageInTicks) * 1.4F;
		this.shape5_8.rotationPointY = MathHelper.cos(0.1162F * ageInTicks) * 1.4F;
		this.shape5_9.rotationPointY = MathHelper.cos(0.1062F * ageInTicks) * 1.4F;
		this.shape5_10.rotationPointY = MathHelper.cos(0.1962F * ageInTicks) * 1.4F;
		this.shape5_11.rotationPointY = MathHelper.cos(0.1862F * ageInTicks) * 1.4F;
		this.shape5_12.rotationPointY = MathHelper.cos(0.1762F * ageInTicks) * 1.4F;

		this.PlateHigh.rotateAngleY = 0.1662F * ageInTicks * 1.4F;
		this.PlateLow.rotateAngleY = 0.1662F * ageInTicks * 1.4F * -1;

		float height = 20F;
		float range = 1.75F;
		float time = 0.2F;
		this.Head.rotationPointY = MathHelper.cos(time * ageInTicks) * range + height;
		this.CloudlayerLow.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 1.5F + 15.5F;
	}
}
