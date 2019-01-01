package xenoscape.worldsretold.heatwave.init;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormEntity;
import xenoscape.worldsretold.hailstorm.entity.hostile.blizzard.EntityBlizzard;
import xenoscape.worldsretold.hailstorm.entity.hostile.guardsman.EntityGuardsman;
import xenoscape.worldsretold.hailstorm.entity.hostile.roller.EntitySnowRoller;
import xenoscape.worldsretold.hailstorm.entity.neutral.automaton.EntityAutomaton;
import xenoscape.worldsretold.hailstorm.entity.passive.nix.EntityNix;
import xenoscape.worldsretold.hailstorm.entity.passive.penguin.EntityPenguin;
import xenoscape.worldsretold.hailstorm.entity.projectiles.black_arrow.EntityBlackArrow;
import xenoscape.worldsretold.hailstorm.entity.projectiles.egg.EntityPenguinEgg;
import xenoscape.worldsretold.hailstorm.entity.projectiles.frost_shot.EntityFrostShot;
import xenoscape.worldsretold.hailstorm.entity.projectiles.hail.EntityHail;
import xenoscape.worldsretold.hailstorm.init.HailstormEntities;
import xenoscape.worldsretold.heatwave.entity.neutral.camel.EntityCamel;

import java.util.Set;

public class HeatwaveEntities {
	public static int EntityID = HailstormEntities.EntityID;

	public static void preInit() {
		// Passive

		// Neutral
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "camel"), EntityCamel.class,
				"camel", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 0, 0);

		// Hostile

		// Projectiles
	}
}
