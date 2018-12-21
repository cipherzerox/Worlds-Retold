package xenoscape.hailstorm.proxy;

import java.util.List;

import com.google.common.base.Predicates;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.Entity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.hailstorm.entity.hostile.guardsman.EntityGuardsman;
import xenoscape.hailstorm.entity.hostile.guardsman.RenderGuardsman;
import xenoscape.hailstorm.entity.neutral.automaton.EntityAutomaton;
import xenoscape.hailstorm.entity.neutral.automaton.RenderAutomaton;
import xenoscape.hailstorm.entity.neutral.sentinel.EntitySentinel;
import xenoscape.hailstorm.entity.neutral.sentinel.RenderSentinel;
import xenoscape.hailstorm.entity.passive.nix.EntityNix;
import xenoscape.hailstorm.entity.passive.nix.RenderNix;
import xenoscape.hailstorm.entity.passive.penguin.EntityPenguin;
import xenoscape.hailstorm.entity.passive.penguin.RenderPenguin;
import xenoscape.hailstorm.entity.projectiles.egg.EntityPenguinEgg;
import xenoscape.hailstorm.entity.projectiles.egg.RenderPenguinEgg;
import xenoscape.hailstorm.entity.projectiles.hail.EntityHail;
import xenoscape.hailstorm.entity.projectiles.hail.RenderHail;
import xenoscape.hailstorm.entity.projectiles.scroll.EntityIceScrollProjectile;
import xenoscape.hailstorm.entity.projectiles.scroll.RenderIceScrollProjectile;
import xenoscape.hailstorm.init.MBlocks;
import xenoscape.hailstorm.init.MClientEvents;

public class ClientProxy extends ServerProxy {

	public RayTraceResult getMouseOver(double d0) {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.getRenderViewEntity() != null) {
			if (mc.world != null) {
				float tickPart = MClientEvents.getPartialTick();
				RayTraceResult objectMouseOver = mc.getRenderViewEntity().rayTrace(d0, tickPart);
				double d1 = d0;
				Vec3d vec3 = mc.getRenderViewEntity().getPositionEyes(tickPart);

				if (objectMouseOver != null) {
					d1 = objectMouseOver.hitVec.distanceTo(vec3);
				}

				Vec3d vec31 = mc.getRenderViewEntity().getLook(tickPart);
				Vec3d vec32 = vec3.addVector(vec31.x * d0, vec31.y * d0, vec31.z * d0);
				Vec3d vec33 = null;
				Entity pointedEntity = null;
				List<Entity> list = mc.world.getEntitiesInAABBexcluding(mc.getRenderViewEntity(),
						mc.getRenderViewEntity().getEntityBoundingBox().expand(vec31.x * d0, vec31.y * d0, vec31.z * d0)
								.grow(1.0D, 1.0D, 1.0D),
						Predicates.and(input -> input != null && input.canBeCollidedWith(),
								EntitySelectors.NOT_SPECTATING));
				double d2 = d1;

				for (Entity entity : list) {
					double f2 = entity.getCollisionBorderSize();
					AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(f2);
					RayTraceResult movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

					if (axisalignedbb.contains(vec3)) {
						if (d2 >= 0.0D) {
							pointedEntity = entity;
							vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
							d2 = 0.0D;
						}
					} else if (movingobjectposition != null) {
						double d3 = vec3.distanceTo(movingobjectposition.hitVec);

						if (d3 < d2 || d2 == 0.0D) {
							if (entity.getLowestRidingEntity() == mc.getRenderViewEntity().getLowestRidingEntity()
									&& !mc.getRenderViewEntity().canRiderInteract()) {
								if (d2 == 0.0D) {
									pointedEntity = entity;
									vec33 = movingobjectposition.hitVec;
								}
							} else {
								pointedEntity = entity;
								vec33 = movingobjectposition.hitVec;
								d2 = d3;
							}
						}
					}
				}

				if (pointedEntity != null && (d2 < d1 || objectMouseOver == null)) {
					objectMouseOver = new RayTraceResult(pointedEntity, vec33);
				}

				return objectMouseOver;
			}
		}
		return null;
	}

	@Override
	protected void registerEventListeners() {
		super.registerEventListeners();
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void preInit(final FMLPreInitializationEvent event) {
		super.preInit(event);
		// Passive
		RenderingRegistry.registerEntityRenderingHandler(EntityPenguin.class, RenderPenguin.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityNix.class, RenderNix.FACTORY);

		// Neutral
		RenderingRegistry.registerEntityRenderingHandler(EntityAutomaton.class, RenderAutomaton.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityGuardsman.class, RenderGuardsman.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySentinel.class, RenderSentinel.FACTORY);

		// Projectile
		RenderingRegistry.registerEntityRenderingHandler(EntityHail.class, RenderHail.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityIceScrollProjectile.class,
				RenderIceScrollProjectile.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPenguinEgg.class, RenderPenguinEgg.FACTORY);
	}

	@Override
	public void init(final FMLInitializationEvent event) {
		super.init(event);
		regColours();
	}

	@Override
	public void postInit(final FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@SideOnly(Side.CLIENT)
	public static void regColours() {
		FMLClientHandler.instance().getClient().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
				return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos)
						: ColorizerGrass.getGrassColor(0.5D, 1.0D);
			}
		}, MBlocks.ARCTIC_WILLOW);
	}
}
