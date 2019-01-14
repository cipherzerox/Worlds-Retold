package xenoscape.worldsretold.hailstorm.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;


public class EnchantmentIceboundBlade extends Enchantment {

	public EnchantmentIceboundBlade(Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.WEAPON, slots);
		this.setRegistryName("icebound_blade");
		this.setName("icebound_blade");
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
		if (target instanceof EntityLivingBase) {
			if (level == 2) {
				((EntityLivingBase) target).addPotionEffect(new PotionEffect(HailstormPotions.FREEZING, 400, 0));
			} else {
				((EntityLivingBase) target).addPotionEffect(new PotionEffect(HailstormPotions.FREEZING, 200, 0));
			}
		}
	}

    public boolean canApplyTogether(Enchantment ench)
    {
        return super.canApplyTogether(ench) && ench != Enchantments.FIRE_ASPECT;
    }
}
