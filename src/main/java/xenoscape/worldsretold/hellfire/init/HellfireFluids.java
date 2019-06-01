package xenoscape.worldsretold.hellfire.init;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import xenoscape.worldsretold.WorldsRetold;

public class HellfireFluids {

    // Fluid
    public static final Fluid RADIANCE_FLUID = registerFluid("radiance", "radiance")
            .setColor(0xFFD700)
            .setTemperature(100);

    //ItemStack, for bucketed fluids
    public static ItemStack RADIANCE_FLUID_BUCKET;

    public static void preInit() {
        FluidRegistry.registerFluid(RADIANCE_FLUID);

        FluidRegistry.addBucketForFluid(RADIANCE_FLUID);
    }

    public static void init() {
        RADIANCE_FLUID_BUCKET = FluidUtil.getFilledBucket(new FluidStack(RADIANCE_FLUID, Fluid.BUCKET_VOLUME));
    }

    private static Fluid registerFluid(String name, String dir) {
        return new Fluid(name, fluidPath(name, dir, "_still"), fluidPath(name, dir, "_flow"));
    }

    private static ResourceLocation fluidPath(String name, String dir, String type) {
        return new ResourceLocation(WorldsRetold.MODID, String.format("fluids/%s/%s%s", dir, name, type));
    }
}
