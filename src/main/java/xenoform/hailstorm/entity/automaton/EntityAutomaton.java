package xenoform.hailstorm.entity.automaton;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xenoform.hailstorm.entity.EntitySurfaceMob;

public class EntityAutomaton extends EntitySurfaceMob
{
    private static final DataParameter<Boolean> ACTIVE = EntityDataManager.createKey(EntityAutomaton.class, DataSerializers.BOOLEAN);

    public EntityAutomaton(World world){
        super(world);
    }

    public boolean isActive() {
        return getDataManager().get(ACTIVE);
    }

    public void setActive(boolean isActive) {
        getDataManager().set(ACTIVE, isActive);
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

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();


    }
}
