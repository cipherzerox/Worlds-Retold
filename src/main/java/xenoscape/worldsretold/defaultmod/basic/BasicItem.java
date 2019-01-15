package xenoscape.worldsretold.defaultmod.basic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import xenoscape.worldsretold.defaultmod.util.ModelRegistry;
import xenoscape.worldsretold.heatwave.init.HeatwaveItems;

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