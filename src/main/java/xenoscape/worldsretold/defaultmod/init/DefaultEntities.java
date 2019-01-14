package xenoscape.worldsretold.defaultmod.init;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.entity.neutral.automaton.EntityAutomaton;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormEntity;
import xenoscape.worldsretold.hailstorm.entity.hostile.blizzard.EntityBlizzard;
import xenoscape.worldsretold.hailstorm.entity.hostile.guardsman.EntityGuardsman;
import xenoscape.worldsretold.hailstorm.entity.hostile.roller.EntitySnowRoller;
import xenoscape.worldsretold.hailstorm.entity.passive.caribou.EntityCaribou;
import xenoscape.worldsretold.hailstorm.entity.passive.nix.EntityNix;
import xenoscape.worldsretold.hailstorm.entity.passive.penguin.EntityPenguin;
import xenoscape.worldsretold.hailstorm.entity.projectiles.black_arrow.EntityBlackArrow;
import xenoscape.worldsretold.hailstorm.entity.projectiles.egg.EntityPenguinEgg;
import xenoscape.worldsretold.hailstorm.entity.projectiles.frost_shot.EntityFrostShot;
import xenoscape.worldsretold.hailstorm.entity.projectiles.hail.EntityHail;

import java.util.Set;

public class DefaultEntities {
    public static int EntityID = 0;

    public static void preInit() {

        // Neutral
        EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "automaton"), EntityAutomaton.class,
                "automaton", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
        //	EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "sentinel"), EntitySentinel.class,
        //			"sentinel", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
    }
}
