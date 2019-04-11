package xenoscape.worldsretold.heatwave.entity.hostile.anubite;

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

public class EntityAnubite extends EntitySurfaceMonster implements IDesertCreature
{
    /** The attribute which determines the chance that this mob will spawn reinforcements */
    protected static final IAttribute SPAWN_REINFORCEMENTS_CHANCE = (new RangedAttribute((IAttribute)null, "anubite.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
    private static final UUID BABY_SPEED_BOOST_ID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier BABY_SPEED_BOOST = new AttributeModifier(BABY_SPEED_BOOST_ID, "Baby speed boost", 0.5D, 1);
    private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.<Boolean>createKey(EntityAnubite.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Optional<BlockPos>> JUMP_BLOCK_POS = EntityDataManager.<Optional<BlockPos>>createKey(EntityAnubite.class, DataSerializers.OPTIONAL_BLOCK_POS);
    private static final DataParameter<Integer> JUMP_COOLDOWN = EntityDataManager.<Integer>createKey(EntityAnubite.class, DataSerializers.VARINT);
    /** The width of the entity */
    private float zombieWidth = -1.0F;
    /** The height of the the entity. */
    private float zombieHeight;

    public EntityAnubite(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.experienceValue = 10;
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
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3.0D);
        this.getAttributeMap().registerAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(this.rand.nextDouble() * 0.25D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.getDataManager().register(IS_CHILD, Boolean.valueOf(false));
        this.getDataManager().register(JUMP_BLOCK_POS, Optional.absent());
        this.getDataManager().register(JUMP_COOLDOWN, Integer.valueOf(0));
    }

    /**
     * If Animal, checks if the age timer is negative
     */
    public boolean isChild()
    {
        return ((Boolean)this.getDataManager().get(IS_CHILD)).booleanValue();
    }

    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer player)
    {
        if (this.isChild())
        {
            this.experienceValue = (int)((float)this.experienceValue * 2.5F);
        }

        return super.getExperiencePoints(player);
    }

    /**
     * Set whether this zombie is a child.
     */
    public void setChild(boolean childZombie)
    {
        this.getDataManager().set(IS_CHILD, Boolean.valueOf(childZombie));

        if (this.world != null && !this.world.isRemote)
        {
            IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
            iattributeinstance.removeModifier(BABY_SPEED_BOOST);

            if (childZombie)
            {
                iattributeinstance.applyModifier(BABY_SPEED_BOOST);
            }
        }

        this.setChildSize(childZombie);
    }

    public void notifyDataManagerChange(DataParameter<?> key)
    {
        if (IS_CHILD.equals(key))
        {
            this.setChildSize(this.isChild());
        }

        super.notifyDataManagerChange(key);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
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
        
        if (!this.world.isRemote && this.onGround && this.getJumpCooldown() <= 0 && this.getAttackTarget() != null && this.getDistanceSq(this.getAttackTarget()) <= 256D && this.getDistanceSq(this.getAttackTarget()) > 16D && this.canEntityBeSeen(this.getAttackTarget()))
        {
        	this.playSound(HailstormSounds.ENTITY_SNAKE_STRIKE, this.getSoundVolume(), this.getSoundPitch());
        	this.setJumpPos(this.getAttackTarget().getPosition());
    		this.getLookHelper().setLookPosition(this.getJumpPos().getX(), this.getJumpPos().getY(), this.getJumpPos().getZ(), 180F, 0F);
    		this.renderYawOffset = this.rotationYaw = this.rotationYawHead;
        	double d01 = this.getAttackTarget().posX - this.posX;
        	double d11 = this.getAttackTarget().posZ - this.posZ;
        	float f21 = MathHelper.sqrt(d01 * d01 + d11 * d11);
        	double hor = f21 / this.getDistance(this.getAttackTarget()) * 1.375D;
        	this.motionX = (d01 / f21 * hor * hor + this.motionX * hor);
        	this.motionZ = (d11 / f21 * hor * hor + this.motionZ * hor);
        	this.motionY = 1D + (this.getAttackTarget().posY - this.posY) * 0.1D;
            List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(1D), EntitySelectors.getTeamCollisionPredicate(this));

            if (!list.isEmpty())
            {
                for (int l = 0; l < list.size(); ++l)
                {
                    Entity entity = list.get(l);
                    if (!(entity instanceof EntityAnubite))
                    	entity.attackEntityFrom(new EntityDamageSource("worldsretold.leap", this), 2F);
                }
            }
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

        if (i > 0 && this.getAttackTarget() != null && this.getJumpCooldown() <= 0)
        {
            List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(2D), EntitySelectors.getTeamCollisionPredicate(this));

            if (!list.isEmpty())
            {
                for (int l = 0; l < list.size(); ++l)
                {
                    Entity entity = list.get(l);
                    if (!(entity instanceof EntityAnubite))
                    	entity.attackEntityFrom(new EntityDamageSource("worldsretold.leap", this), 4F);
                }
            }
            IBlockState state = this.world.getBlockState(this.getPosition().down());
            
        	this.setJumpCooldown(200);
        	this.world.playEvent(2001, this.getPosition(), Block.getStateId(this.world.getBlockState(this.getPosition().down())));

            if (!state.getBlock().isAir(state, world, this.getPosition().down()))
            {
                int i1 = (int)(150.0D * 2.5D);
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

            if (entitylivingbase != null && this.world.getDifficulty() == EnumDifficulty.HARD && (double)this.rand.nextFloat() < this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).getAttributeValue() && this.world.getGameRules().getBoolean("doMobSpawning"))
            {
                EntityAnubite entityzombie = new EntityAnubite(this.world);

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
                            this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).applyModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
                            entityzombie.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).applyModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
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
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag)
        {
        	this.playSound(HailstormSounds.ENTITY_SNAKE_STRIKE, 1F, 1F);
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();

            if (this.isBurning() && this.rand.nextFloat() < f * 0.3F)
            {
                entityIn.setFire(2 * (int)f);
            }
        }

        return flag;
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
        return HailstormSounds.ENTITY_ANUBITE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return HailstormSounds.ENTITY_ANUBITE_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return HailstormSounds.ENTITY_ANUBITE_DEATH;
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
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(HeatwaveItems.KHOPESH));
        this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(HeatwaveItems.KHOPESH));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("JC", this.getJumpCooldown());
        compound.setBoolean("IsBaby", this.isChild());
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
        this.setChild(compound.getBoolean("IsBaby"));
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
        float f = 1.914F;

        if (this.isChild())
        {
            f = (float)((double)f - 0.891D);
        }

        return f;
    }
    
    public float getBlockPathWeight(BlockPos pos)
    {
        return 0.5F - this.world.getLightBrightness(pos);
    }

	public int getSpawnType()
	{
		return 2;
	}

    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        if (wasRecentlyHit && this.rand.nextInt(12) == 0) {
            EntityItem entityitem = this.dropItem(HeatwaveItems.KHOPESH, 1);
            if (entityitem != null) {
                entityitem.setNoDespawn();
            }
        }
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
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.35F * f);

        if ((double)this.world.rand.nextFloat() < 0.05D)
        {
            this.setChild(true);
        }

        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);

        if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
        {
            Calendar calendar = this.world.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
            }
        }

        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.1D, 0));
        double d0 = this.rand.nextDouble() * 1.5D * (double)f;

        if (d0 > 1.0D)
        {
            this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random zombie-spawn bonus", d0, 2));
        }

        return livingdata;
    }

    /**
     * sets the size of the entity to be half of its current size if true.
     */
    public void setChildSize(boolean isChild)
    {
        this.multiplySize(isChild ? 0.5F : 1.0F);
    }

    /**
     * Sets the width and height of the entity.
     */
    protected final void setSize(float width, float height)
    {
        boolean flag = this.zombieWidth > 0.0F && this.zombieHeight > 0.0F;
        this.zombieWidth = width;
        this.zombieHeight = height;

        if (!flag)
        {
            this.multiplySize(1.0F);
        }
    }

    /**
     * Multiplies the height and width by the provided float.
     */
    protected final void multiplySize(float size)
    {
        super.setSize(this.zombieWidth * size, this.zombieHeight * size);
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset()
    {
        return this.isChild() ? 0.0D : -0.45D;
    }
}