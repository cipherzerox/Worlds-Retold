package xenoform.hailstorm.enchantment;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.Mod;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.MPotions;

public class EnchantmentStormCaller extends Enchantment {

	public EnchantmentStormCaller(Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.BOW, slots);
		this.setRegistryName("stormCaller");
		this.setName("stormCaller");
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

	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.INFINITY;
	}
}