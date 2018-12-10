package xenoform.hailstorm.main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xenoform.hailstorm.Hailstorm;

public class MSounds {

	public static final SoundEvent FREEZING;

	@SubscribeEvent
	public static void registerSounds(final RegistryEvent.Register<SoundEvent> evt) {

		evt.getRegistry().register(MSounds.FREEZING);
	}

	private static SoundEvent createEvent(final String soundName) {
		final ResourceLocation soundID = new ResourceLocation(Hailstorm.MODID, soundName);
		return new SoundEvent(soundID).setRegistryName(soundID);
	}

	static {
		FREEZING = createEvent("freezing");
	}
}
