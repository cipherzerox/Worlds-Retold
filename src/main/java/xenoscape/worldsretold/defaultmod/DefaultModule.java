package xenoscape.worldsretold.defaultmod;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.init.DefaultEntities;
import xenoscape.worldsretold.heatwave.init.HeatwaveEntities;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;

public class DefaultModule {

    public static DefaultModule INSTANCE = new DefaultModule();

    public void preInitDefault(FMLPreInitializationEvent event) {
        DefaultEntities.preInit();
        WorldsRetold.LOGGER.info("Heatwave Module Preinitialized");
    }

    public void initDefault(FMLInitializationEvent event) {
        WorldsRetold.LOGGER.info("Heatwave Module Initialized");
    }

    public void postInitDefault(FMLPostInitializationEvent event) {
        WorldsRetold.LOGGER.info("Heatwave Module Postinitialized");
    }
}
