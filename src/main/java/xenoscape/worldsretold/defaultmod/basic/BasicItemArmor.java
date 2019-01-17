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
import xenoscape.worldsretold.hailstorm.entity.ISnowCreature;
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

	public BasicItemArmor(String name, ItemArmor.ArmorMaterial materialIn, int renderIndexIn,
						  EntityEquipmentSlot equipmentSlotIn, String leggingsTexture, String chestTexture) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.name = name;
		this.material = materialIn;
		this.renderIndex = renderIndexIn;
		this.armorType = equipmentSlotIn;
		this.leggingsTexture = leggingsTexture;
		this.chestTexture = chestTexture;
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
}