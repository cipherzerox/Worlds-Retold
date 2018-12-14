package xenoform.hailstorm.entity.guardsman;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.entity.EntitySurfaceGuard;
import xenoform.hailstorm.entity.ISnowCreature;

public class EntityGuardsman extends EntitySurfaceGuard implements ISnowCreature
{

    public EntityGuardsman(World world){
        super(world);
    }
    
	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) {
		if (damageSrc == DamageSource.ON_FIRE || damageSrc == DamageSource.HOT_FLOOR)
			super.damageEntity(damageSrc, damageAmount * 2);
		else if (damageSrc == DamageSource.LAVA)
			super.damageEntity(damageSrc, damageAmount * 3);
		else
			super.damageEntity(damageSrc, damageAmount);
	}

}
