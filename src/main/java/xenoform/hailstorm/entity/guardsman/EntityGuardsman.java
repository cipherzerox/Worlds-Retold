package xenoform.hailstorm.entity.guardsman;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.entity.EntityGuard;
import xenoform.hailstorm.entity.ISnowCreature;

public class EntityGuardsman extends EntityGuard implements ISnowCreature
{

    public EntityGuardsman(World world){
        super(world);
    }
}
