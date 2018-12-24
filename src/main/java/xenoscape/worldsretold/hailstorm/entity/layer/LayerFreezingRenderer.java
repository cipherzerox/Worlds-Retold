package xenoscape.worldsretold.hailstorm.entity.layer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import xenoscape.worldsretold.hailstorm.potion.PotionFreezing;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public enum LayerFreezingRenderer
{
	INSTANCE;

	private static final ResourceLocation FREEZING_TEXTURE = new ResourceLocation("worldsretold",
			"textures/entity/freezing.png");

	public static class LayerFreezing implements LayerRenderer<EntityLivingBase>
    {
		private final RenderLivingBase<EntityLivingBase> renderer;
		private final Predicate<ModelRenderer> modelExclusions;

		public LayerFreezing(final RenderLivingBase<EntityLivingBase> renderer,
				final Predicate<ModelRenderer> modelExclusions)
        {
			this.renderer = renderer;
			this.modelExclusions = modelExclusions;
		}

		public LayerFreezing(final RenderLivingBase<EntityLivingBase> renderer)
        {
			this(renderer, box -> false);
		}

		public void doRenderLayer(final EntityLivingBase living, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale)
        {
            if (living.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(PotionFreezing.MODIFIER_UUID) != null)
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
                this.renderer.bindTexture(LayerFreezingRenderer.FREEZING_TEXTURE);
                GlStateManager.color(1.0f, 1.0f, 1.0f, 0.33f);
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