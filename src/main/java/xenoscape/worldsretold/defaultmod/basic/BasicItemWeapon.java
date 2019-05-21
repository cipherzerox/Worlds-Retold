package xenoscape.worldsretold.defaultmod.basic;

import com.google.common.collect.Multimap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import xenoscape.worldsretold.defaultmod.WorldsRetoldTabs;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;
import xenoscape.worldsretold.hellfire.init.HellfirePotions;
import xenoscape.worldsretold.defaultmod.util.ModelRegistry;


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
		if (this.effect == 4) {
			target.addPotionEffect(new PotionEffect(HellfirePotions.HELLFIRE, 200, 0));
		}
		return true;
	}

	@Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		if (this.effect == 4 && entityLiving instanceof EntityPlayer && ((EntityPlayer)entityLiving).getCooledAttackStrength(0.0F) >= 1F) 
		{
			EntityPlayer playerIn = (EntityPlayer)entityLiving;
	        if (!entityLiving.world.isRemote)
	        {
	            EntitySnowball entitysnowball = new EntitySnowball(entityLiving.world, playerIn);
	            entitysnowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
	            entityLiving.world.spawnEntity(entitysnowball);
	        }
			return true;
		}
		else
        return false;
    }
}
