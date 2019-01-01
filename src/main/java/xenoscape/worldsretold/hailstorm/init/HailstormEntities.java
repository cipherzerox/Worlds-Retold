package xenoscape.worldsretold.hailstorm.init;

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

import java.util.Set;

public class HailstormEntities {
	public static int EntityID = 0;

	public static void preInit() {
		// Passive
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "penguin"), EntityPenguin.class,
				"penguin", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 0x000000, 0xFFFFFF);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "nix"), EntityNix.class, "nix",
				EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 0x00e1ff, 0xffffff);

		// Neutral
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "automaton"), EntityAutomaton.class,
				"automaton", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
	//	EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "sentinel"), EntitySentinel.class,
	//			"sentinel", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);

		// Hostile
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "guardsman"), EntityGuardsman.class,
				"guardsman", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 0x84b3ff, 0x647796);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "blizzard"), EntityBlizzard.class,
				"blizzard", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 0xbff4ff, 0x00d4ff);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "roller"), EntitySnowRoller.class,
				"roller", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 0xffffff, 0xb7b7b7);

		// Projectiles
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "black_arrow"),
				EntityBlackArrow.class, "black_arrow", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "hail"), EntityHail.class, "hail",
				EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "projectile_ice_scroll"),
				EntityHail.class, "projectile_ice_scroll", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "frost_shot"),
				EntityFrostShot.class, "frost_shot", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "penguin_egg"), EntityPenguinEgg.class,
				"penguin_egg", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
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
		if (ConfigHailstormEntity.isPenguinEnabled) {
			EntityRegistry.addSpawn(EntityPenguin.class, 60, 8, 12, EnumCreatureType.CREATURE,
					(Biome[]) snowBiomes.toArray(new Biome[snowBiomes.size()]));
		}
		if (ConfigHailstormEntity.isNixEnabled) {
			EntityRegistry.addSpawn(EntityNix.class, 35, 1, 4, EnumCreatureType.CREATURE,
					(Biome[]) snowBiomes.toArray(new Biome[snowBiomes.size()]));
		}

		// Neutral

		// Hostile
		if (ConfigHailstormEntity.isGuardsmanEnabled) {
			EntityRegistry.addSpawn(EntityGuardsman.class, 15, 1, 1, EnumCreatureType.CREATURE,
					(Biome[]) snowBiomes.toArray(new Biome[snowBiomes.size()]));
		}
		if (ConfigHailstormEntity.isBlizzardEnabled) {
			EntityRegistry.addSpawn(EntityBlizzard.class, 50, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) snowBiomes.toArray(new Biome[snowBiomes.size()]));
		}
		if (ConfigHailstormEntity.isSnowRollerEnabled) {
			EntityRegistry.addSpawn(EntitySnowRoller.class, 85, 1, 4, EnumCreatureType.CREATURE,
					(Biome[]) snowBiomes.toArray(new Biome[snowBiomes.size()]));
		}
	}
}
