package xenoscape.worldsretold.hailstorm.entity.passive.caribou;

import java.util.UUID;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCaribou extends EntityAnimal
{
    private static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityCaribou.class, DataSerializers.BOOLEAN);
    /** The tempt AI task for this mob, used to prevent taming while it is fleeing. */
    private EntityAITempt aiTempt;
    private EntityAIAvoidEntity<EntityPlayer> avoidEntity;
    
    public EntityCaribou(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.4F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIPanic(this, 1.5D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.5D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.75D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(TAMED, Boolean.valueOf(false));
    }
    
    public boolean isTamed()
    {
        return ((Boolean)this.dataManager.get(TAMED)).booleanValue();
    }

    public void setTamed(boolean attacking)
    {
        this.dataManager.set(TAMED, Boolean.valueOf(attacking));
        this.setupTamedAI();
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Tamed", this.isTamed());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setTamed(compound.getBoolean("Tamed"));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }
    
    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        if (this.hasCustomName())
        {
            return this.getCustomNameTag();
        }
        else
        {
            return this.isTamed() ? I18n.translateToLocal("entity.reindeer.name") : super.getName();
        }
    }

    protected void setupTamedAI()
    {
        if (this.avoidEntity == null)
            this.avoidEntity = new EntityAIAvoidEntity<EntityPlayer>(this, EntityPlayer.class, 24.0F, 1.25D, 1.5D);
        
        if (this.aiTempt == null)
        	this.aiTempt = new EntityAITempt(this, 0.5D, Items.WHEAT, true);

        if (this.isTamed())
        	this.aiTempt = new EntityAITempt(this, 1.0D, Items.WHEAT, false);
        else
        	this.aiTempt = new EntityAITempt(this, 0.5D, Items.WHEAT, true);

        this.tasks.removeTask(this.aiTempt);
        this.tasks.removeTask(this.avoidEntity);

        if (!this.isTamed())
        {
            this.tasks.addTask(1, this.aiTempt);
            this.tasks.addTask(2, this.avoidEntity);
        }
        
        if (this.isTamed())
        {
            this.tasks.addTask(1, this.aiTempt);
        }
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_COW_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_COW_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_COW;
    }
    
    /**
     * Play the taming effect, will either be hearts or smoke depending on status
     */
    protected void playTameEffect(boolean play)
    {
        EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;

        if (!play)
        {
            enumparticletypes = EnumParticleTypes.SMOKE_NORMAL;
        }

        for (int i = 0; i < 7; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.spawnParticle(enumparticletypes, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 7)
        {
            this.playTameEffect(true);
        }
        else if (id == 6)
        {
            this.playTameEffect(false);
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!this.isTamed() && (this.aiTempt == null || this.aiTempt.isRunning()) && itemstack.getItem() == Items.WHEAT && player.getDistanceSq(this) < 9.0D)
        {
            if (!player.capabilities.isCreativeMode)
            {
                itemstack.shrink(1);
            }

            if (!this.world.isRemote)
            {
                if (this.rand.nextInt(5) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
                {
                    this.setTamed(true);
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte)6);
                }
            }

            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    public EntityCaribou createChild(EntityAgeable ageable)
    {
    	EntityCaribou baby = new EntityCaribou(this.world);
    	
    	if (this.isTamed())
    		baby.setTamed(true);
    	
        return baby;
    }

    public float getEyeHeight()
    {
        return this.isChild() ? this.height : 1.3F;
    }
}
