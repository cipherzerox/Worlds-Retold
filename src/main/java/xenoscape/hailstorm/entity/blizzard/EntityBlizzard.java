package xenoscape.hailstorm.entity.blizzard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xenoscape.hailstorm.entity.EntitySurfaceMonster;
import xenoscape.hailstorm.entity.ISnowCreature;
import xenoscape.hailstorm.entity.blizzard.hail.EntityHail;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class EntityBlizzard extends EntitySurfaceMonster implements EntityFlying, ISnowCreature {
	Random rand = new Random();
	public int deathTicks;

	public EntityBlizzard(World world) {
		super(world);
		setSize(7F, 4F);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_LIGHTNING_THUNDER;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_END_PORTAL_SPAWN;
	}

	protected float getSoundVolume() {
		return 2.5F;
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		this.tasks.addTask(0, new EntityAIWander(this, 1.8D));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 64));
	}

	public float getEyeHeight() {
		return this.height - 2F;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(.3D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(100D);
	}

	@Override
	public void onLivingUpdate() {
		if (this.isEntityAlive()) {
			if (getAttackTarget() == null) {
				List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class,
						this.getEntityBoundingBox().expand(32.0D, 32.0D, 32.0D));
				for (EntityPlayer entity : list) {
					if (entity != null)
						setAttackTarget(entity);
				}
			} else {
				this.getLookHelper().setLookPositionWithEntity(getAttackTarget(), 10.0f,
						(float) this.getVerticalFaceSpeed());
			}

			boolean minHeight = world.getBlockState(getPosition().down(8)) == Blocks.AIR.getDefaultState();

			if (!minHeight)
				motionY += .1;
			else
				motionY = 0;

			if (!this.world.isRemote && this.getAttackTarget() != null) {
				Entity entity = this.getAttackTarget();

				if (entity != null) {
					double d0 = entity.posX - this.posX;
					double d1 = entity.posZ - this.posZ;
					double d3 = d0 * d0 + d1 * d1;

					if (d3 > 9.0D) {
						double d5 = (double) MathHelper.sqrt(d3);
						this.motionX += (d0 / d5 * 0.5D - this.motionX) * 0.1000000238418579D;
						this.motionZ += (d1 / d5 * 0.5D - this.motionZ) * 0.1000000238418579D;
					}
				}
			}

			if (this.motionX * this.motionX + this.motionZ * this.motionZ >= 0.0025000011722640823D) {
				this.rotationYaw = (float) MathHelper.atan2(this.motionZ, this.motionX) * (180F / (float) Math.PI)
						- 90.0F;
			}

			super.onLivingUpdate();

			launchHailToCoords(posX, posY, posZ);
		}
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	/**
	 * Launches a Wither skull toward (par2, par4, par6)
	 */
	private void launchHailToCoords(double x, double y, double z) {
		double d0 = this.posX;
		double d1 = this.posY + 1.7;
		double d2 = this.posZ;
		double d4 = y - d1;
		EntityHail entityHail = new EntityHail(this.world, this, 0, d4, 0);

		double x0 = d0 - 2;
		double z0 = d2 - 2;

		entityHail.posX = x0 + rand.nextInt(5);
		entityHail.posY = d1;
		entityHail.posZ = z0 + rand.nextInt(5);

		world.spawnEntity(entityHail);
	}

	@Override
	public boolean hasNoGravity() {
		return true;
	}

	public boolean getCanSpawnHere() {
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor(this.posZ);
		BlockPos blockpos = new BlockPos(i, j, k);
		return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.rand.nextInt(20) == 1
				&& this.world.isRaining() && this.world.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS
				&& super.getCanSpawnHere();
	}

	public int getMaxSpawnedInChunk() {
		return 1;
	}

	protected void onDeathUpdate() {
		this.deathTicks++;
		if (this.deathTicks == 100 && !this.world.isRemote) {
			this.setDead();
		}
	}
}