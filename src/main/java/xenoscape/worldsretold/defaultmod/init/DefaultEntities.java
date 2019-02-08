package xenoscape.worldsretold.defaultmod.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.entity.automaton.EntityAutomaton;

public class DefaultEntities {
    public static int EntityID = 0;

    public static void preInit() {

        // Neutral
        EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "automaton"), EntityAutomaton.class,
                "automaton", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
        //	EntityRegistry.registerModEntity(new ResourceLocation(WorldsRetold.MODID, "sentinel"), EntitySentinel.class,
        //			"sentinel", EntityID++, WorldsRetold.INSTANCE, 64, 3, true);
    }
}
