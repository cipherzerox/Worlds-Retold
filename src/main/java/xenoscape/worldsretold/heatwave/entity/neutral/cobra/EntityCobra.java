package xenoscape.worldsretold.heatwave.entity.neutral.cobra;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.basic.EntitySurfaceMonster;

public class EntityCobra extends EntitySurfaceMonster
{
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityCobra.class, DataSerializers.BYTE);
    protected static final DataParameter<Byte> AGGRESSIVE = EntityDataManager.<Byte>createKey(EntityCobra.class, DataSerializers.BYTE);
    public float rearingRot;
    public float prevRearingRot;
    
    public EntityCobra(World worldIn)
    {
        super(worldIn);
        this.setSize(0.4F, 0.2F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityCobra.AICobraAttack(this, false));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityCobra.EntityAISeekShelter(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)(this.height * 0.5F);
    }

    /**
     * Returns new PathNavigateGround instance
     */
    protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateGround(this, worldIn);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(CLIMBING, Byte.valueOf((byte)0));
        this.dataManager.register(AGGRESSIVE, Byte.valueOf((byte)0));
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        
        
        if (this.getAttackTarget() == null && this.world.getClosestPlayerToEntity(this, 12D) != null && !this.world.getClosestPlayerToEntity(this, 12D).capabilities.disableDamage)
            this.setAttackTarget(this.world.getClosestPlayerToEntity(this, 12D));
        
        if (this.getAttackTarget() != null && this.getDistance(this.getAttackTarget()) > 12D)
        	this.setAttackTarget(null);
        
        if (this.world.isRemote)
        {
        	this.prevRearingRot = this.rearingRot;
        	
            if (this.isAggressive())
            {
                this.rearingRot = MathHelper.clamp(this.rearingRot + 0.1F, 0F, 1F);
            }
            else
            {
                this.rearingRot = MathHelper.clamp(this.rearingRot - 0.1F, 0F, 1F);
            }
        }
        
        this.setSize(0.4F, 0.2F + rearingRot);

        if (!this.world.isRemote)
        {
            this.setBesideClimbableBlock(this.collidedHorizontally);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public float getRearingRot(float p_189795_1_)
    {
        return (this.prevRearingRot + (this.rearingRot - this.prevRearingRot) * p_189795_1_);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isAggressive()
    {
        return ((Byte)this.dataManager.get(AGGRESSIVE)).byteValue() == 1;
    }

    public void setAggressive(boolean value)
    {
        int i = ((Byte)this.dataManager.get(AGGRESSIVE)).byteValue();

        if (value)
        {
            i = i | 1;
        }
        else
        {
            i = i & ~1;
        }

        this.dataManager.set(AGGRESSIVE, Byte.valueOf((byte)(i & 255)));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12D);
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_SILVERFISH_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SILVERFISH_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.2F, 0.9F);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return new ResourceLocation(WorldsRetold.MODID, "entity/scorpion");
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            if (entityIn instanceof EntityLivingBase)
            {
                int i = 20 * this.world.getDifficulty().getDifficultyId();

                if (i > 0)
                {
                    ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, i * 20, this.world.getDifficulty().getDifficultyId()));
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns true if this entity should move as if it were on a ladder (either because it's actually on a ladder, or
     * for AI reasons)
     */
    public boolean isOnLadder()
    {
        return this.isBesideClimbableBlock();
    }

    public boolean isPotionApplicable(PotionEffect potioneffectIn)
    {
        return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
    }

    /**
     * Returns true if the WatchableObject (Byte) is 0x01 otherwise returns false. The WatchableObject is updated using
     * setBesideClimableBlock.
     */
    public boolean isBesideClimbableBlock()
    {
        return (((Byte)this.dataManager.get(CLIMBING)).byteValue() & 1) != 0;
    }

    /**
     * Updates the WatchableObject (Byte) created in entityInit(), setting it to 0x01 if par1 is true or 0x00 if it is
     * false.
     */
    public void setBesideClimbableBlock(boolean climbing)
    {
        byte b0 = ((Byte)this.dataManager.get(CLIMBING)).byteValue();

        if (climbing)
        {
            b0 = (byte)(b0 | 1);
        }
        else
        {
            b0 = (byte)(b0 & -2);
        }

        this.dataManager.set(CLIMBING, Byte.valueOf(b0));
    }

    public float getEyeHeight()
    {
        return 0.1F + this.rearingRot;
    }
    
    public float getBlockPathWeight(BlockPos pos)
    {
        return 0.5F - this.world.getLightBrightness(pos);
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.canSeeSky(new BlockPos(this)) && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
    }

    public class AICobraAttack extends EntityAIBase
    {
        World world;
        protected EntityCobra attacker;
        /** An amount of decrementing ticks that allows the entity to attack once the tick reaches 0. */
        protected int attackTick;
        /** When true, the mob will continue chasing its target, even if it can't find a path to them right now. */
        boolean longMemory;
        /** The PathEntity of our entity. */
        Path path;
        private int delayCounter;
        private double targetX;
        private double targetY;
        private double targetZ;
        protected final int attackInterval = 20;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;

        public AICobraAttack(EntityCobra creature, boolean useLongMemory)
        {
            this.attacker = creature;
            this.world = creature.world;
            this.longMemory = useLongMemory;
            this.setMutexBits(3);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

            if (entitylivingbase == null)
            {
                return false;
            }
            else if (!entitylivingbase.isEntityAlive())
            {
                return false;
            }
            else
            {
            	return this.attacker.getDistance(entitylivingbase) <= 12D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

            if (entitylivingbase == null)
            {
                return false;
            }
            else if (!entitylivingbase.isEntityAlive())
            {
                return false;
            }
            else
            {
                return this.attacker.getDistance(entitylivingbase) <= 12D && (!(entitylivingbase instanceof EntityPlayer) || (entitylivingbase instanceof EntityPlayer && !((EntityPlayer)entitylivingbase).isSpectator() && !((EntityPlayer)entitylivingbase).isCreative()));
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            this.delayCounter = 0;
            this.attacker.setAggressive(true);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

            if (entitylivingbase instanceof EntityPlayer && (((EntityPlayer)entitylivingbase).isSpectator() || ((EntityPlayer)entitylivingbase).isCreative()))
                this.attacker.setAttackTarget((EntityLivingBase)null);

            this.attacker.setAggressive(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
            this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 180.0F, 30.0F);
            double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
            --this.delayCounter;
            this.attacker.getNavigator().clearPath();
            this.attackTick = Math.max(this.attackTick - 1, 0);
            this.checkAndPerformAttack(entitylivingbase, d0);
        }

        protected void checkAndPerformAttack(EntityLivingBase p_190102_1_, double p_190102_2_)
        {
            double d0 = this.getAttackReachSqr(p_190102_1_);

            if (p_190102_2_ <= d0 && this.attackTick <= 0)
            {
                this.attackTick = 10;
                this.attacker.swingArm(EnumHand.MAIN_HAND);
                this.attacker.attackEntityAsMob(p_190102_1_);
                this.attacker.renderYawOffset = this.attacker.rotationYaw = this.attacker.rotationYawHead;
            }
        }

        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return (double)(this.attacker.width * 3.0F * this.attacker.width * 3.0F + attackTarget.width);
        }
    }
    
    public class EntityAISeekShelter extends EntityAIBase
    {
        private final EntityCobra creature;
        private double shelterX;
        private double shelterY;
        private double shelterZ;
        private final double movementSpeed;
        private final World world;

        public EntityAISeekShelter(EntityCobra theCreatureIn, double movementSpeedIn)
        {
            this.creature = theCreatureIn;
            this.movementSpeed = movementSpeedIn;
            this.world = theCreatureIn.world;
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            if (!this.world.isDaytime())
            {
                return false;
            }
            else if (!this.world.canSeeSky(new BlockPos(this.creature.posX, this.creature.getEntityBoundingBox().minY, this.creature.posZ)))
            {
                return false;
            }
            else
            {
                Vec3d vec3d = this.findPossibleShelter();

                if (vec3d == null)
                {
                    return false;
                }
                else
                {
                    this.shelterX = vec3d.x;
                    this.shelterY = vec3d.y;
                    this.shelterZ = vec3d.z;
                    return true;
                }
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return !this.creature.getNavigator().noPath();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
        	this.creature.createNavigator(world);
        	if (!this.world.canSeeSky(new BlockPos(this.shelterX, this.shelterY, this.shelterZ)))
        		this.creature.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
        	else
        		this.creature.getNavigator().clearPath();
        }

        @Nullable
        private Vec3d findPossibleShelter()
        {
            Random random = this.creature.getRNG();
            BlockPos blockpos = new BlockPos(this.creature.posX, this.creature.getEntityBoundingBox().minY, this.creature.posZ);

            for (int i = 0; i < 10; ++i)
            {
                BlockPos blockpos1 = blockpos.add(random.nextInt(40) - 20, random.nextInt(10) - 5, random.nextInt(40) - 20);

                if (!this.world.canSeeSky(blockpos1) && this.creature.getBlockPathWeight(blockpos1) < 0.0F)
                {
                    return new Vec3d((double)blockpos1.getX(), (double)blockpos1.getY(), (double)blockpos1.getZ());
                }
            }

            return null;
        }
    }

    public static class GroupData implements IEntityLivingData
        {
            public Potion effect;

            public void setRandomEffect(Random rand)
            {
                int i = rand.nextInt(5);

                if (i <= 1)
                {
                    this.effect = MobEffects.SPEED;
                }
                else if (i <= 2)
                {
                    this.effect = MobEffects.STRENGTH;
                }
                else if (i <= 3)
                {
                    this.effect = MobEffects.REGENERATION;
                }
                else if (i <= 4)
                {
                    this.effect = MobEffects.INVISIBILITY;
                }
            }
        }
}