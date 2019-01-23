package xenoscape.worldsretold.heatwave.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;


public class EnchantmentVenomTippedArrows extends Enchantment {

	public EnchantmentVenomTippedArrows(Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.BOW, slots);
		this.setRegistryName("venomtips");
		this.setName("venomtips");
	}

	public int getMaxLevel() {
		return 2;
	}

	public int getMinEnchantability(int enchantmentLevel) {
		return 15 * enchantmentLevel;
	}

	public int getMaxEnchantability(int enchantmentLevel) {
		return getMinEnchantability(enchantmentLevel) + 30;
	}

	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
		if (target instanceof EntityLivingBase) {
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(HeatwavePotions.VENOM, 20 * (5 * level), level));
		}
	}

    public boolean canApplyTogether(Enchantment ench)
    {
        return super.canApplyTogether(ench) && ench != Enchantments.FLAME;
    }
}
