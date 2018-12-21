package xenoscape.hailstorm.entity.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import xenoscape.hailstorm.entity.automaton.EntityAutomaton;

public class EntityAIAutomatonAttackMelee extends EntityAIAttackMelee
{
    private EntityAutomaton automaton;

    public EntityAIAutomatonAttackMelee(EntityAutomaton automaton, double speedIn, boolean useLongMemory)
    {
        super(automaton, speedIn, useLongMemory);
        this.automaton = automaton;
    }

    @Override
    public boolean shouldExecute() {
        return automaton.isActive() && super.shouldExecute();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return automaton.isActive() && super.shouldContinueExecuting();
    }
}