package xenoscape.worldsretold.defaultmod.entity.neutral.sentinel;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderSentinel extends RenderLiving<EntitySentinel> {

    private ResourceLocation TEXTURE = new ResourceLocation("worldsretold:textures/entity/sentinel.png");
    private ResourceLocation DYING_TEXTURE = new ResourceLocation("worldsretold:textures/entity/sentinel_dying.png");
    private ResourceLocation SHOOTING_TEXTURE = new ResourceLocation(
            "worldsretold:textures/entity/Sentinel_shooting.png");

    public static final RenderSentinel.Factory FACTORY = new RenderSentinel.Factory();

    public RenderSentinel(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelSentinel(), 0.55F);
     //   this.addLayer(new LayerGuardsmanGlow(this, SHOOTING_TEXTURE));
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull EntitySentinel entity) {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntitySentinel entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        GlStateManager.scale(2F, 2F, 2F);
        if (entitylivingbaseIn.deathTicks > 0) {
            float colorTimer = 1.0F - ((entitylivingbaseIn.deathTicks / 100.0F) * 1.5F);
            GlStateManager.color(colorTimer, colorTimer, colorTimer, 1.0F);
        }
    }

    @Override
    public void doRender(EntitySentinel entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected void renderModel(EntitySentinel entitylivingbaseIn, float limbSwing, float limbSwingAmount,
                               float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        if (entitylivingbaseIn.deathTicks > 0) {
            float f = (float) entitylivingbaseIn.deathTicks / 100.0F;
            GlStateManager.depthFunc(GL11.GL_LEQUAL);
            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(GL11.GL_GREATER, f);
            this.bindTexture(DYING_TEXTURE);
            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
                    scaleFactor);
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
            GlStateManager.depthFunc(GL11.GL_EQUAL);
        }

        this.bindEntityTexture(entitylivingbaseIn);
        this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
                scaleFactor);
        GlStateManager.depthFunc(GL11.GL_LEQUAL);
    }

    public static class Factory implements IRenderFactory<EntitySentinel> {
        @Override
        public Render<? super EntitySentinel> createRenderFor(RenderManager manager) {
            return new RenderSentinel(manager);
        }
    }
}
