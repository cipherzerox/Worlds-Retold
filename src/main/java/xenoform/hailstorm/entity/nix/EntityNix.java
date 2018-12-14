package xenoform.hailstorm.entity.nix;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.entity.ISnowCreature;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityNix extends EntityCreature implements ISnowCreature {
    private int size = 0;
    private int stage = 1;
    private static final DataParameter<Float> SIZE = EntityDataManager.createKey(EntityNix.class, DataSerializers.FLOAT);
    private static final DataParameter<Integer> STAGE = EntityDataManager.createKey(EntityNix.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> SHRINK = EntityDataManager.createKey(EntityNix.class, DataSerializers.BOOLEAN);

    private static Random random = new Random();

    public EntityNix(World world) {
        super(world);
        this.moveHelper = new NixMoveHelper(this);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAILookIdle(this));
        this.tasks.addTask(3, new EntityNix.AINixFaceRandom(this));
        this.tasks.addTask(5, new EntityNix.AINixHop(this));
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
    
	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) {
		if (damageSrc == DamageSource.ON_FIRE || damageSrc == DamageSource.HOT_FLOOR)
			super.damageEntity(damageSrc, damageAmount * 2);
		else if (damageSrc == DamageSource.LAVA)
			super.damageEntity(damageSrc, damageAmount * 3);
		else if (damageSrc == Hailstorm.FROSTBITE || damageSrc == Hailstorm.HAIL)
			super.damageEntity(damageSrc, damageAmount * 0);
		else
			super.damageEntity(damageSrc, damageAmount);
	}

    /**
     * Returns true if the Nix makes a sound when it jumps (based upon the Nix's size)
     */
    protected boolean makesSoundOnJump()
    {
        return this.getStage() > 0;
    }

    protected SoundEvent getJumpSound()
    {
        return getStage() == 0 ? SoundEvents.ENTITY_SMALL_SLIME_JUMP : SoundEvents.ENTITY_SLIME_JUMP;
    }

    /**
     * Gets the amount of time the Nix needs to wait between jumps.
     */
    protected int getJumpDelay()
    {
        return this.rand.nextInt(20) + 10;
    }

    static class AINixFaceRandom extends EntityAIBase
    {
        private final EntityNix Nix;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public AINixFaceRandom(EntityNix NixIn)
        {
            this.Nix = NixIn;
            this.setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return this.Nix.getAttackTarget() == null && (this.Nix.onGround || this.Nix.isInWater() || this.Nix.isInLava() || this.Nix.isPotionActive(MobEffects.LEVITATION));
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            if (--this.nextRandomizeTime <= 0)
            {
                this.nextRandomizeTime = 40 + this.Nix.getRNG().nextInt(60);
                this.chosenDegrees = (float)this.Nix.getRNG().nextInt(360);
            }

            ((EntityNix.NixMoveHelper)this.Nix.getMoveHelper()).setDirection(this.chosenDegrees, false);
        }
    }


    static class AINixHop extends EntityAIBase
    {
        private final EntityNix Nix;

        public AINixHop(EntityNix NixIn)
        {
            this.Nix = NixIn;
            this.setMutexBits(5);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return random.nextInt(2) == 0;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            ((EntityNix.NixMoveHelper)this.Nix.getMoveHelper()).setSpeed(1.0D);
        }
    }

    static class NixMoveHelper extends EntityMoveHelper {
        private float yRot;
        private int jumpDelay;
        private final EntityNix Nix;
        private boolean isAggressive;

        public NixMoveHelper(EntityNix NixIn) {
            super(NixIn);
            this.Nix = NixIn;
            this.yRot = 180.0F * NixIn.rotationYaw / (float) Math.PI;
        }

        public void setDirection(float p_179920_1_, boolean p_179920_2_) {
            this.yRot = p_179920_1_;
            this.isAggressive = p_179920_2_;
        }

        public void setSpeed(double speedIn) {
            this.speed = speedIn;
            this.action = EntityMoveHelper.Action.MOVE_TO;
        }

        public void onUpdateMoveHelper() {
            this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.yRot, 90.0F);
            this.entity.rotationYawHead = this.entity.rotationYaw;
            this.entity.renderYawOffset = this.entity.rotationYaw;

            if (this.action != EntityMoveHelper.Action.MOVE_TO) {
                this.entity.setMoveForward(0.0F);
            } else {
                this.action = EntityMoveHelper.Action.WAIT;

                if (this.entity.onGround) {
                    this.entity.setAIMoveSpeed((float) (this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));

                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.Nix.getJumpDelay();

                        if (this.isAggressive) {
                            this.jumpDelay /= 3;
                        }

                        this.Nix.getJumpHelper().setJumping();

                        if (this.Nix.makesSoundOnJump()) {
                            this.Nix.playSound(this.Nix.getJumpSound(), this.Nix.getSoundVolume(), ((this.Nix.getRNG().nextFloat() - this.Nix.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                        }
                    } else {
                        this.Nix.moveStrafing = 0.0F;
                        this.Nix.moveForward = 0.0F;
                        this.entity.setAIMoveSpeed(0.0F);
                    }
                } else {
                    this.entity.setAIMoveSpeed((float) (this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
                }
            }
        }
    }
}
