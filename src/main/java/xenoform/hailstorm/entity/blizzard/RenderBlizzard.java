package xenoform.hailstorm.entity.blizzard;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xenoform.hailstorm.entity.guardsman.EntityGuardsman;
import xenoform.hailstorm.entity.layer.LayerBlizzardCloud;
import xenoform.hailstorm.entity.layer.LayerGuardsmanGlow;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

public class RenderBlizzard extends RenderLiving<EntityBlizzard> {
	private ResourceLocation TEXTURE = new ResourceLocation("hailstorm:textures/entity/blizzard.png");
	private ResourceLocation DYING_TEXTURE = new ResourceLocation("hailstorm:textures/entity/blizzard_dying.png");
	public static final RenderBlizzard.Factory FACTORY = new RenderBlizzard.Factory();

	public RenderBlizzard(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelBlizzard(), 0.55F);
		this.addLayer(new LayerBlizzardCloud(this));
	}

	@Override
	@Nonnull
	public ResourceLocation getEntityTexture(@Nonnull EntityBlizzard entity) {
		return TEXTURE;
	}

	@Override
	protected void preRenderCallback(EntityBlizzard entitylivingbaseIn, float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
		GlStateManager.scale(3F, 3F, 3F);
		if (entitylivingbaseIn.deathTicks > 0) {
			float colorTimer = 1.0F - ((entitylivingbaseIn.deathTicks / 100.0F) * 1.5F);
			GlStateManager.color(colorTimer, colorTimer, colorTimer, 1.0F);
		}
	}

	@Override
	public void doRender(EntityBlizzard entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	protected void renderModel(EntityBlizzard entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		if (entitylivingbaseIn.deathTicks > 0) {
			float f = (float) entitylivingbaseIn.deathTicks / 100.0F;
			GlStateManager.depthFunc(GL11.GL_LEQUAL);
			GlStateManager.enableAlpha();
			GlStateManager.alphaFunc(GL11.GL_GREATER, f);
			this.bindTexture(DYING_TEXTURE);
			this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
					scaleFactor);
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
			GlStateManager.depthFunc(GL11.GL_EQUAL);
		}

		this.bindEntityTexture(entitylivingbaseIn);
		this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
				scaleFactor);
		GlStateManager.depthFunc(GL11.GL_LEQUAL);
	}

	public static class Factory implements IRenderFactory<EntityBlizzard> {
		@Override
		public Render<? super EntityBlizzard> createRenderFor(RenderManager manager) {
			return new RenderBlizzard(manager);
		}
	}
}
