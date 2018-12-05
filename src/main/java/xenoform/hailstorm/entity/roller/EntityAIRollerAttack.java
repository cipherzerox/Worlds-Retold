package xenoform.hailstorm.entity.roller;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIRollerAttack extends EntityAIAttackMelee
{
    public EntityAIRollerAttack(EntityCreature creature, double speedIn, boolean useLongMemory)
    {
        super(creature, speedIn, useLongMemory);
    }

    @Override
    protected double getAttackReachSqr(EntityLivingBase attackTarget)
    {
        return (double)(this.attacker.width * this.attacker.width + attackTarget.width);
    }
}
