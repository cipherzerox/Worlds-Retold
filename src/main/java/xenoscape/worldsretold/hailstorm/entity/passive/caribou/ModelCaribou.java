package xenoscape.worldsretold.hailstorm.entity.passive.caribou;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelReinDeer - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelCaribou extends ModelBase {
    public ModelRenderer RightFrontLeg;
    public ModelRenderer LeftFrontLeg;
    public ModelRenderer RightBackLeg;
    public ModelRenderer LeftBackLeg;
    public ModelRenderer Body;
    public ModelRenderer Head;
    public ModelRenderer Nose;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    public ModelRenderer RightAntler;
    public ModelRenderer LeftAntler;
    protected float childYOffset = 8.0F;
    protected float childZOffset = 4.0F;

    public ModelCaribou() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftFrontLeg = new ModelRenderer(this, 52, 23);
        this.LeftFrontLeg.setRotationPoint(3.0F, 12.0F, -6.0F);
        this.LeftFrontLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        this.Head = new ModelRenderer(this, 0, 23);
        this.Head.setRotationPoint(0.0F, 9.0F, -9.0F);
        this.Head.addBox(-3.0F, -10.0F, -2.5F, 6, 12, 5, 0.0F);
        this.setRotateAngle(Head, 0.17453292519943295F, 0.0F, 0.0F);
        this.RightEar = new ModelRenderer(this, 0, 51);
        this.RightEar.setRotationPoint(-2.0F, -9.0F, 0.0F);
        this.RightEar.addBox(-2.0F, -4.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(RightEar, 0.0F, 0.8726646259971648F, -0.8726646259971648F);
        this.LeftAntler = new ModelRenderer(this, 46, 38);
        this.LeftAntler.mirror = true;
        this.LeftAntler.setRotationPoint(1.5F, -10.0F, 0.0F);
        this.LeftAntler.addBox(-1.0F, -10.0F, -4.0F, 1, 10, 8, 0.0F);
        this.setRotateAngle(LeftAntler, 0.0F, 0.2617993877991494F, 0.2617993877991494F);
        this.RightFrontLeg = new ModelRenderer(this, 52, 23);
        this.RightFrontLeg.setRotationPoint(-3.0F, 12.0F, -6.0F);
        this.RightFrontLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        this.LeftEar = new ModelRenderer(this, 0, 51);
        this.LeftEar.mirror = true;
        this.LeftEar.setRotationPoint(2.0F, -9.0F, 0.0F);
        this.LeftEar.addBox(0.0F, -4.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(LeftEar, 0.0F, -0.8726646259971648F, 0.8726646259971648F);
        this.LeftBackLeg = new ModelRenderer(this, 38, 17);
        this.LeftBackLeg.setRotationPoint(3.0F, 11.0F, 5.0F);
        this.LeftBackLeg.addBox(-1.5F, -2.0F, -2.0F, 3, 15, 4, 0.0F);
        this.RightAntler = new ModelRenderer(this, 46, 38);
        this.RightAntler.setRotationPoint(-1.5F, -10.0F, 0.0F);
        this.RightAntler.addBox(0.0F, -10.0F, -4.0F, 1, 10, 8, 0.0F);
        this.setRotateAngle(RightAntler, 0.0F, -0.2617993877991494F, -0.2617993877991494F);
        this.RightBackLeg = new ModelRenderer(this, 38, 17);
        this.RightBackLeg.setRotationPoint(-3.0F, 11.0F, 5.0F);
        this.RightBackLeg.addBox(-1.5F, -2.0F, -2.0F, 3, 15, 4, 0.0F);
        this.Nose = new ModelRenderer(this, 22, 31);
        this.Nose.setRotationPoint(0.0F, -3.0F, -2.5F);
        this.Nose.addBox(-2.0F, -4.0F, -5.0F, 4, 4, 5, 0.0F);
        this.setRotateAngle(Nose, -0.17453292519943295F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 40);
        this.Body.setRotationPoint(0.0F, 10.0F, 4.5F);
        this.Body.addBox(-4.0F, -4.0F, -13.0F, 8, 8, 16, 0.0F);
        this.Head.addChild(this.RightEar);
        this.Head.addChild(this.LeftAntler);
        this.Head.addChild(this.LeftEar);
        this.Head.addChild(this.RightAntler);
        this.Head.addChild(this.Nose);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, this.childYOffset * scale, this.childZOffset * scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.71428573F, 0.64935064F, 0.7936508F);
            GlStateManager.translate(0.0F, 21.0F * scale, 0.22F);
            this.Head.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.625F, 0.45454544F, 0.45454544F);
            GlStateManager.translate(0.0F, 33.0F * scale, 0.0F);
            this.Body.render(scale);
            this.RightBackLeg.render(scale);
            this.LeftBackLeg.render(scale);
            this.RightFrontLeg.render(scale);
            this.LeftFrontLeg.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.Head.render(scale);
            this.Body.render(scale);
            this.RightBackLeg.render(scale);
            this.LeftBackLeg.render(scale);
            this.RightFrontLeg.render(scale);
            this.LeftFrontLeg.render(scale);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
    	this.LeftAntler.isHidden = this.isChild;
    	this.RightAntler.isHidden = this.isChild;
        this.Head.rotateAngleX = headPitch * 0.017453292F + 0.17453292519943295F;
        this.Head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.RightBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LeftBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.RightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LeftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
