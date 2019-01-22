package xenoscape.worldsretold.defaultmod.config;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class WorldsRetoldConfig {
	
	public static ConfigModules modules;
    public static ConfigDefaultEntity entity;
	public static ConfigDefaultMisc misc;

	public static void preInitConfigs(FMLPreInitializationEvent event) {
		File worldsRetoldFolder = new File(event.getModConfigurationDirectory().getAbsolutePath(), "worldsretold");
		modules = new ConfigModules(new File(worldsRetoldFolder, "modules.cfg"));
        File defaultFolder = new File(worldsRetoldFolder, "default");
        entity = new ConfigDefaultEntity(new File(defaultFolder, "entity.cfg"));
		misc = new ConfigDefaultMisc(new File(defaultFolder, "misc.cfg"));
	}

}
