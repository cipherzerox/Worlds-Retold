package xenoscape.worldsretold.hailstorm.entity.passive.nix;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderNix extends RenderLiving<EntityNix>
{
    private ResourceLocation TEXTURE = new ResourceLocation("worldsretold:textures/entity/nix.png");
    public static final RenderNix.Factory FACTORY = new RenderNix.Factory();

    public RenderNix(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelNix(), 0.5F);
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull EntityNix entity)
    {
        return TEXTURE;
    }

    @Override
    public void doRender(EntityNix entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected void preRenderCallback(EntityNix entitylivingbaseIn, float partialTickTime)
    {
        int s = entitylivingbaseIn.getStage();
        if(s > 0){
            GlStateManager.scale(s * .75, s * .75, s * .75);
        }
        else
            GlStateManager.scale(1, 1, 1);

        if(entitylivingbaseIn.getShrink())
        {
            GlStateManager.scale(1, 1, 1);
        }
    }

    public static class Factory implements IRenderFactory<EntityNix>
    {

        @Override
        public Render<? super EntityNix> createRenderFor(RenderManager manager) {
            return new RenderNix(manager);
        }

    }
}
