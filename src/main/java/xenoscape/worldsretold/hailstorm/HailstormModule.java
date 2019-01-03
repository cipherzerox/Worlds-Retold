package xenoscape.worldsretold.hailstorm;

import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.config.HailstormConfig;
import xenoscape.worldsretold.hailstorm.init.*;
import xenoscape.worldsretold.hailstorm.world.WorldGenHailstorm;

public class HailstormModule {

	public static HailstormModule INSTANCE = new HailstormModule();

	public static final DamageSource FROSTBITE = new DamageSource("worldsretold.frostbite").setDamageBypassesArmor();
	public static final DamageSource ROLLER = new DamageSource("worldsretold.roller");
	public static final DamageSource HAIL = new DamageSource("worldsretold.hail");
	public static final DamageSource ICE_SCROLL_PROJECTILE = new DamageSource("worldsretold.ice_scroll_projectile");

	public void preInitHailstorm(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new HailstormVanillaLootInsertion());
		HailstormEntities.preInit();
		HailstormConfig.preInitConfigs(event);
		HailstormPotions.registerPotions();
		WorldsRetold.LOGGER.info("Hailstorm Module Preinitialized");
	}

	public void initHailstorm(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new WorldGenHailstorm());
		HailstormEntities.init();
		HailstormSmeltingRecipes.init();
		HailstormVillagerTrades.registerTrades();
		WorldsRetold.LOGGER.info("Hailstorm Module Initialized");
	}

	public void postInitHailstorm(FMLPostInitializationEvent event) {
		WorldsRetold.LOGGER.info("Hailstorm Module Postinitialized");
	}
}
