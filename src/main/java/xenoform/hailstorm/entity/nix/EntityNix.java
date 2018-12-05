package xenoform.hailstorm.entity.nix;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityNix extends EntityCreature {
    private int size = 0;
    private int stage = 1;
    private static final DataParameter<Float> SIZE = EntityDataManager.<Float>createKey(EntityNix.class, DataSerializers.FLOAT);
    private static final DataParameter<Integer> STAGE = EntityDataManager.<Integer>createKey(EntityNix.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> SHRINK = EntityDataManager.<Boolean>createKey(EntityNix.class, DataSerializers.BOOLEAN);

    public EntityNix(World world) {
        super(world);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIWander(this, .8D));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if(stage == 1)
            this.setSize(1.0F, 1.0F);
        else if(stage == 2)
            setSize(2.0F, 2.0F);
        else if(stage == 3)
            setSize(2.8F, 2.8F);

        if(world.getBlockState(this.getPosition()) == Blocks.WATER.getDefaultState() || world.getBlockState(this.getPosition()) == Blocks.FLOWING_WATER.getDefaultState() ||
                world.getWorldInfo().isRaining() || world.getWorldInfo().isThundering() && !world.isRemote){
            if(ticksExisted % 20 == 0){
                size += 1;
            }
        }
        if(size % 20 == 0 && size > 0){
            stage++;
            size = 0;
        }

        if(stage == 4){
            world.setBlockState(this.getPosition().down(), Blocks.PACKED_ICE.getDefaultState());
            stage = 0;
            setShrink(true);
        }

        setStage(stage);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SIZE, Float.valueOf(1));
        this.dataManager.register(SHRINK, Boolean.FALSE);
        this.dataManager.register(STAGE, Integer.valueOf(1));
    }

    public float getSize() {
        return this.dataManager.get(SIZE).floatValue();
    }

    public void setSize(float s) {
        this.dataManager.set(SIZE, Float.valueOf(s));
    }

    public int getStage(){
        return this.dataManager.get(STAGE);
    }

    public void setStage(int i) {
        this.dataManager.set(STAGE, Integer.valueOf(i));
    }

    public Boolean getShrink() {
        return this.dataManager.get(SHRINK).booleanValue();
    }

    public void setShrink(Boolean b) {
        this.dataManager.set(SHRINK, Boolean.valueOf(b));
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_GLASS_PLACE;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_GLASS_BREAK;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setFloat("Size", this.getSize());
        compound.setBoolean("Shrink", this.getShrink());
        compound.setInteger("Stage", this.getStage());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSize(compound.getFloat("Size"));
        this.setShrink(compound.getBoolean("Shrink"));
        this.setStage(compound.getInteger("Stage"));
    }
}
