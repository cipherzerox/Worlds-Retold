package xenoscape.worldsretold.hellfire.entity.hostile.hellhound;

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
public class RenderHellhound extends RenderBiped<EntityHellhound>
{
    private static final ResourceLocation ANUBITE_TEXTURES = new ResourceLocation("worldsretold:textures/entity/anubite.png");
	public static final RenderHellhound.Factory FACTORY = new RenderHellhound.Factory();
	
    public RenderHellhound(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelHellhound(), 0.5F);
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
    protected void preRenderCallback(EntityHellhound entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.1F, 1.1F, 1.1F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityHellhound entity)
    {
        return ANUBITE_TEXTURES;
    }
    
	public static class Factory implements IRenderFactory<EntityHellhound> 
	{
		@Override
		public Render<? super EntityHellhound> createRenderFor(RenderManager manager) 
		{
			return new RenderHellhound(manager);
		}
	}
}