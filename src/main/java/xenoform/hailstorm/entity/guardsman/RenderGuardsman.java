package xenoform.hailstorm.entity.guardsman;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderGuardsman extends RenderLiving<EntityGuardsman>
{
    private ResourceLocation TEXTURE = new ResourceLocation("hailstorm:textures/entity/guardsman.png");
    public static final RenderGuardsman.Factory FACTORY = new RenderGuardsman.Factory();

    public RenderGuardsman(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGuardsman(), 0.55F);
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull EntityGuardsman entity)
    {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityGuardsman entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        GlStateManager.scale(2F, 2F, 2F);
    }

    @Override
    public void doRender(EntityGuardsman entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public static class Factory implements IRenderFactory<EntityGuardsman>
    {
        @Override
        public Render<? super EntityGuardsman> createRenderFor(RenderManager manager) {
            return new RenderGuardsman(manager);
        }
    }
}
