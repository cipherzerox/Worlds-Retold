package xenoscape.worldsretold.heatwave.entity.neutral.antlion;

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
public class RenderAntlion extends RenderLiving<EntityAntlion>
{
    private static final ResourceLocation TRAPJAW = new ResourceLocation("worldsretold:textures/entity/trap_jaw.png");
	public static final RenderAntlion.Factory FACTORY = new RenderAntlion.Factory();
    
    public RenderAntlion(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelAntlion(), 1.0F);
    }

    protected float getDeathMaxRotation(EntityAntlion entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityAntlion entity)
    {
        return TRAPJAW;
    }
    
	public static class Factory implements IRenderFactory<EntityAntlion> {
		@Override
		public Render<? super EntityAntlion> createRenderFor(RenderManager manager) {
			return new RenderAntlion(manager);
		}
	}
}