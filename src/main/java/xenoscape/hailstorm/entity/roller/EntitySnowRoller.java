package xenoscape.hailstorm.entity.roller;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
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
import xenoscape.hailstorm.Hailstorm;
import xenoscape.hailstorm.entity.EntitySurfaceMonster;
import xenoscape.hailstorm.entity.ISnowCreature;
import xenoscape.hailstorm.entity.ai.EntityAIRollerAttack;

public class EntitySnowRoller extends EntitySurfaceMonster implements ISnowCreature {
	private float baseSize = 0.35F;
	private float size = baseSize;

	private static final DataParameter<Float> SIZE = EntityDataManager.<Float>createKey(EntitySnowRoller.class,
			DataSerializers.FLOAT);

	public EntitySnowRoller(World world) {
		super(world);
		this.setSize(size);
	}

	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAIWanderAvoidWater(this, 1.0D));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.tasks.addTask(2, new EntityAIRollerAttack(this, 0.8D, false));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SIZE, 0.35F);
	}

	public float getSize() {
		return this.dataManager.get(SIZE);
	}

	public void setSize(float s) {
		this.dataManager.set(SIZE, s);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		setSize(getSize(), getSize());
		if (getSize() <= 2.5F) {
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D + getSize() * 2);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
					.setBaseValue(0.23000000417232513D + getSize() / 8);
			this.heal(1.0f);

			if (!world.isRemote) {
				int i, j, k;

				for (int l = 0; l < 4; ++l) {
					i = MathHelper.floor(this.posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
					j = MathHelper.floor(this.posY);
					k = MathHelper.floor(this.posZ + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
					BlockPos blockpos = new BlockPos(i, j, k);

					if (this.world.getBlockState(blockpos) == Blocks.SNOW_LAYER.getDefaultState()) {
						this.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
						if (getSize() <= 1)
							this.setSize(size += .028);
						else
							this.setSize(size += .056);
					}
				}
			}
		}
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this),
				(float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

		if (flag) {
			if (entityIn instanceof EntityLivingBase) {
				((EntityLivingBase) entityIn).knockBack(this, baseSize + (size * 0.35f),
						(double) MathHelper.sin(this.rotationYaw * 0.017453292F),
						(double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}

			entityIn.attackEntityFrom(Hailstorm.ROLLER, 1 + getSize() * 2);

			if (getSize() > baseSize)
				this.dropItem(Items.SNOWBALL, (int) getSize() * 3);

			setSize(baseSize);
			size = baseSize;
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
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setSize(compound.getFloat("Size"));
	}

	public float getEyeHeight() {
		return 0.7f;
	}

	public int getTalkInterval() {
		return 160;
	}

	public int getHorizontalFaceSpeed() {
		return 500;
	}

	public int getVerticalFaceSpeed() {
		return 500;
	}
}
