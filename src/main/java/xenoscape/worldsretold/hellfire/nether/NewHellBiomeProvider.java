package xenoform.tartheus.world;

import com.google.common.collect.Lists;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Biomes;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewHellBiomeProvider extends BiomeProvider {
    public static List<Biome> allowedBiomes;
    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private BiomeCache biomeCache;
    private List<Biome> biomesToSpawnIn;
    private World world;

    protected NewHellBiomeProvider() {
        this.biomeCache = new BiomeCache(this);
        (this.biomesToSpawnIn = new ArrayList<>()).addAll(xenoform.tartheus.world.NewHellBiomeProvider.allowedBiomes);
    }

    public NewHellBiomeProvider(final long par1, final WorldType par3WorldType, final World world) {
        this();
        GenLayer[] agenlayer = xenoform.tartheus.world.layer.NewHellGenLayer.initializeAllBiomeGenerators(par1, par3WorldType);
        agenlayer = this.getModdedBiomeGenerators(par3WorldType, par1, agenlayer);
        this.genBiomes = agenlayer[0];
        this.biomeIndexLayer = agenlayer[1];
        this.world = world;
    }

    public NewHellBiomeProvider(final World par1World) {
        this(par1World.getSeed(), par1World.getWorldInfo().getTerrainType(), par1World);
    }

    public Biome[] getBiomesForGeneration(Biome[] par1ArrayOfBiomeGenBase, final int par2, final int par3,
                                          final int par4, final int par5) {
        IntCache.resetIntCache();
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
            par1ArrayOfBiomeGenBase = new Biome[par4 * par5];
        }
        final int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);
        try {
            for (int i1 = 0; i1 < par4 * par5; ++i1) {
                par1ArrayOfBiomeGenBase[i1] = Biome.getBiome(aint[i1]);
            }
            return par1ArrayOfBiomeGenBase;
        } catch (Throwable throwable) {
            final CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            final CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
            crashreportcategory.addCrashSection("biomes[] size", par1ArrayOfBiomeGenBase.length);
            crashreportcategory.addCrashSection("x", par2);
            crashreportcategory.addCrashSection("z", par3);
            crashreportcategory.addCrashSection("w", par4);
            crashreportcategory.addCrashSection("h", par5);
            throw new ReportedException(crashreport);
        }
    }

    public Biome[] getBiomes(Biome[] par1ArrayOfBiomeGenBase, final int par2, final int par3, final int par4,
                             final int par5, final boolean par6) {
        IntCache.resetIntCache();
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
            par1ArrayOfBiomeGenBase = new Biome[par4 * par5];
        }
        if (par6 && par4 == 16 && par5 == 16 && (par2 & 0xF) == 0x0 && (par3 & 0xF) == 0x0) {
            final Biome[] abiomegenbase1 = this.biomeCache.getCachedBiomes(par2, par3);
            System.arraycopy(abiomegenbase1, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
            return par1ArrayOfBiomeGenBase;
        }
        final int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);
        for (int i1 = 0; i1 < par4 * par5; ++i1) {
            par1ArrayOfBiomeGenBase[i1] = Biome.getBiome(aint[i1]);
        }
        return par1ArrayOfBiomeGenBase;
    }

    public boolean areBiomesViable(final int par1, final int par2, final int par3, final List par4List) {
        IntCache.resetIntCache();
        final int l = par1 - par3 >> 2;
        final int i1 = par2 - par3 >> 2;
        final int j1 = par1 + par3 >> 2;
        final int k1 = par2 + par3 >> 2;
        final int l2 = j1 - l + 1;
        final int i2 = k1 - i1 + 1;
        final int[] aint = this.genBiomes.getInts(l, i1, l2, i2);
        try {
            for (int j2 = 0; j2 < l2 * i2; ++j2) {
                final Biome biomegenbase = Biome.getBiome(aint[j2]);
                if (!par4List.contains(biomegenbase)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable throwable) {
            final CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            final CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
            crashreportcategory.addCrashSection("x", par1);
            crashreportcategory.addCrashSection("z", par2);
            crashreportcategory.addCrashSection("radius", par3);
            crashreportcategory.addCrashSection("allowed", par4List);
            throw new ReportedException(crashreport);
        }
    }

    public BlockPos findBiomePosition(final int x, final int z, final int range, final List<Biome> biomes,
                                      final Random random) {
        IntCache.resetIntCache();
        final int l = x - range >> 2;
        final int i1 = z - range >> 2;
        final int j1 = x + range >> 2;
        final int k1 = z + range >> 2;
        final int l2 = j1 - l + 1;
        final int i2 = k1 - i1 + 1;
        final int[] aint = this.genBiomes.getInts(l, i1, l2, i2);
        BlockPos pos = null;
        int j2 = 0;
        for (int k2 = 0; k2 < l2 * i2; ++k2) {
            final int l3 = l + k2 % l2 << 2;
            final int i3 = i1 + k2 / l2 << 2;
            final Biome biomegenbase = Biome.getBiome(aint[k2]);
            if (biomes.contains(biomegenbase)) {
                if (pos == null || random.nextInt(j2 + 1) == 0) {
                    pos = new BlockPos(l3, 0, i3);
                    ++j2;
                }
            }
        }
        return pos;
    }

    public void cleanupCache() {
        super.cleanupCache();
    }

    public GenLayer[] getModdedBiomeGenerators(final WorldType worldType, final long seed, final GenLayer[] original) {
        final WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.getNewBiomeGens();
    }

    static {
        xenoform.tartheus.world.NewHellBiomeProvider.allowedBiomes = Lists.newArrayList(
                (Biome[]) new Biome[] {Biomes.HELL, Biomes.PLAINS });
    }
}