package xenoform.hailstorm.entity.blizzard;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderBlizzard extends RenderLiving<EntityBlizzard> 
{
    private ResourceLocation TEXTURE = new ResourceLocation("hailstorm:textures/entity/blizzard.png");
    public static final RenderBlizzard.Factory FACTORY = new RenderBlizzard.Factory();

    public RenderBlizzard(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBlizzard(), 0.55F);
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull EntityBlizzard entity)
    {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityBlizzard entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        GlStateManager.scale(3F, 3F, 3F);
    }

    @Override
    public void doRender(EntityBlizzard entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public static class Factory implements IRenderFactory<EntityBlizzard>
    {
        @Override
        public Render<? super EntityBlizzard> createRenderFor(RenderManager manager) {
            return new RenderBlizzard(manager);
        }
    }
}
