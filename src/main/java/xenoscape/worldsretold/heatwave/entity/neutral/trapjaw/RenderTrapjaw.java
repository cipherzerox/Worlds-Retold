package xenoscape.worldsretold.heatwave.entity.neutral.trapjaw;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTrapjaw extends RenderLiving<EntityTrapjaw>
{
    private static final ResourceLocation TRAPJAW = new ResourceLocation("worldsretold:textures/entity/trap_jaw.png");
	public static final RenderTrapjaw.Factory FACTORY = new RenderTrapjaw.Factory();
    
    public RenderTrapjaw(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelTrapjaw(), 1.0F);
    }

    protected float getDeathMaxRotation(EntityTrapjaw entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTrapjaw entity)
    {
        return TRAPJAW;
    }
    
	public static class Factory implements IRenderFactory<EntityTrapjaw> {
		@Override
		public Render<? super EntityTrapjaw> createRenderFor(RenderManager manager) {
			return new RenderTrapjaw(manager);
		}
	}
}