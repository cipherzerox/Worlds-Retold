package xenoscape.worldsretold.hellfire.entity.hostile.hellhound;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * ModelAnubite - Enderman_of_D00M
 * Created using Tabula 7.0.0
 */
public class ModelHellhound extends ModelBiped 
{
    public ModelHellhound() 
    {
        super(0.0F, 0.0F, 64, 32);
        this.bipedHead = (new ModelRenderer(this));
        this.bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedHead.setTextureOffset(32, 6).addBox(-2.5F, -4.0F, -10.0F, 5, 4, 6, 0.0F);
        this.bipedHead.setTextureOffset(24, 0).addBox(-4.0F, -13.0F, -0.5F, 3, 5, 1, 0.0F);
        this.bipedHead.setTextureOffset(32, 0).addBox(1.0F, -13.0F, -0.5F, 3, 5, 1, 0.0F);
        this.bipedHead.setTextureOffset(48, 0).addBox(0.5F, -11.0F, 2.0F, 7, 11, 1, 0.0F);
        this.bipedHead.setTextureOffset(48, 0).addBox(-7.5F, -11.0F, 2.0F, 7, 11, 1, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();

        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            this.bipedHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.bipedBody.render(scale);
            this.bipedRightArm.render(scale);
            this.bipedLeftArm.render(scale);
            this.bipedRightLeg.render(scale);
            this.bipedLeftLeg.render(scale);
        }
        else
        {
            if (entityIn.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }

            this.bipedHead.render(scale);
            this.bipedBody.render(scale);
            this.bipedRightArm.render(scale);
            this.bipedLeftArm.render(scale);
            this.bipedRightLeg.render(scale);
            this.bipedLeftLeg.render(scale);
        }

        GlStateManager.popMatrix();
    }
    
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
    	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    	
    	EntityHellhound anubite = (EntityHellhound)entityIn;
    	if (!anubite.onGround && anubite.getJumpCooldown() <= 0)
    	{
    		this.bipedHead.rotateAngleX = 0.75F;
    		this.bipedHead.rotateAngleY = 0F;
    		this.bipedRightLeg.rotateAngleX = -1.5F;
    		this.bipedLeftLeg.rotateAngleX = 1.0F;
            if (this.swingProgress <= 0.0F)
            {
        		this.bipedRightArm.rotateAngleX = 1.75F;
        		this.bipedLeftArm.rotateAngleX = 1.75F;
            }
    	}

        if (this.swingProgress > 0.0F)
        {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            float f1 = this.swingProgress;
            this.bipedBody.rotateAngleY = 0F;
            float sw = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;

            this.bipedRightArm.rotationPointZ = MathHelper.sin(sw) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(sw) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(-sw) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(-sw) * 5.0F;
            this.bipedRightArm.rotateAngleY += sw;
            this.bipedLeftArm.rotateAngleY += sw;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float)Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
            this.bipedRightArm.rotateAngleY += sw * 2.0F;
            this.bipedRightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
            this.bipedLeftArm.rotateAngleX = (float)((double)this.bipedLeftArm.rotateAngleX - ((double)f2 * 2.4D + (double)f3));
            this.bipedLeftArm.rotateAngleY += sw * -4.0F;
            this.bipedLeftArm.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * 0.8F;
        }
    }
}
