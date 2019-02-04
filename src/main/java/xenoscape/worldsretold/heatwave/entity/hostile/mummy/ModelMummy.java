package xenoscape.worldsretold.heatwave.entity.hostile.mummy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * Mummy - MCvinnyq
 * Created using Tabula 7.0.0
 */
public class ModelMummy extends ModelBase {
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Hat;
    public ModelRenderer Head;
    public ModelRenderer Cloth4;
    public ModelRenderer Body;
    public ModelRenderer Cloth2;
    public ModelRenderer Cloth3;
    public ModelRenderer Cloth1;

    public ModelMummy() 
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Cloth2 = new ModelRenderer(this, 50, 16);
        this.Cloth2.setRotationPoint(-0.5F, 4.0F, 1.5F);
        this.Cloth2.addBox(-1.0F, -6.0F, 0.0F, 2, 12, 3, 0.0F);
        this.Cloth3 = new ModelRenderer(this, 50, 31);
        this.Cloth3.setRotationPoint(0.5F, 4.0F, 1.5F);
        this.Cloth3.addBox(-1.0F, -6.0F, 0.0F, 2, 12, 3, 0.0F);
        this.Cloth4 = new ModelRenderer(this, 36, 47);
        this.Cloth4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloth4.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.Body = new ModelRenderer(this, 14, 32);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.LeftArm = new ModelRenderer(this, 38, 16);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(4.5F, 2.5F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -1.5F, 3, 12, 3, 0.0F);
        this.setRotateAngle(LeftArm, -1.3089969389957472F, -0.08726646259971647F, 0.0F);
        this.Hat = new ModelRenderer(this, 32, 0);
        this.Hat.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F);
        this.Cloth1 = new ModelRenderer(this, 24, 0);
        this.Cloth1.setRotationPoint(0.0F, -6.0F, 4.25F);
        this.Cloth1.addBox(-4.0F, -2.0F, 0.0F, 8, 8, 0, 0.0F);
        this.setRotateAngle(Cloth1, 0.17453292519943295F, 0.0F, 0.0F);
        this.RightArm = new ModelRenderer(this, 38, 16);
        this.RightArm.setRotationPoint(-4.5F, 2.5F, 0.0F);
        this.RightArm.addBox(-2.0F, -2.0F, -1.5F, 3, 12, 3, 0.0F);
        this.setRotateAngle(RightArm, -1.3089969389957472F, 0.08726646259971647F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 16);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 12, 4, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 16);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 12, 4, 0.0F);
        this.RightArm.addChild(this.Cloth2);
        this.LeftArm.addChild(this.Cloth3);
        this.Hat.addChild(this.Cloth1);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();

        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            this.Head.render(scale);
            this.Hat.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.Cloth4.render(scale);
            this.Body.render(scale);
            this.LeftArm.render(scale);
            this.RightArm.render(scale);
            this.RightLeg.render(scale);
            this.LeftLeg.render(scale);
        }
        else
        {
            if (entityIn.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }

            this.Cloth4.render(scale);
            this.Body.render(scale);
            this.LeftArm.render(scale);
            this.Hat.render(scale);
            this.RightArm.render(scale);
            this.Head.render(scale);
            this.RightLeg.render(scale);
            this.LeftLeg.render(scale);
        }

        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getTicksElytraFlying() > 4;
        this.Head.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag)
        {
            this.Head.rotateAngleX = -((float)Math.PI / 4F);
        }
        else
        {
            this.Head.rotateAngleX = headPitch * 0.017453292F;
        }

        float f = 1.0F;

        if (flag)
        {
            f = (float)(entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F)
        {
            f = 1.0F;
        }

        this.RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.RightArm.rotateAngleZ = 0.0F;
        this.LeftArm.rotateAngleZ = 0.0F;
        this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.RightLeg.rotateAngleY = 0.0F;
        this.LeftLeg.rotateAngleY = 0.0F;
        this.RightLeg.rotateAngleZ = 0.0F;
        this.LeftLeg.rotateAngleZ = 0.0F;

        if (this.isRiding)
        {
            this.RightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.LeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.RightLeg.rotateAngleX = -1.4137167F;
            this.RightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.RightLeg.rotateAngleZ = 0.07853982F;
            this.LeftLeg.rotateAngleX = -1.4137167F;
            this.LeftLeg.rotateAngleY = -((float)Math.PI / 10F);
            this.LeftLeg.rotateAngleZ = -0.07853982F;
        }

        this.RightArm.rotateAngleY = 0.0F;
        this.RightArm.rotateAngleZ = 0.0F;

        this.RightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.LeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.RightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.LeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        copyModelAngles(this.Head, this.Hat);
        boolean flags = entityIn instanceof EntityMummy && ((EntityMummy)entityIn).isArmsRaised();
        float fn = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        this.RightArm.rotateAngleZ = 0.0F;
        this.LeftArm.rotateAngleZ = 0.0F;
        this.RightArm.rotateAngleY = -(0.1F - fn * 0.6F);
        this.LeftArm.rotateAngleY = 0.1F - fn * 0.6F;
        float f2 = -(float)Math.PI / (flags ? 1.5F : 2.25F);
        this.RightArm.rotateAngleX = f2;
        this.LeftArm.rotateAngleX = f2;
        this.RightArm.rotateAngleX += fn * 1.2F - f1 * 0.4F;
        this.LeftArm.rotateAngleX += fn * 1.2F - f1 * 0.4F;
        this.RightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.LeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.RightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.LeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }
}