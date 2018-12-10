package xenoform.hailstorm.world;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;
import xenoform.hailstorm.Hailstorm;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class StructureHailstormShrine extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos origin) {
		WorldServer worldServer = (WorldServer) world;
		MinecraftServer server = world.getMinecraftServer();
		TemplateManager templateManager = worldServer.getStructureTemplateManager();
		Template template = templateManager.getTemplate(server,
				new ResourceLocation(Hailstorm.MODID + ":hailstorm_shrine"));

		if (template == null) {
			System.out.println("NO STRUCTURE");
			return false;
		}

		PlacementSettings settings = (new PlacementSettings())
				.setMirror(Mirror.values()[rand.nextInt(Mirror.values().length)])
				.setRotation(Rotation.values()[rand.nextInt(Rotation.values().length)]).setIgnoreEntities(false);

		BlockPos size = template.getSize();
		BlockPos generatePos = center(settings, origin, size.getX(), size.getZ());

		BlockPos max = generatePos.add(Template.transformedBlockPos(settings, template.getSize()));
		if (WorldGenHailstorm.canSpawnHere(worldServer, generatePos, max)
				&& (world.getBiome(generatePos) == Biomes.ICE_PLAINS
						|| world.getBiome(generatePos) == Biomes.ICE_MOUNTAINS
						|| world.getBiome(generatePos) == Biomes.COLD_TAIGA
						|| world.getBiome(generatePos) == Biomes.COLD_TAIGA_HILLS
						|| world.getBiome(generatePos) == Biomes.MUTATED_TAIGA_COLD
						|| world.getBiome(generatePos) == Biomes.MUTATED_ICE_FLATS)) {
			System.out.println("X:" + origin.getX() + " Y:" + origin.getY() + " Z:" + origin.getZ());

			template.addBlocksToWorld(world, generatePos.up(), settings);

			BlockPos c1 = generatePos.add(template.getSize().getX(), 0, 0);
            BlockPos c2 = generatePos;
            BlockPos c3 = generatePos.add(template.getSize().getX(), 0, template.getSize().getZ());
            BlockPos c4 = generatePos.add(0, 0, template.getSize().getZ());

            BlockPos[] corners = new BlockPos[] { c1, c2, c3, c4 };
            for (BlockPos corner : corners) {
                for (BlockPos pos : BlockPos.getAllInBoxMutable(corner, corner.up(10))) {
                    world.setBlockState(pos, Blocks.REDSTONE_BLOCK.getDefaultState());
                }
            }

			Map<BlockPos, String> dataBlocks = template.getDataBlocks(generatePos, settings);
			for (Entry<BlockPos, String> entry : dataBlocks.entrySet()) {
				handleDataBlock(world, rand, entry.getKey(), entry.getValue());
			}

			return true;
		}

		return false;
	}

	private void handleDataBlock(World world, Random rand, BlockPos pos, String key) {
		if ("chest".equals(key)) {
			world.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 3);
			TileEntity entity = world.getTileEntity(pos);
			if (entity instanceof TileEntityChest) {
				((TileEntityChest) entity).setLootTable(
						new ResourceLocation(Hailstorm.MODID + ":hailstorm_shrine_chest"), rand.nextLong());
			}
		}
	}

	private BlockPos center(PlacementSettings settings, BlockPos pos, int sizeX, int sizeZ) {
		return pos.subtract(Template.transformedBlockPos(settings, new BlockPos(sizeX / 2, 0, sizeZ / 2)));
	}
}
