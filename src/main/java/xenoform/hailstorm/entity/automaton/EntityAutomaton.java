package xenoform.hailstorm.entity.automaton;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.Rotations;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoform.hailstorm.entity.EntitySurfaceMob;
import xenoform.hailstorm.entity.ai.EntityAIAutomatonAttackMelee;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Nullable;

public class EntityAutomaton extends EntitySurfaceMob {
	private static final DataParameter<Boolean> ACTIVE = EntityDataManager.createKey(EntityAutomaton.class,
			DataSerializers.BOOLEAN);
	protected float targetDistance;

	private Rotations headRotation;
	private Rotations bodyRotation;
	private Rotations leftArmRotation;
	private Rotations rightArmRotation;
	private Rotations leftLegRotation;
	private Rotations rightLegRotation;
	private static final Rotations DEFAULT_HEAD_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
	private static final Rotations DEFAULT_BODY_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
	private static final Rotations DEFAULT_LEFTARM_ROTATION = new Rotations(-10.0F, 0.0F, -10.0F);
	private static final Rotations DEFAULT_RIGHTARM_ROTATION = new Rotations(-15.0F, 0.0F, 10.0F);
	private static final Rotations DEFAULT_LEFTLEG_ROTATION = new Rotations(-1.0F, 0.0F, -1.0F);
	private static final Rotations DEFAULT_RIGHTLEG_ROTATION = new Rotations(1.0F, 0.0F, 1.0F);
	public static final DataParameter<Rotations> HEAD_ROTATION = EntityDataManager
			.<Rotations>createKey(EntityArmorStand.class, DataSerializers.ROTATIONS);
	public static final DataParameter<Rotations> BODY_ROTATION = EntityDataManager
			.<Rotations>createKey(EntityArmorStand.class, DataSerializers.ROTATIONS);
	public static final DataParameter<Rotations> LEFT_ARM_ROTATION = EntityDataManager
			.<Rotations>createKey(EntityArmorStand.class, DataSerializers.ROTATIONS);
	public static final DataParameter<Rotations> RIGHT_ARM_ROTATION = EntityDataManager
			.<Rotations>createKey(EntityArmorStand.class, DataSerializers.ROTATIONS);
	public static final DataParameter<Rotations> LEFT_LEG_ROTATION = EntityDataManager
			.<Rotations>createKey(EntityArmorStand.class, DataSerializers.ROTATIONS);
	public static final DataParameter<Rotations> RIGHT_LEG_ROTATION = EntityDataManager
			.<Rotations>createKey(EntityArmorStand.class, DataSerializers.ROTATIONS);

	public EntityAutomaton(World world) {
		super(world);
		this.headRotation = DEFAULT_HEAD_ROTATION;
		this.bodyRotation = DEFAULT_BODY_ROTATION;
		this.leftArmRotation = DEFAULT_LEFTARM_ROTATION;
		this.rightArmRotation = DEFAULT_RIGHTARM_ROTATION;
		this.leftLegRotation = DEFAULT_LEFTLEG_ROTATION;
		this.rightLegRotation = DEFAULT_RIGHTLEG_ROTATION;
	}
	
    public EntityAutomaton(World worldIn, double posX, double posY, double posZ)
    {
        this(worldIn);
        this.setPosition(posX, posY, posZ);
    }

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		tasks.addTask(0, new EntityAIAutomatonAttackMelee(this, 0.2D, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40D);
	}

	public boolean isActive() {
		return getDataManager().get(ACTIVE);
	}

	public void setActive(boolean isActive) {
		getDataManager().set(ACTIVE, isActive);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(ACTIVE, false);
		this.dataManager.register(HEAD_ROTATION, DEFAULT_HEAD_ROTATION);
		this.dataManager.register(BODY_ROTATION, DEFAULT_BODY_ROTATION);
		this.dataManager.register(LEFT_ARM_ROTATION, DEFAULT_LEFTARM_ROTATION);
		this.dataManager.register(RIGHT_ARM_ROTATION, DEFAULT_RIGHTARM_ROTATION);
		this.dataManager.register(LEFT_LEG_ROTATION, DEFAULT_LEFTLEG_ROTATION);
		this.dataManager.register(RIGHT_LEG_ROTATION, DEFAULT_RIGHTLEG_ROTATION);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (getAttackTarget() == null) {
			List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class,
					this.getEntityBoundingBox().expand(32.0D, 32.0D, 32.0D));
			for (EntityPlayer entity : list) {
				if (entity != null && !world.isRemote && !entity.isCreative()) {
					setAttackTarget(entity);
				}
			}
		}

		if (getAttackTarget() != null)
			targetDistance = getDistance(getAttackTarget());

		if (!world.isRemote) {
			if (!isAIDisabled()) {
				if (isActive()) {
					if (getAttackTarget() == null && moveForward == 0) {
						setActive(false);
					}
				} else if (getAttackTarget() != null && targetDistance <= 5) {
					setActive(true);
				}
			}
		}

		if (isActive() && targetDistance > 16 && !world.isRemote)
			setActive(false);

		if (!isActive()) {
			posX = prevPosX;
			posZ = prevPosZ;
			rotationYaw = prevRotationYaw;
		}

		if (!world.isRemote) {
			// System.out.println(getAttackTarget());
			// System.out.println(isActive());
			// System.out.println(targetDistance);
		}
		if (getAttackTarget() != null && isActive()) {
			getNavigator().tryMoveToEntityLiving(getAttackTarget(), .2D);
		}
	}
	
	public void onUpdate() {
		super.onUpdate();
		Rotations rotations = (Rotations) this.dataManager.get(HEAD_ROTATION);

		if (!this.headRotation.equals(rotations)) {
			this.setHeadRotation(rotations);
		}

		Rotations rotations1 = (Rotations) this.dataManager.get(BODY_ROTATION);

		if (!this.bodyRotation.equals(rotations1)) {
			this.setBodyRotation(rotations1);
		}

		Rotations rotations2 = (Rotations) this.dataManager.get(LEFT_ARM_ROTATION);

		if (!this.leftArmRotation.equals(rotations2)) {
			this.setLeftArmRotation(rotations2);
		}

		Rotations rotations3 = (Rotations) this.dataManager.get(RIGHT_ARM_ROTATION);

		if (!this.rightArmRotation.equals(rotations3)) {
			this.setRightArmRotation(rotations3);
		}

		Rotations rotations4 = (Rotations) this.dataManager.get(LEFT_LEG_ROTATION);

		if (!this.leftLegRotation.equals(rotations4)) {
			this.setLeftLegRotation(rotations4);
		}

		Rotations rotations5 = (Rotations) this.dataManager.get(RIGHT_LEG_ROTATION);

		if (!this.rightLegRotation.equals(rotations5)) {
			this.setRightLegRotation(rotations5);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("Active", isActive());
		compound.setTag("Pose", this.readPoseFromNBT());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		setActive(compound.getBoolean("Active"));
		NBTTagCompound nbttagcompound = compound.getCompoundTag("Pose");
		this.writePoseToNBT(nbttagcompound);
	}

	public void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		super.setEquipmentBasedOnDifficulty(difficulty);
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
	}

	protected boolean canEquipItem(ItemStack stack) {
		return super.canEquipItem(stack);
	}

	public EnumHandSide getPrimaryHand() {
		return EnumHandSide.RIGHT;
	}

	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		this.setEquipmentBasedOnDifficulty(difficulty);
		this.setEnchantmentBasedOnDifficulty(difficulty);
		return livingdata;
	}

	@Override
	public boolean canBePushed() {
		return isActive();
	}

	@Override
	public boolean canBeCollidedWith() {
		return super.canBeCollidedWith() && isActive();
	}

	public boolean attackable() {
		return isActive();
	}
	
	@Override
	public boolean isAIDisabled() {
		return false;
	}

	public void travel(float strafe, float vertical, float forward) {
		super.travel(strafe, vertical, forward);
	}

	private byte setBit(byte p_184797_1_, int p_184797_2_, boolean p_184797_3_) {
		if (p_184797_3_) {
			p_184797_1_ = (byte) (p_184797_1_ | p_184797_2_);
		} else {
			p_184797_1_ = (byte) (p_184797_1_ & ~p_184797_2_);
		}

		return p_184797_1_;
	}

	public void setHeadRotation(Rotations vec) {
		this.headRotation = vec;
		this.dataManager.set(HEAD_ROTATION, vec);
	}

	public void setBodyRotation(Rotations vec) {
		this.bodyRotation = vec;
		this.dataManager.set(BODY_ROTATION, vec);
	}

	public void setLeftArmRotation(Rotations vec) {
		this.leftArmRotation = vec;
		this.dataManager.set(LEFT_ARM_ROTATION, vec);
	}

	public void setRightArmRotation(Rotations vec) {
		this.rightArmRotation = vec;
		this.dataManager.set(RIGHT_ARM_ROTATION, vec);
	}

	public void setLeftLegRotation(Rotations vec) {
		this.leftLegRotation = vec;
		this.dataManager.set(LEFT_LEG_ROTATION, vec);
	}

	public void setRightLegRotation(Rotations vec) {
		this.rightLegRotation = vec;
		this.dataManager.set(RIGHT_LEG_ROTATION, vec);
	}

	public Rotations getHeadRotation() {
		return this.headRotation;
	}

	public Rotations getBodyRotation() {
		return this.bodyRotation;
	}

	@SideOnly(Side.CLIENT)
	public Rotations getLeftArmRotation() {
		return this.leftArmRotation;
	}

	@SideOnly(Side.CLIENT)
	public Rotations getRightArmRotation() {
		return this.rightArmRotation;
	}

	@SideOnly(Side.CLIENT)
	public Rotations getLeftLegRotation() {
		return this.leftLegRotation;
	}

	@SideOnly(Side.CLIENT)
	public Rotations getRightLegRotation() {
		return this.rightLegRotation;
	}

	public void setRenderYawOffset(float offset) {
		this.prevRenderYawOffset = this.prevRotationYaw = offset;
		this.prevRotationYawHead = this.rotationYawHead = offset;
	}

	public void setRotationYawHead(float rotation) {
		this.prevRenderYawOffset = this.prevRotationYaw = rotation;
		this.prevRotationYawHead = this.rotationYawHead = rotation;
	}

	protected float updateDistance(float p_110146_1_, float p_110146_2_) {
		this.prevRenderYawOffset = this.prevRotationYaw;
		this.renderYawOffset = this.rotationYaw;
		return 0.0F;
	}

	private NBTTagCompound readPoseFromNBT() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();

		if (!DEFAULT_HEAD_ROTATION.equals(this.headRotation)) {
			nbttagcompound.setTag("Head", this.headRotation.writeToNBT());
		}

		if (!DEFAULT_BODY_ROTATION.equals(this.bodyRotation)) {
			nbttagcompound.setTag("Body", this.bodyRotation.writeToNBT());
		}

		if (!DEFAULT_LEFTARM_ROTATION.equals(this.leftArmRotation)) {
			nbttagcompound.setTag("LeftArm", this.leftArmRotation.writeToNBT());
		}

		if (!DEFAULT_RIGHTARM_ROTATION.equals(this.rightArmRotation)) {
			nbttagcompound.setTag("RightArm", this.rightArmRotation.writeToNBT());
		}

		if (!DEFAULT_LEFTLEG_ROTATION.equals(this.leftLegRotation)) {
			nbttagcompound.setTag("LeftLeg", this.leftLegRotation.writeToNBT());
		}

		if (!DEFAULT_RIGHTLEG_ROTATION.equals(this.rightLegRotation)) {
			nbttagcompound.setTag("RightLeg", this.rightLegRotation.writeToNBT());
		}

		return nbttagcompound;
	}

	private void writePoseToNBT(NBTTagCompound tagCompound) {
		NBTTagList nbttaglist = tagCompound.getTagList("Head", 5);
		this.setHeadRotation(nbttaglist.hasNoTags() ? DEFAULT_HEAD_ROTATION : new Rotations(nbttaglist));
		NBTTagList nbttaglist1 = tagCompound.getTagList("Body", 5);
		this.setBodyRotation(nbttaglist1.hasNoTags() ? DEFAULT_BODY_ROTATION : new Rotations(nbttaglist1));
		NBTTagList nbttaglist2 = tagCompound.getTagList("LeftArm", 5);
		this.setLeftArmRotation(nbttaglist2.hasNoTags() ? DEFAULT_LEFTARM_ROTATION : new Rotations(nbttaglist2));
		NBTTagList nbttaglist3 = tagCompound.getTagList("RightArm", 5);
		this.setRightArmRotation(nbttaglist3.hasNoTags() ? DEFAULT_RIGHTARM_ROTATION : new Rotations(nbttaglist3));
		NBTTagList nbttaglist4 = tagCompound.getTagList("LeftLeg", 5);
		this.setLeftLegRotation(nbttaglist4.hasNoTags() ? DEFAULT_LEFTLEG_ROTATION : new Rotations(nbttaglist4));
		NBTTagList nbttaglist5 = tagCompound.getTagList("RightLeg", 5);
		this.setRightLegRotation(nbttaglist5.hasNoTags() ? DEFAULT_RIGHTLEG_ROTATION : new Rotations(nbttaglist5));
	}
}