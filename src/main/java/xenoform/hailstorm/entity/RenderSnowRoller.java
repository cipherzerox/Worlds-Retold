package xenoform.hailstorm.entity;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderSnowRoller extends RenderLiving<EntitySnowRoller>
{
   // private ResourceLocation TEXTURE = new ResourceLocation("hailstorm:textures/entity/snow_roller.png");
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/chicken.png");
    public static final Factory FACTORY = new Factory();

    public RenderSnowRoller(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelChicken(), 0F);
      //  super(renderManagerIn, new ModelSnowRoller(), 1F);
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull EntitySnowRoller entity)
    {
        return TEXTURE;
    }

    @Override
    public void doRender(EntitySnowRoller entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected void preRenderCallback(EntitySnowRoller entitylivingbaseIn, float partialTickTime)
    {
        double s = entitylivingbaseIn.getSize();
        GlStateManager.scale(s, s, s);

        if(entitylivingbaseIn.getShrink())
        {
            GlStateManager.scale(1, 1, 1);
        }
    }

    public static class Factory implements IRenderFactory<EntitySnowRoller>
    {

        @Override
        public Render<? super EntitySnowRoller> createRenderFor(RenderManager manager) {
            return new RenderSnowRoller(manager);
        }

    }

}
