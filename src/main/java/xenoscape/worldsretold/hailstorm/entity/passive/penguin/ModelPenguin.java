package xenoscape.worldsretold.hailstorm.entity.passive.penguin;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelPenguin - Xenoform55 Created using Tabula 7.0.0
 */
public class ModelPenguin extends ModelBase {
	public ModelRenderer Body;
	public ModelRenderer Head;
	public ModelRenderer LArm;
	public ModelRenderer RArm;
	public ModelRenderer RLeg;
	public ModelRenderer LLeg;
	public ModelRenderer Beak;
	public ModelRenderer RFlipper;
	public ModelRenderer LFlipper;

	public ModelPenguin() {
		this.textureWidth = 46;
		this.textureHeight = 32;
		this.RFlipper = new ModelRenderer(this, 31, 17);
		this.RFlipper.setRotationPoint(0.0F, 1.0F, 0.0F);
		this.RFlipper.addBox(-1.0F, 0.0F, -4.0F, 2, 1, 5, 0.0F);
		this.setRotateAngle(RFlipper, 0.0F, 0.2617993877991494F, 0.0F);
		this.LArm = new ModelRenderer(this, 0, 0);
		this.LArm.setRotationPoint(4.0F, -4.0F, 0.5F);
		this.LArm.addBox(0.0F, -0.5F, -2.0F, 1, 8, 4, 0.0F);
		this.LFlipper = new ModelRenderer(this, 31, 17);
		this.LFlipper.setRotationPoint(0.0F, 1.0F, 0.0F);
		this.LFlipper.addBox(-1.0F, 0.0F, -4.0F, 2, 1, 5, 0.0F);
		this.setRotateAngle(LFlipper, 0.0F, -0.2617993877991494F, 0.0F);
		this.RLeg = new ModelRenderer(this, 29, 12);
		this.RLeg.mirror = true;
		this.RLeg.setRotationPoint(-2.3F, 6.0F, 0.5F);
		this.RLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 1, 3, 0.0F);
		this.RArm = new ModelRenderer(this, 0, 0);
		this.RArm.setRotationPoint(-4.0F, -4.0F, 0.5F);
		this.RArm.addBox(-1.0F, -0.5F, -2.0F, 1, 8, 4, 0.0F);
		this.Body = new ModelRenderer(this, 0, 13);
		this.Body.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.Body.addBox(-4.0F, -5.0F, -3.0F, 8, 11, 7, 0.0F);
		this.Beak = new ModelRenderer(this, 31, 25);
		this.Beak.setRotationPoint(0.0F, -1.5F, -3.0F);
		this.Beak.addBox(-1.0F, -0.5F, -1.5F, 2, 1, 2, 0.0F);
		this.Head = new ModelRenderer(this, 11, 0);
		this.Head.setRotationPoint(0.0F, -5.0F, 0.5F);
		this.Head.addBox(-3.5F, -5.0F, -3.0F, 7, 5, 6, 0.0F);
		this.LLeg = new ModelRenderer(this, 29, 12);
		this.LLeg.setRotationPoint(2.3F, 6.0F, 0.5F);
		this.LLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 1, 3, 0.0F);
		this.RLeg.addChild(this.RFlipper);
		this.Body.addChild(this.LArm);
		this.LLeg.addChild(this.LFlipper);
		this.Body.addChild(this.RLeg);
		this.Body.addChild(this.RArm);
		this.Head.addChild(this.Beak);
		this.Body.addChild(this.Head);
		this.Body.addChild(this.LLeg);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		if (this.isChild) {
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * f5, 0.0F);
			this.Body.render(f5);
			GlStateManager.popMatrix();
		} else {
			this.Body.render(f5);
		}
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
		EntityPenguin entity = (EntityPenguin) entityIn;
		if (entity.isSliding()) {
			this.Head.rotateAngleX = -70.65f;
			this.Head.rotateAngleY = 0f;
			this.Body.rotateAngleX = 89.5f;
			this.Body.offsetY = 0.2f;
			this.RArm.rotateAngleZ = ageInTicks + 19.3F;
			this.LArm.rotateAngleZ = -ageInTicks - 19.3F;
		} else {
			this.Head.rotateAngleX = headPitch / 57.295776f;
			this.Head.rotateAngleY = netHeadYaw / 57.295776f;
			this.Body.rotateAngleX = 0f;
			this.Body.offsetY = 0f;
			this.RArm.rotateAngleZ = ageInTicks + 6.5F;
			this.LArm.rotateAngleZ = -ageInTicks - 6.5F;
		}
		this.RLeg.rotateAngleX = MathHelper.cos(limbSwing * 1.5F) * 0.4F * limbSwingAmount;
		this.LLeg.rotateAngleX = MathHelper.cos(limbSwing * 1.5F + (float) Math.PI) * 0.4F * limbSwingAmount;
	}
}
