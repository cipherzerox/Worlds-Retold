package xenoform.hailstorm;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xenoform.hailstorm.enchantment.EnchantmentArrowIce;
import xenoform.hailstorm.enchantment.EnchantmentIceAspect;
import xenoform.hailstorm.enchantment.EnchantmentStormCaller;

@Mod.EventBusSubscriber(modid = Hailstorm.MODID)
public class MEnchantments {

	public static final Enchantment ICE_ASPECT = new EnchantmentIceAspect(Enchantment.Rarity.RARE,
			new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
	public static final Enchantment ARROW_ICE = new EnchantmentArrowIce(Enchantment.Rarity.RARE,
			new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
	public static final Enchantment STORM_CALLER = new EnchantmentStormCaller(Enchantment.Rarity.VERY_RARE,
			new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });

	@SubscribeEvent
	public static void registerEnchantments(Register<Enchantment> event) {
		event.getRegistry().registerAll(ICE_ASPECT, ARROW_ICE, STORM_CALLER);
	}

}