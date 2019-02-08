package xenoscape.worldsretold.heatwave.init;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import xenoscape.worldsretold.heatwave.potion.PotionVenom;

public class HeatwavePotions {

	public static final Potion VENOM = new PotionVenom("venom", true, 569088, 1, 0)
			.registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED,
					PotionVenom.MODIFIER_UUID.toString(), -0.25D, 1);

	public static final PotionType NORMAL_VENOM = new PotionType("venom",
			new PotionEffect[] { new PotionEffect(VENOM, 900) }).setRegistryName("venom");
	public static final PotionType LONG_VENOM = new PotionType("venom",
			new PotionEffect[] { new PotionEffect(VENOM, 1800) }).setRegistryName("long_venom");
	public static final PotionType STRONG_VENOM = new PotionType("venom",
			new PotionEffect[] { new PotionEffect(VENOM, 450, 1) }).setRegistryName("strong_venom");
	
	public static void registerPotions() {
		registerPotion(NORMAL_VENOM, LONG_VENOM, STRONG_VENOM, VENOM);
		registerPotionRecipes();
	}

	private static void registerPotion(PotionType defaultPotion, PotionType longPotion, PotionType strongPotion, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defaultPotion);
		ForgeRegistries.POTION_TYPES.register(longPotion);
		ForgeRegistries.POTION_TYPES.register(strongPotion);
	}

	private static void registerPotionRecipes() {
		PotionHelper.addMix(PotionTypes.WATER, HeatwaveItems.SCORPION_BULB, PotionTypes.THICK);
		PotionHelper.addMix(PotionTypes.AWKWARD, HeatwaveItems.SCORPION_BULB, NORMAL_VENOM);
		PotionHelper.addMix(NORMAL_VENOM, Items.REDSTONE, LONG_VENOM);
		PotionHelper.addMix(NORMAL_VENOM, Items.GLOWSTONE_DUST, STRONG_VENOM);
		PotionHelper.addMix(NORMAL_VENOM, Items.FERMENTED_SPIDER_EYE, PotionTypes.REGENERATION);
	}
}