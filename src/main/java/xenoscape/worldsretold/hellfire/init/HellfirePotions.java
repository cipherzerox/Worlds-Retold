package xenoscape.worldsretold.hellfire.init;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import xenoscape.worldsretold.heatwave.potion.PotionVenom;

public class HellfirePotions {

	public static final Potion HELLFIRE = new PotionVenom("hellfire", true, 16745472, 2, 0);

	public static final PotionType NORMAL_HELLFIRE = new PotionType("venom",
			new PotionEffect[] { new PotionEffect(HELLFIRE, 900) }).setRegistryName("venom");
	public static final PotionType LONG_HELLFIRE = new PotionType("venom",
			new PotionEffect[] { new PotionEffect(HELLFIRE, 1800) }).setRegistryName("long_venom");
	public static final PotionType STRONG_HELLFIRE = new PotionType("venom",
			new PotionEffect[] { new PotionEffect(HELLFIRE, 450, 1) }).setRegistryName("strong_venom");
	
	public static void registerPotions() {
		registerPotion(NORMAL_HELLFIRE, LONG_HELLFIRE, STRONG_HELLFIRE, HELLFIRE);
		registerPotionRecipes();
	}

	private static void registerPotion(PotionType defaultPotion, PotionType longPotion, PotionType strongPotion, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defaultPotion);
		ForgeRegistries.POTION_TYPES.register(longPotion);
		ForgeRegistries.POTION_TYPES.register(strongPotion);
	}

	private static void registerPotionRecipes() {
		PotionHelper.addMix(PotionTypes.WATER, HellfireItems.HELLFIRE_SCORPION_BULB, PotionTypes.STRONG_HARMING);
		PotionHelper.addMix(PotionTypes.AWKWARD, HellfireItems.HELLFIRE_SCORPION_BULB, NORMAL_HELLFIRE);
		PotionHelper.addMix(NORMAL_HELLFIRE, Items.REDSTONE, LONG_HELLFIRE);
		PotionHelper.addMix(NORMAL_HELLFIRE, Items.GLOWSTONE_DUST, STRONG_HELLFIRE);
		PotionHelper.addMix(NORMAL_HELLFIRE, Items.FERMENTED_SPIDER_EYE, PotionTypes.FIRE_RESISTANCE);
	}
}