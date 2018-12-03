package xenoform.hailstorm;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import xenoform.hailstorm.base.BasicItem;
import xenoform.hailstorm.base.BasicItemUniqueWeapon;

@ObjectHolder(Hailstorm.MODID)
public class MItems {
	
	@ObjectHolder("cryonite")
	public static BasicItem CRYONITE = new BasicItem("cryonite").setCreativeTab(CreativeTabs.MISC);
	
	@ObjectHolder("avalanche_hammer")
	public static BasicItemUniqueWeapon AVALANCHE_HAMMER = new BasicItemUniqueWeapon("avalanche_hammer", MToolMaterial.UNIQUE,
			5, 1.6);

	@Mod.EventBusSubscriber(modid = Hailstorm.MODID)
	public static class ItemsRegistrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> items = event.getRegistry();
			final ItemBlockRegistry itemblocks = new ItemBlockRegistry(event.getRegistry());
			items.registerAll(CRYONITE, AVALANCHE_HAMMER);
			
			itemblocks.registerItemBlock(MBlocks.CRYONITE_ORE);
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