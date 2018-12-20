package xenoscape.hailstorm.entity.projectiles.scroll;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.hailstorm.Hailstorm;

public class EntityIceScrollProjectile extends EntityThrowable {
	public EntityIceScrollProjectile(World worldIn) {
		super(worldIn);
		this.setNoGravity(true);
		this.setSize(0.5F, 0.5F);
	}

	public EntityIceScrollProjectile(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
		this.setNoGravity(true);
		this.setSize(0.5F, 0.5F);
	}

	public EntityIceScrollProjectile(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.setNoGravity(true);
		this.setSize(0.5F, 0.5F);
	}

	public static void registerFixesIceScrollProjectile(DataFixer fixer) {
		EntityThrowable.registerFixesThrowable(fixer, "IceScrollProjectile");
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 3) {
			for (int i = 0; i < 8; ++i) {
				this.world.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, this.posX, this.posY, this.posZ, 0.0D, 0.0D,
						0.0D);
			}
		}
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			result.entityHit.attackEntityFrom(Hailstorm.ICE_SCROLL_PROJECTILE, 60);
		}

		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			this.setDead();
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.ticksExisted >= 20) {
			this.setDead();
		}
	}
}