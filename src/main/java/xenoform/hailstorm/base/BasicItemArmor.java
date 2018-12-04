package xenoform.hailstorm.base;

import com.google.common.base.Predicates;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.UUID;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoform.hailstorm.util.ModelRegistry;

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