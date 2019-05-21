package xenoscape.worldsretold.hellfire;

import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hellfire.config.HellfireConfig;
import xenoscape.worldsretold.hellfire.init.HellfireEntities;
import xenoscape.worldsretold.hellfire.init.HellfirePotions;

public class HellfireModule {

	public static HellfireModule INSTANCE = new HellfireModule();

	public void preInitHellfire(FMLPreInitializationEvent event) {
		HellfireEntities.preInit();
		HellfireConfig.preInitConfigs(event);
		HellfirePotions.registerPotions();
		WorldsRetold.LOGGER.info("Hellfire Module Preinitialized");
	}

	public void initHellfire(FMLInitializationEvent event) {
		HellfireEntities.init();
		WorldsRetold.LOGGER.info("Hellfire Module Initialized");
	}

	public void postInitHellfire(FMLPostInitializationEvent event) {
		WorldsRetold.LOGGER.info("Hellfire Module Postinitialized");
	}
}
