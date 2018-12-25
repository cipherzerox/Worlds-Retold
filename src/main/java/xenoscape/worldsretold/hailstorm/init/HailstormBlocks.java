package xenoscape.worldsretold.hailstorm.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.base.BasicBlock;
import xenoscape.worldsretold.base.BasicBlockOre;
import xenoscape.worldsretold.base.BasicBlockOverlayedPlant;
import xenoscape.worldsretold.config.ConfigModules;
import xenoscape.worldsretold.hailstorm.item.BasicBlockCritter;

@ObjectHolder(WorldsRetold.MODID)
public class HailstormBlocks {

	@ObjectHolder("cryonite_ore")
	public static BasicBlockOre CRYONITE_ORE = new BasicBlockOre(Material.ROCK, "cryonite_ore", 3.0f, "pickaxe", 2,
			HailstormItems.CRYONITE).setResistance(5.0F).setSoundType(SoundType.STONE)
					.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	@ObjectHolder("cryonite_block")
	public static BasicBlock CRYONITE_BLOCK = new BasicBlock(Material.IRON, "cryonite_block", 5.0f, "pickaxe", 2)
			.setResistance(10.0F).setSoundType(SoundType.METAL).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

	@ObjectHolder("arctic_willow")
	public static BasicBlockOverlayedPlant ARCTIC_WILLOW = new BasicBlockOverlayedPlant("arctic_willow")
			.setCreativeTab(CreativeTabs.DECORATIONS);
	@ObjectHolder("boreal_orchid")
	public static BasicBlockOverlayedPlant BOREAL_ORCHID = new BasicBlockOverlayedPlant("boreal_orchid")
			.setCreativeTab(CreativeTabs.DECORATIONS);

	@ObjectHolder("stone_critter_egg")
	public static BasicBlockCritter STONE_CRITTER_EGG = new BasicBlockCritter("stone_critter_egg", Blocks.STONE);
	@ObjectHolder("cobble_critter_egg")
	public static BasicBlockCritter COBBLE_CRITTER_EGG = new BasicBlockCritter("cobble_critter_egg",
			Blocks.COBBLESTONE);
	@ObjectHolder("mosscobble_critter_egg")
	public static BasicBlockCritter MOSSCOBBLE_CRITTER_EGG = new BasicBlockCritter("mosscobble_critter_egg",
			Blocks.MOSSY_COBBLESTONE);

	@Mod.EventBusSubscriber(modid = WorldsRetold.MODID)
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> blocks = event.getRegistry();
			if (ConfigModules.isHailstormEnabled == true) {
				blocks.registerAll(CRYONITE_ORE, CRYONITE_BLOCK, ARCTIC_WILLOW, BOREAL_ORCHID, STONE_CRITTER_EGG,
						COBBLE_CRITTER_EGG, MOSSCOBBLE_CRITTER_EGG);
			}
		}
	}
}