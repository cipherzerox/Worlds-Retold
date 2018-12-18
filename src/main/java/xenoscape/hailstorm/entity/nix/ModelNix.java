package xenoscape.hailstorm.entity.nix;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * SentientSnowball - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelNix extends ModelBase {
    public ModelRenderer OuterBody;
    public ModelRenderer Eye1;
    public ModelRenderer Eye2;
    public ModelRenderer Mouth1;
    public ModelRenderer InnerBody;

    public ModelNix() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Eye1 = new ModelRenderer(this, 0, 0);
        this.Eye1.setRotationPoint(-4.5F, 11.5F, -4.5F);
        this.Eye1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.Eye2 = new ModelRenderer(this, 0, 0);
        this.Eye2.setRotationPoint(2.5F, 11.5F, -4.5F);
        this.Eye2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.InnerBody = new ModelRenderer(this, 0, 0);
        this.InnerBody.setRotationPoint(-4.0F, 10.0F, -4.0F);
        this.InnerBody.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.OuterBody = new ModelRenderer(this, 48, 24);
        this.OuterBody.setRotationPoint(-10.0F, 4.0F, -10.0F);
        this.OuterBody.addBox(0.0F, 0.0F, 0.0F, 20, 20, 20, 0.0F);
        this.Mouth1 = new ModelRenderer(this, 0, 0);
        this.Mouth1.setRotationPoint(0.0F, 15.0F, -4.5F);
        this.Mouth1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9990000000000001F);
        this.Eye1.render(f5);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9990000000000001F);
        this.Eye2.render(f5);
        GlStateManager.disableBlend();
        this.InnerBody.render(f5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
        this.OuterBody.render(f5);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9990000000000001F);
        this.Mouth1.render(f5);
        GlStateManager.disableBlend();
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
