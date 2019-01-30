package xenoscape.worldsretold.heatwave.entity.hostile.anubite;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAnubite extends RenderBiped<EntityAnubite>
{
    private static final ResourceLocation ANUBITE_TEXTURES = new ResourceLocation("worldsretold:textures/entity/anubite.png");
	public static final RenderAnubite.Factory FACTORY = new RenderAnubite.Factory();
	
    public RenderAnubite(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelAnubite(), 0.5F);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelBiped(0.5F, 0.0F, 64, 32);
                this.modelArmor = new ModelBiped(1.0F, 0.0F, 64, 32);
            }
        });
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityAnubite entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.1F, 1.1F, 1.1F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityAnubite entity)
    {
        return ANUBITE_TEXTURES;
    }
    
	public static class Factory implements IRenderFactory<EntityAnubite> 
	{
		@Override
		public Render<? super EntityAnubite> createRenderFor(RenderManager manager) 
		{
			return new RenderAnubite(manager);
		}
	}
}