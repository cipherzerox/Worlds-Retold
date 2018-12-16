package xenoform.hailstorm.entity.guardsman;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xenoform.hailstorm.entity.layer.LayerAutomatonGlow;
import xenoform.hailstorm.entity.layer.LayerGuardsmanGlow;

import javax.annotation.Nonnull;

public class RenderGuardsman extends RenderLiving<EntityGuardsman>
{
    private ResourceLocation TEXTURE = new ResourceLocation("hailstorm:textures/entity/guardsman.png");
    private ResourceLocation SHOOTING_TEXTURE = new ResourceLocation("hailstorm:textures/entity/guardsman_charging.png");
	private ResourceLocation GLOW_TEXTURE = new ResourceLocation("hailstorm:textures/entity/guardsman_glow.png");
    
    public static final RenderGuardsman.Factory FACTORY = new RenderGuardsman.Factory();

    public RenderGuardsman(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGuardsman(), 0.55F);
		this.addLayer(new LayerGuardsmanGlow(this, GLOW_TEXTURE));
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull EntityGuardsman entity)
    {
		if (entity.getCharging()) {
			return SHOOTING_TEXTURE;
		} else {
			return TEXTURE;
		}
    }

    @Override
    protected void preRenderCallback(EntityGuardsman entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        GlStateManager.scale(2F, 2F, 2F);
    }

    @Override
    public void doRender(EntityGuardsman entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public static class Factory implements IRenderFactory<EntityGuardsman>
    {
        @Override
        public Render<? super EntityGuardsman> createRenderFor(RenderManager manager) {
            return new RenderGuardsman(manager);
        }
    }
}
