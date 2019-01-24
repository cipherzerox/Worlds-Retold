package xenoscape.worldsretold.heatwave.entity.hostile.evilcactus;


import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
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
import xenoscape.worldsretold.hailstorm.init.HailstormSounds;
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;

public class EntityEvilCactus extends EntitySurfaceMonster implements IDesertCreature {
    protected static final DataParameter<Byte> AGGRESSIVE = EntityDataManager.<Byte>createKey(EntityEvilCactus.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> SIZE = EntityDataManager.<Integer>createKey(EntityEvilCactus.class, DataSerializers.VARINT);

    public EntityEvilCactus(World worldIn) {
        super(worldIn);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.setPathPriority(PathNodeType.DAMAGE_CACTUS, 0.0F);
        this.setPathPriority(PathNodeType.DANGER_CACTUS, 0.0F);
        this.setSize(0.9375F, 0.99F + this.getSize() - 1);
    }

    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityEvilCactus.AIWait());
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
    
    public boolean isPreventingPlayerRest(EntityPlayer playerIn)
    {
        return this.isAggressive();
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(AGGRESSIVE, Byte.valueOf((byte) 0));
        this.dataManager.register(SIZE, Integer.valueOf(1));
    }

    protected void setSize(int size, boolean resetHealth)
    {
    	if (size < 1)
    		size = 1;
        this.dataManager.set(SIZE, Integer.valueOf(size));
        this.setSize(0.9375F, 0.99F + (float)size - 1);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(9D + (double)(size));
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)(size * 20));
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D + (double)(size * 2));

        if (resetHealth)
        {
            this.setHealth(this.getMaxHealth());
        }

        this.experienceValue = size;
    }

    /**
     * Returns the size of the slime.
     */
    public int getSize()
    {
        return ((Integer)this.dataManager.get(SIZE)).intValue();
    }
    
    public void notifyDataManagerChange(DataParameter<?> key)
    {
        if (SIZE.equals(key))
        {
            int i = this.getSize();
            this.setSize(0.9375F, 0.99F + (float)i - 1);
            this.rotationYaw = this.rotationYawHead;
            this.renderYawOffset = this.rotationYawHead;

            if (this.isInWater() && this.rand.nextInt(20) == 0)
            {
                this.doWaterSplashEffect();
            }
        }

        super.notifyDataManagerChange(key);
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        int i = this.rand.nextInt(3);

        if (this.rand.nextFloat() < 0.5F * difficulty.getClampedAdditionalDifficulty())
        {
            ++i;
        }

        if (this.rand.nextFloat() < 0.05F * difficulty.getClampedAdditionalDifficulty())
        {
            ++i;
        }

        if (this.rand.nextFloat() < 0.001F * difficulty.getClampedAdditionalDifficulty())
        {
            ++i;
        }
    	
        this.setSize(i, true);
		this.prevRenderYawOffset = this.renderYawOffset = this.prevRotationYaw = this.rotationYaw = this.prevRotationYawHead = this.rotationYawHead = 180F;
        return super.onInitialSpawn(difficulty, livingdata);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Size", this.getSize() - 1);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        int i = compound.getInteger("Size");

        if (i < 0)
        {
            i = 0;
        }

        this.setSize(i + 1, false);
    }

    public boolean isAggressive() {
        return ((Byte) this.dataManager.get(AGGRESSIVE)).byteValue() == 1;
    }

    public void setAggressive(boolean value) {
        int i = ((Byte) this.dataManager.get(AGGRESSIVE)).byteValue();

        if (value) {
            i = i | 1;
        } else {
            i = i & ~1;
        }

        this.dataManager.set(AGGRESSIVE, Byte.valueOf((byte) (i & 255)));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    protected SoundEvent getAmbientSound() {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENCHANT_THORNS_HIT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_CLOTH_BREAK;
    }

    /**
     * Returns the <b>solid</b> collision bounding box for this entity. Used to make (e.g.) boats solid. Return null if
     * this entity is not solid.
     *  
     * For general purposes, use {@link #width} and {@link #height}.
     *  
     * @see getEntityBoundingBox
     */
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.isEntityAlive() && !this.isAggressive() ? this.getEntityBoundingBox() : null;
    }

	protected ResourceLocation getLootTable() 
	{
		return new ResourceLocation(WorldsRetold.MODID, "entity/cobra");
	}

    public float getEyeHeight() {
        return this.height - 0.5F;
    }

	public int getSpawnType()
	{
		return 5;
	}

    public int getMaxSpawnedInChunk() 
    {
        return 1;
    }
    
	public boolean attackEntityFrom(DamageSource source, float amount) 
	{
		if (this.isEntityInvulnerable(source)) 
		{
			return false;
		} 
		else if (source == DamageSource.CACTUS) 
		{
			return false;
		} 
		else 
		{
	        if (!source.isMagicDamage() && source.getImmediateSource() instanceof EntityLivingBase)
	        {
	            EntityLivingBase entitylivingbase = (EntityLivingBase)source.getImmediateSource();

	            if (!source.isExplosion())
	            {
	                entitylivingbase.attackEntityFrom(DamageSource.CACTUS, 1F);
	            }
	        }
			return super.attackEntityFrom(source, amount);
		}
	}
	
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	
    	if (this.getAttackTarget() != null && !this.getAttackTarget().isEntityAlive())
    		this.setAttackTarget(null);
    	
    	if (!this.world.isRemote)
    	{
        	if (this.isAggressive())
        	{
        		if (this.getAttackTarget() == null)
                    this.setAggressive(false);
            	this.setInvisible(false);
        		this.prevRenderYawOffset = this.renderYawOffset = this.prevRotationYaw = this.rotationYaw = this.prevRotationYawHead = this.rotationYawHead;
        	}
        	else
        	{
        		if (this.getAttackTarget() != null)
        		{
                    this.setAggressive(true);
                	this.world.playEvent(2001, this.getPosition().down(), Block.getStateId(this.world.getBlockState(this.getPosition().down())));
                    this.motionY += 0.6D;
        		}
            	this.setInvisible(true);
            	if (this.ticksExisted % 60 == 0)
            		this.heal(1F);
        	}
        	
            List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(0.25D, 0.125D, 0.25D).offset(-0.125D, 0D, -0.125D), null);

            if (!list.isEmpty() && this.isEntityAlive())
            {
                for (int l = 0; l < list.size(); ++l)
                {
                    Entity entity = list.get(l);
                    entity.attackEntityFrom(DamageSource.CACTUS, 1F);
                }
            }
    	}
    }
    
	public boolean getCanSpawnHere() 
	{
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor(this.posZ);
		BlockPos blockpos = new BlockPos(i, j, k);
		return this.world.provider.getDimension() == 0 && this.world.getBlockState(blockpos.south()).getBlock() == Blocks.AIR && this.world.getBlockState(blockpos.north()).getBlock() == Blocks.AIR && this.world.getBlockState(blockpos.west()).getBlock() == Blocks.AIR && this.world.getBlockState(blockpos.east()).getBlock() == Blocks.AIR && this.world.canSeeSky(new BlockPos(this)) && this.world.getBlockState(blockpos.down()).getBlock() == Blocks.SAND && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
	}

    class AIWait extends EntityAIBase
    {
        public AIWait()
        {
            this.setMutexBits(7);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return !EntityEvilCactus.this.isAggressive();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
        	EntityEvilCactus.this.motionX = 0.0D;
        	EntityEvilCactus.this.motionY = EntityEvilCactus.this.motionY > 0D ? 0D : EntityEvilCactus.this.motionY;
        	EntityEvilCactus.this.motionZ = 0.0D;
        	EntityEvilCactus.this.prevRenderYawOffset = 180.0F;
        	EntityEvilCactus.this.renderYawOffset = 180.0F;
        	EntityEvilCactus.this.rotationYaw = 180.0F;
        	EntityEvilCactus.this.rotationYawHead = 180.0F;
        }
    }
}