package xenoform.hailstorm.main;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.entity.blizzard.EntityBlizzard;
import xenoform.hailstorm.entity.blizzard.RenderBlizzard;
import xenoform.hailstorm.entity.blizzard.hail.EntityHail;
import xenoform.hailstorm.entity.blizzard.hail.RenderHail;
import xenoform.hailstorm.entity.nix.EntityNix;
import xenoform.hailstorm.entity.nix.RenderNix;
import xenoform.hailstorm.entity.roller.EntitySnowRoller;
import xenoform.hailstorm.entity.roller.RenderSnowRoller;

public class MEntities
{
    private static int EntityID = 0;

    public static void preInit()
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "roller"), EntitySnowRoller.class,
                "roller", EntityID++, Hailstorm.instance, 64, 3, true, 0xffffff, 0xb7b7b7);
        EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "nix"), EntityNix.class,
                "nix", EntityID++, Hailstorm.instance, 64, 3, true, 0x00e1ff, 0xffffff);
        EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "blizzard"), EntityBlizzard.class,
                "blizzard", EntityID++, Hailstorm.instance, 64, 3, true, 0xbff4ff, 0x00d4ff);
        EntityRegistry.registerModEntity(new ResourceLocation(Hailstorm.MODID, "hail"), EntityHail.class,
                "hail", EntityID++, Hailstorm.instance, 64, 3, true);
    }

    @SideOnly(Side.CLIENT)
    public static void initRenderAndModel()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntitySnowRoller.class, RenderSnowRoller.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityNix.class, RenderNix.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBlizzard.class, RenderBlizzard.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHail.class, RenderHail.FACTORY);
    }
}
