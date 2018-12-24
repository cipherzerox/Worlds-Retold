package xenoscape.worldsretold.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.util.ModelRegistry;

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
		this.setCreativeTab(CreativeTabs.COMBAT);
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