package xenoscape.worldsretold.defaultmod.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.config.ConfigModules;
import xenoscape.worldsretold.defaultmod.enchantment.EnchantmentLightningRod;
import xenoscape.worldsretold.hailstorm.enchantment.EnchantmentGlacier;
import xenoscape.worldsretold.hailstorm.enchantment.EnchantmentIceboundBlade;

@Mod.EventBusSubscriber(modid = WorldsRetold.MODID)
public class DefaultEnchantments {

	public static final Enchantment LIGHTNING_ROD = new EnchantmentLightningRod(Enchantment.Rarity.RARE,
			EntityEquipmentSlot.MAINHAND);

	@SubscribeEvent
	public static void registerEnchantments(Register<Enchantment> event) {
			event.getRegistry().registerAll(LIGHTNING_ROD);
	}

}