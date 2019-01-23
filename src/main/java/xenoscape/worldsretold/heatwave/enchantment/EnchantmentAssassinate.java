package xenoscape.worldsretold.heatwave.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;


public class EnchantmentAssassinate extends Enchantment {

	public EnchantmentAssassinate(Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.WEAPON, slots);
		this.setRegistryName("assassinate");
		this.setName("assassinate");
	}

	public int getMaxLevel() {
		return 3;
	}

	public int getMinEnchantability(int enchantmentLevel) {
		return 10 * (enchantmentLevel - 1);
	}

	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}

	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity target, int level) 
	{
		if (target instanceof EntityLivingBase)
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(HailstormPotions.FREEZING, 20 * (5 * level), level));
	}

    public boolean canApplyTogether(Enchantment ench)
    {
        return super.canApplyTogether(ench) && ench != Enchantments.FIRE_ASPECT;
    }
}
