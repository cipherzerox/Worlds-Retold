package xenoscape.worldsretold.heatwave.entity.neutral.cobra;

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
public class RenderCobra extends RenderLiving<EntityCobra>
{
    private static final ResourceLocation COBRA = new ResourceLocation("worldsretold:textures/entity/egyptian_cobra.png");
	public static final RenderCobra.Factory FACTORY = new RenderCobra.Factory();
    
    public RenderCobra(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCobra(), 0.5F);
    }

    protected float getDeathMaxRotation(EntityCobra entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCobra entity)
    {
        return COBRA;
    }
    
	public static class Factory implements IRenderFactory<EntityCobra> {
		@Override
		public Render<? super EntityCobra> createRenderFor(RenderManager manager) {
			return new RenderCobra(manager);
		}
	}
}