package xenoform.hailstorm.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import xenoform.hailstorm.MPotions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public enum FreezingRenderer
{
	INSTANCE;

	private static final ResourceLocation FROZEN_TEXTURE = new ResourceLocation("hailstorm",
			"textures/entity/frozen.png");

	public static class LayerFrozen implements LayerRenderer<EntityLivingBase>
    {
		private final RenderLivingBase<EntityLivingBase> renderer;
		private final Predicate<ModelRenderer> modelExclusions;

		public LayerFrozen(final RenderLivingBase<EntityLivingBase> renderer,
				final Predicate<ModelRenderer> modelExclusions)
        {
			this.renderer = renderer;
			this.modelExclusions = modelExclusions;
		}

		public LayerFrozen(final RenderLivingBase<EntityLivingBase> renderer)
        {
			this(renderer, box -> false);
		}

		public void doRenderLayer(final EntityLivingBase living, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale)
        {
            if (living.isPotionActive(MPotions.FREEZING))
            {
                final ModelBase model = this.renderer.getMainModel();
                final Map<ModelRenderer, Boolean> visibilities = new HashMap<ModelRenderer, Boolean>();
                for (final ModelRenderer box : model.boxList)
                {
                    if (this.modelExclusions.test(box))
                    {
                        visibilities.put(box, box.showModel);
                        box.showModel = false;
                    }
                }
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                this.renderer.bindTexture(FreezingRenderer.FROZEN_TEXTURE);
                GlStateManager.color(1.0f, 1.0f, 1.0f, 0.4f);
                model.render((Entity)living, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                for (final Map.Entry<ModelRenderer, Boolean> entry : visibilities.entrySet())
                {
                    entry.getKey().showModel = entry.getValue();
                }
            }
        }

		public boolean shouldCombineTextures()
        {
			return false;
		}
	}
}