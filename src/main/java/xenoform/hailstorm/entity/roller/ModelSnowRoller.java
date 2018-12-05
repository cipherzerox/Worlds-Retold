package xenoform.hailstorm.entity.roller;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SnowRoller - Xenoform55 Created using Tabula 7.0.0
 */
public class ModelSnowRoller extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer Top1;
    public ModelRenderer Bottom1;
    public ModelRenderer Back1;
    public ModelRenderer Front1;
    public ModelRenderer Left1;
    public ModelRenderer Right1;
    public ModelRenderer Top2;
    public ModelRenderer Bottom2;
    public ModelRenderer Back2;
    public ModelRenderer Front2;
    public ModelRenderer Left2;
    public ModelRenderer Right2;

    public ModelSnowRoller() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Top1 = new ModelRenderer(this, 0, 0);
        this.Top1.setRotationPoint(0.0F, -7.5F, 0.0F);
        this.Top1.addBox(-5.0F, -0.5F, -5.0F, 10, 1, 10, 0.0F);
        this.Right2 = new ModelRenderer(this, 0, 0);
        this.Right2.setRotationPoint(-1.0F, -0.5F, 0.5F);
        this.Right2.addBox(-0.5F, -2.5F, -3.0F, 1, 6, 6, 0.0F);
        this.Right1 = new ModelRenderer(this, 0, 0);
        this.Right1.setRotationPoint(-7.5F, 0.0F, 0.0F);
        this.Right1.addBox(-0.5F, -5.0F, -5.0F, 1, 10, 10, 0.0F);
        this.Bottom2 = new ModelRenderer(this, 31, 52);
        this.Bottom2.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.Bottom2.addBox(-3.0F, -0.5F, -3.0F, 6, 1, 6, 0.0F);
        this.Head = new ModelRenderer(this, 32, 4);
        this.Head.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.Head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.Bottom1 = new ModelRenderer(this, 0, 51);
        this.Bottom1.setRotationPoint(0.0F, 7.5F, 0.0F);
        this.Bottom1.addBox(-5.0F, -0.5F, -5.0F, 10, 1, 10, 0.0F);
        this.Body = new ModelRenderer(this, 0, 21);
        this.Body.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.Body.addBox(-7.0F, -7.0F, -7.0F, 14, 14, 14, 0.0F);
        this.Back2 = new ModelRenderer(this, 0, 0);
        this.Back2.setRotationPoint(0.0F, -0.5F, 0.5F);
        this.Back2.addBox(-3.0F, -2.5F, 0.0F, 6, 6, 1, 0.0F);
        this.Front1 = new ModelRenderer(this, 0, 0);
        this.Front1.setRotationPoint(0.0F, 0.0F, -7.5F);
        this.Front1.addBox(-5.0F, -5.0F, -0.5F, 10, 10, 1, 0.0F);
        this.Front2 = new ModelRenderer(this, 0, 0);
        this.Front2.setRotationPoint(0.0F, -0.5F, -1.5F);
        this.Front2.addBox(-3.0F, -2.5F, 0.0F, 6, 6, 1, 0.0F);
        this.Left1 = new ModelRenderer(this, 0, 0);
        this.Left1.setRotationPoint(12.0F, 0.0F, 0.0F);
        this.Left1.addBox(-5.0F, -5.0F, -5.0F, 1, 10, 10, 0.0F);
        this.Top2 = new ModelRenderer(this, 0, 0);
        this.Top2.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Top2.addBox(-3.0F, -0.5F, -3.0F, 6, 1, 6, 0.0F);
        this.Left2 = new ModelRenderer(this, 0, 0);
        this.Left2.setRotationPoint(-3.5F, -0.5F, 0.5F);
        this.Left2.addBox(-0.5F, -2.5F, -3.0F, 1, 6, 6, 0.0F);
        this.Back1 = new ModelRenderer(this, 0, 0);
        this.Back1.setRotationPoint(0.0F, 0.0F, 7.4F);
        this.Back1.addBox(-5.0F, -5.0F, -0.5F, 10, 10, 1, 0.0F);
        this.Body.addChild(this.Top1);
        this.Right1.addChild(this.Right2);
        this.Body.addChild(this.Right1);
        this.Bottom1.addChild(this.Bottom2);
        this.Body.addChild(this.Bottom1);
        this.Back1.addChild(this.Back2);
        this.Body.addChild(this.Front1);
        this.Front1.addChild(this.Front2);
        this.Body.addChild(this.Left1);
        this.Top1.addChild(this.Top2);
        this.Left1.addChild(this.Left2);
        this.Body.addChild(this.Back1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Head.render(f5);
        this.Body.render(f5);
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
		this.Body.rotateAngleX = 0.6f * limbSwing;
		this.Head.rotateAngleX = headPitch / 57.295776f;
		this.Head.rotateAngleY = netHeadYaw / 57.295776f;
	}
}
