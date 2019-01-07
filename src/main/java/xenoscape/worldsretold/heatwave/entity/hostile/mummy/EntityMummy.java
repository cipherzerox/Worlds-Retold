package xenoscape.worldsretold.heatwave.entity.hostile.mummy;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;

public class EntityMummy extends EntityZombie implements IDesertCreature
{
    public EntityMummy(World worldIn)
    {
        super(worldIn);
    }

    protected boolean shouldBurnInDay()
    {
        return false;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_HUSK_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_HUSK_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_HUSK_DEATH;
    }

    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_HUSK_STEP;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_HUSK;
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase)
        {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 140 * (int)f));
            if (f > 3F)
                ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER, 20 * (int)f));
        }

        return flag;
    }

    protected ItemStack getSkullDrop()
    {
        return ItemStack.EMPTY;
    }
}