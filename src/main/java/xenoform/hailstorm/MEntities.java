package xenoform.hailstorm;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoform.hailstorm.entities.roller.EntitySnowRoller;
import xenoform.hailstorm.entities.roller.RenderSnowRoller;

public class MEntities
{
    private static int EntityID = 0;

    public static void preInit()
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "roller"), EntitySnowRoller.class,
                "roller", EntityID++, Hailstorm.instance, 64, 3, true, 0xffffff, 0xb7b7b7);
    }

    public static void Init()
    {
        Hailstorm.LOGGER.info("MEntities: Entities Looted!");
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntitySnowRoller.class, RenderSnowRoller.FACTORY);
    }
}
