package xenoform.hailstorm.entity.guardsman;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Cloudboi - T3DK
 * Created using Tabula 7.0.0
 */
public class ModelGuardsman extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer poleNW1;
    public ModelRenderer poleNE1;
    public ModelRenderer poleSW1;
    public ModelRenderer poleSE1;
    public ModelRenderer Float1;
    public ModelRenderer poleNW2;
    public ModelRenderer shieldface1;
    public ModelRenderer poleNE2;
    public ModelRenderer shieldface2;
    public ModelRenderer poleSW2;
    public ModelRenderer shieldface3;
    public ModelRenderer poleSE2;
    public ModelRenderer shieldface4;
    public ModelRenderer Float2;
    public ModelRenderer Float3;

    public ModelGuardsman() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.poleSW2 = new ModelRenderer(this, 5, 48);
        this.poleSW2.setRotationPoint(-3.0F, 0.0F, 3.0F);
        this.poleSW2.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.poleSE2 = new ModelRenderer(this, 0, 48);
        this.poleSE2.setRotationPoint(3.0F, 0.0F, 3.0F);
        this.poleSE2.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.Float2 = new ModelRenderer(this, 0, 0);
        this.Float2.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.Float2.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
        this.poleSE1 = new ModelRenderer(this, 8, 48);
        this.poleSE1.setRotationPoint(-8.0F, -2.0F, 3.0F);
        this.poleSE1.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.poleNE2 = new ModelRenderer(this, 4, 48);
        this.poleNE2.setRotationPoint(3.0F, 0.0F, -3.0F);
        this.poleNE2.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.Float1 = new ModelRenderer(this, 0, 0);
        this.Float1.setRotationPoint(0.0F, 8.5F, 0.0F);
        this.Float1.addBox(-3.0F, 0.0F, -3.0F, 6, 1, 6, 0.0F);
        this.shieldface4 = new ModelRenderer(this, 0, 0);
        this.shieldface4.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.shieldface4.addBox(0.0F, 0.0F, 0.0F, 3, 14, 1, 0.0F);
        this.setRotateAngle(shieldface4, 0.0F, 2.356194490192345F, 0.0F);
        this.shieldface1 = new ModelRenderer(this, 0, 0);
        this.shieldface1.setRotationPoint(3.0F, 0.0F, 4.0F);
        this.shieldface1.addBox(0.0F, 0.0F, 0.0F, 3, 14, 1, 0.0F);
        this.setRotateAngle(shieldface1, 0.0F, 2.356194490192345F, 0.0F);
        this.poleSW1 = new ModelRenderer(this, 1, 48);
        this.poleSW1.setRotationPoint(6.0F, -2.0F, 3.0F);
        this.poleSW1.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.poleNW2 = new ModelRenderer(this, 0, 48);
        this.poleNW2.setRotationPoint(-3.0F, 0.0F, -3.0F);
        this.poleNW2.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.Float3 = new ModelRenderer(this, 0, 0);
        this.Float3.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.Float3.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.head.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 8, 0.0F);
        this.shieldface2 = new ModelRenderer(this, 0, 0);
        this.shieldface2.setRotationPoint(-2.0F, 0.0F, 3.0F);
        this.shieldface2.addBox(0.0F, 0.0F, 0.0F, 3, 14, 1, 0.0F);
        this.setRotateAngle(shieldface2, 0.0F, 0.7853981633974483F, 0.0F);
        this.poleNE1 = new ModelRenderer(this, 2, 48);
        this.poleNE1.setRotationPoint(-8.0F, -2.0F, -5.0F);
        this.poleNE1.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.shieldface3 = new ModelRenderer(this, 0, 0);
        this.shieldface3.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.shieldface3.addBox(0.0F, 0.0F, 0.0F, 3, 14, 1, 0.0F);
        this.setRotateAngle(shieldface3, 0.0F, 0.7853981633974483F, 0.0F);
        this.poleNW1 = new ModelRenderer(this, 8, 48);
        this.poleNW1.setRotationPoint(6.0F, -2.0F, -5.0F);
        this.poleNW1.addBox(0.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.poleSW1.addChild(this.poleSW2);
        this.poleSE1.addChild(this.poleSE2);
        this.Float1.addChild(this.Float2);
        this.head.addChild(this.poleSE1);
        this.poleNE1.addChild(this.poleNE2);
        this.head.addChild(this.Float1);
        this.poleSE2.addChild(this.shieldface4);
        this.poleNW2.addChild(this.shieldface1);
        this.head.addChild(this.poleSW1);
        this.poleNW1.addChild(this.poleNW2);
        this.Float2.addChild(this.Float3);
        this.poleNE2.addChild(this.shieldface2);
        this.head.addChild(this.poleNE1);
        this.poleSW2.addChild(this.shieldface3);
        this.head.addChild(this.poleNW1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.head.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
