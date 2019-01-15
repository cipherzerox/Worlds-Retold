package xenoscape.worldsretold.heatwave.entity.hostile.mummy;

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
import net.minecraft.entity.passive.EntityOcelot;
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
import xenoscape.worldsretold.hailstorm.init.HailstormSounds;
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;
import xenoscape.worldsretold.heatwave.init.HeatwavePotions;

public class EntityMummy extends EntityZombie implements IDesertCreature, IRangedAttackMob
{
    public EntityMummy(World worldIn)
    {
        super(worldIn);
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
        this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(0D);
    }
    
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityOcelot.class, 8.0F, 1.2D, 1.5D));
        this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 20, 15.0F)
        {
            public boolean shouldExecute()
            {
                EntityLivingBase entitylivingbase = EntityMummy.this.getAttackTarget();

                if (entitylivingbase == null)
                {
                    return false;
                }
                else if (entitylivingbase.isPotionActive(HeatwavePotions.VENOM))
                {
                    return false;
                }
                else
                {
                    return super.shouldExecute();
                }
            }
        });
        this.tasks.addTask(3, new EntityAIZombieAttack(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    protected boolean shouldBurnInDay()
    {
        return false;
    }

    protected SoundEvent getAmbientSound()
    {
        return HailstormSounds.ENTITY_MUMMY_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return HailstormSounds.ENTITY_MUMMY_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return HailstormSounds.ENTITY_MUMMY_DEATH;
    }

    protected SoundEvent getStepSound()
    {
        return SoundEvents.BLOCK_CLOTH_BREAK;
    }

	protected ResourceLocation getLootTable() 
	{
		return new ResourceLocation(WorldsRetold.MODID, "entity/mummy");
	}

    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase)
        {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 240 * (int)f));
            if (f >= 1F)
            	((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 160 * (int)f));
            if (f >= 1.5F)
            	((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80 * (int)f));
            if (f >= 2F)
                ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER, 80 * (int)f));
        }

        return flag;
    }
    
    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
        float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
        target.attackEntityFrom(DamageSource.STARVE, 3F);
        target.addPotionEffect(new PotionEffect(HeatwavePotions.VENOM, 200));
        target.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 240 * (int)f));
        if (f >= 1F)
        	target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 160 * (int)f));
        if (f >= 1.5F)
        	target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80 * (int)f));
        if (f >= 2F)
        	target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 80 * (int)f));
        this.playSound(HailstormSounds.ENTITY_MUMMY_INFECT, 3.0F, this.isChild() ? 1.5F : 1.0F);
        if (target instanceof EntityMob)
        	((EntityMob)target).setRevengeTarget(this);
    }

    protected ItemStack getSkullDrop()
    {
        return ItemStack.EMPTY;
    }

    public boolean getCanSpawnHere() {
        return this.world.provider.getDimension() == 0
                && super.getCanSpawnHere();
    }

	public void setSwingingArms(boolean swingingArms) {}
}