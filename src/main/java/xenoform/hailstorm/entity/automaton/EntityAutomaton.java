package xenoform.hailstorm.entity.automaton;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xenoform.hailstorm.entity.ai.EntityAIAutomatonAttackMelee;

import java.util.List;

public class EntityAutomaton extends EntityMob
{
    private static final DataParameter<Boolean> ACTIVE = EntityDataManager.createKey(EntityAutomaton.class, DataSerializers.BOOLEAN);
    protected float targetDistance;

    public EntityAutomaton(World world){
        super(world);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        tasks.addTask(0, new EntityAIAutomatonAttackMelee(this, 0.2D, false));
        tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 16));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40D);
    }

    public boolean isActive() {
        return getDataManager().get(ACTIVE);
    }

    public void setActive(boolean isActive) {
        getDataManager().set(ACTIVE, isActive);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ACTIVE, false);
    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if(getAttackTarget()==null)
        {
            List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(32.0D, 32.0D, 32.0D));
            for(EntityPlayer entity : list)
            {
                if(entity != null && !world.isRemote && !entity.isCreative()){
                    setAttackTarget(entity);
                }
            }
        }

        if(getAttackTarget() != null)
            targetDistance = getDistance(getAttackTarget());

        if (!world.isRemote)
        {
            if (!isAIDisabled())
            {
                if (isActive())
                {
                    if (getAttackTarget() == null && moveForward == 0)
                    {
                        setActive(false);
                    }
                } else if (getAttackTarget() != null && targetDistance <= 5)
                {
                    setActive(true);
                }
            }
        }

        if(isActive() && targetDistance > 16 && !world.isRemote)
            setActive(false);

        if (!isActive()) {
            posX = prevPosX;
            posZ = prevPosZ;
            rotationYaw = prevRotationYaw;
        }

        if(!world.isRemote) {
        //    System.out.println(getAttackTarget());
        //    System.out.println(isActive());
       //     System.out.println(targetDistance);
        }
        if(getAttackTarget() != null && isActive()){
            getNavigator().tryMoveToEntityLiving(getAttackTarget(), .2D);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Active", isActive());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        setActive(compound.getBoolean("Active"));
    }
}
