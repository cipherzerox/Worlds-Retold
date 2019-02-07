package xenoscape.worldsretold.heatwave.entity.hostile.antlion;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.heatwave.entity.hostile.mummy.EntityMummy;

@SideOnly(Side.CLIENT)
public class RenderAntlion extends RenderLiving<EntityAntlion>
{
    private static final ResourceLocation ANTLION = new ResourceLocation("worldsretold:textures/entity/antlion.png");
	public static final RenderAntlion.Factory FACTORY = new RenderAntlion.Factory();
    
    public RenderAntlion(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelAntlion(), 1.0F);
    }
    
    protected void preRenderCallback(EntityAntlion entitylivingbaseIn, float partialTickTime)
    {
        if (entitylivingbaseIn.isDugIn())
        {
            float f = partialTickTime - (float)entitylivingbaseIn.ticksExisted;
            float baserot = entitylivingbaseIn.getStingerBaseRot(f);
            GlStateManager.translate(0.0F, 2.5F - baserot, 1.0F);
            GlStateManager.rotate(-90F, 1F, 0F, 0F);
        	this.shadowOpaque = baserot;
        }
        else
        {
        	this.shadowOpaque = 1F;
        }
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
        return ANTLION;
    }
    
	public static class Factory implements IRenderFactory<EntityAntlion> {
		@Override
		public Render<? super EntityAntlion> createRenderFor(RenderManager manager) {
			return new RenderAntlion(manager);
		}
	}
}