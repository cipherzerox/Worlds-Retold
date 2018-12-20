package xenoscape.hailstorm.entity.sentinel;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import xenoscape.hailstorm.entity.guardsman.ModelGuardsman;

/**
 * Cloudboi - T3DK Created using Tabula 6.0.0
 */
public class ModelSentinel extends ModelGuardsman {

    public float defaultRotationX, defaultRotationY, defaultRotationZ;
    public float defaultOffsetX, defaultOffsetY, defaultOffsetZ;
    public float defaultPositionX, defaultPositionY, defaultPositionZ;
    
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
                                  float headPitch, float scaleFactor, Entity entityIn) {
        if (entityIn instanceof EntitySentinel) {
            EntitySentinel sentinel = (EntitySentinel) entityIn;
            
            if (sentinel.isActive()) {
                this.Head.rotateAngleX = headPitch / 50f;
                this.Head.rotateAngleY = netHeadYaw / 50f;

                if (sentinel.deathTicks == 0) {
                    if (sentinel.getSpinning()) {
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

                    this.Float1.rotateAngleY = 0.1662F * ageInTicks * 1.2F;
                    this.Float2.rotateAngleY = 0.1662F * ageInTicks * 1.2F * -1;
                    this.Float3.rotateAngleY = 0.1662F * ageInTicks * 1.2F;
                    this.Head.rotationPointY = MathHelper.cos(0.1F * ageInTicks) * 5.0F + 12.5F;
                }
            }
            else{
                this.Head.rotationPointY = 24;

                this.Shield1.rotationPointY = -1;
                this.Shield1.rotationPointZ = 5;
                this.Shield1.rotationPointX = 3;
                this.Shield1.rotateAngleX = 80;

                this.Shield2.rotationPointY = 14;
                this.Shield2.rotationPointZ = -5;
                this.Shield2.rotationPointX = 3;
                this.Shield2.rotateAngleZ = -100;

                this.Shield3.rotationPointY = 14;
                this.Shield3.rotationPointZ = -5;
                this.Shield3.rotationPointX = -3;
                this.Shield3.rotateAngleZ = -140;

                this.Shield4.rotationPointY = -1;
                this.Shield4.rotationPointZ = -1;
                this.Shield4.rotationPointX = -7;
                this.Shield4.rotateAngleX = 80;
                this.Shield4.rotateAngleZ = 0;

                this.Float1.rotationPointY = -1;
                this.Float2.rotationPointY = -2;
                this.Float3.rotationPointY = -3;
            }
        }
    }

    /**
     * Sets the current pose to the previously set default pose
     */
    public void resetToDefaultPose() {
        this.boxList.stream().filter(modelRenderer -> modelRenderer instanceof ModelRenderer).forEach(modelRenderer -> {
            ModelRenderer advancedModelRenderer = (ModelRenderer) modelRenderer;
            this.resetToDefaultPose(advancedModelRenderer);
        });
    }

    /**
     * Sets this ModelRenderer's default pose to the current pose.
     */
    public void updateDefaultPose(ModelRenderer box) {
        this.defaultRotationX = box.rotateAngleX;
        this.defaultRotationY = box.rotateAngleY;
        this.defaultRotationZ = box.rotateAngleZ;

        this.defaultOffsetX = box.offsetX;
        this.defaultOffsetY = box.offsetY;
        this.defaultOffsetZ = box.offsetZ;

        this.defaultPositionX = box.rotationPointX;
        this.defaultPositionY = box.rotationPointY;
        this.defaultPositionZ = box.rotationPointZ;
    }
    
    /**
     * Sets the current pose to the previously set default pose.
     */
    public void resetToDefaultPose(ModelRenderer box) {
        box.rotateAngleX = this.defaultRotationX;
        box.rotateAngleY = this.defaultRotationY;
        box.rotateAngleZ = this.defaultRotationZ;

        box.offsetX = this.defaultOffsetX;
        box.offsetY = this.defaultOffsetY;
        box.offsetZ = this.defaultOffsetZ;

        box.rotationPointX = this.defaultPositionX;
        box.rotationPointY = this.defaultPositionY;
        box.rotationPointZ = this.defaultPositionZ;
    }

    public void transitionTo(ModelRenderer to, float timer, float maxTime) {
        to.rotateAngleX += ((to.rotateAngleX - to.rotateAngleX) / maxTime) * timer;
        to.rotateAngleY += ((to.rotateAngleY - to.rotateAngleY) / maxTime) * timer;
        to.rotateAngleZ += ((to.rotateAngleZ - to.rotateAngleZ) / maxTime) * timer;

        to.rotationPointX += ((to.rotationPointX - to.rotationPointX) / maxTime) * timer;
        to.rotationPointY += ((to.rotationPointY - to.rotationPointY) / maxTime) * timer;
        to.rotationPointZ += ((to.rotationPointZ - to.rotationPointZ) / maxTime) * timer;

        to.offsetX += ((to.offsetX - to.offsetX) / maxTime) * timer;
        to.offsetY += ((to.offsetY - to.offsetY) / maxTime) * timer;
        to.offsetZ += ((to.offsetZ - to.offsetZ) / maxTime) * timer;
    }
}