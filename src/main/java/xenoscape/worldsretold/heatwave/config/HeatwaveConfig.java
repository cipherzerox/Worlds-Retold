package xenoscape.worldsretold.heatwave.config;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormEntity;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormWorldGen;

import java.io.File;

public class HeatwaveConfig {

    public static ConfigHeatwaveEntity entity;

    public static void preInitConfigs(FMLPreInitializationEvent event) {
        File worldsRetoldFolder = new File(event.getModConfigurationDirectory().getAbsolutePath(), "worldsretold");
        File heatwaveFolder = new File(worldsRetoldFolder, "heatwave");
        entity = new ConfigHeatwaveEntity(new File(heatwaveFolder, "entity.cfg"));
    }

}
