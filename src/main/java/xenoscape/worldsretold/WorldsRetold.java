package xenoscape.worldsretold;

import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xenoscape.worldsretold.defaultmod.DefaultModule;
import xenoscape.worldsretold.defaultmod.config.ConfigModules;
import xenoscape.worldsretold.defaultmod.config.WorldsRetoldConfig;
import xenoscape.worldsretold.defaultmod.init.WorldsRetoldKeys;
import xenoscape.worldsretold.hailstorm.HailstormModule;
import xenoscape.worldsretold.heatwave.HeatwaveModule;
import xenoscape.worldsretold.hellfire.HellfireModule;
import xenoscape.worldsretold.defaultmod.proxy.ServerProxy;

@Mod(modid = WorldsRetold.MODID, name = WorldsRetold.NAME, version = WorldsRetold.VERSION, guiFactory = WorldsRetold.CONFIG, updateJSON = WorldsRetold.UPDATE)
public class WorldsRetold {
	public static final String MODID = "worldsretold";
	public static final String NAME = "Worlds Retold";
	public static final String VERSION = "1.0.5";
    public static final String CONFIG = "xenoscape.worldsretold.defaultmod.config.ForgeConfigFactory";
	public static final String UPDATE = "https://raw.githubusercontent.com/Xenoform55/Worlds-Retold/master/src/main/resources/update_changelog.json";
	public static final Logger LOGGER = LogManager.getLogger(" </> " + WorldsRetold.MODID + " </> ");

	@Instance(MODID)
	public static WorldsRetold INSTANCE = new WorldsRetold();
	public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(WorldsRetold.MODID);
	public static final WorldsRetoldKeys KEYS = new WorldsRetoldKeys();

	@SidedProxy(clientSide = "xenoscape.worldsretold.defaultmod.proxy.ClientProxy", serverSide = "xenoscape.worldsretold.defaultmod.proxy.ServerProxy")
	private static ServerProxy proxy;

	public static final DamageSource FROSTBITE = new DamageSource("worldsretold.frostbite").setDamageBypassesArmor();
	public static final DamageSource ROLLER = new DamageSource("worldsretold.roller");
	public static final DamageSource HAIL = new DamageSource("worldsretold.hail");
	public static final DamageSource ICE_SCROLL_PROJECTILE = new DamageSource("worldsretold.ice_scroll_projectile");
	public static final DamageSource VENOM = new DamageSource("worldsretold.venom").setDamageBypassesArmor().setDamageIsAbsolute();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		WorldsRetoldConfig.preInitConfigs(event);
		proxy.preInit(event);
		DefaultModule.INSTANCE.preInitDefault(event);
		if (ConfigModules.isHailstormEnabled) {
			HailstormModule.INSTANCE.preInitHailstorm(event);
		}
		if (ConfigModules.isHeatwaveEnabled) {
			HeatwaveModule.INSTANCE.preInitHeatwave(event);
		}
		if (ConfigModules.isHellfireEnabled) {
			HellfireModule.INSTANCE.preInitHellfire(event);
		}
		WorldsRetold.LOGGER.info(NAME + " Preinitialized");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		DefaultModule.INSTANCE.initDefault(event);
		if (ConfigModules.isHailstormEnabled) {
			HailstormModule.INSTANCE.initHailstorm(event);
		}
		if (ConfigModules.isHeatwaveEnabled) {
			HeatwaveModule.INSTANCE.initHeatwave(event);
		}
		if (ConfigModules.isHellfireEnabled) {
			HellfireModule.INSTANCE.initHellfire(event);
		}
		WorldsRetold.LOGGER.info(NAME + " Initialized");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		DefaultModule.INSTANCE.postInitDefault(event);
		if (ConfigModules.isHailstormEnabled) {
			HailstormModule.INSTANCE.postInitHailstorm(event);
		}
		if (ConfigModules.isHeatwaveEnabled) {
			HeatwaveModule.INSTANCE.postInitHeatwave(event);
		}
		if (ConfigModules.isHellfireEnabled) {
			HellfireModule.INSTANCE.postInitHellfire(event);
		}
		WorldsRetold.LOGGER.info(NAME + " Postinitialized");
	}
}
