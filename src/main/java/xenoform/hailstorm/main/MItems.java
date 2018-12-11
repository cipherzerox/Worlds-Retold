package xenoform.hailstorm.main;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.base.*;

import java.util.Set;

@ObjectHolder(Hailstorm.MODID)
public class MItems {

	private static final Set<Block> EFFECTIVE_AXE = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG,
			Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER,
			Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);
	private static final Set<Block> EFFECTIVE_SHOVEL = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND,
			Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND,
			Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER);
	private static final Set<Block> EFFECTIVE_PICKAXE = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE,
			Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE,
			Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE,
			Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE,
			Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE,
			Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON,
			Blocks.STONE_PRESSURE_PLATE);

	// Weapons
	@ObjectHolder("cryonite_sword")
	public static BasicItemWeapon CRYONITE_SWORD = new BasicItemWeapon("cryonite_sword", MMaterials.CRYONITE_TOOL, 5.5f,
			2.4000000953674316D);
	@ObjectHolder("avalanche_hammer")
	public static BasicItemUniqueWeapon AVALANCHE_HAMMER = new BasicItemUniqueWeapon("avalanche_hammer",
			MMaterials.UNIQUE_TOOL, 8, 4.2);
	@ObjectHolder("relic_sword")
	public static BasicItemWeapon RELIC_SWORD = new BasicItemWeapon("relic_sword", MMaterials.RELIC_TOOL, 9f,
			3.0D);

	// Tools
	@ObjectHolder("cryonite_shovel")
	public static BasicItemTool CRYONITE_SHOVEL = new BasicItemTool("cryonite_shovel", MMaterials.CRYONITE_TOOL,
			EFFECTIVE_SHOVEL, 0, 1.5f, -3.0f);
	@ObjectHolder("cryonite_pickaxe")
	public static BasicItemTool CRYONITE_PICKAXE = new BasicItemTool("cryonite_pickaxe", MMaterials.CRYONITE_TOOL,
			EFFECTIVE_PICKAXE, 1, 1.0f, -2.8f);
	@ObjectHolder("cryonite_axe")
	public static BasicItemTool CRYONITE_AXE = new BasicItemTool("cryonite_axe", MMaterials.CRYONITE_TOOL,
			EFFECTIVE_AXE, 2, 5.5f, -3.0f);
	@ObjectHolder("cryonite_hoe")
	public static BasicItemHoe CRYONITE_HOE = new BasicItemHoe("cryonite_hoe", MMaterials.CRYONITE_TOOL);

	// Armor
	@ObjectHolder("cryonite_helmet")
	public static BasicItemArmor CRYONITE_HELMET = new BasicItemArmor("cryonite_helmet", MMaterials.CRYONITE_ARMOR, 0,
			EntityEquipmentSlot.HEAD, Hailstorm.MODID + ":textures/armor/cryonite_leggings.png",
			Hailstorm.MODID + ":textures/armor/cryonite_armor.png");
	@ObjectHolder("cryonite_chestplate")
	public static BasicItemArmor CRYONITE_CHESTPLATE = new BasicItemArmor("cryonite_chestplate",
			MMaterials.CRYONITE_ARMOR, 0, EntityEquipmentSlot.CHEST,
			Hailstorm.MODID + ":textures/armor/cryonite_leggings.png",
			Hailstorm.MODID + ":textures/armor/cryonite_armor.png");
	@ObjectHolder("cryonite_leggings")
	public static BasicItemArmor CRYONITE_LEGGINGS = new BasicItemArmor("cryonite_leggings", MMaterials.CRYONITE_ARMOR,
			1, EntityEquipmentSlot.LEGS, Hailstorm.MODID + ":textures/armor/cryonite_leggings.png",
			Hailstorm.MODID + ":textures/armor/cryonite_armor.png");
	@ObjectHolder("cryonite_boots")
	public static BasicItemArmor CRYONITE_BOOTS = new BasicItemArmor("cryonite_boots", MMaterials.CRYONITE_ARMOR, 0,
			EntityEquipmentSlot.FEET, Hailstorm.MODID + ":textures/armor/cryonite_leggings.png",
			Hailstorm.MODID + ":textures/armor/cryonite_armor.png");

	@ObjectHolder("hailstorm_helmet")
	public static BasicItemArmor HAILSTORM_HELMET = new BasicItemArmor("hailstorm_helmet", MMaterials.HAILSTORM_ARMOR,
			0, EntityEquipmentSlot.HEAD, Hailstorm.MODID + ":textures/armor/hailstorm_leggings.png",
			Hailstorm.MODID + ":textures/armor/hailstorm_armor.png");
	@ObjectHolder("hailstorm_chestplate")
	public static BasicItemArmor HAILSTORM_CHESTPLATE = new BasicItemArmor("hailstorm_chestplate",
			MMaterials.HAILSTORM_ARMOR, 0, EntityEquipmentSlot.CHEST,
			Hailstorm.MODID + ":textures/armor/hailstorm_leggings.png",
			Hailstorm.MODID + ":textures/armor/hailstorm_armor.png");
	@ObjectHolder("hailstorm_leggings")
	public static BasicItemArmor HAILSTORM_LEGGINGS = new BasicItemArmor("hailstorm_leggings",
			MMaterials.HAILSTORM_ARMOR, 1, EntityEquipmentSlot.LEGS,
			Hailstorm.MODID + ":textures/armor/hailstorm_leggings.png",
			Hailstorm.MODID + ":textures/armor/hailstorm_armor.png");
	@ObjectHolder("hailstorm_boots")
	public static BasicItemArmor HAILSTORM_BOOTS = new BasicItemArmor("hailstorm_boots", MMaterials.HAILSTORM_ARMOR, 0,
			EntityEquipmentSlot.FEET, Hailstorm.MODID + ":textures/armor/hailstorm_leggings.png",
			Hailstorm.MODID + ":textures/armor/hailstorm_armor.png");

	// Basic
	@ObjectHolder("cryonite")
	public static BasicItem CRYONITE = new BasicItem("cryonite").setCreativeTab(CreativeTabs.MISC);

	@ObjectHolder("ice_scroll_projectile")
	public static BasicItem ICE_SCROLL_PROJECTILE = new BasicItem("ice_scroll_projectile");

	@ObjectHolder("statue")
	public static BasicItemStatue STATUE = new BasicItemStatue("statue");

	@Mod.EventBusSubscriber(modid = Hailstorm.MODID)
	public static class ItemsRegistrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> items = event.getRegistry();
			final ItemBlockRegistry itemblocks = new ItemBlockRegistry(event.getRegistry());
			items.registerAll(CRYONITE_SWORD, RELIC_SWORD, CRYONITE_SHOVEL, CRYONITE_PICKAXE, CRYONITE_AXE,
					CRYONITE_HOE, CRYONITE_HELMET, CRYONITE_CHESTPLATE, CRYONITE_LEGGINGS, CRYONITE_BOOTS,
					HAILSTORM_HELMET, HAILSTORM_CHESTPLATE, HAILSTORM_LEGGINGS, HAILSTORM_BOOTS, CRYONITE,
					ICE_SCROLL_PROJECTILE, STATUE);
			itemblocks.registerItemBlock(MBlocks.CRYONITE_ORE);
			itemblocks.registerItemBlock(MBlocks.CRYONITE_BLOCK);
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