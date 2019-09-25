package xenoscape.worldsretold.hailstorm.world.structure;

import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Rotations;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.entity.automaton.EntityAutomaton;
import xenoscape.worldsretold.hailstorm.world.WorldGenHailstorm;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class StructureIceTower extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos origin) {
		WorldServer worldServer = (WorldServer) world;
		MinecraftServer server = world.getMinecraftServer();
		TemplateManager templateManager = worldServer.getStructureTemplateManager();
		Template template = templateManager.getTemplate(server,
				new ResourceLocation(WorldsRetold.MODID + ":ice_tower"));

		if (template == null) {
			System.out.println("NO STRUCTURE");
			return false;
		}

		PlacementSettings settings = (new PlacementSettings())
				.setRotation(Rotation.values()[rand.nextInt(Rotation.values().length)]).setIgnoreEntities(false);

		BlockPos size = template.getSize();
		BlockPos generatePos = center(settings, origin, size.getX(), size.getZ());

		BlockPos max = generatePos.add(Template.transformedBlockPos(settings, template.getSize()));
		if (WorldGenHailstorm.canSpawnHere(worldServer, generatePos, max)) {
			System.out.println("X:" + origin.getX() + " Y:" + origin.getY() + " Z:" + origin.getZ());

			template.addBlocksToWorld(world, generatePos.up(1), settings);

			Map<BlockPos, String> dataBlocks = template.getDataBlocks(generatePos, settings);
			for (Entry<BlockPos, String> entry : dataBlocks.entrySet()) {
				handleDataBlock(world, rand, entry.getKey(), entry.getValue());
			}

			return true;
		}

		return false;
	}

	private void handleDataBlock(World world, Random rand, BlockPos pos, String key) {
		if ("chest_1".equals(key)) {
			TileEntity entity = world.getTileEntity(pos);
			if (entity instanceof TileEntityChest) {
				((TileEntityChest) entity).setLootTable(
						new ResourceLocation(WorldsRetold.MODID, "chest/hailstorm_shrine_chest"), rand.nextLong());
			}
		}

		if ("chest_2".equals(key)) {
			TileEntity entity = world.getTileEntity(pos);
			if (entity instanceof TileEntityChest) {
				((TileEntityChest) entity).setLootTable(
						new ResourceLocation(WorldsRetold.MODID, "chest/hailstorm_shrine_chest"), rand.nextLong());
			}
		}

		if ("ent_1".equals(key)) {
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY() + 3;
			double d2 = (double) pos.getZ() + 0.5D;
			EntityAutomaton entity = new EntityAutomaton(world, d0, d1, d2);
			entity.setLocationAndAngles(d0, d1, d2, 135.0F, 0.0F);
			entity.setEquipmentBasedOnDifficulty(world.getDifficultyForLocation(pos));
			entity.enablePersistence();
			this.applyRandomRotations(entity, world.rand);
			world.spawnEntity(entity);
		}

		if ("ent_2".equals(key)) {
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY() + 3;
			double d2 = (double) pos.getZ() + 0.5D;
			EntityAutomaton entity = new EntityAutomaton(world, d0, d1, d2);
			entity.setLocationAndAngles(d0, d1, d2, 225.0F, 0.0F);
			entity.setEquipmentBasedOnDifficulty(world.getDifficultyForLocation(pos));
			entity.enablePersistence();
			this.applyRandomRotations(entity, world.rand);
			world.spawnEntity(entity);
		}
	}

	private void applyRandomRotations(EntityAutomaton armorStand, Random rand) {
		Rotations rotations = armorStand.getHeadRotation();
		float f = rand.nextFloat() * 5.0F;
		float f1 = rand.nextFloat() * 20.0F - 10.0F;
		Rotations rotations1 = new Rotations(rotations.getX() + f, rotations.getY() + f1, rotations.getZ());
		armorStand.setHeadRotation(rotations1);
		rotations = armorStand.getBodyRotation();
		f = rand.nextFloat() * 10.0F - 5.0F;
		rotations1 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
		armorStand.setBodyRotation(rotations1);
	}

	private BlockPos center(PlacementSettings settings, BlockPos pos, int sizeX, int sizeZ) {
		return pos.subtract(Template.transformedBlockPos(settings, new BlockPos(sizeX / 2, 0, sizeZ / 2)));
	}
}
