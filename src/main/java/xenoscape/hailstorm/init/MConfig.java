package xenoscape.hailstorm.init;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoscape.hailstorm.config.ConfigEntity;

import java.io.File;

public class MConfig {

	public static ConfigEntity entity;

	public static void preInitConfigs(FMLPreInitializationEvent event) {
		File configFolder = new File(event.getModConfigurationDirectory().getAbsolutePath(), "hailstorm");
		entity = new ConfigEntity(new File(configFolder, "entity.cfg"));
	}

}
