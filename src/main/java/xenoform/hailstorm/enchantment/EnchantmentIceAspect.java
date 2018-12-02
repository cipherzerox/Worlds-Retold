package xenoform.hailstorm.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.Mod;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.MPotions;

public class EnchantmentIceAspect extends Enchantment {

	public EnchantmentIceAspect(Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.WEAPON, slots);
		this.setRegistryName("iceAspect");
		this.setName("iceAspect");
	}

	public int getMaxLevel() {
		return 2;
	}

	public int getMinEnchantability(int enchantmentLevel) {
		return 10 + 20 * (enchantmentLevel - 1);
	}

	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}

	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
		if (level == 2) {
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(MPotions.FREEZING, 400, 0));
		} else {
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(MPotions.FREEZING, 200, 0));
		}
	}
}