package xenoform.hailstorm.util;

import java.lang.reflect.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.*;
import java.util.*;
import net.minecraftforge.fml.relauncher.*;

public class RenderHelper {
	private static Field fieldLayerRenderers = ReflectionHelper.findField((Class) RenderLivingBase.class,
			new String[] { "layerRenderers", "field_177097_h", "i" });

	public static <T extends LayerRenderer<?>> T getRenderLayer(final RenderLivingBase<?> renderer, final Class<T> cls,
			final boolean subclasses) {
		try {
			final List<LayerRenderer<?>> layers = (List<LayerRenderer<?>>) RenderHelper.fieldLayerRenderers
					.get(renderer);
			for (final LayerRenderer<?> layer : layers) {
				if (subclasses) {
					if (cls.isInstance(layer)) {
						return (T) layer;
					}
					continue;
				} else {
					if (cls == layer.getClass()) {
						return (T) layer;
					}
					continue;
				}
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return null;
	}

	public static boolean doesRendererHaveLayer(final RenderLivingBase<?> renderer,
			final Class<? extends LayerRenderer<?>> cls, final boolean subclasses) {
		return getRenderLayer(renderer, cls, subclasses) != null;
	}
}
