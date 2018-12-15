package xenoform.hailstorm.world;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import xenoform.hailstorm.base.BasicBlockOverlayedPlant;

public class WorldGenOverlayedFlower extends WorldGenerator {
	private BasicBlockOverlayedPlant flower;

	public WorldGenOverlayedFlower(BasicBlockOverlayedPlant flowerIn) {
		this.setGeneratedBlock(flowerIn);
	}

	public void setGeneratedBlock(BasicBlockOverlayedPlant flowerIn) {
		this.flower = flowerIn;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4),
					rand.nextInt(8) - rand.nextInt(8));

			if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 255)
					&& this.flower.canBlockStay(worldIn, blockpos, this.flower.getDefaultState())) {
				worldIn.setBlockState(blockpos, this.flower.getDefaultState(), 2);
			}
		}
		return true;
	}
}