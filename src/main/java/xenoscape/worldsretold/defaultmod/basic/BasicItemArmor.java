package xenoscape.worldsretold.defaultmod.basic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.defaultmod.WorldsRetoldTabs;
import xenoscape.worldsretold.defaultmod.util.ModelRegistry;
import xenoscape.worldsretold.hailstorm.init.HailstormItems;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;

import java.util.List;

public class BasicItemArmor extends ItemArmor implements ModelRegistry {

	protected final String name;
	private final ItemArmor.ArmorMaterial material;
	public final int renderIndex;
	public final EntityEquipmentSlot armorType;
	public final String leggingsTexture;
	public final String chestTexture;
	protected final int effect;

	public BasicItemArmor(String name, ItemArmor.ArmorMaterial materialIn, int renderIndexIn,
						  EntityEquipmentSlot equipmentSlotIn, String leggingsTexture, String chestTexture, int effect) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.name = name;
		this.material = materialIn;
		this.renderIndex = renderIndexIn;
		this.armorType = equipmentSlotIn;
		this.leggingsTexture = leggingsTexture;
		this.chestTexture = chestTexture;
		this.effect = effect;
		setUnlocalizedName(name);
		setRegistryName(name);
        this.setCreativeTab(WorldsRetoldTabs.W_TAB);
	}

	@SideOnly(Side.CLIENT)
	public String getArmorTexture(final ItemStack is, final Entity entity, final EntityEquipmentSlot slot,
			final String type) {
		if (slot == EntityEquipmentSlot.LEGS) {
			return leggingsTexture;
		}
		return chestTexture;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if (this.effect == 1) {
			if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem().equals(HailstormItems.CRYONITE_HELMET)
					&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem().equals(HailstormItems.CRYONITE_CHESTPLATE)
					&& player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem().equals(HailstormItems.CRYONITE_LEGGINGS)
					&& player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem().equals(HailstormItems.CRYONITE_BOOTS)) {
				List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(10.0D, 10.0D, 10.0D));
				for (Entity entity : entities)
					if (entity instanceof IMob && entity instanceof EntityLivingBase && entity != null) {
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(HailstormPotions.FREEZING, 600, 0));
					}
			}
		}
	}
}