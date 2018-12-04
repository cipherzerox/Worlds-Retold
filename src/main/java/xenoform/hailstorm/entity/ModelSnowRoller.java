package xenoform.hailstorm.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * RollerBois - T3DK
 * Created using Tabula 6.0.0
 */
public class ModelSnowRoller extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer REye;
    public ModelRenderer LEye;
    public ModelRenderer SnowLayer1;
    public ModelRenderer SnowLayer2;
    public ModelRenderer SnowLayer5;
    public ModelRenderer SnowLayer8;
    public ModelRenderer SnowLayer9;
    public ModelRenderer SnowLayer12;
    public ModelRenderer SnowLayer3;
    public ModelRenderer SnowLayer4;
    public ModelRenderer SnowLayer6;
    public ModelRenderer SnowLayer7;
    public ModelRenderer SnowLayer10;
    public ModelRenderer SnowLayer11;
    public ModelRenderer SnowLayer13;
    public ModelRenderer SnowLayer14;

    public ModelSnowRoller() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.SnowLayer2 = new ModelRenderer(this, 0, 0);
        this.SnowLayer2.setRotationPoint(0.0F, -11.0F, 0.0F);
        this.SnowLayer2.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10, 0.0F);
        this.setRotateAngle(SnowLayer2, 4.71238898038469F, 0.0F, 0.0F);
        this.SnowLayer9 = new ModelRenderer(this, 0, 0);
        this.SnowLayer9.setRotationPoint(11.0F, -1.0F, 11.0F);
        this.SnowLayer9.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10, 0.0F);
        this.setRotateAngle(SnowLayer9, 1.5707963267948966F, 1.5707963267948966F, 0.0F);
        this.SnowLayer14 = new ModelRenderer(this, 0, 0);
        this.SnowLayer14.setRotationPoint(1.0F, 2.0F, 2.0F);
        this.SnowLayer14.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8, 0.0F);
        this.LEye = new ModelRenderer(this, 0, 0);
        this.LEye.setRotationPoint(1.4F, -2.0F, 2.6F);
        this.LEye.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.SnowLayer1 = new ModelRenderer(this, 0, 0);
        this.SnowLayer1.setRotationPoint(-5.0F, 7.0F, -5.0F);
        this.SnowLayer1.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10, 0.0F);
        this.SnowLayer12 = new ModelRenderer(this, 0, 0);
        this.SnowLayer12.setRotationPoint(-1.0F, -1.0F, 1.0F);
        this.SnowLayer12.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10, 0.0F);
        this.setRotateAngle(SnowLayer12, 1.5707963267948966F, -1.5707963267948966F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 16);
        this.Body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.Body.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(Body, 0.0F, 3.141592653589793F, 0.0F);
        this.REye = new ModelRenderer(this, 0, 0);
        this.REye.setRotationPoint(-3.4F, -2.0F, 2.6F);
        this.REye.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.SnowLayer7 = new ModelRenderer(this, 0, 0);
        this.SnowLayer7.setRotationPoint(2.0F, 2.0F, 2.0F);
        this.SnowLayer7.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8, 0.0F);
        this.SnowLayer10 = new ModelRenderer(this, 0, 0);
        this.SnowLayer10.setRotationPoint(0.0F, -1.0F, -1.0F);
        this.SnowLayer10.addBox(0.0F, 0.0F, 0.0F, 10, 1, 12, 0.0F);
        this.SnowLayer6 = new ModelRenderer(this, 0, 0);
        this.SnowLayer6.setRotationPoint(-1.0F, -1.0F, -1.0F);
        this.SnowLayer6.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12, 0.0F);
        this.SnowLayer11 = new ModelRenderer(this, 0, 0);
        this.SnowLayer11.setRotationPoint(1.0F, 2.0F, 2.0F);
        this.SnowLayer11.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8, 0.0F);
        this.SnowLayer13 = new ModelRenderer(this, 0, 0);
        this.SnowLayer13.setRotationPoint(0.0F, -1.0F, -1.0F);
        this.SnowLayer13.addBox(0.0F, 0.0F, 0.0F, 10, 1, 12, 0.0F);
        this.SnowLayer8 = new ModelRenderer(this, 0, 0);
        this.SnowLayer8.setRotationPoint(0.0F, -12.0F, 11.0F);
        this.SnowLayer8.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10, 0.0F);
        this.setRotateAngle(SnowLayer8, 3.141592653589793F, 0.0F, 0.0F);
        this.SnowLayer5 = new ModelRenderer(this, 0, 0);
        this.SnowLayer5.setRotationPoint(0.0F, -1.0F, 12.0F);
        this.SnowLayer5.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10, 0.0F);
        this.setRotateAngle(SnowLayer5, 1.5707963267948966F, 0.0F, 0.0F);
        this.SnowLayer4 = new ModelRenderer(this, 0, 0);
        this.SnowLayer4.setRotationPoint(2.0F, 2.0F, 2.0F);
        this.SnowLayer4.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8, 0.0F);
        this.SnowLayer3 = new ModelRenderer(this, 0, 0);
        this.SnowLayer3.setRotationPoint(-1.0F, -1.0F, -1.0F);
        this.SnowLayer3.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12, 0.0F);
        this.SnowLayer1.addChild(this.SnowLayer2);
        this.SnowLayer1.addChild(this.SnowLayer9);
        this.SnowLayer13.addChild(this.SnowLayer14);
        this.Body.addChild(this.LEye);
        this.Body.addChild(this.SnowLayer1);
        this.SnowLayer1.addChild(this.SnowLayer12);
        this.Body.addChild(this.REye);
        this.SnowLayer6.addChild(this.SnowLayer7);
        this.SnowLayer9.addChild(this.SnowLayer10);
        this.SnowLayer5.addChild(this.SnowLayer6);
        this.SnowLayer10.addChild(this.SnowLayer11);
        this.SnowLayer12.addChild(this.SnowLayer13);
        this.SnowLayer1.addChild(this.SnowLayer8);
        this.SnowLayer1.addChild(this.SnowLayer5);
        this.SnowLayer3.addChild(this.SnowLayer4);
        this.SnowLayer2.addChild(this.SnowLayer3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
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

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.Body.rotateAngleX = -1 * limbSwing * 0.4662F * limbSwingAmount;
    }
}
