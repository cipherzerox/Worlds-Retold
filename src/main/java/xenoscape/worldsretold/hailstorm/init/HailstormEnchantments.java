package xenoscape.worldsretold.hailstorm.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.config.ConfigModules;
import xenoscape.worldsretold.hailstorm.enchantment.EnchantmentGlacier;
import xenoscape.worldsretold.hailstorm.enchantment.EnchantmentIceboundBlade;
import xenoscape.worldsretold.hailstorm.enchantment.EnchantmentLightningRod;

@Mod.EventBusSubscriber(modid = WorldsRetold.MODID)
public class HailstormEnchantments {

	public static final Enchantment ICEBOUND_BLADE = new EnchantmentIceboundBlade(Enchantment.Rarity.RARE,
			EntityEquipmentSlot.MAINHAND);
	public static final Enchantment GLACIER = new EnchantmentGlacier(Enchantment.Rarity.RARE,
			EntityEquipmentSlot.MAINHAND);
	public static final Enchantment LIGHTNING_ROD = new EnchantmentLightningRod(Enchantment.Rarity.RARE,
			EntityEquipmentSlot.MAINHAND);

	@SubscribeEvent
	public static void registerEnchantments(Register<Enchantment> event) {
		if (ConfigModules.isHailstormEnabled == true) {
			event.getRegistry().registerAll(ICEBOUND_BLADE, GLACIER, LIGHTNING_ROD);
		}
	}

}