package xenoscape.worldsretold.heatwave.init;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.WorldsRetoldTabs;
import xenoscape.worldsretold.basic.BasicItem;
import xenoscape.worldsretold.basic.BasicItemArmor;
import xenoscape.worldsretold.basic.BasicItemFood;
import xenoscape.worldsretold.basic.BasicItemHoe;
import xenoscape.worldsretold.basic.BasicItemTool;
import xenoscape.worldsretold.basic.BasicItemWeapon;
import xenoscape.worldsretold.config.ConfigModules;
import xenoscape.worldsretold.hailstorm.init.HailstormMaterials;
import xenoscape.worldsretold.hailstorm.item.BasicItemBlackArrow;
import xenoscape.worldsretold.hailstorm.item.BasicItemBlizzScroll;
import xenoscape.worldsretold.hailstorm.item.BasicItemPenguinEgg;
import xenoscape.worldsretold.hailstorm.item.BasicItemStatue;

@ObjectHolder(WorldsRetold.MODID)
public class HeatwaveItems {

	// Default

	// Weapons
	@ObjectHolder("venom_sword")
	public static BasicItemWeapon VENOM_SWORD = new BasicItemWeapon("venom_sword", HeatwaveMaterials.VENOM_TOOL, 1.5F, 0.0D, 3);

	// Tools

	// Armor

	// Misc
	@ObjectHolder("snake_fang")
	public static BasicItem SNAKE_FANG = new BasicItem("snake_fang").setCreativeTab(WorldsRetoldTabs.W_TAB);
	@ObjectHolder("scorpion_bulb")
	public static BasicItem SCORPION_BULB = new BasicItem("scorpion_bulb").setCreativeTab(WorldsRetoldTabs.W_TAB);


	@Mod.EventBusSubscriber(modid = WorldsRetold.MODID)
	public static class ItemsRegistrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> items = event.getRegistry();
			final ItemBlockRegistry itemblocks = new ItemBlockRegistry(event.getRegistry());
			if (ConfigModules.isHeatwaveEnabled == true) {
				items.registerAll(VENOM_SWORD, SNAKE_FANG, SCORPION_BULB);

			}
		}

		private static class ItemBlockRegistry {
			private final IForgeRegistry<Item> registry;

			ItemBlockRegistry(final IForgeRegistry<Item> registry) {
				this.registry = registry;
			}

			private void registerItemBlock(final Block block) {
				final ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(itemBlock.getBlock().getRegistryName());
				itemBlock.setUnlocalizedName(itemBlock.getBlock().getUnlocalizedName());
				this.registry.register(itemBlock);
			}
		}
	}

	static {
	}
}