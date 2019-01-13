package xenoscape.worldsretold.defaultmod.config;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class WorldsRetoldConfig {
	
	public static ConfigModules modules;

	public static void preInitConfigs(FMLPreInitializationEvent event) {
		File worldsRetoldFolder = new File(event.getModConfigurationDirectory().getAbsolutePath(), "worldsretold");
		modules = new ConfigModules(new File(worldsRetoldFolder, "modules.cfg"));
	}

}
