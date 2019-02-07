package xenoscape.worldsretold.heatwave.init;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormEntity;
import xenoscape.worldsretold.hailstorm.entity.hostile.blizzard.EntityBlizzard;
import xenoscape.worldsretold.hailstorm.entity.passive.penguin.EntityPenguin;
import xenoscape.worldsretold.hailstorm.entity.projectiles.black_arrow.EntityBlackArrow;
import xenoscape.worldsretold.hailstorm.init.HailstormEntities;
import xenoscape.worldsretold.heatwave.config.ConfigHeatwaveEntity;
import xenoscape.worldsretold.heatwave.entity.hostile.antlion.EntityAntlion;
import xenoscape.worldsretold.heatwave.entity.hostile.anubite.EntityAnubite;
import xenoscape.worldsretold.heatwave.entity.hostile.evilcactus.EntityEvilCactus;
import xenoscape.worldsretold.heatwave.entity.hostile.fester.EntityFester;
import xenoscape.worldsretold.heatwave.entity.hostile.mummy.EntityMummy;
import xenoscape.worldsretold.heatwave.entity.neutral.camel.EntityCamel;
import xenoscape.worldsretold.heatwave.entity.neutral.cobra.EntityCobra;
import xenoscape.worldsretold.heatwave.entity.neutral.scorpion.EntityScorpion;
import xenoscape.worldsretold.heatwave.entity.passive.roadrunner.EntityRoadrunner;
import xenoscape.worldsretold.heatwave.entity.projectiles.EntityThrownSand;

import java.util.Set;

public class HeatwaveEntities {
	public static int EntityID = HailstormEntities.EntityID;

	public static void preInit() {
		// Passive
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "roadrunner"), EntityRoadrunner.class,
				"roadrunner", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 5920331, 921101);
		
		// Neutral
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "scorpion"), EntityScorpion.class,
				"scorpion", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 14987315, 6440746);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "cobra"), EntityCobra.class,
				"cobra", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 6569510, 4269587);

		// Hostile
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "antlion"), EntityAntlion.class,
				"antlion", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 14193267, 15323177);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "anubite"), EntityAnubite.class,
				"anubite", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 14193267, 15323177);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "evilcactus"), EntityEvilCactus.class,
				"evilcactus", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 1146656, 606736);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "fester"), EntityFester.class,
				"fester", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 6575187, 3484972);
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "mummy"), EntityMummy.class,
				"mummy", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 12956006, 0);

		// Projectiles
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "thrown_sand"),
				EntityThrownSand.class, "thrown_sand", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
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
		
		final Set<Biome> savannahBiomes = (Set<Biome>) new ObjectArraySet();
		for (final Biome biome : Biome.REGISTRY) {
			final Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
			if (types.contains(BiomeDictionary.Type.SAVANNA) && !types.contains(BiomeDictionary.Type.BEACH)
					&& !types.contains(BiomeDictionary.Type.OCEAN) && !types.contains(BiomeDictionary.Type.RIVER)
					&& !types.contains(BiomeDictionary.Type.NETHER) && !types.contains(BiomeDictionary.Type.END)) {
				savannahBiomes.add(biome);
			}
		}
		
		// Passive
        if (ConfigHeatwaveEntity.isRoadrunnerEnabled) {
			EntityRegistry.addSpawn(EntityRoadrunner.class, 5, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
			EntityRegistry.addSpawn(EntityRoadrunner.class, 1, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) savannahBiomes.toArray(new Biome[savannahBiomes.size()]));
		}

		// Neutral
        if (ConfigHeatwaveEntity.isCobraEnabled) {
			EntityRegistry.addSpawn(EntityCobra.class, 30, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
			EntityRegistry.addSpawn(EntityCobra.class, 10, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) savannahBiomes.toArray(new Biome[savannahBiomes.size()]));
		}
        if (ConfigHeatwaveEntity.isScorpionEnabled) {
			EntityRegistry.addSpawn(EntityScorpion.class, 100, 1, 4, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
			EntityRegistry.addSpawn(EntityScorpion.class, 10, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) savannahBiomes.toArray(new Biome[savannahBiomes.size()]));
			EntityRegistry.removeSpawn(EntitySpider.class, EnumCreatureType.MONSTER, 
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
		}
		// Hostile
        if (ConfigHeatwaveEntity.isAntlionEnabled) {
			EntityRegistry.addSpawn(EntityAntlion.class, 5, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
		}
        if (ConfigHeatwaveEntity.isAnubiteEnabled) {
			EntityRegistry.addSpawn(EntityAnubite.class, 1, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
		}
        if (ConfigHeatwaveEntity.isCactorEnabled) {
			EntityRegistry.addSpawn(EntityEvilCactus.class, 20, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
		}
        if (ConfigHeatwaveEntity.isFesterEnabled) {
			EntityRegistry.addSpawn(EntityFester.class, 80, 1, 4, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
			EntityRegistry.addSpawn(EntitySkeleton.class, 20, 1, 4, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
		}
        if (ConfigHeatwaveEntity.isMummyEnabled) {
			EntityRegistry.addSpawn(EntityMummy.class, 20, 1, 4, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
			EntityRegistry.removeSpawn(EntityZombie.class, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
			EntityRegistry.removeSpawn(EntityZombieVillager.class, EnumCreatureType.MONSTER,
					(Biome[]) desertBiomes.toArray(new Biome[desertBiomes.size()]));
		}
	}
}
