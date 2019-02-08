package xenoscape.worldsretold.defaultmod.entity.sentinel;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xenoscape.worldsretold.hailstorm.entity.hostile.guardsman.EntityGuardsman;

import java.util.List;

public class EntitySentinel extends EntityGuardsman
{
    private static final DataParameter<Boolean> ACTIVE = EntityDataManager.createKey(EntitySentinel.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> SHOULD_EXPLODE = EntityDataManager.createKey(EntitySentinel.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HAS_EXPLODED = EntityDataManager.createKey(EntitySentinel.class, DataSerializers.BOOLEAN);
    private float targetDistance;

    public EntitySentinel(World world){
        super(world);
        setSize(2, 2);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(ACTIVE, false);
        dataManager.register(SHOULD_EXPLODE, false);
        dataManager.register(HAS_EXPLODED, false);
    }

    public boolean isActive() {
        return getDataManager().get(ACTIVE);
    }

    public void setActive(boolean isActive) {
        getDataManager().set(ACTIVE, isActive);
    }

    public boolean shouldExplode() {
        return getDataManager().get(SHOULD_EXPLODE);
    }

    public void setShouldExplode(boolean shouldExplode) {
        getDataManager().set(SHOULD_EXPLODE, shouldExplode);
    }

    public boolean hasExploded() {
        return getDataManager().get(HAS_EXPLODED);
    }

    public void setHasExploded(boolean hasExploded) {
        getDataManager().set(HAS_EXPLODED, hasExploded);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (getAttackTarget() == null) {
            List<EntityPlayer> list = this.world.getEntitiesWithinAABB(EntityPlayer.class,
                    this.getEntityBoundingBox().expand(32.0D, 32.0D, 32.0D));
            for (EntityPlayer entity : list) {
                if (entity != null && !world.isRemote && !entity.isCreative()) {
                    setAttackTarget(entity);
                }
            }
        }

        if (getAttackTarget() != null)
            targetDistance = getDistance(getAttackTarget());

        if (!world.isRemote) {
            if (!isAIDisabled()) {
                if (isActive()) {
                    if (getAttackTarget() == null && moveForward == 0) {
                        setActive(false);
                    }
                } else if (getAttackTarget() != null && targetDistance <= 5) {
                    if(!hasExploded()){
                        setShouldExplode(true);
                        setHasExploded(true);
                    }
                    setActive(true);
                }
            }
        }

        if(shouldExplode()){
            if(!world.isRemote){
                boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this);
                float f = 3.5F;
                this.world.createExplosion(this, this.posX, this.posY, this.posZ, f, flag);
                setShouldExplode(false);
                setHasExploded(true);
            }
        }

        if (isActive() && targetDistance > 16 && !world.isRemote)
            setActive(false);

        if (!isActive()) {
            posX = prevPosX;
            posZ = prevPosZ;
            rotationYaw = prevRotationYaw;
        }

        if (!world.isRemote) {
            // System.out.println(getAttackTarget());
        //     System.out.println("Is active? " + isActive());
            // System.out.println(targetDistance);
            //System.out.println("Has Exploded? " + hasExploded());
            System.out.println("Should Explode? " + shouldExplode());
        }
        if (getAttackTarget() != null && isActive()) {
            getNavigator().tryMoveToEntityLiving(getAttackTarget(), .2D);
        }

        if(!isActive()){
         if(world.getBlockState(this.getPosition().down()) == Blocks.AIR.getDefaultState())
             motionY -=1;
         else
             motionY = 0;
        }

        if(isActive()){
            if (this.isEntityAlive()) {
                boolean minHeight = world.getBlockState(getPosition().down(2)) == Blocks.AIR.getDefaultState();
                boolean maxHeight = world.getBlockState(getPosition().down(4)) != Blocks.AIR.getDefaultState();
                if (!minHeight)
                    motionY += .1;
                else if (!maxHeight)
                    motionY -= .1;
                else
                    motionY = 0;

                if (!world.isRemote) {
                    if (getCharging() && chargeTicks < 40) {
                        chargeTicks++;
                        setChargeTicks(chargeTicks);
                    }

                    if (chargeTicks >= 40 && !getCharging()) {
                        setChargeTicks(0);
                        chargeTicks = 0;
                    }
                }

                ticksSinceLastAttack++;
                setTicksSinceLastAttack(ticksSinceLastAttack);

                boolean thirtyPercent = rand.nextInt(3) == 0;

                if (!world.isRemote && getAttackTarget() != null) {
                    if (getTicksSinceLastAttack() == 20 && thirtyPercent) {
                        resetStuff();
                    } else if (getTicksSinceLastAttack() == 40 && thirtyPercent) {
                        resetStuff();
                    } else if (getTicksSinceLastAttack() == 60 && thirtyPercent) {
                        resetStuff();
                    } else if (getTicksSinceLastAttack() >= 80) {
                        resetStuff();
                    }
                }
            }
            if (!this.world.isRemote && this.getAttackTarget() != null) {
                Entity entity = this.getAttackTarget();

                this.getLookHelper().setLookPositionWithEntity(getAttackTarget(), 10.0f,
                        (float) this.getVerticalFaceSpeed());

                if (entity != null) {
                    double d0 = entity.posX - this.posX;
                    double d1 = entity.posZ - this.posZ;
                    double d3 = d0 * d0 + d1 * d1;

                    if (d3 > 9.0D) {
                        double d5 = (double) MathHelper.sqrt(d3);
                        this.motionX += (d0 / d5 * 0.25D - this.motionX) * 0.1000000238418579D;
                        this.motionZ += (d1 / d5 * 0.25D - this.motionZ) * 0.1000000238418579D;
                    }
                }

                this.rotationYaw = (float) MathHelper.atan2(this.motionZ, this.motionX) * (180F / (float) Math.PI) - 90.0F;
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Active", isActive());
        compound.setBoolean("ShouldExplode", shouldExplode());
        compound.setBoolean("HasExploded", hasExploded());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        setActive(compound.getBoolean("Active"));
        setShouldExplode(compound.getBoolean("ShouldExplode"));
        setHasExploded(compound.getBoolean("HasExploded"));
    }

    @Override
    public boolean canBePushed() {
        return isActive();
    }

    @Override
    public boolean canBeCollidedWith() {
        return super.canBeCollidedWith() && isActive();
    }

    public boolean attackable() {
        return isActive();
    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    public void travel(float strafe, float vertical, float forward) {
        super.travel(strafe, vertical, forward);
    }
}
