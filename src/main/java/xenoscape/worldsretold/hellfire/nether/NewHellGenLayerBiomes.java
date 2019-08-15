package xenoscape.worldsretold.hellfire.nether;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class NewHellGenLayerBiomes extends GenLayer {
	protected Object[] biomes;

	public NewHellGenLayerBiomes(final long par1, final GenLayer par3GenLayer) {
		super(par1);
		this.biomes = new Biome[] { Biomes.HELL, Biomes.PLAINS };
		this.parent = par3GenLayer;
	}

	public NewHellGenLayerBiomes(final long par1) {
		super(par1);
		this.biomes = new Biome[] { Biomes.HELL, Biomes.PLAINS };
	}

	public int[] getInts(final int x, final int z, final int width, final int depth) {
		final int[] dest = IntCache.getIntCache(width * depth);
		for (int dz = 0; dz < depth; ++dz) {
			for (int dx = 0; dx < width; ++dx) {
				this.initChunkSeed((long) (dx + x), (long) (dz + z));
					dest[dx + dz * width] = Biome
							.getIdForBiome((Biome) this.biomes[this.nextInt(this.biomes.length)]);
			}
		}
		return dest;
	}
}
