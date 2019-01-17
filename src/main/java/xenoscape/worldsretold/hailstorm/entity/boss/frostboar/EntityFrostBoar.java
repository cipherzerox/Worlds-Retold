package xenoscape.worldsretold.hailstorm.entity.boss.frostboar;

import net.minecraft.world.World;
import xenoscape.worldsretold.defaultmod.basic.EntitySurfaceMonster;
import xenoscape.worldsretold.defaultmod.util.EntityElementalLookHelper;
import xenoscape.worldsretold.hailstorm.entity.ISnowCreature;

public class EntityFrostBoar extends EntitySurfaceMonster implements ISnowCreature 
{
	public EntityFrostBoar(World world) 
	{
		super(world);
		setSize(1F, 6F);
	}
}
