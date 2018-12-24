package xenoscape.worldsretold.hailstorm.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xenoscape.worldsretold.WorldsRetold;

public class HailstormSounds {

	public static final SoundEvent FREEZING;

	@SubscribeEvent
	public static void registerSounds(final RegistryEvent.Register<SoundEvent> evt) {

		evt.getRegistry().register(HailstormSounds.FREEZING);
	}

	private static SoundEvent createEvent(final String soundName) {
		final ResourceLocation soundID = new ResourceLocation(WorldsRetold.MODID, soundName);
		return new SoundEvent(soundID).setRegistryName(soundID);
	}

	static {
		FREEZING = createEvent("freezing");
	}
}
