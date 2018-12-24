package xenoscape.worldsretold.hailstorm;

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

import xenoscape.worldsretold.WorldsRetold;
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

public class HailstormModule {

	public static HailstormModule INSTANCE = new HailstormModule();

	public static final DamageSource FROSTBITE = new DamageSource("worldsretold.frostbite").setDamageBypassesArmor();
	public static final DamageSource ROLLER = new DamageSource("worldsretold.roller");
	public static final DamageSource HAIL = new DamageSource("worldsretold.hail");
	public static final DamageSource ICE_SCROLL_PROJECTILE = new DamageSource("worldsretold.ice_scroll_projectile");

	public void preInitHailstorm(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new HailstormClientEvents());
		MinecraftForge.EVENT_BUS.register(new HailstormVanillaLootInsertion());
		HailstormEntities.preInit();
		HailstormConfig.preInitConfigs(event);
		HailstormPotions.registerPotions();
		WorldsRetold.LOGGER.info("Hailstorm Preinitialization Done");
	}

	public void initHailstorm(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new WorldGenHailstorm());
		HailstormEntities.init();
		HailstormSmeltingRecipes.init();
		HailstormVillagerTrades.registerTrades();
		WorldsRetold.LOGGER.info("Hailstorm Initialization Done");
	}

	public void postInitHailstorm(FMLPostInitializationEvent event) {
		WorldsRetold.LOGGER.info("Hailstorm Postinitialization Done");
	}
}
