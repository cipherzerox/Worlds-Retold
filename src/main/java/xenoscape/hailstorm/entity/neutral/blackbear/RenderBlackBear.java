package xenoscape.hailstorm.entity.neutral.blackbear;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelPolarBear;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderBlackBear extends RenderLiving<EntityBlackBear>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("hailstorm:textures/entity/blackbear.png");
	public static final RenderBlackBear.Factory FACTORY = new RenderBlackBear.Factory();

    public RenderBlackBear(RenderManager p_i47197_1_)
    {
        super(p_i47197_1_, new ModelPolarBear(), 0.7F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
	@Override
	@Nonnull
    protected ResourceLocation getEntityTexture(EntityBlackBear entity)
    {
        return TEXTURE;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityBlackBear entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityBlackBear entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
    
	public static class Factory implements IRenderFactory<EntityBlackBear> {
		@Override
		public Render<? super EntityBlackBear> createRenderFor(RenderManager manager) {
			return new RenderBlackBear(manager);
		}
	}
}