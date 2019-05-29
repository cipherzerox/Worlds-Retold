package xenoscape.worldsretold.hellfire.entity.hostile.livingflame;


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
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.basic.EntitySurfaceMonster;
import xenoscape.worldsretold.hailstorm.init.HailstormSounds;
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;
import xenoscape.worldsretold.hellfire.entity.INetherCreature;

public class EntityLivingFlame extends EntitySurfaceMonster implements INetherCreature {
    protected static final DataParameter<Byte> AGGRESSIVE = EntityDataManager.<Byte>createKey(EntityLivingFlame.class, DataSerializers.BYTE);
    public float extraheight;
    public float prevextraheight;
    
    public EntityLivingFlame(World worldIn) {
        super(worldIn);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.setPathPriority(PathNodeType.LAVA, 8.0F);
        this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.isImmuneToFire = true;
        this.experienceValue = 10;
        this.setSize(0.75F, 0.1F);
        this.stepHeight = 2F;
    }

    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityLivingFlame.AIWait());
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(AGGRESSIVE, Byte.valueOf((byte) 0));
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
		this.prevRenderYawOffset = this.renderYawOffset = this.prevRotationYaw = this.rotationYaw = this.prevRotationYawHead = this.rotationYawHead = 180F;
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public boolean isAggressive() {
        return ((Byte) this.dataManager.get(AGGRESSIVE)).byteValue() == 1;
    }

    public void setAggressive(boolean value) {
        int i = ((Byte) this.dataManager.get(AGGRESSIVE)).byteValue();

        if (value) {
            i = i | 1;
            if (this.world.getBlockState(getPosition()).getBlock() == Blocks.FIRE)
    		this.world.setBlockState(getPosition(), Blocks.AIR.getDefaultState());
        } else {
            i = i & ~1;
        }

        this.dataManager.set(AGGRESSIVE, Byte.valueOf((byte) (i & 255)));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6D);

    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLOCK_FIRE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_FIRE_EXTINGUISH;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ITEM_BUCKET_EMPTY_LAVA;
    }

    public float getEyeHeight() {
        return this.height;
    }

    public int getMaxSpawnedInChunk() 
    {
        return 8;
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag)
        {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();

            if (this.isBurning() && this.rand.nextFloat() < f * 0.6F)
            {
                entityIn.setFire(8 * (int)f);
            }
        }

        return flag;
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
		else if (source == DamageSource.IN_FIRE) 
		{
			return false;
		} 
		else if (source == DamageSource.ON_FIRE) 
		{
			return false;
		} 
		else if (source == DamageSource.LAVA) 
		{
			return false;
		}  
		else if (source == DamageSource.IN_WALL) 
		{
			return false;
		} 
		else if (source == DamageSource.FALL) 
		{
			return false;
		} 
		else 
		{
			return super.attackEntityFrom(source, amount);
		}
	}
	
    public boolean isBurning()
    {
        return this.world.getBlockState(getPosition()).getBlock() != Blocks.FIRE && this.isEntityAlive();
    }
    
    protected boolean isValidLightLevel()
    {
        return true;
    }
    
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_BLAZE;
    }
    
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        return 15728880;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness()
    {
        return 1.0F;
    }
	
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	
    	if (this.isInLava() && this.getAttackTarget() != null)
    	{
            double d0 = this.getAttackTarget().posX - this.posX;
            double d1 = this.getAttackTarget().posZ - this.posZ;
            double d3 = d0 * d0 + d1 * d1;
            double d5 = (double)MathHelper.sqrt(d3);
            this.motionX += (d0 / d5 * 0.5D - this.motionX) * 0.6000000238418579D;
            this.motionZ += (d1 / d5 * 0.5D - this.motionZ) * 0.6000000238418579D;
    	}
    	
    	this.fallDistance = 0;
    	
    	if (this.getAttackTarget() != null && !this.getAttackTarget().isEntityAlive())
    		this.setAttackTarget(null);
    	
    	if (!this.world.isRemote)
    	{
        	if (this.isAggressive() || !this.onGround)
        	{
        		if (this.getAttackTarget() == null)
                    this.setAggressive(false);
            	this.setInvisible(false);
        		this.prevRenderYawOffset = this.renderYawOffset = this.prevRotationYaw = this.rotationYaw = this.prevRotationYawHead = this.rotationYawHead;
        	}
        	else
        	{
            	this.setInvisible(true);
        		if (this.world.isAirBlock(getPosition()) && this.isEntityAlive())
        		this.world.setBlockState(getPosition(), Blocks.FIRE.getDefaultState());
        		if (this.world.getBlockState(getPosition().down()).getBlock().isFlammable(world, getPosition().down(), EnumFacing.UP) && this.ticksExisted % 40 == 0)
        		{
        	        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() + 1D);
        	        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue() + 0.1D);
        		}
        		if (this.getAttackTarget() != null)
        		{
                    this.setAggressive(true);
                	this.world.playEvent((EntityPlayer)null, 1018, this.getPosition(), 0);
        		}
            	if (this.ticksExisted % 60 == 0)
            		this.heal(1F);
        	}
        	
            List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(0.25D, 0.125D, 0.25D).offset(-0.125D, 0D, -0.125D), null);

            if (!list.isEmpty() && this.isEntityAlive())
            {
                for (int l = 0; l < list.size(); ++l)
                {
                    Entity entity = list.get(l);
                    if (!entity.isImmuneToFire())
                    {
                        entity.attackEntityFrom(DamageSource.IN_FIRE, 1F);
                        if (!entity.isWet())
                        	entity.setFire(8);
                    }
                }
            }
            
            int i = MathHelper.floor(this.posX);
            int j = MathHelper.floor(this.posY);
            int k = MathHelper.floor(this.posZ);

            if (this.isWet() || (this.world.getBiome(new BlockPos(i, 0, k)).getTemperature(new BlockPos(i, j, k)) < (!this.world.canSeeSky(getPosition()) ? 0.75F : 2F) && this.world.getBiome(new BlockPos(i, 0, k)).getRainfall() > 0.5F && this.world.getBiome(new BlockPos(i, 0, k)) != Biomes.HELL))
            {
                this.attackEntityFrom(DamageSource.DROWN, 4F);
            }
    	}
    	
    	this.prevextraheight = this.extraheight;
    	
    	boolean height = this.world.getBlockState(this.getPosition()).isOpaqueCube();
    	boolean height2 = this.world.getBlockState(this.getPosition()).isSideSolid(world, this.getPosition(), EnumFacing.UP);
    	boolean height3 = this.world.getBlockState(this.getPosition().up()).isOpaqueCube();
    	boolean height4 = this.world.getBlockState(this.getPosition().up()).isSideSolid(world, this.getPosition(), EnumFacing.UP);
    	
        if (this.isAggressive() || !this.onGround)
        {
            this.extraheight = MathHelper.clamp(this.extraheight + 0.1F, 0F, height2 ? 0.1F : height ? 0.4F : height4 ? 0.9F : height3 ? 1.4F : 1.9F);
        }
        else
        {
            this.extraheight = MathHelper.clamp(this.extraheight - 0.1F, 0F, height2 ? 0.1F : height ? 0.4F : height4 ? 0.9F : height3 ? 1.4F : 1.9F);
        }

        this.setSize(0.75F, 0.1F);
        if (this.world.isRemote)
            this.setSize(0.75F, 0.1F + this.extraheight);
        
        if (this.isEntityAlive() && (this.isAggressive() || !this.onGround))
        {
            double d0 = (double)this.getPosition().getX() + rand.nextDouble();
            double d1 = (double)this.getPosition().getY() - 0.5D + rand.nextDouble() * 0.5D;
            double d2 = (double)this.getPosition().getZ() + rand.nextDouble();
            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
        else if (!this.isEntityAlive())
        {
            if (this.world.getBlockState(getPosition()).getBlock() == Blocks.FIRE)
    		this.world.setBlockState(getPosition(), Blocks.AIR.getDefaultState());
            for (int i = 0; i < 30 - (this.deathTime <= 28 ? this.deathTime : 28); ++i)
            {
                double d0 = (double)this.getPosition().getX() + rand.nextDouble();
                double d1 = (double)this.getPosition().getY() - 0.5D + rand.nextDouble() * (this.deathTime * 0.1D);
                double d2 = (double)this.getPosition().getZ() + rand.nextDouble();
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
    
	public boolean getCanSpawnHere() 
	{
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor(this.posZ);
		BlockPos blockpos = new BlockPos(i, j, k);
		return this.world.provider.getDimension() == -1 && this.world.getBlockState(blockpos.down()).getBlock() == Blocks.NETHERRACK && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
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
            return !EntityLivingFlame.this.isAggressive() && !EntityLivingFlame.this.isInLava();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
        	EntityLivingFlame.this.motionX = 0.0D;
        	EntityLivingFlame.this.motionY = EntityLivingFlame.this.motionY > 0D ? 0D : EntityLivingFlame.this.motionY;
        	EntityLivingFlame.this.motionZ = 0.0D;
        	EntityLivingFlame.this.prevRenderYawOffset = 180.0F;
        	EntityLivingFlame.this.renderYawOffset = 180.0F;
        	EntityLivingFlame.this.rotationYaw = 180.0F;
        	EntityLivingFlame.this.rotationYawHead = 180.0F;
        }
    }
}