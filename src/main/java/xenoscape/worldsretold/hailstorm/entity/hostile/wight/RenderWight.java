package xenoscape.worldsretold.hailstorm.entity.hostile.wight;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.hailstorm.entity.hostile.guardsman.EntityGuardsman;
import xenoscape.worldsretold.hailstorm.entity.hostile.guardsman.RenderGuardsman;

@SideOnly(Side.CLIENT)
public class RenderWight extends RenderBiped<EntityWight>
{
    private static final ResourceLocation WIGHT_TEXTURE = new ResourceLocation("worldsretold:textures/entity/wight.png");
	public static final RenderWight.Factory FACTORY = new RenderWight.Factory();
	
    public RenderWight(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelZombie(), 0.5F);
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        });
    }

    @Override
    protected void preRenderCallback(EntityWight entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        GlStateManager.scale(1.15F, 1.15F, 1.15F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityWight entity)
    {
        return WIGHT_TEXTURE;
    }
    
	public static class Factory implements IRenderFactory<EntityWight> {
		@Override
		public Render<? super EntityWight> createRenderFor(RenderManager manager) {
			return new RenderWight(manager);
		}
	}
}