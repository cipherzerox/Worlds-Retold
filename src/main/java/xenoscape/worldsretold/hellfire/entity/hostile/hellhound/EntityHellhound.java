package xenoscape.worldsretold.hellfire.entity.hostile.hellhound;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

import com.google.common.base.Optional;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.defaultmod.basic.EntitySurfaceMonster;
import xenoscape.worldsretold.hailstorm.init.HailstormItems;
import xenoscape.worldsretold.hailstorm.init.HailstormSounds;
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;
import xenoscape.worldsretold.heatwave.init.HeatwaveItems;
import xenoscape.worldsretold.hellfire.entity.INetherCreature;
import xenoscape.worldsretold.hellfire.init.HellfirePotions;

public class EntityHellhound extends EntitySurfaceMonster implements INetherCreature
{
    /** The attribute which determines the chance that this mob will spawn reinforcements */
    protected static final IAttribute SPAWN_REINFORCEMENTS_CHANCE = (new RangedAttribute((IAttribute)null, "anubite.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
    protected static final DataParameter<Optional<BlockPos>> JUMP_BLOCK_POS = EntityDataManager.<Optional<BlockPos>>createKey(EntityHellhound.class, DataSerializers.OPTIONAL_BLOCK_POS);
    private static final DataParameter<Integer> JUMP_COOLDOWN = EntityDataManager.<Integer>createKey(EntityHellhound.class, DataSerializers.VARINT);

    public EntityHellhound(World worldIn)
    {
        super(worldIn);
        this.setSize(0.75F, 0.95F);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.setPathPriority(PathNodeType.LAVA, 8.0F);
        this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.isImmuneToFire = true;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 0.75D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityAnimal.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySpider.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySnowman.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getAttributeMap().registerAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(this.rand.nextDouble() * 0.2D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.getDataManager().register(JUMP_BLOCK_POS, Optional.absent());
        this.getDataManager().register(JUMP_COOLDOWN, Integer.valueOf(0));
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        if (!this.world.isRemote && this.isInLava() && this.ticksExisted % 20 == 0)
        	this.heal(2);
        
        if (this.getRevengeTarget() != null && !this.getRevengeTarget().isEntityAlive())
        	this.setRevengeTarget(null);
        
        if (this.getAttackTarget() != null && !this.getAttackTarget().isEntityAlive())
        	this.setAttackTarget(null);
        
        if (this.getJumpCooldown() > 0)
        {
        	this.setJumpCooldown(this.getJumpCooldown() - 1);
        	if (!this.onGround && this.getJumpPos() != null)
        	{
        		this.renderYawOffset = this.rotationYaw = this.rotationYawHead;
        		this.getLookHelper().setLookPosition(this.getJumpPos().getX(), this.getJumpPos().getY(), this.getJumpPos().getZ(), 180F, 0F);
        	}
        }
        
        if (!this.world.isRemote && this.getAttackTarget() != null && this.onGround)
        	this.setJumpPos(this.getAttackTarget().getPosition());
        
        if (!this.world.isRemote && this.onGround && this.getJumpCooldown() <= 0 && this.getAttackTarget() != null && this.getDistanceSq(this.getAttackTarget()) <= 128D && this.getDistanceSq(this.getAttackTarget()) > 16D && this.canEntityBeSeen(this.getAttackTarget()))
        {
        	this.playSound(HailstormSounds.ENTITY_SNAKE_STRIKE, this.getSoundVolume(), this.getSoundPitch());
        	this.setJumpPos(this.getAttackTarget().getPosition());
    		this.getLookHelper().setLookPosition(this.getJumpPos().getX(), this.getJumpPos().getY(), this.getJumpPos().getZ(), 180F, 0F);
    		this.renderYawOffset = this.rotationYaw = this.rotationYawHead;
        	double d01 = this.getJumpPos().getX() - this.posX;
        	double d11 = this.getJumpPos().getZ() - this.posZ;
        	float f21 = MathHelper.sqrt(d01 * d01 + d11 * d11);
        	double hor = f21 / this.getDistance(getJumpPos().getX(), getJumpPos().getY(), getJumpPos().getZ()) * (this.getDistance(getJumpPos().getX(), getJumpPos().getY(), getJumpPos().getZ()) * 0.1D);
        	this.motionX = (d01 / f21 * hor * hor + this.motionX * hor);
        	this.motionZ = (d11 / f21 * hor * hor + this.motionZ * hor);
        	this.motionY = 0.6D + (this.getAttackTarget().posY - this.posY) * 0.1D;
        }
        
		if (this.getAttackTarget() != null) 
		{
			Entity entity = this.getAttackTarget();

			if (!entity.isEntityAlive())
				this.setAttackTarget(null);
		}
		else
		{
			
			List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(32D));
			
			for (EntityPlayer entity : list) 
			{
				if (entity != null && !entity.isCreative())
					setAttackTarget(entity);
			}
			
			List<EntityLivingBase> listother = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(32D));
			
			for (EntityLivingBase entity : listother) 
			{
				if (entity != null && entity.isEntityAlive() && (entity instanceof EntityVillager || entity instanceof EntityIronGolem))
					setAttackTarget(entity);
			}
		}
    }
    
    public void fall(float distance, float damageMultiplier)
    {
        float[] ret = net.minecraftforge.common.ForgeHooks.onLivingFall(this, distance, damageMultiplier);
        if (ret == null) return;
        distance = ret[0]; damageMultiplier = ret[1];
        if (this.isBeingRidden())
        {
            for (Entity entity : this.getPassengers())
            {
                entity.fall(distance, damageMultiplier);
            }
        }
        PotionEffect potioneffect = this.getActivePotionEffect(MobEffects.JUMP_BOOST);
        float f = potioneffect == null ? 0.0F : (float)(potioneffect.getAmplifier() + 1);
        int i = MathHelper.ceil((distance - 3.0F - f) * damageMultiplier);

        if (this.getAttackTarget() != null && this.getJumpCooldown() <= 0)
        {
            List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(2D), EntitySelectors.getTeamCollisionPredicate(this));

            if (!list.isEmpty())
            {
                for (int l = 0; l < list.size(); ++l)
                {
                    Entity entity = list.get(l);
                    if (!(entity instanceof EntityHellhound) && !(entity instanceof EntityWitherSkeleton))
                    	entity.attackEntityFrom(new EntityDamageSource("worldsretold.leap", this), 4F);
                }
            }
            IBlockState state = this.world.getBlockState(this.getPosition().down());
            
        	this.setJumpCooldown(100);

            if (!state.getBlock().isAir(state, world, this.getPosition().down()))
            {
                int i1 = (int)(50.0D * 2.5D);
                if (!state.getBlock().addLandingEffects(state, (WorldServer)this.world, this.getPosition().down(), state, this, i1))
                ((WorldServer)this.world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, i1, 0.0D, 0.0D, 0.0D, 0.15000000596046448D, Block.getStateId(state));
                ((WorldServer)this.world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, i1, 0.0D, 0.0D, 0.0D, 0.2000000596046448D, Block.getStateId(state));
            }
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (super.attackEntityFrom(source, amount))
        {
            EntityLivingBase entitylivingbase = this.getAttackTarget();

            if (entitylivingbase == null && source.getTrueSource() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)source.getTrueSource();
            }

            int i = MathHelper.floor(this.posX);
            int j = MathHelper.floor(this.posY);
            int k = MathHelper.floor(this.posZ);

            if (entitylivingbase != null && (double)this.rand.nextFloat() < this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).getAttributeValue() && this.world.getGameRules().getBoolean("doMobSpawning"))
            {
                EntityHellhound entityzombie = new EntityHellhound(this.world);

                for (int l = 0; l < 50; ++l)
                {
                    int i1 = i + MathHelper.getInt(this.rand, 4, 16) * MathHelper.getInt(this.rand, -1, 1);
                    int j1 = j + MathHelper.getInt(this.rand, 4, 16) * MathHelper.getInt(this.rand, -1, 1);
                    int k1 = k + MathHelper.getInt(this.rand, 4, 16) * MathHelper.getInt(this.rand, -1, 1);

                    if (this.world.getBlockState(new BlockPos(i1, j1 - 1, k1)).isSideSolid(this.world, new BlockPos(i1, j1 - 1, k1), net.minecraft.util.EnumFacing.UP))
                    {
                        entityzombie.setPosition((double)i1, (double)j1, (double)k1);

                        if (!this.world.isAnyPlayerWithinRangeAt((double)i1, (double)j1, (double)k1, 7.0D) && this.world.checkNoEntityCollision(entityzombie.getEntityBoundingBox(), entityzombie) && this.world.getCollisionBoxes(entityzombie, entityzombie.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(entityzombie.getEntityBoundingBox()))
                        {
                            this.world.spawnEntity(entityzombie);
                            if (entitylivingbase != null) entityzombie.setAttackTarget(entitylivingbase);
                            entityzombie.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityzombie)), (IEntityLivingData)null);
                            this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).applyModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.1D, 0));
                            break;
                        }
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (!super.attackEntityAsMob(entityIn))
        {
            return false;
        }
        else
        {
            if (entityIn instanceof EntityLivingBase)
            {
            	this.playSound(HailstormSounds.ENTITY_HELLHOUND_ATTCK, this.getSoundVolume(), this.getSoundPitch());
                ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(HellfirePotions.HELLFIRE, 200));
            }

            return true;
        }
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
    
    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return this.onGround && this.isEntityAlive() && !this.isOnLadder();
    }
    
    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return this.onGround && !this.isDead;
    }

    protected SoundEvent getAmbientSound()
    {
        return HailstormSounds.ENTITY_HELLHOUND_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return HailstormSounds.ENTITY_HELLHOUND_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return HailstormSounds.ENTITY_HELLHOUND_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_ZOMBIE;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("JC", this.getJumpCooldown());
        if (compound.hasKey("JPX"))
        {
            int i = compound.getInteger("JPX");
            int j = compound.getInteger("JPY");
            int k = compound.getInteger("JPZ");
            this.dataManager.set(JUMP_BLOCK_POS, Optional.of(new BlockPos(i, j, k)));
        }
        else
        {
            this.dataManager.set(JUMP_BLOCK_POS, Optional.absent());
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setJumpCooldown(compound.getInteger("JC"));
        BlockPos blockpos = this.getJumpPos();

        if (blockpos != null)
        {
            compound.setInteger("JPX", blockpos.getX());
            compound.setInteger("JPY", blockpos.getY());
            compound.setInteger("JPZ", blockpos.getZ());
        }
    }

    @Nullable
    public BlockPos getJumpPos()
    {
        return (BlockPos)((Optional)this.dataManager.get(JUMP_BLOCK_POS)).orNull();
    }

    public void setJumpPos(@Nullable BlockPos pos)
    {
        this.dataManager.set(JUMP_BLOCK_POS, Optional.fromNullable(pos));
    }

    public int getJumpCooldown()
    {
        return ((Integer)this.dataManager.get(JUMP_COOLDOWN)).intValue();
    }

    public void setJumpCooldown(int time)
    {
        this.dataManager.set(JUMP_COOLDOWN, Integer.valueOf(time));
    }

    public float getEyeHeight()
    {
        return this.height * 0.9F;
    }

	public int getSpawnType()
	{
		return 4;
	}

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        float f = difficulty.getClampedAdditionalDifficulty();

        if (this.world.rand.nextInt(100) == 0)
        {
            EntityWitherSkeleton entityskeleton = new EntityWitherSkeleton(this.world);
            entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entityskeleton.onInitialSpawn(difficulty, (IEntityLivingData)null);
            this.world.spawnEntity(entityskeleton);
            entityskeleton.startRiding(this);
        }

        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.1D, 0));
        double d0 = this.rand.nextDouble() * 1.5D * (double)f;

        if (d0 > 1.0D)
        {
            this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random zombie-spawn bonus", d0, 2));
        }

        return livingdata;
    }
}