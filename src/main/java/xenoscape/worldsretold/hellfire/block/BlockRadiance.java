package xenoscape.worldsretold.hellfire.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xenoscape.worldsretold.defaultmod.basic.BasicBlockFluid;
import xenoscape.worldsretold.hellfire.init.HellfireFluids;

public class BlockRadiance extends BasicBlockFluid {

    public BlockRadiance() {
        super("radiance", HellfireFluids.RADIANCE_FLUID, Material.WATER);
    }

    //TODO: Find out how to make the player float upwards?

    @Override
    public void mixFluids(World world, BlockPos pos) {
        for (EnumFacing side : EnumFacing.VALUES) {
            if (side != EnumFacing.DOWN) {
                IBlockState offset = world.getBlockState(pos.offset(side));
                if (offset.getMaterial().isLiquid()) {
                    if (offset.getMaterial() == Material.LAVA) {
                        world.setBlockState(pos, Blocks.MAGMA.getDefaultState());
                        this.playSound(world, pos);
                        break;
                    }
                }
            }
        }
    }
}
