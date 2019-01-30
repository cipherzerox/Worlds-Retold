package xenoscape.worldsretold.heatwave.entity.hostile.anubite;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelAnubite - Enderman_of_D00M
 * Created using Tabula 7.0.0
 */
public class ModelAnubite extends ModelBiped 
{
    public ModelAnubite() 
    {
        super(0.0F, 0.0F, 64, 32);
        this.bipedHead = (new ModelRenderer(this));
        this.bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedHead.setTextureOffset(32, 6).addBox(-3.0F, -4.0F, -10.0F, 5, 4, 6, 0.0F);
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
}
