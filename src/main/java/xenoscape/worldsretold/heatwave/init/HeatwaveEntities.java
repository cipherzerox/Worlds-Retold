package xenoscape.worldsretold.heatwave.init;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormEntity;
import xenoscape.worldsretold.hailstorm.entity.hostile.blizzard.EntityBlizzard;
import xenoscape.worldsretold.hailstorm.entity.passive.penguin.EntityPenguin;
import xenoscape.worldsretold.hailstorm.init.HailstormEntities;
import xenoscape.worldsretold.heatwave.entity.hostile.mummy.EntityMummy;
import xenoscape.worldsretold.heatwave.entity.neutral.camel.EntityCamel;
import xenoscape.worldsretold.heatwave.entity.neutral.scorpion.EntityScorpion;

import java.util.Set;

public class HeatwaveEntities {
	public static int EntityID = HailstormEntities.EntityID;

	public static void preInit() {
		// Passive

		// Neutral
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "camel"), EntityCamel.class,
				"camel", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 0, 0);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "scorpion"), EntityScorpion.class,
				"scorpion", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 0, 0);

		// Hostile
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "mummy"), EntityMummy.class,
				"mummy", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 0, 0);

		// Projectiles
	}
	
	public static void init() {
		final Set<Biome> desertBiomes = (Set<Biome>) new ObjectArraySet();
		for (final Biome biome : Biome.REGISTRY) {
			final Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
			if (types.contains(BiomeDictionary.Type.SANDY) && !types.contains(BiomeDictionary.Type.BEACH)
					&& !types.contains(BiomeDictionary.Type.OCEAN) && !types.contains(BiomeDictionary.Type.RIVER)
					&& !types.contains(BiomeDictionary.Type.NETHER) && !types.contains(BiomeDictionary.Type.END)) {
				desertBiomes.add(biome);
			}
		}
		
		// Passive

		// Neutral
		if (ConfigHailstormEntity.isPenguinEnabled) {
			EntityRegistry.addSpawn(EntityCamel.class, 20, 2, 4, EnumCreatureType.CREATURE,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
		}
		if (ConfigHailstormEntity.isBlizzardEnabled) {
			EntityRegistry.addSpawn(EntityScorpion.class, 100, 1, 4, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
			EntityRegistry.removeSpawn(EntitySpider.class, EnumCreatureType.MONSTER, 
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
		}
		// Hostile
	}
}
