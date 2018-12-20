package xenoscape.hailstorm.init;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import xenoscape.hailstorm.potions.PotionFreezing;

public class MPotions {

	public static final Potion FREEZING = new PotionFreezing("freezing", true, 13035007, 0, 0)
			.registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED,
					PotionFreezing.MODIFIER_UUID.toString(), -0.25D, 1);

	public static final PotionType NORMAL_FREEZING = new PotionType("freezing",
			new PotionEffect[] { new PotionEffect(FREEZING, 700) }).setRegistryName("freezing");
	public static final PotionType LONG_FREEZING = new PotionType("freezing",
			new PotionEffect[] { new PotionEffect(FREEZING, 1400) }).setRegistryName("long_freezing");

	public static void registerPotions() {
		registerPotion(NORMAL_FREEZING, LONG_FREEZING, FREEZING);
		registerPotionRecipes();
	}

	private static void registerPotion(PotionType defaultPotion, PotionType longPotion, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defaultPotion);
		ForgeRegistries.POTION_TYPES.register(longPotion);
	}

	private static void registerPotionRecipes() {
		PotionHelper.addMix(PotionTypes.AWKWARD, MItems.CRYONITE, NORMAL_FREEZING);
		PotionHelper.addMix(NORMAL_FREEZING, Items.REDSTONE, LONG_FREEZING);
	}
}