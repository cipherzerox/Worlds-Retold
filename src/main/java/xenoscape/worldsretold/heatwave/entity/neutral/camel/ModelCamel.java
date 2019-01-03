package xenoscape.worldsretold.heatwave.entity.neutral.camel;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelCamel - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelCamel extends ModelBase {
    public ModelRenderer RightFrontLeg;
    public ModelRenderer LeftFrontLeg;
    public ModelRenderer RightBackLeg;
    public ModelRenderer LeftBackLeg;
    public ModelRenderer Body;
    public ModelRenderer Neck1;
    public ModelRenderer Hump;
    public ModelRenderer Neck2;
    public ModelRenderer Head;
    public ModelRenderer Snout;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    protected float childYOffset = 8.0F;
    protected float childZOffset = 4.0F;

    public ModelCamel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftFrontLeg = new ModelRenderer(this, 48, 23);
        this.LeftFrontLeg.mirror = true;
        this.LeftFrontLeg.setRotationPoint(4.5F, 11.0F, 0.0F);
        this.LeftFrontLeg.addBox(-2.0F, -1.0F, -2.0F, 4, 14, 4, 0.0F);
        this.Body = new ModelRenderer(this, 0, 34);
        this.Body.setRotationPoint(0.0F, 5.0F, 15.0F);
        this.Body.addBox(-6.0F, -5.0F, -18.0F, 12, 10, 20, 0.0F);
        this.RightEar = new ModelRenderer(this, 0, 40);
        this.RightEar.setRotationPoint(-3.0F, -3.0F, 0.0F);
        this.RightEar.addBox(-1.0F, -3.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(RightEar, 0.4363323129985824F, 0.4363323129985824F, 0.0F);
        this.Snout = new ModelRenderer(this, 0, 45);
        this.Snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Snout.addBox(-2.5F, -3.0F, -9.0F, 5, 4, 5, 0.0F);
        this.Head = new ModelRenderer(this, 38, 0);
        this.Head.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.Head.addBox(-3.0F, -4.0F, -4.0F, 6, 5, 7, 0.0F);
        this.setRotateAngle(Head, -0.35F, 0.0F, 0.0F);
        this.RightBackLeg = new ModelRenderer(this, 48, 23);
        this.RightBackLeg.mirror = true;
        this.RightBackLeg.setRotationPoint(-4.5F, 11.0F, 16.0F);
        this.RightBackLeg.addBox(-2.0F, -1.0F, -2.0F, 4, 14, 4, 0.0F);
        this.RightFrontLeg = new ModelRenderer(this, 48, 23);
        this.RightFrontLeg.setRotationPoint(-4.5F, 11.0F, 0.0F);
        this.RightFrontLeg.addBox(-2.0F, -1.0F, -2.0F, 4, 14, 4, 0.0F);
        this.Hump = new ModelRenderer(this, 0, 18);
        this.Hump.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hump.addBox(-4.0F, -9.0F, -11.0F, 8, 4, 12, 0.0F);
        this.Neck2 = new ModelRenderer(this, 44, 41);
        this.Neck2.setRotationPoint(0.0F, 0.0F, -8.5F);
        this.Neck2.addBox(-2.5F, -10.5F, -2.5F, 5, 8, 5, 0.0F);
        this.LeftEar = new ModelRenderer(this, 0, 40);
        this.LeftEar.setRotationPoint(3.0F, -3.0F, 0.0F);
        this.LeftEar.addBox(-1.0F, -3.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(LeftEar, 0.4363323129985824F, -0.4363323129985824F, 0.0F);
        this.LeftBackLeg = new ModelRenderer(this, 48, 23);
        this.LeftBackLeg.setRotationPoint(4.5F, 11.0F, 16.0F);
        this.LeftBackLeg.addBox(-2.0F, -1.0F, -2.0F, 4, 14, 4, 0.0F);
        this.Neck1 = new ModelRenderer(this, 0, 0);
        this.Neck1.setRotationPoint(0.0F, 3.0F, -2.0F);
        this.Neck1.addBox(-2.5F, -2.5F, -11.0F, 5, 5, 12, 0.0F);
        this.setRotateAngle(Neck1, 0.35F, 0.0F, 0.0F);
        this.Head.addChild(this.RightEar);
        this.Head.addChild(this.Snout);
        this.Neck2.addChild(this.Head);
        this.Body.addChild(this.Hump);
        this.Neck1.addChild(this.Neck2);
        this.Head.addChild(this.LeftEar);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, this.childYOffset * scale, this.childZOffset * scale);
            this.Neck1.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.Body.render(scale);
            this.LeftFrontLeg.render(scale);
            this.RightFrontLeg.render(scale);
            this.LeftBackLeg.render(scale);
            this.RightBackLeg.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.Neck1.render(scale);
            this.Body.render(scale);
            this.LeftFrontLeg.render(scale);
            this.RightFrontLeg.render(scale);
            this.LeftBackLeg.render(scale);
            this.RightBackLeg.render(scale);
        }
    }
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.Neck1.rotateAngleX = 0.35F + headPitch * 0.017453292F;
        this.Neck1.rotateAngleY = netHeadYaw * 0.017453292F;
        this.LeftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.RightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LeftBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.RightBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}
