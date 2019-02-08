package xenoscape.worldsretold.heatwave.entity.passive;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelRoadrunner - Enderman_of_D00M
 * Created using Tabula 7.0.0
 */
public class ModelRoadrunner extends ModelBase {
    public ModelRenderer rightWing;
    public ModelRenderer leftLeg;
    public ModelRenderer leftWing;
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rightLeg;
    public ModelRenderer tail;
    public ModelRenderer beak;

    public ModelRoadrunner() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer(this, 0, 8);
        this.body.setRotationPoint(0.0F, 17.0F, 0.0F);
        this.body.addBox(-3.0F, -5.0F, -3.0F, 6, 10, 4, 0.0F);
        this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        this.beak = new ModelRenderer(this, 31, 14);
        this.beak.setRotationPoint(0.0F, -1.0F, -4.0F);
        this.beak.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F);
        this.leftWing = new ModelRenderer(this, 20, 7);
        this.leftWing.setRotationPoint(4.0F, 16.0F, -1.0F);
        this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 7, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 17.0F, -4.0F);
        this.head.addBox(-2.0F, -4.0F, -4.0F, 4, 4, 4, 0.0F);
        this.rightLeg = new ModelRenderer(this, 16, 0);
        this.rightLeg.mirror = true;
        this.rightLeg.setRotationPoint(-2.0F, 20.0F, 1.0F);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 4, 3, 0.0F);
        this.leftLeg = new ModelRenderer(this, 16, 0);
        this.leftLeg.setRotationPoint(1.0F, 20.0F, 1.0F);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 4, 3, 0.0F);
        this.tail = new ModelRenderer(this, 30, 0);
        this.tail.setRotationPoint(0.0F, 17.5F, 4.0F);
        this.tail.addBox(-1.5F, 0.0F, -1.0F, 3, 12, 2, 0.0F);
        this.setRotateAngle(tail, 1.5707963267948966F, 0.0F, 0.0F);
        this.rightWing = new ModelRenderer(this, 20, 7);
        this.rightWing.mirror = true;
        this.rightWing.setRotationPoint(-4.0F, 16.0F, -1.0F);
        this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 7, 0.0F);
        this.head.addChild(this.beak);
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
            GlStateManager.translate(0.0F, 5.0F * scale, 2.0F * scale);
            this.head.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.body.render(scale);
            this.rightLeg.render(scale);
            this.leftLeg.render(scale);
            this.rightWing.render(scale);
            this.leftWing.render(scale);
            this.tail.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.head.render(scale);
            this.body.render(scale);
            this.rightLeg.render(scale);
            this.leftLeg.render(scale);
            this.rightWing.render(scale);
            this.leftWing.render(scale);
            this.tail.render(scale);
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

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightWing.rotateAngleZ = ageInTicks;
        this.leftWing.rotateAngleZ = -ageInTicks;
        this.tail.rotateAngleX = 1.5707963267948966F + (limbSwingAmount * 0.5F);
    }
}
