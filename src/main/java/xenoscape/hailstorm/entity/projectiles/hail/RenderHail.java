package xenoscape.hailstorm.entity.projectiles.hail;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xenoscape.hailstorm.entity.projectiles.ModelSphere;

public class RenderHail extends Render<EntityHail>
{
    private ResourceLocation TEXTURE = new ResourceLocation("hailstorm:textures/entity/snow_roller.png");
    /** The Skeleton's head model. */
    private final ModelSphere hailModel = new ModelSphere();
    public static final RenderHail.Factory FACTORY = new RenderHail.Factory();

    public RenderHail(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }
        
    private float getRenderYaw(float p_82400_1_, float p_82400_2_, float p_82400_3_)
    {
        float f;

        for (f = p_82400_2_ - p_82400_1_; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return p_82400_1_ + p_82400_3_ * f;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityHail entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        float f = this.getRenderYaw(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
        float f1 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        GlStateManager.translate((float)x, (float)y, (float)z);
        float f2 = 0.0625F;
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(.25F, .25F, .25F);
        GlStateManager.enableAlpha();
        this.bindEntityTexture(entity);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.hailModel.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.0625F);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityHail entity)
    {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityHail>
    {
        @Override
        public Render<? super EntityHail> createRenderFor(RenderManager manager) {
            return new RenderHail(manager);
        }
    }
}
