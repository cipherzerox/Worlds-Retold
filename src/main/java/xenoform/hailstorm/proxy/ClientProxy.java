package xenoform.hailstorm.proxy;

import com.google.common.base.Predicates;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xenoform.hailstorm.MForgeEvents;

import java.lang.reflect.Field;
import java.util.List;

public class ClientProxy extends ServerProxy {
	
	public RayTraceResult getMouseOver(double d0) {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.getRenderViewEntity() != null) {
			if (mc.world != null) {
				float tickPart = MForgeEvents.getPartialTick();
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
	public void preInit(final FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(final FMLInitializationEvent event) {
		super.init(event);

	}

	@Override
	public void postInit(final FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}