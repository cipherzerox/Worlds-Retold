package xenoscape.worldsretold.heatwave.entity.hostile.antlion;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
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
import xenoscape.worldsretold.hailstorm.init.HailstormSounds;
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;
import xenoscape.worldsretold.heatwave.entity.hostile.evilcactus.EntityEvilCactus;
import xenoscape.worldsretold.heatwave.entity.hostile.fester.EntityFester;
import xenoscape.worldsretold.heatwave.entity.neutral.cobra.EntityCobra;
import xenoscape.worldsretold.heatwave.entity.projectiles.EntityThrownSand;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;

public class EntityAntlion extends EntitySurfaceMonster implements IRangedAttackMob, IDesertCreature
{
    protected static final DataParameter<Byte> DUGIN = EntityDataManager.<Byte>createKey(EntityAntlion.class, DataSerializers.BYTE);
    protected static final DataParameter<Byte> AGGRESSIVE = EntityDataManager.<Byte>createKey(EntityAntlion.class, DataSerializers.BYTE);
    public float mandableRot;
    public float prevMandableRot;
    
    public EntityAntlion(World worldIn)
    {
        super(worldIn);
        this.setSize(1.6F, 0.8F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackRanged(this, 0D, 60, 16F)
        {
            public boolean shouldExecute()
            {
                return EntityAntlion.this.isDugIn() && super.shouldExecute();
            }
        });
        this.tasks.addTask(2, new AISitInPlace());
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 0.8D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)(this.height * 0.5F);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(AGGRESSIVE, Byte.valueOf((byte)0));
        this.dataManager.register(DUGIN, Byte.valueOf((byte)0));
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
    
    public boolean isDugIn()
    {
        return ((Byte)this.dataManager.get(DUGIN)).byteValue() == 1;
    }

    public void setDugIn(boolean value)
    {
        int i = ((Byte)this.dataManager.get(DUGIN)).byteValue();

        if (value)
        {
            i = i | 1;
        }
        else
        {
            i = i & ~1;
        }

        this.dataManager.set(DUGIN, Byte.valueOf((byte)(i & 255)));
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
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.275D);
    }
    
    protected void updateAITasks()
    {
        super.updateAITasks();

        this.setAggressive(this.getAttackTarget() != null);
        
        if (this.isDugIn())
        {
        	this.motionX = 0.0D;
        	this.motionY = this.motionY > 0D ? 0D : this.motionY;
        	this.motionZ = 0.0D;
        }
        
    	if (!this.world.isRemote && !this.isDugIn() && this.getAttackTarget() == null && this.world.getBlockState(this.getPosition().down()).getMaterial().isSolid() && rand.nextInt(50) == 0 && this.ticksExisted % 20 == 0)
    		this.setDugIn(true);
    	
    	if (!this.world.isRemote && this.isDugIn() && this.getAttackTarget() != null && this.getDistance(this.getAttackTarget()) <= 4D)
    	{
    		this.setDugIn(false);
    		this.jump();
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
        return 0.3F;
    }
    
    public float getBlockPathWeight(BlockPos pos)
    {
        return 0.5F - this.world.getLightBrightness(pos);
    }

	public int getSpawnType()
	{
		return 2;
	}
	

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
    	EntityThrownSand entitysnowball = new EntityThrownSand(this.world, this);
    	entitysnowball.posY = this.posY + 1D;
        double d1 = target.posX - this.posX;
        double d2 = (target.posY + (target.height / 3)) - (entitysnowball.posY);
        double d3 = target.posZ - this.posZ;
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        entitysnowball.shoot(d1, d2 + (double)f, d3, 1.6F, 1.0F);
        this.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entitysnowball);
        if (!target.isEntityAlive())
        	this.setAttackTarget(null);
    }

	public void setSwingingArms(boolean swingingArms) {}
	

    class AISitInPlace extends EntityAIBase
    {
        public AISitInPlace()
        {
            this.setMutexBits(7);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return EntityAntlion.this.isDugIn();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
        	EntityAntlion.this.motionX = 0.0D;
        	EntityAntlion.this.motionY = EntityAntlion.this.motionY > 0D ? 0D : EntityAntlion.this.motionY;
        	EntityAntlion.this.motionZ = 0.0D;
        }
    }
}