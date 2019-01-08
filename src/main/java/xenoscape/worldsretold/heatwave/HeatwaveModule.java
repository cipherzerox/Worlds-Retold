package xenoscape.worldsretold.heatwave;

import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.config.HailstormConfig;
import xenoscape.worldsretold.hailstorm.init.*;
import xenoscape.worldsretold.hailstorm.world.WorldGenHailstorm;
import xenoscape.worldsretold.heatwave.init.HeatwaveEntities;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;

public class HeatwaveModule {

	public static HeatwaveModule INSTANCE = new HeatwaveModule();

	public void preInitHeatwave(FMLPreInitializationEvent event) {
		HeatwaveEntities.preInit();
		HeatwavePotions.registerPotions();
		WorldsRetold.LOGGER.info("Heatwave Module Preinitialized");
	}

	public void initHeatwave(FMLInitializationEvent event) {
		HeatwaveEntities.init();
		WorldsRetold.LOGGER.info("Heatwave Module Initialized");
	}

	public void postInitHeatwave(FMLPostInitializationEvent event) {
		WorldsRetold.LOGGER.info("Heatwave Module Postinitialized");
	}
}
