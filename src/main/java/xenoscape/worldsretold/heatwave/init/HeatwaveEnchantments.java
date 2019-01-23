package xenoscape.worldsretold.heatwave.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.config.ConfigModules;
import xenoscape.worldsretold.heatwave.enchantment.EnchantmentAssassinate;
import xenoscape.worldsretold.heatwave.enchantment.EnchantmentToxinProtection;
import xenoscape.worldsretold.heatwave.enchantment.EnchantmentVenomTippedArrows;

@Mod.EventBusSubscriber(modid = WorldsRetold.MODID)
public class HeatwaveEnchantments {

	public static final Enchantment ASSASSINATE = new EnchantmentAssassinate(Enchantment.Rarity.RARE,
			EntityEquipmentSlot.MAINHAND);
	public static final Enchantment VENOM_TIPS = new EnchantmentVenomTippedArrows(Enchantment.Rarity.RARE,
			EntityEquipmentSlot.MAINHAND);
	public static final Enchantment TOXIC_PROTECTION = new EnchantmentToxinProtection(Enchantment.Rarity.UNCOMMON,
			EntityEquipmentSlot.MAINHAND);

	@SubscribeEvent
	public static void registerEnchantments(Register<Enchantment> event) {
		if (ConfigModules.isHeatwaveEnabled == true) {
			event.getRegistry().registerAll(TOXIC_PROTECTION, ASSASSINATE, VENOM_TIPS);
		}
	}

}