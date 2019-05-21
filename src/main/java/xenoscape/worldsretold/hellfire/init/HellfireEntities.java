package xenoscape.worldsretold.hellfire.init;

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
import xenoscape.worldsretold.hailstorm.init.HailstormEntities;
import xenoscape.worldsretold.heatwave.config.ConfigHeatwaveEntity;
import xenoscape.worldsretold.heatwave.entity.hostile.antlion.EntityAntlion;
import xenoscape.worldsretold.heatwave.entity.hostile.anubite.EntityAnubite;
import xenoscape.worldsretold.heatwave.entity.hostile.evilcactus.EntityEvilCactus;
import xenoscape.worldsretold.heatwave.entity.hostile.fester.EntityFester;
import xenoscape.worldsretold.heatwave.entity.hostile.mummy.EntityMummy;
import xenoscape.worldsretold.heatwave.entity.neutral.cobra.EntityCobra;
import xenoscape.worldsretold.heatwave.entity.neutral.scorpion.EntityScorpion;
import xenoscape.worldsretold.heatwave.entity.passive.roadrunner.EntityRoadrunner;
import xenoscape.worldsretold.heatwave.entity.projectiles.EntityThrownSand;
import xenoscape.worldsretold.hellfire.config.ConfigHellfireEntity;
import xenoscape.worldsretold.hellfire.entity.hostile.livingflame.EntityLivingFlame;

import java.util.Set;

public class HellfireEntities {
	public static int EntityID = HailstormEntities.EntityID;

	public static void preInit() {
		// Passive
		
		// Neutral

		// Hostile
		EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "livingflame"), EntityLivingFlame.class,
				"livingflame", EntityID++, WorldsRetold.INSTANCE, 64, 3, true, 16711680, 16734720);

		// Projectiles
	}
	
	public static void init() {
		final Set<Biome> hellBiomes = (Set<Biome>) new ObjectArraySet();
		for (final Biome biome : Biome.REGISTRY)
			if (BiomeDictionary.getTypes(biome).contains(BiomeDictionary.Type.NETHER))
				hellBiomes.add(biome);
			
		// Passive

		// Neutral
		
		// Hostile
        if (ConfigHellfireEntity.isLivingFlameEnabled) {
			EntityRegistry.addSpawn(EntityLivingFlame.class, 10, 1, 1, EnumCreatureType.MONSTER,
					(Biome[]) hellBiomes.toArray(new Biome[hellBiomes.size()]));
		}
	}
}
