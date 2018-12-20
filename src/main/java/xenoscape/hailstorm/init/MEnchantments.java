package xenoscape.hailstorm.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xenoscape.hailstorm.enchantment.EnchantmentLightningRod;
import xenoscape.hailstorm.Hailstorm;
import xenoscape.hailstorm.enchantment.EnchantmentGlacier;
import xenoscape.hailstorm.enchantment.EnchantmentIceboundBlade;

@Mod.EventBusSubscriber(modid = Hailstorm.MODID)
public class MEnchantments {

	public static final Enchantment ICEBOUND_BLADE = new EnchantmentIceboundBlade(Enchantment.Rarity.RARE,
			new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
	public static final Enchantment GLACIER = new EnchantmentGlacier(Enchantment.Rarity.RARE,
			new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
	public static final Enchantment LIGHTNING_ROD = new EnchantmentLightningRod(Enchantment.Rarity.VERY_RARE,
			new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });

	@SubscribeEvent
	public static void registerEnchantments(Register<Enchantment> event) {
		event.getRegistry().registerAll(ICEBOUND_BLADE, GLACIER, LIGHTNING_ROD);
	}

}