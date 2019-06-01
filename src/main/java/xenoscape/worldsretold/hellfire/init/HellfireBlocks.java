package xenoscape.worldsretold.hellfire.init;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.WorldsRetoldTabs;
import xenoscape.worldsretold.defaultmod.basic.BasicBlockFluid;
import xenoscape.worldsretold.defaultmod.config.ConfigModules;
import xenoscape.worldsretold.hellfire.block.BlockRadiance;

@GameRegistry.ObjectHolder(WorldsRetold.MODID)
public class HellfireBlocks {

    @GameRegistry.ObjectHolder("radiance")
    public static Block RADIANCE_BLOCK = new BlockRadiance()
            .setCreativeTab(WorldsRetoldTabs.W_TAB);

    @Mod.EventBusSubscriber(modid = WorldsRetold.MODID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> blocks = event.getRegistry();
            if (ConfigModules.isHellfireEnabled) {
                blocks.registerAll(RADIANCE_BLOCK);
            }
        }
    }
}
