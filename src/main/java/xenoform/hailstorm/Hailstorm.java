package xenoform.hailstorm;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoform.hailstorm.proxy.ServerProxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Hailstorm.MODID, name = Hailstorm.NAME, version = Hailstorm.VERSION)
public class Hailstorm {
	public static final String MODID = "hailstorm";
	public static final String NAME = "Hailstorm";
	public static final String VERSION = "0.1.0";

	public static final Logger LOGGER = LogManager.getLogger(" </> " + Hailstorm.MODID + " </> ");

	@SidedProxy(clientSide = "xenoform.hailstorm.proxy.ClientProxy", serverSide = "xenoform.hailstorm.proxy.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		Hailstorm.LOGGER.info("Preinitialization Done");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		Hailstorm.LOGGER.info("Initialization Done");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		Hailstorm.LOGGER.info("Postinitialization Done");
	}
}