package xenoform.hailstorm;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum MToolMaterial {

	UNIQUE(0, 64, 2.0F, 0.25F, 0);

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float damageVsEntity;
	private final int enchantability;
	private ItemStack repairMaterial = ItemStack.EMPTY;

	MToolMaterial(int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.damageVsEntity = damageVsEntity;
		this.enchantability = enchantability;
	}

	public int getMaxUses() {
		return this.maxUses;
	}

	public float getEfficiency() {
		return this.efficiency;
	}

	public float getAttackDamage() {
		return this.damageVsEntity;
	}

	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	@Deprecated
	public Item getRepairItem() {
		return this == UNIQUE ? Items.FIRE_CHARGE : null;
	}

	public MToolMaterial setRepairItem(ItemStack stack) {
		if (!this.repairMaterial.isEmpty())
			throw new RuntimeException("Repair material has already been set");
		if (this == UNIQUE)
			throw new RuntimeException("Can not change vanilla tool repair materials");
		this.repairMaterial = stack;
		return this;
	}

	public ItemStack getRepairItemStack() {
		if (!repairMaterial.isEmpty())
			return repairMaterial;
		Item ret = this.getRepairItem();
		if (ret != null)
			repairMaterial = new ItemStack(ret, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE);
		return repairMaterial;
	}
}