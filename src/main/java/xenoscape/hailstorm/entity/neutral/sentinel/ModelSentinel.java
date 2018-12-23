package xenoscape.hailstorm.entity.neutral.sentinel;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import xenoscape.hailstorm.util.AdvancedModelBase;
import xenoscape.hailstorm.util.AdvancedModelRenderer;

/**
 * Cloudboi - T3DK Created using Tabula 6.0.0
 */
public class ModelSentinel extends AdvancedModelBase {

    public AdvancedModelRenderer Head;
    public AdvancedModelRenderer Float1;
    public AdvancedModelRenderer Shield1;
    public AdvancedModelRenderer Float3;
    public AdvancedModelRenderer Float2;
    public AdvancedModelRenderer Shield2;
    public AdvancedModelRenderer Shield3;
    public AdvancedModelRenderer Shield4;
    public AdvancedModelRenderer Pole1A;
    public AdvancedModelRenderer Pole1B;
    public AdvancedModelRenderer Pole2A;
    public AdvancedModelRenderer Pole2B;
    public AdvancedModelRenderer Pole3A;
    public AdvancedModelRenderer Pole3B;
    public AdvancedModelRenderer Pole4A;
    public AdvancedModelRenderer Pole4B;

    public ModelSentinel() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Shield2 = new AdvancedModelRenderer(this, 0, 48);
        this.Shield2.setRotationPoint(0.0F, 3.0F, 0.1F);
        this.Shield2.addBox(-1.4F, -5.0F, 7.2F, 3, 14, 1, 0.0F);
        this.setRotateAngle(Shield2, 0.0F, -2.367539130330308F, 0.0F);
        this.Float1 = new AdvancedModelRenderer(this, 0, 0);
        this.Float1.setRotationPoint(0.0F, 8.5F, 0.0F);
        this.Float1.addBox(-3.0F, 0.0F, -3.0F, 6, 1, 6, 0.0F);
        this.Shield4 = new AdvancedModelRenderer(this, 0, 48);
        this.Shield4.setRotationPoint(0.0F, 3.0F, 0.1F);
        this.Shield4.addBox(-1.4F, -5.0F, 7.2F, 3, 14, 1, 0.0F);
        this.setRotateAngle(Shield4, 0.0F, 0.7740535232594852F, 0.0F);
        this.Pole4A = new AdvancedModelRenderer(this, 8, 48);
        this.Pole4A.setRotationPoint(1.1F, -5.0F, 7.6F);
        this.Pole4A.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(Pole4A, 0.0F, 0.7853981633974483F, 0.0F);
        this.Head = new AdvancedModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.Head.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 8, 0.0F);
        this.Pole3B = new AdvancedModelRenderer(this, 0, 48);
        this.Pole3B.setRotationPoint(-1.9F, -5.0F, 6.1F);
        this.Pole3B.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(Pole3B, 0.0F, -0.7853981633974483F, 0.0F);
        this.Pole4B = new AdvancedModelRenderer(this, 0, 48);
        this.Pole4B.setRotationPoint(-1.9F, -5.0F, 6.1F);
        this.Pole4B.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(Pole4B, 0.0F, -0.7853981633974483F, 0.0F);
        this.Pole1A = new AdvancedModelRenderer(this, 8, 48);
        this.Pole1A.setRotationPoint(1.1F, -5.0F, 7.6F);
        this.Pole1A.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(Pole1A, 0.0F, 0.7853981633974483F, 0.0F);
        this.Shield1 = new AdvancedModelRenderer(this, 0, 48);
        this.Shield1.setRotationPoint(0.0F, 3.0F, 0.1F);
        this.Shield1.addBox(-1.4F, -5.0F, 7.2F, 3, 14, 1, 0.0F);
        this.setRotateAngle(Shield1, 0.0F, 2.356194490192345F, 0.0F);
        this.Float2 = new AdvancedModelRenderer(this, 0, 0);
        this.Float2.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.Float2.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
        this.Float3 = new AdvancedModelRenderer(this, 0, 0);
        this.Float3.setRotationPoint(0.0F, 11.5F, 0.0F);
        this.Float3.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.Pole2B = new AdvancedModelRenderer(this, 0, 48);
        this.Pole2B.setRotationPoint(-1.9F, -5.0F, 6.1F);
        this.Pole2B.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(Pole2B, 0.0F, -0.7853981633974483F, 0.0F);
        this.Pole1B = new AdvancedModelRenderer(this, 0, 48);
        this.Pole1B.setRotationPoint(-1.9F, -5.0F, 6.1F);
        this.Pole1B.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(Pole1B, 0.0F, -0.7853981633974483F, 0.0F);
        this.Pole3A = new AdvancedModelRenderer(this, 8, 48);
        this.Pole3A.setRotationPoint(1.1F, -5.0F, 7.6F);
        this.Pole3A.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(Pole3A, 0.0F, 0.7853981633974483F, 0.0F);
        this.Shield3 = new AdvancedModelRenderer(this, 0, 48);
        this.Shield3.setRotationPoint(0.0F, 3.0F, 0.1F);
        this.Shield3.addBox(-1.4F, -5.0F, 7.2F, 3, 14, 1, 0.0F);
        this.setRotateAngle(Shield3, 0.0F, -0.8196066167365371F, 0.0F);
        this.Pole2A = new AdvancedModelRenderer(this, 8, 48);
        this.Pole2A.setRotationPoint(1.1F, -5.0F, 7.6F);
        this.Pole2A.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(Pole2A, 0.0F, 0.7853981633974483F, 0.0F);
        this.Head.addChild(this.Shield2);
        this.Head.addChild(this.Float1);
        this.Head.addChild(this.Shield4);
        this.Shield4.addChild(this.Pole4A);
        this.Shield3.addChild(this.Pole3B);
        this.Shield4.addChild(this.Pole4B);
        this.Shield1.addChild(this.Pole1A);
        this.Head.addChild(this.Shield1);
        this.Head.addChild(this.Float2);
        this.Head.addChild(this.Float3);
        this.Shield2.addChild(this.Pole2B);
        this.Shield1.addChild(this.Pole1B);
        this.Shield3.addChild(this.Pole3A);
        this.Head.addChild(this.Shield3);
        this.Shield2.addChild(this.Pole2A);

     //   updateDefaultPose();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Head.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer AdvancedModelRenderer, float x, float y, float z) {
        AdvancedModelRenderer.rotateAngleX = x;
        AdvancedModelRenderer.rotateAngleY = y;
        AdvancedModelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (entityIn instanceof EntitySentinel) {
            EntitySentinel sentinel = (EntitySentinel) entityIn;

            if(sentinel.shouldExplode()) {
                System.out.println("reset");
                this.resetToDefaultPose();
            }
            if(sentinel.isActive()){
                this.Float1.rotateAngleY = 0.1662F * ageInTicks * 1.2F;
                this.Float2.rotateAngleY = 0.1662F * ageInTicks * 1.2F * -1;
                this.Float3.rotateAngleY = 0.1662F * ageInTicks * 1.2F;
                this.Head.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 5.0F + 12.5F;
            }

            if (sentinel.isActive())
            {
                this.Head.rotateAngleX = headPitch / 50F;
                this.Head.rotateAngleY = netHeadYaw / 50F;

                if (sentinel.deathTicks == 0)
                {
                    if (sentinel.getSpinning())
                    {
                        this.Shield1.rotateAngleY = 0.1762F * ageInTicks * 2.6F;
                        this.Shield2.rotateAngleY = 0.1762F * ageInTicks * 2.6F + 1.5F;
                        this.Shield3.rotateAngleY = 0.1762F * ageInTicks * 2.6F + 3.0F;
                        this.Shield4.rotateAngleY = 0.1762F * ageInTicks * 2.6F + 4.5F;
                        this.Shield1.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 4.0F + 2;
                        this.Shield2.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 3.0F + 2;
                        this.Shield3.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 2.0F + 2;
                        this.Shield4.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 1.0F + 2;
                    } else {
                        this.Shield1.rotateAngleY = 0.007F * ageInTicks * 2.6F;
                        this.Shield2.rotateAngleY = 0.007F * ageInTicks * 2.6F + 1.5F;
                        this.Shield3.rotateAngleY = 0.007F * ageInTicks * 2.6F + 3.0F;
                        this.Shield4.rotateAngleY = 0.007F * ageInTicks * 2.6F + 4.5F;
                        this.Shield1.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 4.0F + 2;
                        this.Shield2.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 3.0F + 2;
                        this.Shield3.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 2.0F + 2;
                        this.Shield4.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 1.0F + 2;
                    }

                }
            }
            else if(!sentinel.hasExploded() && !sentinel.isActive()){
                this.Head.rotationPointY = 24;
                this.Head.rotateAngleX = 0;

                this.Shield1.rotationPointY = -1;
                this.Shield1.rotationPointZ = 5;
                this.Shield1.rotationPointX = 3;
                this.Shield1.rotateAngleX = 80;

                this.Shield2.rotationPointY = 0;
                this.Shield2.rotationPointZ = -5;
                this.Shield2.rotationPointX = 3;
                this.Shield2.rotateAngleZ = 0;
                this.Shield2.rotateAngleX = 80;

                this.Shield3.rotationPointY = 0;
                this.Shield3.rotationPointZ = -5;
                this.Shield3.rotationPointX = -3;
                this.Shield3.rotateAngleZ = 0;
                this.Shield3.rotateAngleX = 80;

                this.Shield4.rotationPointY = -1;
                this.Shield4.rotationPointZ = 5;
                this.Shield4.rotationPointX = -3;
                this.Shield4.rotateAngleX = 80;
                this.Shield4.rotateAngleZ = 0;

                this.Float1.rotationPointY = -1;
                this.Float2.rotationPointY = -2;
                this.Float3.rotationPointY = -3;
            }

        }
    }

    public void resetToDefaultPose(){
        this.Head.rotationPointY = 0;
        this.Head.rotateAngleX = 0;

        this.Shield1.rotationPointY = 0;
        this.Shield1.rotationPointZ = 0;
        this.Shield1.rotationPointX = 0;
        this.Shield1.rotateAngleX = 0;

        this.Shield2.rotationPointY = 0;
        this.Shield2.rotationPointZ = 0;
        this.Shield2.rotationPointX = 0;
        this.Shield2.rotateAngleZ = 0;
        this.Shield2.rotateAngleX = 0;

        this.Shield3.rotationPointY = 0;
        this.Shield3.rotationPointZ = 0;
        this.Shield3.rotationPointX = 0;
        this.Shield3.rotateAngleZ = 0;
        this.Shield3.rotateAngleX = 0;

        this.Shield4.rotationPointY = 0;
        this.Shield4.rotationPointZ = 0;
        this.Shield4.rotationPointX = 0;
        this.Shield4.rotateAngleX = 0;
        this.Shield4.rotateAngleZ = 0;

        this.Float1.rotationPointY = 0;
        this.Float2.rotationPointY = 0;
        this.Float3.rotationPointY = 0;
    }

}