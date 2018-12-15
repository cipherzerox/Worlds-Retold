package xenoform.hailstorm.main;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.entity.automaton.EntityAutomaton;
import xenoform.hailstorm.entity.blizzard.EntityBlizzard;
import xenoform.hailstorm.entity.blizzard.hail.EntityHail;
import xenoform.hailstorm.entity.guardsman.EntityIceGuardsman;
import xenoform.hailstorm.entity.nix.EntityNix;
import xenoform.hailstorm.entity.penguin.EntityPenguin;
import xenoform.hailstorm.entity.projectiles.egg.EntityPenguinEgg;
import xenoform.hailstorm.entity.roller.EntitySnowRoller;

public class MEntities {
	private static int EntityID = 0;
	private static Biome[] COLD_BIOMES = new Biome[] { Biomes.ICE_PLAINS, Biomes.ICE_MOUNTAINS, Biomes.COLD_TAIGA,
			Biomes.COLD_TAIGA_HILLS, Biomes.MUTATED_TAIGA_COLD, Biomes.MUTATED_ICE_FLATS };

	public static void preInit() {
		//Animals
	    EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "penguin"), EntityPenguin.class,
				"penguin", EntityID++, Hailstorm.instance, 64, 3, true, 0x000000, 0xFFFFFF);
		//Mobs
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "roller"), EntitySnowRoller.class,
				"roller", EntityID++, Hailstorm.instance, 64, 3, true, 0xffffff, 0xb7b7b7);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "nix"), EntityNix.class, "nix",
				EntityID++, Hailstorm.instance, 64, 3, true, 0x00e1ff, 0xffffff);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "blizzard"), EntityBlizzard.class,
				"blizzard", EntityID++, Hailstorm.instance, 64, 3, true, 0xbff4ff, 0x00d4ff);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "automaton"), EntityAutomaton.class,
				"automaton", EntityID++, Hailstorm.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "ice_guardsman"), EntityIceGuardsman.class,
                "ice_guardsman", EntityID++, Hailstorm.instance, 64, 3, true, 0x84b3ff, 0x647796);
        //Projectiles
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "hail"), EntityHail.class, "hail",
				EntityID++, Hailstorm.instance, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "projectile_ice_scroll"),
				EntityHail.class, "projectile_ice_scroll", EntityID++, Hailstorm.instance, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "penguin_egg"),
				EntityPenguinEgg.class, "penguin_egg", EntityID++, Hailstorm.instance, 64, 3, true);
	}
	
	public static void init() {
		EntityRegistry.addSpawn(EntityPenguin.class, 70, 8, 16, EnumCreatureType.CREATURE, COLD_BIOMES);
		
		EntityRegistry.addSpawn(EntitySnowRoller.class, 100, 1, 2, EnumCreatureType.CREATURE, COLD_BIOMES);
		EntityRegistry.addSpawn(EntityNix.class, 20, 1, 4, EnumCreatureType.CREATURE, COLD_BIOMES);
		EntityRegistry.addSpawn(EntityBlizzard.class, 55, 1, 1, EnumCreatureType.CREATURE, COLD_BIOMES);
	}
}
