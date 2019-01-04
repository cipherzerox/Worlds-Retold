package xenoscape.worldsretold.heatwave.entity.neutral.scorpion;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.util.math.MathHelper;

/**
 * ModelScorpion - Enderman_of_D00M
 * Created using Tabula 7.0.0
 */
public class ModelScorpion extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer rightleg1;
    public ModelRenderer rightleg2;
    public ModelRenderer rightleg3;
    public ModelRenderer leftleg1;
    public ModelRenderer leftleg2;
    public ModelRenderer leftleg3;
    public ModelRenderer leftleg4;
    public ModelRenderer rightleg4;
    public ModelRenderer tailbase;
    public ModelRenderer leftarm1;
    public ModelRenderer rightarm1;
    public ModelRenderer leftarm2;
    public ModelRenderer leftclaw1;
    public ModelRenderer leftclaw2;
    public ModelRenderer rightarm2;
    public ModelRenderer rightclaw1;
    public ModelRenderer rightclaw2;
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tailbulb;
    public ModelRenderer tailpoint;

    public ModelScorpion() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.rightarm1 = new ModelRenderer(this, 0, 49);
        this.rightarm1.setRotationPoint(-2.0F, 0.0F, -4.0F);
        this.rightarm1.addBox(-1.5F, -1.5F, -12.0F, 2, 3, 12, 0.0F);
        this.setRotateAngle(rightarm1, 0.0F, 1.1344640137963142F, 0.0F);
        this.rightclaw2 = new ModelRenderer(this, 0, 30);
        this.rightclaw2.setRotationPoint(0.0F, -1.0F, -12.0F);
        this.rightclaw2.addBox(-0.5F, -0.5F, -5.0F, 1, 1, 5, 0.0F);
        this.leftleg1 = new ModelRenderer(this, 0, 31);
        this.leftleg1.mirror = true;
        this.leftleg1.setRotationPoint(4.0F, 0.0F, -4.0F);
        this.leftleg1.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(leftleg1, 0.4363323129985824F, -0.9599310885968813F, 0.0F);
        this.rightclaw1 = new ModelRenderer(this, 10, 15);
        this.rightclaw1.setRotationPoint(0.0F, 0.5F, -12.0F);
        this.rightclaw1.addBox(-0.5F, -0.2F, -5.0F, 1, 1, 5, 0.0F);
        this.tail2 = new ModelRenderer(this, 44, 0);
        this.tail2.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.tail2.addBox(-2.0F, -10.0F, -2.0F, 4, 10, 4, 0.0F);
        this.setRotateAngle(tail2, 0.6981317007977318F, 0.0F, 0.0F);
        this.leftarm1 = new ModelRenderer(this, 0, 49);
        this.leftarm1.mirror = true;
        this.leftarm1.setRotationPoint(2.0F, 0.0F, -4.0F);
        this.leftarm1.addBox(-1.5F, -1.5F, -12.0F, 2, 3, 12, 0.0F);
        this.setRotateAngle(leftarm1, 0.0F, -1.1344640137963142F, 0.0F);
        this.rightleg3 = new ModelRenderer(this, 0, 31);
        this.rightleg3.setRotationPoint(-4.0F, 0.0F, 1.0F);
        this.rightleg3.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(rightleg3, 0.4363323129985824F, 1.8325957145940461F, 0.0F);
        this.rightleg4 = new ModelRenderer(this, 0, 31);
        this.rightleg4.setRotationPoint(-4.0F, 0.0F, 4.0F);
        this.rightleg4.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(rightleg4, 0.4363323129985824F, 2.356194490192345F, 0.0F);
        this.tailbulb = new ModelRenderer(this, 20, 30);
        this.tailbulb.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.tailbulb.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 6, 0.0F);
        this.leftclaw2 = new ModelRenderer(this, 0, 30);
        this.leftclaw2.mirror = true;
        this.leftclaw2.setRotationPoint(0.0F, -1.0F, -12.0F);
        this.leftclaw2.addBox(-0.5F, -0.5F, -5.0F, 1, 1, 5, 0.0F);
        this.leftleg3 = new ModelRenderer(this, 0, 31);
        this.leftleg3.mirror = true;
        this.leftleg3.setRotationPoint(4.0F, 0.0F, 1.0F);
        this.leftleg3.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(leftleg3, 0.4363323129985824F, -1.8325957145940461F, 0.0F);
        this.rightleg1 = new ModelRenderer(this, 0, 31);
        this.rightleg1.setRotationPoint(-4.0F, 0.0F, -4.0F);
        this.rightleg1.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(rightleg1, 0.4363323129985824F, 0.9599310885968813F, 0.0F);
        this.tailpoint = new ModelRenderer(this, 0, 12);
        this.tailpoint.setRotationPoint(0.0F, 0.0F, -5.0F);
        this.tailpoint.addBox(-0.5F, 2.5F, -6.0F, 1, 1, 6, 0.0F);
        this.leftleg2 = new ModelRenderer(this, 0, 31);
        this.leftleg2.mirror = true;
        this.leftleg2.setRotationPoint(4.0F, 0.0F, -1.0F);
        this.leftleg2.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(leftleg2, 0.4363323129985824F, -1.3089969389957472F, 0.0F);
        this.tailbase = new ModelRenderer(this, 44, 0);
        this.tailbase.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tailbase.addBox(-2.0F, -10.0F, -2.0F, 4, 10, 4, 0.0F);
        this.setRotateAngle(tailbase, -1.0471975511965976F, 0.0F, 0.0F);
        this.leftleg4 = new ModelRenderer(this, 0, 31);
        this.leftleg4.mirror = true;
        this.leftleg4.setRotationPoint(4.0F, 0.0F, 4.0F);
        this.leftleg4.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(leftleg4, 0.4363323129985824F, -2.356194490192345F, 0.0F);
        this.leftclaw1 = new ModelRenderer(this, 10, 15);
        this.leftclaw1.mirror = true;
        this.leftclaw1.setRotationPoint(0.0F, 0.5F, -12.0F);
        this.leftclaw1.addBox(-0.5F, -0.2F, -5.0F, 1, 1, 5, 0.0F);
        this.leftarm2 = new ModelRenderer(this, 0, 49);
        this.leftarm2.mirror = true;
        this.leftarm2.setRotationPoint(0.0F, 0.0F, -12.0F);
        this.leftarm2.addBox(-1.0F, -1.5F, -12.0F, 2, 3, 12, 0.0F);
        this.setRotateAngle(leftarm2, 0.0F, 1.5707963267948966F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, -10.0F);
        this.head.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 6, 0.0F);
        this.tail3 = new ModelRenderer(this, 44, 0);
        this.tail3.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.tail3.addBox(-2.0F, -10.0F, -2.0F, 4, 10, 4, 0.0F);
        this.setRotateAngle(tail3, 1.0471975511965976F, 0.0F, 0.0F);
        this.rightarm2 = new ModelRenderer(this, 0, 49);
        this.rightarm2.setRotationPoint(0.0F, 0.0F, -12.0F);
        this.rightarm2.addBox(-1.0F, -1.5F, -12.0F, 2, 3, 12, 0.0F);
        this.setRotateAngle(rightarm2, 0.0F, -1.5707963267948966F, 0.0F);
        this.body = new ModelRenderer(this, 4, 3);
        this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.body.addBox(-5.0F, -3.5F, -10.0F, 10, 7, 20, 0.0F);
        this.rightleg2 = new ModelRenderer(this, 0, 31);
        this.rightleg2.setRotationPoint(-4.0F, 0.0F, -1.0F);
        this.rightleg2.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(rightleg2, 0.4363323129985824F, 1.3089969389957472F, 0.0F);
        this.tail1 = new ModelRenderer(this, 44, 0);
        this.tail1.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.tail1.addBox(-2.0F, -10.0F, -2.0F, 4, 10, 4, 0.0F);
        this.setRotateAngle(tail1, 0.8726646259971648F, 0.0F, 0.0F);
        this.head.addChild(this.rightarm1);
        this.rightarm2.addChild(this.rightclaw2);
        this.body.addChild(this.leftleg1);
        this.rightarm2.addChild(this.rightclaw1);
        this.tail1.addChild(this.tail2);
        this.head.addChild(this.leftarm1);
        this.body.addChild(this.rightleg3);
        this.body.addChild(this.rightleg4);
        this.tail3.addChild(this.tailbulb);
        this.leftarm2.addChild(this.leftclaw2);
        this.body.addChild(this.leftleg3);
        this.body.addChild(this.rightleg1);
        this.tailbulb.addChild(this.tailpoint);
        this.body.addChild(this.leftleg2);
        this.body.addChild(this.tailbase);
        this.body.addChild(this.leftleg4);
        this.leftarm2.addChild(this.leftclaw1);
        this.leftarm1.addChild(this.leftarm2);
        this.body.addChild(this.head);
        this.tail2.addChild(this.tail3);
        this.rightarm1.addChild(this.rightarm2);
        this.body.addChild(this.rightleg2);
        this.tailbase.addChild(this.tail1);
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.body.render(scale);
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
    	EntityScorpion scorpion = (EntityScorpion)entityIn;
        float f = ageInTicks - (float)entityIn.ticksExisted;
        float baserot = scorpion.getStingerBaseRot(f);
        float base1 = scorpion.getStinger1Rot(f);
        float base2 = scorpion.getStinger2Rot(f);
        float base3 = scorpion.getStinger3Rot(f);
        
        float fg = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float fg1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        
        this.rightarm1.rotateAngleX = (MathHelper.cos(ageInTicks * 0.05F) * 0.05F) - (base1 / 2);
        this.leftarm1.rotateAngleX = (MathHelper.cos(ageInTicks * 0.05F) * 0.05F) - (base1 / 2);
        
        this.rightarm2.rotateAngleY = -1.5707963267948966F + (base3 / 2) - fg1;
        this.leftarm2.rotateAngleY = 1.5707963267948966F - (base3 / 2) + fg1;
        
        this.rightclaw1.rotateAngleX = -(MathHelper.cos(ageInTicks * 0.05F) * 0.05F) + (base1 / 2);
        this.rightclaw2.rotateAngleX = (MathHelper.cos(ageInTicks * 0.05F) * 0.05F);
        this.leftclaw1.rotateAngleX = -(MathHelper.cos(ageInTicks * 0.05F) * 0.05F) + (base1 / 2);
        this.leftclaw2.rotateAngleX = (MathHelper.cos(ageInTicks * 0.05F) * 0.05F);

        this.tailbase.rotateAngleX = (fg1 * 0.5F) + baserot + (MathHelper.sin(ageInTicks * 0.05F) * 0.05F) + (MathHelper.cos(limbSwing * 0.6662F) * (scorpion.isAggressive() ? 0.25F : 0.05F) * limbSwingAmount);
        this.tailbase.rotateAngleZ = (MathHelper.cos(ageInTicks * 0.0325F) * 0.05F);
        this.tail1.rotateAngleX = (fg1 * 0.5F) + base1 + (MathHelper.sin(ageInTicks * 0.05F) * 0.05F);
        this.tail1.rotateAngleZ = (MathHelper.cos(ageInTicks * 0.0325F + 0.025F) * 0.05F);
        this.tail2.rotateAngleX = -(fg1 * 0.25F) + base2 + (MathHelper.sin(ageInTicks * 0.05F + 0.125F) * 0.05F);
        this.tail2.rotateAngleZ = (MathHelper.cos(ageInTicks * 0.0325F + 0.05F) * 0.05F);
        this.tail3.rotateAngleX = -(fg1 * 0.325F) + base3 + (MathHelper.sin(ageInTicks * 0.05F + 0.25F) * 0.05F);
        this.tail3.rotateAngleZ = (MathHelper.cos(ageInTicks * 0.0325F + 0.075F) * 0.05F);
        this.tailbulb.rotateAngleX = -(fg1 * 2.0F) + (MathHelper.sin(ageInTicks * 0.05F + 0.325F) * 0.05F);
        this.tailbulb.rotateAngleZ = (MathHelper.cos(ageInTicks * 0.0325F + 0.1F) * 0.05F);
        
        this.rightleg1.rotateAngleY = 0.9599310885968813F - MathHelper.cos(limbSwing * 0.6662F + 0.75F) * limbSwingAmount;
        this.rightleg2.rotateAngleY = 1.3089969389957472F - MathHelper.cos(limbSwing * 0.6662F + 1.5F) * limbSwingAmount;
        this.rightleg3.rotateAngleY = 1.8325957145940461F - MathHelper.cos(limbSwing * 0.6662F + 2.25F) * limbSwingAmount;
        this.rightleg4.rotateAngleY = 2.356194490192345F - MathHelper.cos(limbSwing * 0.6662F + 3.0F) * limbSwingAmount;
        this.leftleg1.rotateAngleY = -0.9599310885968813F + MathHelper.cos(limbSwing * 0.6662F + 0.75F) * limbSwingAmount;
        this.leftleg2.rotateAngleY = -1.3089969389957472F + MathHelper.cos(limbSwing * 0.6662F + 1.5F) * limbSwingAmount;
        this.leftleg3.rotateAngleY = -1.8325957145940461F + MathHelper.cos(limbSwing * 0.6662F + 2.25F) * limbSwingAmount;
        this.leftleg4.rotateAngleY = -2.356194490192345F + MathHelper.cos(limbSwing * 0.6662F + 3.0F) * limbSwingAmount;
        
        this.rightleg1.rotateAngleX = 0.4363323129985824F - MathHelper.cos(limbSwing * 0.6662F + 1.75F) * limbSwingAmount;
        this.rightleg2.rotateAngleX = 0.4363323129985824F - MathHelper.cos(limbSwing * 0.6662F + 2.5F) * limbSwingAmount;
        this.rightleg3.rotateAngleX = 0.4363323129985824F - MathHelper.cos(limbSwing * 0.6662F + 3.25F) * limbSwingAmount;
        this.rightleg4.rotateAngleX = 0.4363323129985824F - MathHelper.cos(limbSwing * 0.6662F + 4.0F) * limbSwingAmount;
        this.leftleg1.rotateAngleX = 0.4363323129985824F - MathHelper.cos(limbSwing * 0.6662F + 1.75F) * limbSwingAmount;
        this.leftleg2.rotateAngleX = 0.4363323129985824F - MathHelper.cos(limbSwing * 0.6662F + 2.5F) * limbSwingAmount;
        this.leftleg3.rotateAngleX = 0.4363323129985824F - MathHelper.cos(limbSwing * 0.6662F + 3.25F) * limbSwingAmount;
        this.leftleg4.rotateAngleX = 0.4363323129985824F - MathHelper.cos(limbSwing * 0.6662F + 4.0F) * limbSwingAmount;
        
        if (this.rightleg1.rotateAngleX > 0.4363323129985824F)
        	this.rightleg1.rotateAngleX = 0.4363323129985824F; 
        
        if (this.rightleg2.rotateAngleX > 0.4363323129985824F)
        	this.rightleg2.rotateAngleX = 0.4363323129985824F; 
        
        if (this.rightleg3.rotateAngleX > 0.4363323129985824F)
        	this.rightleg3.rotateAngleX = 0.4363323129985824F;  
        
        if (this.rightleg4.rotateAngleX > 0.4363323129985824F)
        	this.rightleg4.rotateAngleX = 0.4363323129985824F;        
        
        if (this.leftleg1.rotateAngleX > 0.4363323129985824F)
        	this.leftleg1.rotateAngleX = 0.4363323129985824F;
        
        if (this.leftleg2.rotateAngleX > 0.4363323129985824F)
        	this.leftleg2.rotateAngleX = 0.4363323129985824F;
        
        if (this.leftleg3.rotateAngleX > 0.4363323129985824F)
        	this.leftleg3.rotateAngleX = 0.4363323129985824F;
        
        if (this.leftleg4.rotateAngleX > 0.4363323129985824F)
        	this.leftleg4.rotateAngleX = 0.4363323129985824F;
    }
}
