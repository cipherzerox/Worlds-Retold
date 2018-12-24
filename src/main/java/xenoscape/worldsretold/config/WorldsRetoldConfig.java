package xenoscape.worldsretold.config;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormEntity;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormWorldGen;

import java.io.File;

public class WorldsRetoldConfig {
	
	public static ConfigModules modules;

	public static void preInitConfigs(FMLPreInitializationEvent event) {
		File worldsRetoldFolder = new File(event.getModConfigurationDirectory().getAbsolutePath(), "worldsretold");
		modules = new ConfigModules(new File(worldsRetoldFolder, "modules.cfg"));
	}

}
