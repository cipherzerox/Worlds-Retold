package xenoscape.worldsretold.heatwave.entity.neutral.antlion;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.basic.EntitySurfaceMonster;
import xenoscape.worldsretold.hailstorm.entity.passive.penguin.EntityPenguin;
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;
import xenoscape.worldsretold.heatwave.entity.hostile.fester.EntityFester;
import xenoscape.worldsretold.heatwave.entity.neutral.cobra.EntityCobra;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;

public class EntityAntlion extends EntitySurfaceMonster implements IDesertCreature
{
    protected static final DataParameter<Byte> AGGRESSIVE = EntityDataManager.<Byte>createKey(EntityAntlion.class, DataSerializers.BYTE);
    public EntityLivingBase heldEntity;
    public float mandableRot;
    public float prevMandableRot;
    
    public EntityAntlion(World worldIn)
    {
        super(worldIn);
        this.setSize(1.4F, 0.8F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIRestrictSun(this));
        this.tasks.addTask(2, new EntityAntlion.EntityAISeekShelter(this, 1.2D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityEnderman.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityAntlion.AIScorpionAttack(this));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityAntlion.AIScorpionTarget(this, EntityPlayer.class));
        this.targetTasks.addTask(3, new EntityAntlion.AIScorpionTarget(this, EntityIronGolem.class));
        this.targetTasks.addTask(7, new EntityAntlion.AIScorpionTarget(this, EntityCobra.class));
        this.targetTasks.addTask(6, new EntityAntlion.AIScorpionTarget(this, EntityBat.class));
        this.targetTasks.addTask(5, new EntityAntlion.AIScorpionTarget(this, EntityParrot.class));
        this.targetTasks.addTask(4, new EntityAntlion.AIScorpionTarget(this, EntityChicken.class));
        this.targetTasks.addTask(4, new EntityAntlion.AIScorpionTarget(this, EntityPenguin.class));
        this.targetTasks.addTask(5, new EntityAntlion.AIScorpionTarget(this, EntityPig.class));
        this.targetTasks.addTask(5, new EntityAntlion.AIScorpionTarget(this, EntitySheep.class));
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
        return this.world.isDaytime() && this.world.canBlockSeeSky(getPosition()) ? new PathNavigateGround(this, worldIn) : new PathNavigateClimber(this, worldIn);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(AGGRESSIVE, Byte.valueOf((byte)0));
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        
        if (this.isBeingRidden() && this.getPassengers() instanceof EntityCreature)
        {
            EntityCreature entitycreature = (EntityCreature)this.getPassengers();
            entitycreature.renderYawOffset = this.renderYawOffset;
        }

        if (this.heldEntity != null)
        {
        	this.renderYawOffset = this.rotationYaw = this.rotationYawHead;
        	this.heldEntity.renderYawOffset = this.heldEntity.rotationYaw = this.heldEntity.rotationYawHead;
        	float f2 = this.renderYawOffset * 3.1415927F / 180.0F;
        	float f19 = MathHelper.sin(f2);
        	float f3 = MathHelper.cos(f2);
        	this.heldEntity.fallDistance = 0F;
        	this.heldEntity.onGround = false;
        	this.heldEntity.isAirBorne = true;
        	this.heldEntity.setLocationAndAngles(this.posX + f19 * -1.5F, this.posY + 0.5D, this.posZ - f3 * -1.5F, this.rotationYawHead + 180F, 20F);
        	if (!this.heldEntity.isEntityAlive())
        		this.heldEntity = null;
        }
        
        if (this.world.isRemote)
        {
        	this.prevMandableRot = this.mandableRot;
        	
            if (this.isAggressive())
            {
                this.mandableRot = MathHelper.clamp(this.mandableRot + 0.1F, 0.0F, 1.0F);
            }
            else
            {
                this.mandableRot = MathHelper.clamp(this.mandableRot - 0.1F, 0.0F, 1.0F);
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public float getStingerBaseRot(float p_189795_1_)
    {
        return (this.prevMandableRot + (this.mandableRot - this.prevMandableRot) * p_189795_1_);
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.325D);
    }
    
    protected void updateAITasks()
    {
        super.updateAITasks();

        this.setAggressive(this.getAttackTarget() != null);
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SPIDER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_SPIDER_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SPIDER_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.2F, 0.9F);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return new ResourceLocation(WorldsRetold.MODID, "entity/scorpion");
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public float getEyeHeight()
    {
        return 0.65F;
    }
    
    public float getBlockPathWeight(BlockPos pos)
    {
        return 0.5F - this.world.getLightBrightness(pos);
    }

	public int getSpawnType()
	{
		return 2;
	}

    static class AIScorpionAttack extends EntityAIAttackMelee
        {
            public AIScorpionAttack(EntityAntlion spider)
            {
                super(spider, 1.0D, true);
            }

            /**
             * Returns whether an in-progress EntityAIBase should continue executing
             */
            public boolean shouldContinueExecuting()
            {
                float f = this.attacker.getBrightness();

                if ((f >= 0.5F || (this.attacker.getAttackTarget() != null && this.attacker.getAttackTarget().getBrightness() >= 0.5F)) && this.attacker.getRNG().nextInt(100) == 0)
                {
                    this.attacker.setAttackTarget((EntityLivingBase)null);
                    return false;
                }
                else
                {
                    return super.shouldContinueExecuting();
                }
            }

            protected double getAttackReachSqr(EntityLivingBase attackTarget)
            {
                return (double)(4.0F + attackTarget.width);
            }
        }

    static class AIScorpionTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T>
        {
            public AIScorpionTarget(EntityAntlion spider, Class<T> classTarget)
            {
                super(spider, classTarget, true);
            }

            /**
             * Returns whether the EntityAIBase should begin execution.
             */
            public boolean shouldExecute()
            {
                float f = this.taskOwner.getBrightness();
                return f >= 0.5F || (this.target != null && this.target.getBrightness() >= 0.5F) ? false : super.shouldExecute();
            }
        }
    
    public class EntityAISeekShelter extends EntityAIBase
    {
        private final EntityAntlion creature;
        private double shelterX;
        private double shelterY;
        private double shelterZ;
        private final double movementSpeed;
        private final World world;

        public EntityAISeekShelter(EntityAntlion theCreatureIn, double movementSpeedIn)
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