package xenoscape.hailstorm.init;

import java.util.Set;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xenoscape.hailstorm.entity.hostile.roller.EntitySnowRoller;
import xenoscape.hailstorm.Hailstorm;
import xenoscape.hailstorm.config.ConfigEntity;
import xenoscape.hailstorm.entity.hostile.blizzard.EntityBlizzard;
import xenoscape.hailstorm.entity.hostile.guardsman.EntityGuardsman;
import xenoscape.hailstorm.entity.neutral.automaton.EntityAutomaton;
import xenoscape.hailstorm.entity.neutral.sentinel.EntitySentinel;
import xenoscape.hailstorm.entity.passive.nix.EntityNix;
import xenoscape.hailstorm.entity.passive.penguin.EntityPenguin;
import xenoscape.hailstorm.entity.projectiles.egg.EntityPenguinEgg;
import xenoscape.hailstorm.entity.projectiles.hail.EntityHail;

public class MEntities {
	private static int EntityID = 0;

	public static void preInit() {
		// Passive
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "penguin"), EntityPenguin.class,
				"penguin", EntityID++, Hailstorm.instance, 64, 3, true, 0x000000, 0xFFFFFF);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "nix"), EntityNix.class, "nix",
				EntityID++, Hailstorm.instance, 64, 3, true, 0x00e1ff, 0xffffff);

		// Neutral
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "automaton"), EntityAutomaton.class,
				"automaton", EntityID++, Hailstorm.instance, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "sentinel"), EntitySentinel.class,
				"sentinel", EntityID++, Hailstorm.instance, 64, 3, true, 0x84b3ff, 0x647796);

		// Hostile
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "guardsman"), EntityGuardsman.class,
				"guardsman", EntityID++, Hailstorm.instance, 64, 3, true, 0x84b3ff, 0x647796);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "blizzard"), EntityBlizzard.class,
				"blizzard", EntityID++, Hailstorm.instance, 64, 3, true, 0xbff4ff, 0x00d4ff);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "roller"), EntitySnowRoller.class,
				"roller", EntityID++, Hailstorm.instance, 64, 3, true, 0xffffff, 0xb7b7b7);

		// Projectiles
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "hail"), EntityHail.class, "hail",
				EntityID++, Hailstorm.instance, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "projectile_ice_scroll"),
				EntityHail.class, "projectile_ice_scroll", EntityID++, Hailstorm.instance, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "penguin_egg"), EntityPenguinEgg.class,
				"penguin_egg", EntityID++, Hailstorm.instance, 64, 3, true);
	}

	public static void init() {
		final Set<Biome> snowBiomes = (Set<Biome>) new ObjectArraySet();
		for (final Biome biome : Biome.REGISTRY) {
			final Set<BiomeDictionary.Type> types = (Set<BiomeDictionary.Type>) BiomeDictionary.getTypes(biome);
			if (types.contains(BiomeDictionary.Type.SNOWY) && !types.contains(BiomeDictionary.Type.BEACH)
					&& !types.contains(BiomeDictionary.Type.OCEAN) && !types.contains(BiomeDictionary.Type.RIVER)
					&& !types.contains(BiomeDictionary.Type.NETHER) && !types.contains(BiomeDictionary.Type.END)) {
				snowBiomes.add(biome);
			}
		}

		// Passive
		if (ConfigEntity.isPenguinEnabled) {
			EntityRegistry.addSpawn(EntityPenguin.class, 60, 8, 12, EnumCreatureType.CREATURE,
					(Biome[]) snowBiomes.toArray(new Biome[snowBiomes.size()]));
		}
		if (ConfigEntity.isNixEnabled) {
			EntityRegistry.addSpawn(EntityNix.class, 35, 1, 4, EnumCreatureType.CREATURE,
					(Biome[]) snowBiomes.toArray(new Biome[snowBiomes.size()]));
		}

		// Neutral

		// Hostile
		if (ConfigEntity.isGuardsmanEnabled) {
			EntityRegistry.addSpawn(EntityGuardsman.class, 15, 1, 1, EnumCreatureType.CREATURE,
					(Biome[]) snowBiomes.toArray(new Biome[snowBiomes.size()]));
		}
		if (ConfigEntity.isBlizzardEnabled) {
			EntityRegistry.addSpawn(EntityBlizzard.class, 50, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) snowBiomes.toArray(new Biome[snowBiomes.size()]));
		}
		if (ConfigEntity.isSnowRollerEnabled) {
			EntityRegistry.addSpawn(EntitySnowRoller.class, 85, 1, 4, EnumCreatureType.CREATURE,
					(Biome[]) snowBiomes.toArray(new Biome[snowBiomes.size()]));
		}
	}
}
