package xenoform.hailstorm.entity.passive.penguin;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.entity.EntitySurfaceMonster;
import xenoform.hailstorm.entity.ISnowCreature;
import xenoform.hailstorm.entity.ai.EntityAIAutomatonAttackMelee;
import xenoform.hailstorm.entity.projectiles.hail.EntityHail;
import xenoform.hailstorm.init.MItems;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class EntityPenguin extends EntityAnimal {
	private static final DataParameter<Boolean> IS_SLIDING = EntityDataManager.<Boolean>createKey(EntityPenguin.class,
			DataSerializers.BOOLEAN);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.FISH);
	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;

	public int timeUntilNextEgg;

	public EntityPenguin(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 1.1F);
		this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
		this.setPathPriority(PathNodeType.WATER, 0.0F);
	}

	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAITempt(this, 1.25D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(3, new EntityPenguin.AISlideAway(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(3, new EntityPenguin.AISlideAway(this, EntityArrow.class, 16.0F));
		this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(IS_SLIDING, Boolean.valueOf(false));
	}

	public float getEyeHeight() {
		return this.height - 0.15F;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 2) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

		if (!this.onGround && this.wingRotDelta < 1.0F) {
			this.wingRotDelta = 1.0F;
		}

		this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);

		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.8D;
		}

		this.wingRotation += this.wingRotDelta * 2.0F;

		if (!this.world.isRemote && !this.isChild() && --this.timeUntilNextEgg <= 0) {
			this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F,
					(this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.dropItem(MItems.PENGUIN_EGG, 1);
			this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
		}
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_CHICKEN_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_CHICKEN_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_CHICKEN_DEATH;
	}

	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		return new ResourceLocation(Hailstorm.MODID, "entity/penguin");
	}

	public EntityPenguin createChild(EntityAgeable ageable) {
		return new EntityPenguin(this.world);
	}

	public boolean isSliding() {
		return ((Boolean) this.dataManager.get(IS_SLIDING)).booleanValue();
	}

	public void setSliding(boolean standing) {
		this.dataManager.set(IS_SLIDING, Boolean.valueOf(standing));
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	public boolean isBreedingItem(ItemStack stack) {
		return TEMPTATION_ITEMS.contains(stack.getItem());
	}

	/**
	 * Get the experience points the entity currently has.
	 */
	protected int getExperiencePoints(EntityPlayer player) {
		return super.getExperiencePoints(player);
	}

	public static void registerFixesPenguin(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, EntityPenguin.class);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasKey("EggLayTime")) {
			this.timeUntilNextEgg = compound.getInteger("EggLayTime");
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("EggLayTime", this.timeUntilNextEgg);
	}

	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		if (rand.nextInt(8) == 0) {
			if (livingdata instanceof EntityPenguin.GroupData) {
				if (((EntityPenguin.GroupData) livingdata).madeParent) {
					this.setGrowingAge(-24000);
				}
			} else {
				EntityPenguin.GroupData entitypenguin$groupdata = new EntityPenguin.GroupData();
				entitypenguin$groupdata.madeParent = true;
				livingdata = entitypenguin$groupdata;
			}
		}
		return livingdata;
	}

	public boolean getCanSpawnHere() {
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor(this.posZ);
		BlockPos blockpos = new BlockPos(i, j, k);
		return this.world.provider.getDimension() == 0
				&& this.world.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS && super.getCanSpawnHere();
	}

	static class GroupData implements IEntityLivingData {
		public boolean madeParent;

		private GroupData() {
		}
	}

	class AISlideAway extends EntityAIAvoidEntity {

		private EntityPenguin entity;
		private Class entityToAvoid;
		private float avoidDistance;

		public AISlideAway(EntityPenguin entityIn, Class entityToAvoidIn, float avoidDistanceIn) {
			super(entityIn, entityToAvoidIn, avoidDistanceIn, 1.75D, 2.0D);
			this.entity = entityIn;
			this.entityToAvoid = entityToAvoidIn;
			this.avoidDistance = avoidDistanceIn;
		}

		public void startExecuting() {
			EntityPenguin.this.setSliding(true);
			super.startExecuting();
		}

		public void resetTask() {
			EntityPenguin.this.setSliding(false);
			super.resetTask();
		}
	}
}