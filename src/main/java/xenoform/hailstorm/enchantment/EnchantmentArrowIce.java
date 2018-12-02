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

public class EnchantmentArrowIce extends Enchantment {

	public EnchantmentArrowIce(Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.BOW, slots);
		this.setRegistryName("arrowIce");
		this.setName("arrowIce");
	}

	public int getMaxLevel() {
		return 1;
	}

	public int getMinEnchantability(int enchantmentLevel) {
		return 20;
	}

	public int getMaxEnchantability(int enchantmentLevel) {
		return 50;
	}

	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
		((EntityLivingBase) target).addPotionEffect(new PotionEffect(MPotions.FREEZING, 300, 0));
	}
}