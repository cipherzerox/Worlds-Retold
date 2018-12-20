package xenoscape.hailstorm.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
<<<<<<< HEAD:src/main/java/xenoform/hailstorm/enchantment/EnchantmentGlacier.java
import xenoform.hailstorm.init.MPotions;
=======
import xenoscape.hailstorm.main.MPotions;
>>>>>>> 2f34effc0ad705294dd03b18a13038d17d9b8376:src/main/java/xenoscape/hailstorm/enchantment/EnchantmentGlacier.java

public class EnchantmentGlacier extends Enchantment {

	public EnchantmentGlacier(Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.BOW, slots);
		this.setRegistryName("glacier");
		this.setName("glacier");
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
	
    public boolean canApplyTogether(Enchantment ench)
    {
        return super.canApplyTogether(ench) && ench != Enchantments.FLAME;
    }
}