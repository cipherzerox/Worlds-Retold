package xenoscape.worldsretold.heatwave.entity.neutral.scorpion;

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
public class RenderScorpion extends RenderLiving<EntityScorpion>
{
    private static final ResourceLocation DESERT_SCORPION = new ResourceLocation("worldsretold:textures/entity/deathstalker.png");
	public static final RenderScorpion.Factory FACTORY = new RenderScorpion.Factory();
    
    public RenderScorpion(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelScorpion(), 1.0F);
    }

    protected float getDeathMaxRotation(EntityScorpion entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityScorpion entity)
    {
        return DESERT_SCORPION;
    }
    
	public static class Factory implements IRenderFactory<EntityScorpion> {
		@Override
		public Render<? super EntityScorpion> createRenderFor(RenderManager manager) {
			return new RenderScorpion(manager);
		}
	}
}