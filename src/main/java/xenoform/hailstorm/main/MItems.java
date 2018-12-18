package xenoform.hailstorm.main;

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
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.base.*;

import java.util.Set;

@ObjectHolder(Hailstorm.MODID)
public class MItems {

	// Default
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

	// For Cryonite gear so it can mine snow blocks faster.
	private static final Set<Block> EFFECTIVE_CRYON_AXE = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG,
			Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER,
			Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE, Blocks.ICE, Blocks.FROSTED_ICE, Blocks.PACKED_ICE,
			Blocks.SNOW, Blocks.SNOW_LAYER);
	private static final Set<Block> EFFECTIVE_CRYON_SHOVEL = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND,
			Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SOUL_SAND, Blocks.GRASS_PATH,
			Blocks.CONCRETE_POWDER, Blocks.ICE, Blocks.FROSTED_ICE, Blocks.PACKED_ICE, Blocks.SNOW, Blocks.SNOW_LAYER);
	private static final Set<Block> EFFECTIVE_CRYON_PICKAXE = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE,
			Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE,
			Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.IRON_BLOCK,
			Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE,
			Blocks.NETHERRACK, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE,
			Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.ICE, Blocks.FROSTED_ICE,
			Blocks.PACKED_ICE, Blocks.SNOW, Blocks.SNOW_LAYER);

	// Weapons
	@ObjectHolder("cryonite_sword")
	public static BasicItemWeapon CRYONITE_SWORD = new BasicItemWeapon("cryonite_sword", MMaterials.CRYONITE_TOOL, 5.5F,
			2.4000000953674316D, 1);
	@ObjectHolder("avalanche_hammer")
	public static BasicItemWeapon AVALANCHE_HAMMER = new BasicItemWeapon("avalanche_hammer", MMaterials.UNIQUE_TOOL, 8F,
			2.4D, 2);
	@ObjectHolder("relic_sword")
	public static BasicItemWeapon RELIC_SWORD = new BasicItemWeapon("relic_sword", MMaterials.RELIC_TOOL, 9F, 3.0D, 0);
	@ObjectHolder("blizz_scroll")
	public static BasicItemBlizzScroll BLIZZ_SCROLL = new BasicItemBlizzScroll("blizz_scroll");

	// Tools
	@ObjectHolder("cryonite_shovel")
	public static BasicItemTool CRYONITE_SHOVEL = new BasicItemTool("cryonite_shovel", MMaterials.CRYONITE_TOOL,
			EFFECTIVE_CRYON_SHOVEL, 0, 1.5f, -3.0f, 1);
	@ObjectHolder("cryonite_pickaxe")
	public static BasicItemTool CRYONITE_PICKAXE = new BasicItemTool("cryonite_pickaxe", MMaterials.CRYONITE_TOOL,
			EFFECTIVE_CRYON_PICKAXE, 1, 1.0f, -2.8f, 1);
	@ObjectHolder("cryonite_axe")
	public static BasicItemTool CRYONITE_AXE = new BasicItemTool("cryonite_axe", MMaterials.CRYONITE_TOOL,
			EFFECTIVE_CRYON_AXE, 2, 5.5f, -3.0f, 1);
	@ObjectHolder("cryonite_hoe")
	public static BasicItemHoe CRYONITE_HOE = new BasicItemHoe("cryonite_hoe", MMaterials.CRYONITE_TOOL, 1);

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

	@ObjectHolder("cryonite")
	public static BasicItem CRYONITE = new BasicItem("cryonite").setCreativeTab(CreativeTabs.MISC);

	@ObjectHolder("penguin_egg")
	public static BasicItemPenguinEgg PENGUIN_EGG = new BasicItemPenguinEgg("penguin_egg");
	@ObjectHolder("penguin_feather")
	public static BasicItem PENGUIN_FEATHER = new BasicItem("penguin_feather").setCreativeTab(CreativeTabs.MISC);
	@ObjectHolder("manchot_raw")
	public static BasicItemFood MANCHOT_RAW = new BasicItemFood("manchot_raw", 32, 2, 1.2F, true, EnumAction.EAT);
	@ObjectHolder("manchot_cooked")
	public static BasicItemFood MANCHOT_COOKED = new BasicItemFood("manchot_cooked", 32, 4, 3.0F, true, EnumAction.EAT);

	@ObjectHolder("statue")
	public static BasicItemStatue STATUE = new BasicItemStatue("statue");

	@Mod.EventBusSubscriber(modid = Hailstorm.MODID)
	public static class ItemsRegistrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> items = event.getRegistry();
			final ItemBlockRegistry itemblocks = new ItemBlockRegistry(event.getRegistry());
			items.registerAll(CRYONITE_SWORD, RELIC_SWORD, BLIZZ_SCROLL, CRYONITE_SHOVEL, CRYONITE_PICKAXE,
					CRYONITE_AXE, CRYONITE_HOE, CRYONITE_HELMET, CRYONITE_CHESTPLATE, CRYONITE_LEGGINGS, CRYONITE_BOOTS,
					CRYONITE, PENGUIN_EGG, PENGUIN_FEATHER, MANCHOT_RAW, MANCHOT_COOKED, STATUE);
			itemblocks.registerItemBlock(MBlocks.CRYONITE_ORE);
			itemblocks.registerItemBlock(MBlocks.CRYONITE_BLOCK);
			itemblocks.registerItemBlock(MBlocks.ARCTIC_WILLOW);
			itemblocks.registerItemBlock(MBlocks.CRITTER_EGG);
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