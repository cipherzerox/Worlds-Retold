package xenoscape.worldsretold.heatwave.entity.neutral.scorpion;

import java.util.Random;
import javax.annotation.Nullable;
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
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
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
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;
import xenoscape.worldsretold.heatwave.entity.neutral.cobra.EntityCobra;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;

public class EntityScorpion extends EntitySurfaceMonster implements IDesertCreature
{
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityScorpion.class, DataSerializers.BYTE);
    protected static final DataParameter<Byte> AGGRESSIVE = EntityDataManager.<Byte>createKey(EntityScorpion.class, DataSerializers.BYTE);
    public EntityLivingBase heldEntity;
    public float stingerBaseRot;
    public float stinger1Rot;
    public float stinger2Rot;
    public float stinger3Rot;
    public float prevStingerBaseRot;
    public float prevStinger1Rot;
    public float prevStinger2Rot;
    public float prevStinger3Rot;
    
    public EntityScorpion(World worldIn)
    {
        super(worldIn);
        this.setSize(1.6F, 0.8F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIRestrictSun(this));
        this.tasks.addTask(2, new EntityScorpion.EntityAISeekShelter(this, 1.2D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityEnderman.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityScorpion.AIScorpionAttack(this));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityScorpion.AIScorpionTarget(this, EntityPlayer.class));
        this.targetTasks.addTask(3, new EntityScorpion.AIScorpionTarget(this, EntityIronGolem.class));
        this.targetTasks.addTask(4, new EntityScorpion.AIScorpionTarget(this, EntityChicken.class));
        this.targetTasks.addTask(5, new EntityScorpion.AIScorpionTarget(this, EntityPig.class));
        this.targetTasks.addTask(6, new EntityScorpion.AIScorpionTarget(this, EntityBat.class));
        this.targetTasks.addTask(7, new EntityScorpion.AIScorpionTarget(this, EntityCobra.class));
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
        this.dataManager.register(CLIMBING, Byte.valueOf((byte)0));
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
        	this.prevStingerBaseRot = this.stingerBaseRot;
        	this.prevStinger1Rot = this.stinger1Rot;
        	this.prevStinger2Rot = this.stinger2Rot;
        	this.prevStinger3Rot = this.stinger3Rot;
        	
            if (this.isAggressive())
            {
                this.stingerBaseRot = MathHelper.clamp(this.stingerBaseRot + 0.1F, -1.6F, -1.0471975511965976F);
                this.stinger1Rot = MathHelper.clamp(this.stinger1Rot + 0.1F, 0.0625F, 0.8726646259971648F);
                this.stinger2Rot = MathHelper.clamp(this.stinger2Rot + 0.1F, 0.0625F, 0.6981317007977318F);
                this.stinger3Rot = MathHelper.clamp(this.stinger3Rot + 0.1F, 0.0625F, 1.0471975511965976F);
            }
            else
            {
                this.stingerBaseRot = MathHelper.clamp(this.stingerBaseRot - 0.1F, -1.6F, -1.0471975511965976F);
                this.stinger1Rot = MathHelper.clamp(this.stinger1Rot - 0.1F, 0.0625F, 0.8726646259971648F);
                this.stinger2Rot = MathHelper.clamp(this.stinger2Rot - 0.1F, 0.0625F, 0.6981317007977318F);
                this.stinger3Rot = MathHelper.clamp(this.stinger3Rot - 0.1F, 0.0625F, 1.0471975511965976F);
            }
        }

        if (!this.world.isRemote)
        {
            this.setBesideClimbableBlock(this.collidedHorizontally);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public float getStingerBaseRot(float p_189795_1_)
    {
        return (this.prevStingerBaseRot + (this.stingerBaseRot - this.prevStingerBaseRot) * p_189795_1_);
    }
    
    @SideOnly(Side.CLIENT)
    public float getStinger1Rot(float p_189795_1_)
    {
        return (this.prevStinger1Rot + (this.stinger1Rot - this.prevStinger1Rot) * p_189795_1_);
    }
    
    @SideOnly(Side.CLIENT)
    public float getStinger2Rot(float p_189795_1_)
    {
        return (this.prevStinger2Rot + (this.stinger2Rot - this.prevStinger2Rot) * p_189795_1_);
    }
    
    @SideOnly(Side.CLIENT)
    public float getStinger3Rot(float p_189795_1_)
    {
        return (this.prevStinger3Rot + (this.stinger3Rot - this.prevStinger3Rot) * p_189795_1_);
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
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(2D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.325D);
    }
    
    protected void updateAITasks()
    {
        super.updateAITasks();

        this.setAggressive(this.getAttackTarget() != null);
        if (!this.world.canBlockSeeSky(getPosition()) && !(this.getNavigator() instanceof PathNavigateClimber))
        	this.createNavigator(world);
        
        if (this.getAttackTarget() != null)
        {
            double d = this.getDistanceSq(this.getAttackTarget());

            if (d >= 4.0D && d <= 16.0D && this.onGround && this.getRNG().nextInt(5) == 0)
            {
                double d0 = this.getAttackTarget().posX - this.posX;
                double d1 = this.getAttackTarget().posZ - this.posZ;
                float f = MathHelper.sqrt(d0 * d0 + d1 * d1);
                this.motionX += d0 / (double)f * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                this.motionZ += d1 / (double)f * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                this.jump();
            }
        }
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
    
    protected float getSoundPitch()
    {
    	return super.getSoundPitch() + (rand.nextFloat() * 0.2F - 0.1F);
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
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
        int i = 5 * 1 + (int)this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
        PotionEffect poison = new PotionEffect(HeatwavePotions.VENOM, i * 20, this.world.getDifficulty().getDifficultyId() - 1);
        
    	if (this.heldEntity == null && entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).isPotionApplicable(poison) && !((EntityLivingBase)entityIn).isPotionActive(HeatwavePotions.VENOM))
    	{
    		this.heldEntity = (EntityLivingBase)entityIn;
    		this.playSound(SoundEvents.ENTITY_GENERIC_HURT, 1F, 1F);
    		this.ticksExisted = 0;
            return true;
    	}
        if (super.attackEntityAsMob(entityIn))
        {
            if (this.heldEntity != null && entityIn == this.heldEntity && entityIn instanceof EntityLivingBase)
            {

                if (i > 0)
                {
                    ((EntityLivingBase)entityIn).addPotionEffect(poison);
                	this.heldEntity = null;
                }
            }
            else
            	this.swingArm(EnumHand.MAIN_HAND);

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

    /**
     * Sets the Entity inside a web block.
     */
    public void setInWeb()
    {
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
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

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

        if (this.world.rand.nextInt(100) == 0)
        {
            EntityHusk entityskeleton = new EntityHusk(this.world);
            entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entityskeleton.onInitialSpawn(difficulty, (IEntityLivingData)null);
            this.world.spawnEntity(entityskeleton);
            entityskeleton.startRiding(this);
        }

        if (livingdata == null)
        {
            livingdata = new EntityScorpion.GroupData();

            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty())
            {
                ((EntityScorpion.GroupData)livingdata).setRandomEffect(this.world.rand);
            }
        }

        if (livingdata instanceof EntityScorpion.GroupData)
        {
            Potion potion = ((EntityScorpion.GroupData)livingdata).effect;

            if (potion != null)
            {
                this.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE));
            }
        }

        return livingdata;
    }

    public float getEyeHeight()
    {
        return 0.65F;
    }
    
    public float getBlockPathWeight(BlockPos pos)
    {
        return 0.5F - this.world.getLightBrightness(pos);
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    protected boolean isValidLightLevel()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32))
        {
            return false;
        }
        else
        {
            int i = this.world.getLightFromNeighbors(blockpos);

            if (this.world.isThundering())
            {
                int j = this.world.getSkylightSubtracted();
                this.world.setSkylightSubtracted(10);
                i = this.world.getLightFromNeighbors(blockpos);
                this.world.setSkylightSubtracted(j);
            }

            return i <= this.rand.nextInt(8);
        }
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this) && this.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F;
    }

    static class AIScorpionAttack extends EntityAIAttackMelee
        {
            public AIScorpionAttack(EntityScorpion spider)
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
            public AIScorpionTarget(EntityScorpion spider, Class<T> classTarget)
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
        private final EntityScorpion creature;
        private double shelterX;
        private double shelterY;
        private double shelterZ;
        private final double movementSpeed;
        private final World world;

        public EntityAISeekShelter(EntityScorpion theCreatureIn, double movementSpeedIn)
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