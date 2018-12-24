package xenoscape.worldsretold;

import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import xenoscape.worldsretold.config.ConfigModules;
import xenoscape.worldsretold.config.WorldsRetoldConfig;
import xenoscape.worldsretold.hailstorm.HailstormModule;
import xenoscape.worldsretold.hailstorm.config.HailstormConfig;
import xenoscape.worldsretold.hailstorm.init.HailstormClientEvents;
import xenoscape.worldsretold.hailstorm.init.HailstormEntities;
import xenoscape.worldsretold.hailstorm.init.HailstormItems;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;
import xenoscape.worldsretold.hailstorm.init.HailstormSmeltingRecipes;
import xenoscape.worldsretold.hailstorm.init.HailstormVanillaLootInsertion;
import xenoscape.worldsretold.hailstorm.init.HailstormVillagerTrades;
import xenoscape.worldsretold.hailstorm.world.WorldGenHailstorm;
import xenoscape.worldsretold.proxy.ServerProxy;

@Mod(modid = WorldsRetold.MODID, name = WorldsRetold.NAME, version = WorldsRetold.VERSION, guiFactory = WorldsRetold.CONFIG)
public class WorldsRetold {
	public static final String MODID = "worldsretold";
	public static final String NAME = "Worlds Retold";
	public static final String VERSION = "1.0.0";
	public static final String CONFIG = "xenoscape.worldsretold.config.ForgeConfigFactory";
	public static final Logger LOGGER = LogManager.getLogger(" </> " + WorldsRetold.MODID + " </> ");

	@Instance(MODID)
	public static WorldsRetold INSTANCE = new WorldsRetold();

	@SidedProxy(clientSide = "xenoscape.worldsretold.proxy.ClientProxy", serverSide = "xenoscape.worldsretold.proxy.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		WorldsRetoldConfig.preInitConfigs(event);
		if (ConfigModules.isHailstormEnabled == true) {
			HailstormModule.INSTANCE.preInitHailstorm(event);
		}
		WorldsRetold.LOGGER.info(NAME + " Preinitialization Done");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		if (ConfigModules.isHailstormEnabled == true) {
			HailstormModule.INSTANCE.initHailstorm(event);
		}
		WorldsRetold.LOGGER.info(NAME + " Initialization Done");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		if (ConfigModules.isHailstormEnabled == true) {
			HailstormModule.INSTANCE.postInitHailstorm(event);
		}
		WorldsRetold.LOGGER.info(NAME + " Postinitialization Done");
	}
}
