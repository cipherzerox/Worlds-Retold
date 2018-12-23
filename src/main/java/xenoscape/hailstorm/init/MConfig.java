package xenoscape.hailstorm.init;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoscape.hailstorm.config.ConfigEntity;
import xenoscape.hailstorm.config.ConfigWorldGen;

import java.io.File;

public class MConfig {

	public static ConfigEntity entity;
	public static ConfigWorldGen world_gen;

	public static void preInitConfigs(FMLPreInitializationEvent event) {
		File configFolder = new File(event.getModConfigurationDirectory().getAbsolutePath(), "hailstorm");
		entity = new ConfigEntity(new File(configFolder, "entity.cfg"));
		world_gen = new ConfigWorldGen(new File(configFolder, "world_gen.cfg"));
	}

}
