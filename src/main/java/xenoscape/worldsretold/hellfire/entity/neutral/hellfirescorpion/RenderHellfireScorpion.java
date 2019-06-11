package xenoscape.worldsretold.hellfire.entity.neutral.hellfirescorpion;

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
public class RenderHellfireScorpion extends RenderLiving<EntityHellfireScorpion>
{
    private static final ResourceLocation DESERT_SCORPION = new ResourceLocation("worldsretold:textures/entity/deathstalker.png");
	public static final RenderHellfireScorpion.Factory FACTORY = new RenderHellfireScorpion.Factory();
    
    public RenderHellfireScorpion(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelHellfireScorpion(), 1.0F);
    }

    protected float getDeathMaxRotation(EntityHellfireScorpion entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityHellfireScorpion entity)
    {
        return DESERT_SCORPION;
    }
    
	public static class Factory implements IRenderFactory<EntityHellfireScorpion> {
		@Override
		public Render<? super EntityHellfireScorpion> createRenderFor(RenderManager manager) {
			return new RenderHellfireScorpion(manager);
		}
	}
}