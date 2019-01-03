package xenoscape.worldsretold;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xenoscape.worldsretold.config.ConfigModules;
import xenoscape.worldsretold.config.WorldsRetoldConfig;
import xenoscape.worldsretold.hailstorm.HailstormModule;
import xenoscape.worldsretold.heatwave.HeatwaveModule;
import xenoscape.worldsretold.proxy.ServerProxy;

@Mod(modid = WorldsRetold.MODID, name = WorldsRetold.NAME, version = WorldsRetold.VERSION, guiFactory = WorldsRetold.CONFIG, updateJSON = WorldsRetold.UPDATE)
public class WorldsRetold {
	public static final String MODID = "worldsretold";
	public static final String NAME = "Worlds Retold";
	public static final String VERSION = "1.0.3";
	public static final String CONFIG = "xenoscape.worldsretold.config.ForgeConfigFactory";
	public static final String UPDATE = "https://raw.githubusercontent.com/Xenoform55/Worlds-Retold/master/src/main/resources/update_changelog.json";
	public static final Logger LOGGER = LogManager.getLogger(" </> " + WorldsRetold.MODID + " </> ");

	@Instance(MODID)
	public static WorldsRetold INSTANCE = new WorldsRetold();

	@SidedProxy(clientSide = "xenoscape.worldsretold.proxy.ClientProxy", serverSide = "xenoscape.worldsretold.proxy.ServerProxy")
	private static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		WorldsRetoldConfig.preInitConfigs(event);
		proxy.preInit(event);
		if (ConfigModules.isHailstormEnabled) {
			HailstormModule.INSTANCE.preInitHailstorm(event);
		}
		if (ConfigModules.isHeatwaveEnabled) {
			HeatwaveModule.INSTANCE.preInitHeatwave(event);
		}
		WorldsRetold.LOGGER.info(NAME + " Preinitialized");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		if (ConfigModules.isHailstormEnabled) {
			HailstormModule.INSTANCE.initHailstorm(event);
		}
		if (ConfigModules.isHeatwaveEnabled) {
			HeatwaveModule.INSTANCE.initHeatwave(event);
		}
		WorldsRetold.LOGGER.info(NAME + " Initialized");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		if (ConfigModules.isHailstormEnabled) {
			HailstormModule.INSTANCE.postInitHailstorm(event);
		}
		if (ConfigModules.isHeatwaveEnabled) {
			HeatwaveModule.INSTANCE.postInitHeatwave(event);
		}
		WorldsRetold.LOGGER.info(NAME + " Postinitialized");
	}
}
