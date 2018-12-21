package xenoscape.hailstorm.main;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xenoscape.hailstorm.Hailstorm;
import xenoscape.hailstorm.entity.automaton.EntityAutomaton;
import xenoscape.hailstorm.entity.blizzard.EntityBlizzard;
import xenoscape.hailstorm.entity.blizzard.hail.EntityHail;
import xenoscape.hailstorm.entity.guardsman.EntityGuardsman;
import xenoscape.hailstorm.entity.nix.EntityNix;
import xenoscape.hailstorm.entity.penguin.EntityPenguin;
import xenoscape.hailstorm.entity.projectiles.egg.EntityPenguinEgg;
import xenoscape.hailstorm.entity.roller.EntitySnowRoller;
import xenoscape.hailstorm.entity.sentinel.EntitySentinel;

public class MEntities {
	private static int EntityID = 0;
	private static Biome[] COLD_BIOMES = new Biome[] { Biomes.ICE_PLAINS, Biomes.ICE_MOUNTAINS, Biomes.COLD_TAIGA,
			Biomes.COLD_TAIGA_HILLS, Biomes.MUTATED_TAIGA_COLD, Biomes.MUTATED_ICE_FLATS };

	public static void preInit() {
		//Animals
	    EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "penguin"), EntityPenguin.class,
				"penguin", EntityID++, Hailstorm.instance, 64, 3, true, 0x000000, 0xFFFFFF);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "nix"), EntityNix.class, "nix",
				EntityID++, Hailstorm.instance, 64, 3, true, 0x00e1ff, 0xffffff);
		//Monsters
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "roller"), EntitySnowRoller.class,
				"roller", EntityID++, Hailstorm.instance, 64, 3, true, 0xffffff, 0xb7b7b7);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "blizzard"), EntityBlizzard.class,
				"blizzard", EntityID++, Hailstorm.instance, 64, 3, true, 0xbff4ff, 0x00d4ff);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "automaton"), EntityAutomaton.class,
				"automaton", EntityID++, Hailstorm.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "guardsman"), EntityGuardsman.class,
                "guardsman", EntityID++, Hailstorm.instance, 64, 3, true, 0x84b3ff, 0x647796);
        EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "sentinel"), EntitySentinel.class,
                "sentinel", EntityID++, Hailstorm.instance, 64, 3, true, 0x647796, 0x8b9099);
        //Projectiles
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "hail"), EntityHail.class, "hail",
				EntityID++, Hailstorm.instance, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "projectile_ice_scroll"),
				EntityHail.class, "projectile_ice_scroll", EntityID++, Hailstorm.instance, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "penguin_egg"),
				EntityPenguinEgg.class, "penguin_egg", EntityID++, Hailstorm.instance, 64, 3, true);
	}

	public static void init() {
		EntityRegistry.addSpawn(EntityPenguin.class, 60, 8, 12, EnumCreatureType.CREATURE, COLD_BIOMES);
		EntityRegistry.addSpawn(EntityNix.class, 35, 1, 4, EnumCreatureType.CREATURE, COLD_BIOMES);

		EntityRegistry.addSpawn(EntitySnowRoller.class, 85, 1, 4, EnumCreatureType.CREATURE, COLD_BIOMES);
		EntityRegistry.addSpawn(EntityBlizzard.class, 50, 1, 1, EnumCreatureType.MONSTER, COLD_BIOMES);
		EntityRegistry.addSpawn(EntityGuardsman.class, 15, 1, 1, EnumCreatureType.CREATURE, COLD_BIOMES);
	}
}