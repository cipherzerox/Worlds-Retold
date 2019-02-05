package xenoscape.worldsretold.heatwave.entity.neutral.antlion;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

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

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body.render(f5);
        this.RLeg.render(f5);
        this.Neck.render(f5);
        this.LBLeg.render(f5);
        this.RFLeg.render(f5);
        this.LLeg.render(f5);
        this.RBLeg.render(f5);
        this.LFLeg.render(f5);
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
