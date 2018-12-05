package xenoform.hailstorm;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoform.hailstorm.entity.nix.EntityNix;
import xenoform.hailstorm.entity.roller.EntitySnowRoller;
import xenoform.hailstorm.render.RenderNix;
import xenoform.hailstorm.render.RenderSnowRoller;

public class MEntities
{
    private static int EntityID = 0;

    public static void preInit()
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "roller"), EntitySnowRoller.class,
                "roller", EntityID++, Hailstorm.instance, 64, 3, true, 0xffffff, 0xb7b7b7);
        EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "nix"), EntityNix.class,
                "nix", EntityID++, Hailstorm.instance, 64, 3, true, 0x00e1ff, 0xffffff);
    }

    @SideOnly(Side.CLIENT)
    public static void initRenderAndModel()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntitySnowRoller.class, RenderSnowRoller.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityNix.class, RenderNix.FACTORY);
    }
}
