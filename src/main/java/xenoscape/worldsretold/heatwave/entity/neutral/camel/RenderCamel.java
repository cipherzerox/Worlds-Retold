package xenoscape.worldsretold.heatwave.entity.neutral.camel;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelLlama;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xenoscape.worldsretold.hailstorm.entity.layer.LayerBlizzardCloud;

public class RenderCamel extends RenderLiving<EntityCamel> {
	private ResourceLocation TEXTURE = new ResourceLocation("worldsretold:textures/entity/blizzard.png");
	private ResourceLocation DYING_TEXTURE = new ResourceLocation("worldsretold:textures/entity/blizzard_dying.png");
	public static final RenderCamel.Factory FACTORY = new RenderCamel.Factory();

	public RenderCamel(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelChicken(), 0.55F);
	}

	@Override
	@Nonnull
	public ResourceLocation getEntityTexture(@Nonnull EntityCamel entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityCamel entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public static class Factory implements IRenderFactory<EntityCamel> {
		@Override
		public Render<? super EntityCamel> createRenderFor(RenderManager manager) {
			return new RenderCamel(manager);
		}
	}
}
