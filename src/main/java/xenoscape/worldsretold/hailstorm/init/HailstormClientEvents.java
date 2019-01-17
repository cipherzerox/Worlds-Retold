package xenoscape.worldsretold.hailstorm.init;

import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.entity.layer.LayerFreezingRenderer.LayerFreezing;
import xenoscape.worldsretold.hailstorm.potion.PotionFreezing;
import xenoscape.worldsretold.defaultmod.util.RenderHelper;

public class HailstormClientEvents {

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
		ResourceLocation danger = new ResourceLocation(WorldsRetold.MODID, "particle/shielded");
		event.getMap().registerSprite(danger);
	}
}