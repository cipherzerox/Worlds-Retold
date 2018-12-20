package xenoform.hailstorm.init;

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
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.base.BasicBlock;
import xenoform.hailstorm.base.BasicBlockCritter;
import xenoform.hailstorm.base.BasicBlockOre;
import xenoform.hailstorm.base.BasicBlockOverlayedPlant;

@ObjectHolder(Hailstorm.MODID)
public class MBlocks {

	@ObjectHolder("cryonite_ore")
	public static BasicBlockOre CRYONITE_ORE = new BasicBlockOre(Material.ROCK, "cryonite_ore", 3.0f, "pickaxe", 2,
			MItems.CRYONITE).setResistance(5.0F).setSoundType(SoundType.STONE)
					.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	@ObjectHolder("cryonite_block")
	public static BasicBlock CRYONITE_BLOCK = new BasicBlock(Material.IRON, "cryonite_block", 5.0f, "pickaxe", 2)
			.setResistance(10.0F).setSoundType(SoundType.METAL).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

	@ObjectHolder("arctic_willow")
	public static BasicBlockOverlayedPlant ARCTIC_WILLOW = new BasicBlockOverlayedPlant("arctic_willow")
			.setCreativeTab(CreativeTabs.DECORATIONS);

	@ObjectHolder("stone_critter_egg")
	public static BasicBlockCritter STONE_CRITTER_EGG = new BasicBlockCritter("stone_critter_egg", Blocks.STONE);
	@ObjectHolder("cobble_critter_egg")
	public static BasicBlockCritter COBBLE_CRITTER_EGG = new BasicBlockCritter("cobble_critter_egg",
			Blocks.COBBLESTONE);
	@ObjectHolder("mosscobble_critter_egg")
	public static BasicBlockCritter MOSSCOBBLE_CRITTER_EGG = new BasicBlockCritter("mosscobble_critter_egg",
			Blocks.MOSSY_COBBLESTONE);
	@ObjectHolder("packedice_critter_egg")
	public static BasicBlockCritter PACKEDICE_CRITTER_EGG = new BasicBlockCritter("packedice_critter_egg",
			Blocks.PACKED_ICE);

	@Mod.EventBusSubscriber(modid = Hailstorm.MODID)
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> blocks = event.getRegistry();
			blocks.registerAll(CRYONITE_ORE, CRYONITE_BLOCK, ARCTIC_WILLOW, STONE_CRITTER_EGG, COBBLE_CRITTER_EGG,
					MOSSCOBBLE_CRITTER_EGG, PACKEDICE_CRITTER_EGG);
		}
	}
}