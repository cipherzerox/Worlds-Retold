package xenoscape.worldsretold.heatwave.entity.hostile.fester;

import net.minecraft.client.model.ModelSkeleton;
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
import xenoscape.worldsretold.heatwave.entity.hostile.mummy.EntityMummy;
import xenoscape.worldsretold.heatwave.entity.hostile.mummy.RenderMummy;

@SideOnly(Side.CLIENT)
public class RenderFester extends RenderBiped<AbstractSkeleton>
{
    private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("worldsretold:textures/entity/fester.png");
	public static final RenderFester.Factory FACTORY = new RenderFester.Factory();
	
    public RenderFester(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSkeleton(), 0.5F);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelSkeleton(0.5F, true);
                this.modelArmor = new ModelSkeleton(1.0F, true);
            }
        });
    }

    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(AbstractSkeleton entity)
    {
        return SKELETON_TEXTURES;
    }
    
	public static class Factory implements IRenderFactory<AbstractSkeleton> 
	{
		@Override
		public Render<? super AbstractSkeleton> createRenderFor(RenderManager manager) 
		{
			return new RenderFester(manager);
		}
	}
}