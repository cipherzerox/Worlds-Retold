package xenoscape.worldsretold.hailstorm.entity.hostile.blizzard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xenoscape.worldsretold.basic.EntitySurfaceMonster;
import xenoscape.worldsretold.hailstorm.entity.ISnowCreature;
import xenoscape.worldsretold.hailstorm.entity.projectiles.hail.EntityHail;
import xenoscape.worldsretold.util.EntityElementalLookHelper;

import javax.annotation.Nullable;
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

	protected float getSoundVolume() 
	{
		return 4F;
	}

	@Override
	protected void initEntityAI() 
	{
		super.initEntityAI();
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
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
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7D);
	}

	@Override
	public void onLivingUpdate() 
	{
		if (this.isEntityAlive()) 
		{
			super.onLivingUpdate();

			boolean minHeight = world.getBlockState(getPosition().down(2)) != Blocks.AIR.getDefaultState();

			if (minHeight)
				motionY += (0.2D - this.motionY) * 0.1000000238418579D;

			if (!this.world.isRemote)
			if (this.getAttackTarget() != null) 
			{
				Entity entity = this.getAttackTarget();

				if (entity != null) 
				{
					double d0 = entity.posX - this.posX;
					double d1 = entity.posZ - this.posZ;
					double d3 = d0 * d0 + d1 * d1;

					if (d3 > 9.0D) 
					{
						double d5 = (double) MathHelper.sqrt(d3);
						this.motionX += (d0 / d5 * 0.42D - this.motionX) * 0.1000000238418579D;
						this.motionZ += (d1 / d5 * 0.42D - this.motionZ) * 0.1000000238418579D;
					}
				}
				
				this.launchHailToCoords(this.getAttackTarget().posX, this.getAttackTarget().posY - this.getEyeHeight() - 1D + (rand.nextDouble() * 2D - 1D), this.getAttackTarget().posZ);
				this.launchHailToCoords(this.getAttackTarget().posX, this.getAttackTarget().posY - this.getEyeHeight() - 1D + (rand.nextDouble() * 2D - 1D), this.getAttackTarget().posZ);
				
				if (this.rand.nextInt(20) == 0)
					this.playSound(SoundEvents.ITEM_ELYTRA_FLYING, this.getSoundVolume(), this.getSoundPitch());
				
				this.renderYawOffset = this.rotationYaw = this.rotationYawHead;
				this.getLookHelper().setLookPositionWithEntity(getAttackTarget(), 180F, 180F);
			}
			else
			{
				this.launchHailToCoords(posX, posY - 1.7, posZ);
				
				List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class,
						this.getEntityBoundingBox().grow(64.0D));
				
				for (EntityPlayer entity : list) 
				{
					if (entity != null && !entity.isCreative())
						setAttackTarget(entity);
				}
			}
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
		if (!this.world.isRemote)
		world.spawnEntity(entityHail);
	}

	@Override
	public boolean hasNoGravity() 
	{
		return true;
	}

	public boolean getCanSpawnHere() 
	{
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor(this.posZ);
		BlockPos blockpos = new BlockPos(i, j, k);
		return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.rand.nextInt(25) == 0
				&& this.world.isRaining() && this.world.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS
				&& super.getCanSpawnHere();
	}

	public int getMaxSpawnedInChunk() {
		return 1;
	}

	protected void onDeathUpdate() 
	{
		this.deathTicks++;
        boolean flag = this.world.getGameRules().getBoolean("doMobLoot");
        int j = 50;
        if (this.deathTicks > 50 && this.deathTicks % 5 == 0 && flag && !this.world.isRemote)
        {
            while (j > 0)
            {
                int i = EntityXPOrb.getXPSplit(j);
                j -= i;
                this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, i));
            }
        }
        
		if (this.deathTicks == 100 && !this.world.isRemote)
			this.setDead();
	}
}