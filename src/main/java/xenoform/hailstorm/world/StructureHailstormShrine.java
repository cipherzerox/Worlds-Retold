package xenoform.hailstorm.world;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
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
	public boolean generate(World world, Random rand, BlockPos position) {
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = templatemanager.getTemplate(minecraftserver,
				new ResourceLocation(Hailstorm.MODID + ":hailstorm_shrine"));

		if (template == null) {
			System.out.println("NO STRUCTURE");
			return false;
		}

		if (WorldGenHailstorm.canSpawnHere(template, worldserver, position)
		// && world.getBiome(position) instanceof Biomes.
		) {
			IBlockState iblockstate = world.getBlockState(position);
			world.notifyBlockUpdate(position, iblockstate, iblockstate, 3);

			System.out.println("X:" + position.getX() + " Y:" + position.getY() + " Z:" + position.getZ());
			Rotation rotation = Rotation.NONE;
			switch (rand.nextInt(3)) {
			case 0:
				rotation = Rotation.NONE;
				break;
			case 1:
				rotation = Rotation.CLOCKWISE_180;
				break;
			case 2:
				rotation = Rotation.CLOCKWISE_90;
				break;
			case 3:
				rotation = Rotation.COUNTERCLOCKWISE_90;
				break;
			default:
				break;
			}

			PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(rotation)
					.setIgnoreEntities(false).setChunk((ChunkPos) null).setReplacedBlock((Block) null)
					.setIgnoreStructureBlock(false);

			position = position
					.subtract(Template.transformedBlockPos(placementsettings, new BlockPos(-15 / 2, -4 / 2, -13 / 2)));

			template.getDataBlocks(position, placementsettings);
			template.addBlocksToWorld(world, position.add(0, 1, 0), placementsettings);

			Map<BlockPos, String> map = template.getDataBlocks(position, placementsettings);

			for (Entry<BlockPos, String> entry : map.entrySet()) {
				if ("chest".equals(entry.getValue())) {
					BlockPos blockpos2 = entry.getKey();
					world.setBlockState(blockpos2.up(), Blocks.AIR.getDefaultState(), 3);
					TileEntity tileentity = world.getTileEntity(blockpos2);

					if (tileentity instanceof TileEntityChest) {
						((TileEntityChest) tileentity).setLootTable(LootTableList.CHESTS_SIMPLE_DUNGEON,
								rand.nextLong());
					}
				}
			}

			return true;
		}

		return false;
	}
}