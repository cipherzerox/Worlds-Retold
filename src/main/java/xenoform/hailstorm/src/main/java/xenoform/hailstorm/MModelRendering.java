package xenoform.hailstorm;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.relauncher.*;
import xenoform.hailstorm.util.ModelRegistry;
import net.minecraftforge.client.event.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import java.util.*;

import net.minecraftforge.fml.common.eventhandler.*;

@Mod.EventBusSubscriber(modid = Hailstorm.MODID, value = { Side.CLIENT })
public class MModelRendering
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
