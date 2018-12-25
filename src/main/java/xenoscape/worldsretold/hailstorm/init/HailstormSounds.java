package xenoscape.worldsretold.hailstorm.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.config.ConfigModules;

public class HailstormSounds {

	public static final SoundEvent FREEZING = createEvent("freezing");;

	@SubscribeEvent
	public static void registerSounds(final RegistryEvent.Register<SoundEvent> evt) {

		if (ConfigModules.isHailstormEnabled == true) {
			evt.getRegistry().register(HailstormSounds.FREEZING);
		}
	}

	private static SoundEvent createEvent(final String soundName) {
		final ResourceLocation soundID = new ResourceLocation(WorldsRetold.MODID, soundName);
		return new SoundEvent(soundID).setRegistryName(soundID);
	}
}
