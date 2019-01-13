package xenoscape.worldsretold.defaultmod.basic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import xenoscape.worldsretold.defaultmod.util.ModelRegistry;

public class BasicItem extends Item implements ModelRegistry {

	protected String name;

	public BasicItem(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	@Override
	public BasicItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}