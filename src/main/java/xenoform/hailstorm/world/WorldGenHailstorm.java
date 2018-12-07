package xenoform.hailstorm.world;

import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;
import xenoform.hailstorm.MBlocks;

public class WorldGenHailstorm implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		// Nether
		case -1:
			break;
		// Overworld
		case 0:
			runOreGenerator(MBlocks.CRYONITE_ORE.getDefaultState(), 8, 10, 0, 32, BlockMatcher.forBlock(Blocks.STONE),
					world, random, chunkX, chunkZ);
			generateHailstormShrine(world, random, chunkX + 8, chunkZ + 8);
			break;
		// End
		case 1:
			break;
		// Everything else
		default:
			break;
		}
	}

	private void runOreGenerator(IBlockState blockToGen, int blockAmount, int chancesToSpawn, int minHeight,
			int maxHeight, Predicate<IBlockState> blockToReplace, World world, Random rand, int chunk_X, int chunk_Z) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		WorldGenMinable generator = new WorldGenMinable(blockToGen, blockAmount, blockToReplace);
		int heightdiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightdiff);
			int z = chunk_Z * 16 + rand.nextInt(16);

			BlockPos pos = new BlockPos(x, y, z);
			if (world.getBiome(pos) == Biomes.FROZEN_OCEAN || world.getBiome(pos) == Biomes.FROZEN_RIVER
					|| world.getBiome(pos) == Biomes.COLD_BEACH || world.getBiome(pos) == Biomes.COLD_TAIGA
					|| world.getBiome(pos) == Biomes.COLD_TAIGA_HILLS || world.getBiome(pos) == Biomes.ICE_MOUNTAINS
					|| world.getBiome(pos) == Biomes.ICE_PLAINS || world.getBiome(pos) == Biomes.MUTATED_TAIGA_COLD) {
				generator.generate(world, rand, pos);
			}
		}
	}

	private void generateHailstormShrine(World world, Random rand, int blockX, int blockZ) {
		int y = getGroundFromAbove(world, blockX, blockZ);
		BlockPos pos = new BlockPos(blockX, y, blockZ);
		WorldGenerator structure = new StructureHailstormShrine();
		structure.generate(world, rand, pos);
	}

	public static int getGroundFromAbove(World world, int x, int z) {
		int y = 255;
		boolean foundGround = false;
		while (!foundGround && y-- >= 0) {
			Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			foundGround = blockAt == Blocks.GRASS || blockAt == Blocks.SNOW
					|| blockAt == Blocks.SNOW_LAYER;
		}
		return y;
	}
}