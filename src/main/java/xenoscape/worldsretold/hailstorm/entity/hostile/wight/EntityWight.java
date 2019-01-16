package xenoscape.worldsretold.hailstorm.entity.hostile.wight;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.entity.ISnowCreature;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;
import xenoscape.worldsretold.hailstorm.init.HailstormSounds;
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;

public class EntityWight extends EntityZombie implements ISnowCreature
{
    public EntityWight(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 3.2F);
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28);
    }
    
    public float getEyeHeight()
    {
        float f = 2.76F;

        if (this.isChild())
        {
            f = (float)((double)f - 0.81D);
        }

        return f;
    }

    protected boolean shouldBurnInDay()
    {
        return false;
    }

    protected SoundEvent getAmbientSound()
    {
        return HailstormSounds.ENTITY_WIGHT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return HailstormSounds.ENTITY_WIGHT_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return HailstormSounds.ENTITY_WIGHT_DEATH;
    }

    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

	protected ResourceLocation getLootTable() 
	{
		return LootTableList.ENTITIES_ZOMBIE;
	}

    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase)
        {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 400 * (int)f));
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(HailstormPotions.FREEZING, 400 * (int)f, 1));
        }

        return flag;
    }

    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        return this.world.canSeeSky(new BlockPos(this)) && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this) && super.getCanSpawnHere();
    }

    protected ItemStack getSkullDrop()
    {
        return ItemStack.EMPTY;
    }
}