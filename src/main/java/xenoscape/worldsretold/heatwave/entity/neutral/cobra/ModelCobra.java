package xenoscape.worldsretold.heatwave.entity.neutral.cobra;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

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

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.midsec.render(f5);
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
