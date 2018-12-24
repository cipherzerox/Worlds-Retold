package xenoscape.worldsretold.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import xenoscape.worldsretold.WorldsRetold;

@Mod.EventBusSubscriber(modid = WorldsRetold.MODID, value = { Side.CLIENT })
public class ModelRendering
{
    @SubscribeEvent
    public static void onModelRegistryReady(final ModelRegistryEvent event) {
        for (final Block b : Block.REGISTRY) {
            if (b instanceof ModelRegistry) {
                ((ModelRegistry)b).registerModel();
            }
        }
        for (final Item i : Item.REGISTRY) {
            if (i instanceof ModelRegistry) {
                ((ModelRegistry)i).registerModel();
            }
        }
    }
}
