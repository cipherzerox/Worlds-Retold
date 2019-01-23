package xenoscape.worldsretold.heatwave.entity.hostile.evilcactus;


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
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
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
    }

    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityEvilCactus.AIWait());
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
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
        this.dataManager.set(SIZE, Integer.valueOf(size));
        this.setSize(0.9375F, 0.99F + (float)size);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D + (double)(size * 20));
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4D + (double)(size * 2));

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
    
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else if (source == DamageSource.CACTUS) {
			return false;
		} else {
			return super.attackEntityFrom(source, amount);
		}
	}
	
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	
    	if (this.isAggressive())
    		this.prevRenderYawOffset = this.renderYawOffset = this.prevRotationYaw = this.rotationYaw = this.prevRotationYawHead = this.rotationYawHead;
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
        	EntityEvilCactus.this.motionY = 0.0D;
        	EntityEvilCactus.this.motionZ = 0.0D;
        	EntityEvilCactus.this.prevRenderYawOffset = 180.0F;
        	EntityEvilCactus.this.renderYawOffset = 180.0F;
        	EntityEvilCactus.this.rotationYaw = 180.0F;
        	EntityEvilCactus.this.rotationYawHead = 180.0F;
        }
    }
}