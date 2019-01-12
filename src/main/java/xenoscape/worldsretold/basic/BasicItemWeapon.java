package xenoscape.worldsretold.basic;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.WorldsRetoldTabs;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;
import xenoscape.worldsretold.util.ModelRegistry;


public class BasicItemWeapon extends ItemSword implements ModelRegistry {

	private final Item.ToolMaterial material;
	protected String name;
	protected final double damage;
	protected final double speed;
	protected final int effect;

	public BasicItemWeapon(String name, Item.ToolMaterial material, double damage, double speed,
						   int effect) {
		super(material);
		this.name = name;
		this.material = material;
		this.damage = damage;
		this.speed = speed;
		this.effect = effect;
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses());
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(WorldsRetoldTabs.W_TAB);
	}

	@Override
	public BasicItemWeapon setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
					new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", damage, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
					new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -speed, 0));
		}

		return multimap;
	}

	@Override
	public int getItemEnchantability() {
		return this.material.getEnchantability();
	}

	@Override
	public String getToolMaterialName() {
		return this.material.toString();
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		ItemStack mat = this.material.getRepairItemStack();
		if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false))
			return true;
		return super.getIsRepairable(toRepair, repair);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		if (this.effect == 1) {
			target.addPotionEffect(new PotionEffect(HailstormPotions.FREEZING, 100, 0));
		}
		if (!((EntityPlayer) attacker).getCooldownTracker().hasCooldown(this) && this.effect == 2) {
			target.addPotionEffect(new PotionEffect(HailstormPotions.FREEZING, 100));
			attacker.world.createExplosion(target, target.posX, target.posY + 1, target.posZ, 1.0F, true);
			((EntityPlayer) attacker).getCooldownTracker().setCooldown(this, 140);
		}
		if (this.effect == 3) {
			target.addPotionEffect(new PotionEffect(HeatwavePotions.VENOM, 100, 0));
		}
		return true;
	}
}
