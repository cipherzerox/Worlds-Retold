package xenoscape.worldsretold.hailstorm.entity.hostile.blizzard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.basic.EntitySurfaceMonster;
import xenoscape.worldsretold.hailstorm.entity.ISnowCreature;
import xenoscape.worldsretold.hailstorm.entity.projectiles.hail.EntityHail;
import xenoscape.worldsretold.defaultmod.util.EntityElementalLookHelper;

import java.util.List;
import java.util.Random;

public class EntityBlizzard extends EntitySurfaceMonster implements ISnowCreature 
{
	Random rand = new Random();
	public int deathTicks;

	public EntityBlizzard(World world) 
	{
		super(world);
		setSize(7F, 5F);
        this.lookHelper = new EntityElementalLookHelper(this);
	}

	protected SoundEvent getAmbientSound() 
	{
		return SoundEvents.ENTITY_LIGHTNING_THUNDER;
	}
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return SoundEvents.ENTITY_SNOWMAN_HURT;
	}

	protected SoundEvent getDeathSound() 
	{
		return SoundEvents.BLOCK_END_PORTAL_SPAWN;
	}
	
	protected ResourceLocation getLootTable() 
	{
		return new ResourceLocation(WorldsRetold.MODID, "entity/blizzard");
	}

	protected float getSoundVolume() 
	{
		return 4F;
	}

	@Override
	protected void initEntityAI() 
	{
		super.initEntityAI();
		this.tasks.addTask(1, new EntityBlizzard.AIBlizzardAttack(this));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityBlizzard.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
	}

	public float getEyeHeight()
	{
		return this.height * 0.325F - (MathHelper.cos(this.ticksExisted * 0.2F) * 0.325F);
	}

	@Override
	protected void applyEntityAttributes() 
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
	}

	@Override
	public void onLivingUpdate() 
	{
		if (this.isEntityAlive()) 
		{
			super.onLivingUpdate();

			boolean minHeight = world.getBlockState(getPosition().down(5)) != Blocks.AIR.getDefaultState();

			if (minHeight)
				motionY += (0.2D - this.motionY) * 0.1000000238418579D;

			if (!this.world.isRemote)
			if (this.getAttackTarget() != null) 
			{
				Entity entity = this.getAttackTarget();

				if (!entity.isEntityAlive())
					this.setAttackTarget(null);
				
				this.renderYawOffset = this.rotationYaw = this.rotationYawHead;
			}
			else
			{
				
				List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(64.0D));
				
				for (EntityPlayer entity : list) 
				{
					if (entity != null && !entity.isCreative())
						setAttackTarget(entity);
				}
				
				List<EntityLivingBase> listother = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(64.0D));
				
				for (EntityLivingBase entity : listother) 
				{
					if (entity != null && entity.isEntityAlive() && (entity instanceof EntityVillager || entity instanceof EntityIronGolem))
						setAttackTarget(entity);
				}
			}
			this.launchHailToCoords(posX, posY - 1.7, posZ);
		}
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	/**
	 * Launches a Wither skull toward (par2, par4, par6)
	 */
	private void launchHailToCoords(double x, double y, double z) 
	{
		double d0 = x - this.posX;
		double d1 = y - this.posY;
		double d2 = z - this.posZ;
		EntityHail entityHail = new EntityHail(this.world, this, d0, d1, d2);
		entityHail.posX += rand.nextDouble() * 5D - 2.5D;
		entityHail.posY += this.getEyeHeight() + 2D;
		entityHail.posZ += rand.nextDouble() * 5D - 2.5D;
		if (!this.world.isRemote) {
			world.spawnEntity(entityHail);
		}
	}

	@Override
	public boolean hasNoGravity() 
	{
		return true;
	}
	
	public int getSpawnType()
	{
		return 6;
	}

	public boolean getCanSpawnHere() 
	{
		return this.world.provider.getDimension() == 0 && this.rand.nextInt(10) == 0 && super.getCanSpawnHere();
	}

	public int getMaxSpawnedInChunk() 
	{
		return 1;
	}

	protected void onDeathUpdate() 
	{
		this.deathTicks++;

		if (!this.world.isRemote) {
			boolean flag = this.world.getGameRules().getBoolean("doMobLoot");
			if (this.deathTicks > 50 && this.deathTicks % 5 == 0 && flag) {
				this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY + 1, this.posZ, 10));
			}
		}

		if (this.deathTicks == 100 && !this.world.isRemote)
			this.setDead();
	}
	
    static class AIBlizzardAttack extends EntityAIBase
    {
        private final EntityBlizzard blizzard;
        private int attackStep;
        private int attackTime;

        public AIBlizzardAttack(EntityBlizzard blizzardIn)
        {
            this.blizzard = blizzardIn;
            this.setMutexBits(3);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.blizzard.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            this.attackStep = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
            this.blizzard.setAttackTarget(null);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            --this.attackTime;
            EntityLivingBase entitylivingbase = this.blizzard.getAttackTarget();
            double dou = this.blizzard.getDistanceSq(entitylivingbase);

            if (dou < 25D)
            {
                if (this.attackTime <= 0)
                {
                    this.attackTime = 20;
                    this.blizzard.attackEntityAsMob(entitylivingbase);
                }

                this.blizzard.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            else if (dou < this.getFollowDistance() * this.getFollowDistance())
            {
				if (entitylivingbase != null) 
				{
					double d0 = entitylivingbase.posX - this.blizzard.posX;
					double d1 = entitylivingbase.posZ - this.blizzard.posZ;
					double d3 = d0 * d0 + d1 * d1;

					if (d3 > 9.0D) 
					{
						double d5 = (double) MathHelper.sqrt(d3);
						this.blizzard.motionX += (d0 / d5 * 0.42D - this.blizzard.motionX) * 0.1000000238418579D;
						this.blizzard.motionZ += (d1 / d5 * 0.42D - this.blizzard.motionZ) * 0.1000000238418579D;
					}
				}
            	
                double d1 = entitylivingbase.posX - this.blizzard.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (this.blizzard.posY + (double)(this.blizzard.height / 2.0F));
                double d3 = entitylivingbase.posZ - this.blizzard.posZ;

                if (this.attackTime <= 0)
                {
                    ++this.attackStep;

                    if (this.attackStep == 1)
                    {
                        this.attackTime = 100;
                    }
                    else if (this.attackStep <= 40)
                    {
                        this.attackTime = 2;
                    }
                    else
                    {
                        this.attackTime = 100;
                        this.attackStep = 0;
                    }

                    if (this.attackStep > 1)
                    {
    					this.blizzard.playSound(SoundEvents.ITEM_ELYTRA_FLYING, this.blizzard.getSoundVolume(), this.blizzard.getSoundPitch());
        				this.blizzard.launchHailToCoords(entitylivingbase.posX, entitylivingbase.posY - this.blizzard.getEyeHeight() - 1D + (this.blizzard.rand.nextDouble() * 2D - 1D), this.blizzard.getAttackTarget().posZ);
        				this.blizzard.launchHailToCoords(entitylivingbase.posX, entitylivingbase.posY - this.blizzard.getEyeHeight() - 1D + (this.blizzard.rand.nextDouble() * 2D - 1D), this.blizzard.getAttackTarget().posZ);
                    }
                }

                this.blizzard.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            }
            else
            {
                this.blizzard.getNavigator().clearPath();
                this.blizzard.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }

            super.updateTask();
        }

        private double getFollowDistance()
        {
            IAttributeInstance iattributeinstance = this.blizzard.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
        }
    }
}