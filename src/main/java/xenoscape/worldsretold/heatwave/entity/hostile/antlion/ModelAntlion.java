package xenoscape.worldsretold.heatwave.entity.hostile.antlion;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelAntlion - Xenoform55
 * Created using Tabula 7.0.0
 */
public class ModelAntlion extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer Neck;
    public ModelRenderer LLeg;
    public ModelRenderer RLeg;
    public ModelRenderer LFLeg;
    public ModelRenderer RFLeg;
    public ModelRenderer LBLeg;
    public ModelRenderer RBLeg;
    public ModelRenderer Head;
    public ModelRenderer LMandible;
    public ModelRenderer RMandible;

    public ModelAntlion() {
        this.textureWidth = 86;
        this.textureHeight = 64;
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 16.0F, 8.0F);
        this.Body.addBox(-6.0F, -4.5F, -10.0F, 12, 6, 24, 0.0F);
        this.setRotateAngle(Body, -0.17453292519943295F, 0.0F, 0.0F);
        this.LMandible = new ModelRenderer(this, 0, 46);
        this.LMandible.mirror = true;
        this.LMandible.setRotationPoint(2.0F, 0.5F, -8.0F);
        this.LMandible.addBox(-1.5F, -0.5F, -12.0F, 3, 1, 14, 0.0F);
        this.Head = new ModelRenderer(this, 0, 32);
        this.Head.setRotationPoint(0.0F, 0.7F, -9.4F);
        this.Head.addBox(-4.0F, -2.0F, -8.0F, 8, 4, 8, 0.0F);
        this.setRotateAngle(Head, -0.8196066167365371F, 0.0F, 0.0F);
        this.RLeg = new ModelRenderer(this, 48, 45);
        this.RLeg.setRotationPoint(-5.5F, 16.0F, 0.0F);
        this.RLeg.addBox(-1.0F, 0.0F, -15.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(RLeg, 0.40980330836826856F, 1.5025539530419183F, 0.0F);
        this.Neck = new ModelRenderer(this, 52, 6);
        this.Neck.setRotationPoint(0.0F, 12.4F, 0.0F);
        this.Neck.addBox(-2.5F, -2.0F, -11.0F, 5, 4, 11, 0.0F);
        this.setRotateAngle(Neck, 0.8196066167365371F, 0.0F, 0.0F);
        this.LBLeg = new ModelRenderer(this, 54, 31);
        this.LBLeg.mirror = true;
        this.LBLeg.setRotationPoint(4.5F, 16.0F, 5.0F);
        this.LBLeg.addBox(-1.0F, 0.0F, -9.0F, 2, 2, 10, 0.0F);
        this.setRotateAngle(LBLeg, 0.8651597102135892F, -2.408554367752175F, 0.0F);
        this.RFLeg = new ModelRenderer(this, 27, 38);
        this.RFLeg.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.RFLeg.addBox(-1.0F, 0.0F, -12.0F, 2, 2, 13, 0.0F);
        this.setRotateAngle(RFLeg, 0.5462880558742251F, 1.0927506446736497F, 0.0F);
        this.RMandible = new ModelRenderer(this, 0, 46);
        this.RMandible.setRotationPoint(-2.0F, 0.5F, -8.0F);
        this.RMandible.addBox(-1.5F, -0.5F, -12.0F, 3, 1, 14, 0.0F);
        this.LLeg = new ModelRenderer(this, 48, 45);
        this.LLeg.mirror = true;
        this.LLeg.setRotationPoint(5.5F, 16.0F, 0.0F);
        this.LLeg.addBox(-1.0F, 0.0F, -15.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(LLeg, 0.40980330836826856F, -1.5025539530419183F, 0.0F);
        this.RBLeg = new ModelRenderer(this, 54, 31);
        this.RBLeg.setRotationPoint(-4.5F, 16.0F, 5.0F);
        this.RBLeg.addBox(-1.0F, 0.0F, -9.0F, 2, 2, 10, 0.0F);
        this.setRotateAngle(RBLeg, 0.8651597102135892F, 2.408554367752175F, 0.0F);
        this.LFLeg = new ModelRenderer(this, 27, 38);
        this.LFLeg.mirror = true;
        this.LFLeg.setRotationPoint(2.5F, 16.0F, -4.0F);
        this.LFLeg.addBox(-1.0F, 0.0F, -12.0F, 2, 2, 13, 0.0F);
        this.setRotateAngle(LFLeg, 0.5462880558742251F, -1.0927506446736497F, 0.0F);
        this.Head.addChild(this.LMandible);
        this.Neck.addChild(this.Head);
        this.Head.addChild(this.RMandible);
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    	EntityAntlion antlion = (EntityAntlion)entityIn;
        GlStateManager.pushMatrix();
        if (antlion.isDugIn())
        {
            GlStateManager.translate(0.0F, 1.5F, 1.0F);
            GlStateManager.rotate(-90F, 1F, 0F, 0F);
        }
        this.Body.render(scale);
        this.RLeg.render(scale);
        this.Neck.render(scale);
        this.LBLeg.render(scale);
        this.RFLeg.render(scale);
        this.LLeg.render(scale);
        this.RBLeg.render(scale);
        this.LFLeg.render(scale);
        GlStateManager.popMatrix();
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
    	EntityAntlion antlion = (EntityAntlion)entityIn;
        float f = ageInTicks - (float)entityIn.ticksExisted;
        float baserot = antlion.getStingerBaseRot(f);
        float fg = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float fg1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);

        if (antlion.isDugIn())
        {
            this.RMandible.rotateAngleY = (MathHelper.sin(ageInTicks * 0.05F) * 0.05F);
            this.LMandible.rotateAngleY = -(MathHelper.sin(ageInTicks * 0.05F) * 0.05F);

            this.Head.rotateAngleY = netHeadYaw * 0.008726646F;
            this.Head.rotateAngleX = 1F - baserot;
            this.Neck.rotateAngleY = netHeadYaw * 0.008726646F;
            this.Neck.rotateAngleX = 1F - baserot;
        	
            this.RFLeg.rotateAngleY = 3F;
            this.RLeg.rotateAngleY = 3F;
            this.RBLeg.rotateAngleY = 3F;
            this.LFLeg.rotateAngleY = -3F;
            this.LLeg.rotateAngleY = -3F;
            this.LBLeg.rotateAngleY = -3F;
        }
        else
        {
            this.RMandible.rotateAngleY = -(fg * 1.25F) + baserot + (MathHelper.sin(ageInTicks * 0.05F) * 0.05F);
            this.LMandible.rotateAngleY = (fg * 1.25F) - baserot - (MathHelper.sin(ageInTicks * 0.05F) * 0.05F);

            this.Head.rotateAngleY = netHeadYaw * 0.008726646F;
            this.Head.rotateAngleX = -0.8196066167365371F + headPitch * 0.008726646F;
            this.Neck.rotateAngleY = netHeadYaw * 0.008726646F;
            this.Neck.rotateAngleX = 0.8196066167365371F + headPitch * 0.008726646F;
        	
            this.RFLeg.rotateAngleY = 1.0927506446736497F - MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
            this.RLeg.rotateAngleY = 1.5025539530419183F + MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
            this.RBLeg.rotateAngleY = 2.408554367752175F - MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
            this.LFLeg.rotateAngleY = -1.0927506446736497F - MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
            this.LLeg.rotateAngleY = -1.5025539530419183F + MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
            this.LBLeg.rotateAngleY = -2.408554367752175F - MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
        }
        
    }
}
