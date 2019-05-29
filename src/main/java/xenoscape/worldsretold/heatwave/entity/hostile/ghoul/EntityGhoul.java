package xenoscape.worldsretold.heatwave.entity.hostile.ghoul;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;
import xenoscape.worldsretold.heatwave.entity.hostile.mummy.EntityMummy;

public class EntityGhoul extends EntityZombie implements IDesertCreature
{
	protected static final DataParameter<Byte> DISGUISENAME = EntityDataManager.<Byte>createKey(EntityGhoul.class, DataSerializers.BYTE);

	public EntityGhoul(World worldIn) 
	{
		super(worldIn);
        this.experienceValue = 10;
        this.setSize(0.5F, 1.8F);
	}

}
