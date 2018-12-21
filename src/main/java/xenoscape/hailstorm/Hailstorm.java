package xenoscape.hailstorm;

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
import xenoscape.hailstorm.main.*;
import xenoscape.hailstorm.proxy.ServerProxy;
import xenoscape.hailstorm.world.WorldGenHailstorm;

@Mod(modid = Hailstorm.MODID, name = Hailstorm.NAME, version = Hailstorm.VERSION)
public class Hailstorm {
	public static final String MODID = "hailstorm";
	public static final String NAME = "Hailstorm";
	public static final String VERSION = "B-1";
	public static final Logger LOGGER = LogManager.getLogger(" </> " + Hailstorm.MODID + " </> ");
	
	@Instance(MODID)
	public static Hailstorm instance = new Hailstorm();

	@SidedProxy(clientSide = "xenoscape.hailstorm.proxy.ClientProxy", serverSide = "xenoscape.hailstorm.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	public static final DamageSource FROSTBITE = new DamageSource("hailstorm.frostbite").setDamageBypassesArmor();
    public static final DamageSource ROLLER = new DamageSource("hailstorm.roller");
    public static final DamageSource HAIL = new DamageSource("hailstorm.hail");
    public static final DamageSource ICE_SCROLL_PROJECTILE = new DamageSource("hailstorm.ice_scroll_projectile");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		MPotions.registerPotions();
		MEntities.preInit();
		MEntities.init();
		MinecraftForge.EVENT_BUS.register(new MClientEvents());
		MinecraftForge.EVENT_BUS.register(new MVanillaLootInsertion());
		Hailstorm.LOGGER.info("Preinitialization Done");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		GameRegistry.registerWorldGenerator(new WorldGenHailstorm(), 0);
		MVillagerTrades.registerTrades();
		Hailstorm.LOGGER.info("Initialization Done");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		Hailstorm.LOGGER.info("Postinitialization Done");
	}
}