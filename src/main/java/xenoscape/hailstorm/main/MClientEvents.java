package xenoscape.hailstorm.main;

import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.hailstorm.Hailstorm;
import xenoscape.hailstorm.entity.layer.LayerFreezingRenderer.LayerFreezing;
import xenoscape.hailstorm.potions.PotionFreezing;
import xenoscape.hailstorm.util.RenderHelper;

public class MClientEvents {

	private static float partialTick;

	public static float getPartialTick() {
		return partialTick;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPreRenderFrozenEntity(final RenderLivingEvent.Pre event) {
		final EntityLivingBase player = event.getEntity();
		if (player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
				.getModifier(PotionFreezing.MODIFIER_UUID) != null
				&& !RenderHelper.doesRendererHaveLayer(event.getRenderer(), LayerFreezing.class, false)) {
			event.getRenderer().addLayer(new LayerFreezing(event.getRenderer()));
		}
	}

	@SubscribeEvent
	public void stitcherEventPre(TextureStitchEvent.Pre event) {
		ResourceLocation danger = new ResourceLocation(Hailstorm.MODID, "particle/shielded");
		event.getMap().registerSprite(danger);
	}

}