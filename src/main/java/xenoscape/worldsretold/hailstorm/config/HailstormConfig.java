package xenoscape.worldsretold.hailstorm.config;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class HailstormConfig {

	public static ConfigHailstormEntity entity;
	public static ConfigHailstormWorldGen world_gen;

	public static void preInitConfigs(FMLPreInitializationEvent event) {
		File worldsRetoldFolder = new File(event.getModConfigurationDirectory().getAbsolutePath(), "worldsretold");
		File hailstormFolder = new File(worldsRetoldFolder, "hailstorm");
		entity = new ConfigHailstormEntity(new File(hailstormFolder, "entity.cfg"));
		world_gen = new ConfigHailstormWorldGen(new File(hailstormFolder, "world_gen.cfg"));
	}

}
