package xenoform.hailstorm.entities.roller;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xenoform.hailstorm.MDamageSources;

public class EntitySnowRoller extends EntityMob
{
    private float size = 1;

    private static final DataParameter<Float> SIZE = EntityDataManager.<Float>createKey(EntitySnowRoller.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> SHRINK = EntityDataManager.<Boolean>createKey(EntitySnowRoller.class, DataSerializers.BOOLEAN);

    public EntitySnowRoller(World world)
    {
        super(world);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAIWanderAvoidWater(this, 1.0D, 1.0000001E-5F));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.8D, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SIZE, Float.valueOf(1));
        this.dataManager.register(SHRINK, Boolean.FALSE);
    }

    public float getSize()
    {
        return this.dataManager.get(SIZE).floatValue();
    }

    public void setSize(float s)
    {
        this.dataManager.set(SIZE, Float.valueOf(s));
    }

    public Boolean getShrink()
    {
        return this.dataManager.get(SHRINK).booleanValue();
    }

    public void setShrink(Boolean b)
    {
        this.dataManager.set(SHRINK, Boolean.valueOf(b));
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        this.setSize(getSize(), getSize());
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D + getSize());
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D + getSize() / 8);

        if(!world.isRemote)
        {
            int i, j, k;

            for (int l = 0; l < 4; ++l)
            {
                i = MathHelper.floor(this.posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                j = MathHelper.floor(this.posY);
                k = MathHelper.floor(this.posZ + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockpos = new BlockPos(i, j, k);

                if (this.world.getBlockState(blockpos) == Blocks.SNOW_LAYER.getDefaultState())
                {
                    this.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());

                    if(getSize() <= 4)
                        this.setSize(size += .05);
                }
            }
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        super.onCollideWithPlayer(entityIn);

        if(this.world.isRemote)
        {
            this.world.playSound(entityIn, this.getPosition(), SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.HOSTILE, 10F, 1F);

            for (int i = 0; i < 100; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                        this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                        (this.rand.nextDouble() - 0.5D), -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D));
            }
        }

        if (!this.world.isRemote)
        {
            entityIn.attackEntityFrom(MDamageSources.ROLLER, 1 + getSize() * 2);

            if(getSize() > 1)
                this.dropItem(Items.SNOWBALL, (int)getSize() * 2);

            setSize(1);
            size = 1;
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setFloat("Size", this.getSize());
        compound.setBoolean("Shrink", this.getShrink());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setSize(compound.getFloat("Size"));
        this.setShrink(compound.getBoolean("Shrink"));
    }
}
