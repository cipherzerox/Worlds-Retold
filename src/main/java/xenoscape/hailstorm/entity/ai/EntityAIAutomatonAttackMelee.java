package xenoscape.hailstorm.entity.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import xenoscape.hailstorm.entity.automaton.EntityAutomaton;
=======
import xenoscape.hailstorm.entity.automaton.EntityAutomaton;
>>>>>>> 2f34effc0ad705294dd03b18a13038d17d9b8376:src/main/java/xenoscape/hailstorm/entity/ai/EntityAIAutomatonAttackMelee.java

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
