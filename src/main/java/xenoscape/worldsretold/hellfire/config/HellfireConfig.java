package xenoscape.worldsretold.hellfire.config;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormEntity;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormWorldGen;

import java.io.File;

public class HellfireConfig {

    public static ConfigHellfireEntity entity;

    public static void preInitConfigs(FMLPreInitializationEvent event) {
        File worldsRetoldFolder = new File(event.getModConfigurationDirectory().getAbsolutePath(), "worldsretold");
        File heatwaveFolder = new File(worldsRetoldFolder, "hellfire");
        entity = new ConfigHellfireEntity(new File(heatwaveFolder, "entity.cfg"));
    }

}
