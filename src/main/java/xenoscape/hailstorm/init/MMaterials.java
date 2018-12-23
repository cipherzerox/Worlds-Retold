package xenoscape.hailstorm.init;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class MMaterials {

	public static ItemArmor.ArmorMaterial QUICKSILVER_ARMOR = EnumHelper.addArmorMaterial("QUICKSILVER", "quicksilver",
			10, new int[] { 1, 3, 4, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f);
	public static ItemArmor.ArmorMaterial CRYONITE_ARMOR = EnumHelper.addArmorMaterial("CRYONITE", "cryonite", 24,
			new int[] { 2, 6, 7, 2 }, 18, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0f);

	public static Item.ToolMaterial QUICKSILVER_TOOL = EnumHelper.addToolMaterial("QUICKSILVER", 1, 192, 9.0F, 1.5F,
			12);
	public static Item.ToolMaterial CRYONITE_TOOL = EnumHelper.addToolMaterial("CRYONITE", 2, 693, 5.0F, 2.5F, 18);
	public static Item.ToolMaterial UNIQUE_TOOL = EnumHelper.addToolMaterial("UNIQUE", 3, 1561, 10.0F, 4.0F, 30);
	public static Item.ToolMaterial RELIC_TOOL = EnumHelper.addToolMaterial("RELIC", 0, 52, 0.0F, 0.0F, 0);

	public static void initRepairMaterials() {

		MMaterials.CRYONITE_ARMOR.setRepairItem(new ItemStack(MItems.CRYONITE));

		MMaterials.CRYONITE_TOOL.setRepairItem(new ItemStack(MItems.CRYONITE));
		MMaterials.UNIQUE_TOOL.setRepairItem(new ItemStack(MItems.CRYONITE));
	}

}