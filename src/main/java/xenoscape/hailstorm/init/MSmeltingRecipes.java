package xenoscape.hailstorm.init;

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
import xenoscape.hailstorm.entity.projectiles.frost_shot.EntityFrostShot;
import xenoscape.hailstorm.entity.projectiles.hail.EntityHail;

public class MSmeltingRecipes {

	public static void init() {
		GameRegistry.addSmelting(new ItemStack(MItems.MANCHOT_RAW), new ItemStack(MItems.MANCHOT_COOKED), 10);
		GameRegistry.addSmelting(MBlocks.CRYONITE_ORE, new ItemStack(MItems.CRYONITE), 10);
	}
}
