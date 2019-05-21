package xenoscape.worldsretold.hellfire.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.WorldsRetoldTabs;
import xenoscape.worldsretold.defaultmod.basic.BasicItem;
import xenoscape.worldsretold.defaultmod.basic.BasicItemWeapon;
import xenoscape.worldsretold.defaultmod.config.ConfigModules;

@ObjectHolder(WorldsRetold.MODID)
public class HellfireItems {

	// Default

	// Weapons
	@ObjectHolder("inferno_sword")
    public static BasicItemWeapon INFERNO_SWORD = new BasicItemWeapon("inferno_sword", HellfireMaterials.INFERNO, 0.0F, 0.0D, 3);

	// Tools

	// Armor

	// Misc
	@ObjectHolder("hellfire_scorpion_bulb")
	public static BasicItem HELLFIRE_SCORPION_BULB = new BasicItem("hellfire_scorpion_bulb").setCreativeTab(WorldsRetoldTabs.W_TAB);


	@Mod.EventBusSubscriber(modid = WorldsRetold.MODID)
	public static class ItemsRegistrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> items = event.getRegistry();
			final ItemBlockRegistry itemblocks = new ItemBlockRegistry(event.getRegistry());
			if (ConfigModules.isHellfireEnabled == true) {
				items.registerAll(INFERNO_SWORD, HELLFIRE_SCORPION_BULB);

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