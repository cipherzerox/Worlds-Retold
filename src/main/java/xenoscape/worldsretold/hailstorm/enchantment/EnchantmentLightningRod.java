package xenoscape.worldsretold.hailstorm.enchantment;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentLightningRod extends Enchantment {

	public EnchantmentLightningRod(Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.BOW, slots);
		this.setRegistryName("lightning_rod");
		this.setName("lightning_rod");
	}

	public int getMaxLevel() {
		return 1;
	}

	public int getMinEnchantability(int enchantmentLevel) {
		return 30;
	}

	public int getMaxEnchantability(int enchantmentLevel) {
		return 60;
	}

	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
		if (target instanceof EntityLivingBase) {
			if ((user.world.isThundering() || user.world.isRaining()) && (target.world.canSeeSky(target.getPosition()))) {
				IBlockState iblockstate = user.world.getBlockState(target.getPosition());
				if (!(iblockstate.getMaterial() == Material.WATER)
						&& (!(iblockstate.getBlock() == net.minecraft.init.Blocks.WATER)
						|| !(iblockstate.getBlock() == net.minecraft.init.Blocks.FLOWING_WATER))) {
					user.world.addWeatherEffect(
							new EntityLightningBolt(user.world, target.posX, target.posY, target.posZ, false));
				}
			}
		}
	}

	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.INFINITY && ench != Enchantments.POWER;
	}
}