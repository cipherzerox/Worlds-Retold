package xenoform.hailstorm.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xenoform.hailstorm.Hailstorm;

public class EntitySnowRoller extends EntityMob {
	private float size = 1;

	private static final DataParameter<Float> SIZE = EntityDataManager.<Float>createKey(EntitySnowRoller.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Boolean> SHRINK = EntityDataManager.<Boolean>createKey(EntitySnowRoller.class,
			DataSerializers.BOOLEAN);

	public EntitySnowRoller(World world) {
		super(world);
		this.setSize(1.25f, 1.25f);
	}

	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAIWanderAvoidWater(this, 1.0D));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
		this.tasks.addTask(2, new EntityAIRollerAttack(this, 0.8D, false));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SIZE, Float.valueOf(1));
		this.dataManager.register(SHRINK, Boolean.FALSE);
	}

	public float getSize() {
		return this.dataManager.get(SIZE).floatValue();
	}

	public void setSize(float s) {
		this.dataManager.set(SIZE, Float.valueOf(s));
	}

	public Boolean getShrink() {
		return this.dataManager.get(SHRINK).booleanValue();
	}

	public void setShrink(Boolean b) {
		this.dataManager.set(SHRINK, Boolean.valueOf(b));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D + getSize());
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
				.setBaseValue(0.23000000417232513D + getSize() / 8);

		if (!world.isRemote) {
			int i, j, k;

			for (int l = 0; l < 4; ++l) {
				i = MathHelper.floor(this.posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
				j = MathHelper.floor(this.posY);
				k = MathHelper.floor(this.posZ + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
				BlockPos blockpos = new BlockPos(i, j, k);

				if (this.world.getBlockState(blockpos) == Blocks.SNOW_LAYER.getDefaultState()) {
					this.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());

					if (getSize() <= 4)
						this.setSize(size += .01);
				}
			}
		}
	}

	/*
	 * @Override public void onCollideWithPlayer(EntityPlayer entityIn) {
	 * super.onCollideWithPlayer(entityIn);
	 * 
	 * if(this.world.isRemote) { this.world.playSound(entityIn,
	 * this.getPosition(), SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.HOSTILE,
	 * 10F, 1F);
	 * 
	 * for (int i = 0; i < 100; ++i) {
	 * this.world.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, this.posX +
	 * (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY +
	 * this.rand.nextDouble() * (double)this.height, this.posZ +
	 * (this.rand.nextDouble() - 0.5D) * (double)this.width,
	 * (this.rand.nextDouble() - 0.5D), -this.rand.nextDouble(),
	 * (this.rand.nextDouble() - 0.5D)); } } }
	 */

	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this),
				(float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

        if (flag) {
			if (entityIn instanceof EntityLivingBase) {
				((EntityLivingBase) entityIn).knockBack(this, 0.2f + (size * 0.35f),
						(double) MathHelper.sin(this.rotationYaw * 0.017453292F),
						(double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}

			entityIn.attackEntityFrom(Hailstorm.ROLLER, 1 + getSize() * 2);

			if (getSize() > 1)
				this.dropItem(Items.SNOWBALL, (int) getSize() * 2);

			setSize(1);
			size = 1;
		}
		return flag;
	}

	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) {
		if (damageSrc == DamageSource.ON_FIRE || damageSrc == DamageSource.HOT_FLOOR)
			super.damageEntity(damageSrc, damageAmount * 2);
		else if (damageSrc == DamageSource.LAVA)
			super.damageEntity(damageSrc, damageAmount * 3);
		else
			super.damageEntity(damageSrc, damageAmount);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setFloat("Size", this.getSize());
		compound.setBoolean("Shrink", this.getShrink());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setSize(compound.getFloat("Size"));
		this.setShrink(compound.getBoolean("Shrink"));
	}

}
