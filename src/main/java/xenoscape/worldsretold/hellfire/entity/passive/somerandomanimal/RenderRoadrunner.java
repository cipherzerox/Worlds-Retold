package xenoscape.worldsretold.hellfire.entity.passive.somerandomanimal;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRoadrunner extends RenderLiving<EntityRoadrunner>
{
    private static final ResourceLocation ROADRUNNER = new ResourceLocation("worldsretold:textures/entity/roadrunner.png");
	public static final RenderRoadrunner.Factory FACTORY = new RenderRoadrunner.Factory();
    
    public RenderRoadrunner(RenderManager p_i47211_1_)
    {
        super(p_i47211_1_, new ModelRoadrunner(), 0.3F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityRoadrunner entity)
    {
        return ROADRUNNER;
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityRoadrunner livingBase, float partialTicks)
    {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
    
	public static class Factory implements IRenderFactory<EntityRoadrunner> {
		@Override
		public Render<? super EntityRoadrunner> createRenderFor(RenderManager manager) {
			return new RenderRoadrunner(manager);
		}
	}
}