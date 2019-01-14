package xenoscape.worldsretold.heatwave.config;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormEntity;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormWorldGen;

import java.io.File;

public class HeatwaveConfig {

    public static ConfigHailstormEntity entity;
    public static ConfigHailstormWorldGen world_gen;

    public static void preInitConfigs(FMLPreInitializationEvent event) {
        File worldsRetoldFolder = new File(event.getModConfigurationDirectory().getAbsolutePath(), "worldsretold");
        File hailstormFolder = new File(worldsRetoldFolder, "hailstorm");
        entity = new ConfigHailstormEntity(new File(hailstormFolder, "entity.cfg"));
        world_gen = new ConfigHailstormWorldGen(new File(hailstormFolder, "world_gen.cfg"));
    }

}
