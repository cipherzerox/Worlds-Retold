package xenoscape.hailstorm.entity.layer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import xenoscape.hailstorm.entity.automaton.EntityAutomaton;

public class LayerAutomatonGlow<T extends EntityLivingBase> implements LayerRenderer<T> {
	public final RenderLivingBase<T> renderer;
	public final ResourceLocation glowTexture;
	private float alpha;

	public LayerAutomatonGlow(final RenderLivingBase<T> renderer, final ResourceLocation glowTexture) {
		this.alpha = 0.7f;
		this.renderer = renderer;
		this.glowTexture = glowTexture;
	}

	public void doRenderLayer(final T entity, final float limbSwing, final float limbSwingAmount,
			final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch,
			final float scale) {
		EntityAutomaton ent = (EntityAutomaton) entity;
		if (entity instanceof EntityAutomaton && ent.isActive()) {
			this.renderer.bindTexture(this.glowTexture);
			final ModelBase mainModel = this.renderer.getMainModel();
			final float alpha = this.getAlpha();
			GlStateManager.disableLighting();
			GlStateManager.enableBlend();
			GlStateManager.enableAlpha();
			GlStateManager.depthMask(!entity.isInvisible());
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA,
					GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0f, 1.0f, 1.0f, alpha);
			mainModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
			mainModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
			mainModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.color(alpha, alpha, alpha, alpha);
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			final int i = 61680;
			final int j = i % 65536;
			final int k = i / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
			mainModel.setLivingAnimations(entity, limbSwingAmount, ageInTicks, partialTicks);
			mainModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
			mainModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			this.setLightmap(entity, partialTicks);
			GlStateManager.depthMask(true);
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
		}
	}

	public boolean shouldCombineTextures() {
		return false;
	}

	protected void setLightmap(final T entityLivingIn, final float partialTicks) {
		final int i = entityLivingIn.getBrightnessForRender();
		final int j = i % 65536;
		final int k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
	}

	public LayerAutomatonGlow<T> setAlpha(final float alpha) {
		this.alpha = alpha;
		return this;
	}

	public float getAlpha() {
		return this.alpha;
	}
}