package xenoscape.worldsretold.hailstorm.init;

import java.util.Set;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.config.ConfigHailstormEntity;
import xenoscape.worldsretold.hailstorm.entity.hostile.blizzard.EntityBlizzard;
import xenoscape.worldsretold.hailstorm.entity.hostile.guardsman.EntityGuardsman;
import xenoscape.worldsretold.hailstorm.entity.hostile.roller.EntitySnowRoller;
import xenoscape.worldsretold.hailstorm.entity.neutral.automaton.EntityAutomaton;
import xenoscape.worldsretold.hailstorm.entity.neutral.sentinel.EntitySentinel;
import xenoscape.worldsretold.hailstorm.entity.passive.nix.EntityNix;
import xenoscape.worldsretold.hailstorm.entity.passive.penguin.EntityPenguin;
import xenoscape.worldsretold.hailstorm.entity.projectiles.egg.EntityPenguinEgg;
import xenoscape.worldsretold.hailstorm.entity.projectiles.frost_shot.EntityFrostShot;
import xenoscape.worldsretold.hailstorm.entity.projectiles.hail.EntityHail;

public class HailstormSmeltingRecipes {

	public static void init() {
		GameRegistry.addSmelting(new ItemStack(HailstormItems.MANCHOT_RAW), new ItemStack(HailstormItems.MANCHOT_COOKED), 10);
		GameRegistry.addSmelting(HailstormBlocks.CRYONITE_ORE, new ItemStack(HailstormItems.CRYONITE), 10);
	}
}
