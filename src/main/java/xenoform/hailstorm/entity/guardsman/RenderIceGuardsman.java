package xenoform.hailstorm.entity.guardsman;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderIceGuardsman extends RenderLiving<EntityIceGuardsman>
{
    private ResourceLocation TEXTURE = new ResourceLocation("hailstorm:textures/entity/ice_guardsman.png");
    public static final RenderIceGuardsman.Factory FACTORY = new RenderIceGuardsman.Factory();

    public RenderIceGuardsman(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGuardsman(), 0.55F);
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull EntityIceGuardsman entity)
    {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityIceGuardsman entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        GlStateManager.scale(2F, 2F, 2F);
    }

    @Override
    public void doRender(EntityIceGuardsman entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public static class Factory implements IRenderFactory<EntityIceGuardsman>
    {
        @Override
        public Render<? super EntityIceGuardsman> createRenderFor(RenderManager manager) {
            return new RenderIceGuardsman(manager);
        }
    }
}
