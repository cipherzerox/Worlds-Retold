package xenoscape.worldsretold.heatwave.entity.neutral.cobra;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelCobra - Enderman_of_D00M
 * Created using Tabula 7.0.0
 */
public class ModelCobra extends ModelBase {
    public ModelRenderer midsec;
    public ModelRenderer frontsec1;
    public ModelRenderer tailsec1;
    public ModelRenderer frontsec2;
    public ModelRenderer headsec;
    public ModelRenderer hood;
    public ModelRenderer head;
    public ModelRenderer tailsec2;
    public ModelRenderer tailsec3;
    public ModelRenderer tailend;

    public ModelCobra() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.hood = new ModelRenderer(this, 22, 8);
        this.hood.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hood.addBox(-4.5F, -1.5F, -9.0F, 9, 1, 9, 0.0F);
        this.tailend = new ModelRenderer(this, 0, 26);
        this.tailend.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tailend.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 8, 0.0F);
        this.frontsec1 = new ModelRenderer(this, 18, 0);
        this.frontsec1.setRotationPoint(0.0F, -1.0F, -3.0F);
        this.frontsec1.addBox(-1.5F, -1.0F, -6.0F, 3, 2, 6, 0.0F);
        this.headsec = new ModelRenderer(this, 0, 8);
        this.headsec.setRotationPoint(0.0F, 0.0F, -6.0F);
        this.headsec.addBox(-1.5F, -1.0F, -8.0F, 3, 2, 8, 0.0F);
        this.tailsec1 = new ModelRenderer(this, 0, 18);
        this.tailsec1.setRotationPoint(0.0F, -1.0F, 3.0F);
        this.tailsec1.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 6, 0.0F);
        this.head = new ModelRenderer(this, 14, 8);
        this.head.setRotationPoint(0.0F, 0.0F, -8.0F);
        this.head.addBox(-1.5F, -1.0F, -4.0F, 3, 2, 4, 0.0F);
        this.frontsec2 = new ModelRenderer(this, 36, 0);
        this.frontsec2.setRotationPoint(0.0F, 0.0F, -6.0F);
        this.frontsec2.addBox(-1.5F, -1.0F, -6.0F, 3, 2, 6, 0.0F);
        this.tailsec2 = new ModelRenderer(this, 18, 18);
        this.tailsec2.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.tailsec2.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 7, 0.0F);
        this.tailsec3 = new ModelRenderer(this, 38, 18);
        this.tailsec3.setRotationPoint(0.0F, 0.0F, 7.0F);
        this.tailsec3.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 8, 0.0F);
        this.midsec = new ModelRenderer(this, 0, 0);
        this.midsec.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.midsec.addBox(-1.5F, -2.0F, -3.0F, 3, 2, 6, 0.0F);
        this.headsec.addChild(this.hood);
        this.tailsec3.addChild(this.tailend);
        this.midsec.addChild(this.frontsec1);
        this.frontsec2.addChild(this.headsec);
        this.midsec.addChild(this.tailsec1);
        this.headsec.addChild(this.head);
        this.frontsec1.addChild(this.frontsec2);
        this.tailsec1.addChild(this.tailsec2);
        this.tailsec2.addChild(this.tailsec3);
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.midsec.render(scale);
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
    	EntityCobra cobra = (EntityCobra)entityIn;

        float f = ageInTicks - (float)entityIn.ticksExisted;
        float rot = cobra.getRearingRot(f);
        float fg1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        
        this.hood.showModel = cobra.isAggressive();
        this.head.rotateAngleX = headPitch * 0.017453292F + (rot * 1.5F) - (fg1);

        this.frontsec1.rotateAngleX = -(rot * 0.65F) + (fg1 / 2);
        this.frontsec2.rotateAngleX = -(rot * 0.4F) + (fg1 / 2);
        this.headsec.rotateAngleX = -(rot * 0.4F) + (fg1 / 2);
        
        this.tailsec1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F - 0.6F) * 0.75F * limbSwingAmount + (rot) - (0.25F - (cobra.limbSwingAmount * 0.25F));
        this.tailsec2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F - 1.2F) * 0.75F * limbSwingAmount + (rot) + (0.25F - (cobra.limbSwingAmount * 0.25F));
        this.tailsec3.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F - 1.8F) * 0.75F * limbSwingAmount + (rot) - (0.25F - (cobra.limbSwingAmount * 0.25F));
        this.tailend.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F - 2.4F) * 0.75F * limbSwingAmount + (rot) + (0.25F - (cobra.limbSwingAmount * 0.25F));
        
        if (cobra.isAggressive())
        {
            this.tailsec1.rotateAngleY -= netHeadYaw * 0.004363323F;
            this.tailsec2.rotateAngleY -= netHeadYaw * 0.004363323F;
            this.tailsec3.rotateAngleY -= netHeadYaw * 0.004363323F;
            this.tailend.rotateAngleY -= netHeadYaw * 0.004363323F;
            this.head.rotateAngleY = 0F;
            this.midsec.rotationPointX = 0F;
            this.midsec.rotateAngleY = netHeadYaw * 0.017453292F;
            this.frontsec1.rotateAngleY = 0F;
            this.frontsec2.rotateAngleY = 0F;
            this.headsec.rotateAngleY = 0F;
            this.frontsec1.rotateAngleZ = MathHelper.cos(ageInTicks * 0.2F) * 0.1F;
            this.frontsec2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.2F - 1F) * 0.1F;
            this.headsec.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.2F - 2F) * 0.2F;
        }
        else
        {
            this.midsec.rotateAngleY = 0F;
            this.head.rotateAngleY = netHeadYaw * 0.017453292F;
            this.midsec.rotationPointX = MathHelper.cos(limbSwing * 0.6662F) * 12F * limbSwingAmount;
            this.frontsec1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 0.6F) * 0.75F * limbSwingAmount + (0.25F - (cobra.limbSwingAmount * 0.25F));
            this.frontsec2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 1.2F) * 0.75F * limbSwingAmount - (0.25F - (cobra.limbSwingAmount * 0.25F));
            this.headsec.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * -1.5F * limbSwingAmount;
        }
    }
}
