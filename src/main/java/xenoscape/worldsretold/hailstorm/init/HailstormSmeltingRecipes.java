package xenoscape.worldsretold.hailstorm.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HailstormSmeltingRecipes {

	public static void init() {
		GameRegistry.addSmelting(new ItemStack(HailstormItems.MANCHOT_RAW), new ItemStack(HailstormItems.MANCHOT_COOKED), 10);
		GameRegistry.addSmelting(new ItemStack(HailstormItems.VENISON_RAW), new ItemStack(HailstormItems.VENISON_COOKED), 10);
		GameRegistry.addSmelting(HailstormBlocks.CRYONITE_ORE, new ItemStack(HailstormItems.CRYONITE), 10);
	}
}
