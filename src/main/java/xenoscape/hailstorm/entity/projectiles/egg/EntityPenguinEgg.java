package xenoscape.hailstorm.entity.projectiles.egg;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xenoscape.hailstorm.entity.penguin.EntityPenguin;
=======
import xenoscape.hailstorm.entity.penguin.EntityPenguin;
>>>>>>> 2f34effc0ad705294dd03b18a13038d17d9b8376:src/main/java/xenoscape/hailstorm/entity/projectiles/egg/EntityPenguinEgg.java

public class EntityPenguinEgg extends EntityThrowable {
	public EntityPenguinEgg(World worldIn) {
		super(worldIn);
	}

	public EntityPenguinEgg(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityPenguinEgg(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public static void registerFixesEgg(DataFixer fixer) {
		EntityThrowable.registerFixesThrowable(fixer, "ThrownPenguinEgg");
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
		}

		if (!this.world.isRemote) {
			if (this.rand.nextInt(8) == 0) {
				int i = 1;

				if (this.rand.nextInt(32) == 0) {
					i = 4;
				}

				for (int j = 0; j < i; ++j) {
					EntityPenguin entitychicken = new EntityPenguin(this.world);
					entitychicken.setGrowingAge(-24000);
					entitychicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
					this.world.spawnEntity(entitychicken);
				}
			}

			this.world.setEntityState(this, (byte) 3);
			this.setDead();
		}
	}
}
