package xenoscape.worldsretold.heatwave.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;


public class EnchantmentToxinProtection extends Enchantment {

	public EnchantmentToxinProtection(Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.ARMOR, slots);
		this.setRegistryName("glacier");
		this.setName("glacier");
	}

	public int getMaxLevel() 
	{
		return 4;
	}

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 5 + (enchantmentLevel - 1) * 8;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + 8;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench)
    {
        if (ench instanceof EnchantmentProtection)
        {
            EnchantmentProtection enchantmentprotection = (EnchantmentProtection)ench;

            return enchantmentprotection.protectionType == EnchantmentProtection.Type.FALL;
        }
        else
        {
            return super.canApplyTogether(ench);
        }
    }
    
    /**
     * Calculates the damage protection of the enchantment based on level and damage source passed.
     */
    public int calcModifierDamage(int level, DamageSource source)
    {
        if (source.canHarmInCreative())
        {
            return 0;
        }
        else if (source == DamageSource.MAGIC || source == WorldsRetold.VENOM)
        {
            return level * 2;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Return the name of key in translation table of this enchantment.
     */
    public String getName()
    {
        return "enchantment.protect.toxin";
    }
}
