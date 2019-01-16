package xenoscape.worldsretold.heatwave.entity.hostile.mummy;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.heatwave.entity.neutral.scorpion.EntityScorpion;
import xenoscape.worldsretold.heatwave.entity.neutral.scorpion.ModelScorpion;

@SideOnly(Side.CLIENT)
public class RenderMummy extends RenderLiving<EntityMummy>
{
    private static final ResourceLocation MUMMY = new ResourceLocation("worldsretold:textures/entity/mummy.png");
	public static final RenderMummy.Factory FACTORY = new RenderMummy.Factory();

    public RenderMummy(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelMummy(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityMummy entity)
    {
        return MUMMY;
    }
    
    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityMummy entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.translate(0F, entitylivingbaseIn.getRisingRot(partialTickTime), 0F);
        if (!entitylivingbaseIn.isChild())
            GlStateManager.translate(0F, entitylivingbaseIn.getRisingRot(partialTickTime), 0F);
    }
    
	public static class Factory implements IRenderFactory<EntityMummy> {
		@Override
		public Render<? super EntityMummy> createRenderFor(RenderManager manager) {
			return new RenderMummy(manager);
		}
	}
}