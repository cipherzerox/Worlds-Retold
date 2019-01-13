package xenoscape.worldsretold.defaultmod.basic;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import xenoscape.worldsretold.hailstorm.entity.ISnowCreature;
import xenoscape.worldsretold.heatwave.entity.neutral.cobra.EntityCobra;

public abstract class EntitySurfaceMonster extends EntityCreature implements IMob {
	protected float targetDistance;

	public EntitySurfaceMonster(World worldIn) {
		super(worldIn);
		this.experienceValue = 5;
	}

	public SoundCategory getSoundCategory() {
		return SoundCategory.HOSTILE;
	}

	public void onLivingUpdate() {
		this.updateArmSwingProgress();
		super.onLivingUpdate();
	}

	public void onUpdate() {
		super.onUpdate();

		if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			this.setDead();
		}
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_HOSTILE_SWIM;
	}

	protected SoundEvent getSplashSound() {
		return SoundEvents.ENTITY_HOSTILE_SPLASH;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this instanceof EntityCobra && source.getImmediateSource() != null && source.getImmediateSource() instanceof EntityLivingBase && ((EntityLivingBase)source.getImmediateSource()).getHeldItemMainhand().isEmpty())
			this.attackEntityAsMob(source.getImmediateSource());
		
		return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount);
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_HOSTILE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_HOSTILE_DEATH;
	}

	protected SoundEvent getFallSound(int heightIn) {
		return heightIn > 4 ? SoundEvents.ENTITY_HOSTILE_BIG_FALL : SoundEvents.ENTITY_HOSTILE_SMALL_FALL;
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		int i = 0;

		if (entityIn instanceof EntityLivingBase) {
			f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(),
					((EntityLivingBase) entityIn).getCreatureAttribute());
			i += EnchantmentHelper.getKnockbackModifier(this);
		}

		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

		if (flag) {
			if (i > 0 && entityIn instanceof EntityLivingBase) {
				((EntityLivingBase) entityIn).knockBack(this, (float) i * 0.5F,
						(double) MathHelper.sin(this.rotationYaw * 0.017453292F),
						(double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}

			int j = EnchantmentHelper.getFireAspectModifier(this);

			if (j > 0) {
				entityIn.setFire(j * 4);
			}

			if (entityIn instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) entityIn;
				ItemStack itemstack = this.getHeldItemMainhand();
				ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack()
						: ItemStack.EMPTY;

				if (!itemstack.isEmpty() && !itemstack1.isEmpty()
						&& itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this)
						&& itemstack1.getItem().isShield(itemstack1, entityplayer)) {
					float f1 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

					if (this.rand.nextFloat() < f1) {
						entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
						this.world.setEntityState(entityplayer, (byte) 30);
					}
				}
			}

			this.applyEnchantments(this, entityIn);
		}

		return flag;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	}

	/**
	 * Entity won't drop items or experience points if this returns false
	 */
	protected boolean canDropLoot() {
		return true;
	}

	public boolean isPreventingPlayerRest(EntityPlayer playerIn) {
		return true;
	}

	protected void damageEntity(DamageSource damageSrc, float damageAmount) {
		if (this instanceof ISnowCreature) {
			if (damageSrc == DamageSource.ON_FIRE || damageSrc == DamageSource.HOT_FLOOR)
				super.damageEntity(damageSrc, damageAmount * 1.5F);
			else if (damageSrc == DamageSource.LAVA)
				super.damageEntity(damageSrc, damageAmount * 2);
			else
				super.damageEntity(damageSrc, damageAmount);
		} else {
			super.damageEntity(damageSrc, damageAmount);
		}
	}
	
	public int getSpawnType()
	{
		return 0;
	}
	
    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    protected boolean isValidLightLevel()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32))
        {
            return false;
        }
        else
        {
            int i = this.world.getLightFromNeighbors(blockpos);

            if (this.world.isThundering())
            {
                int j = this.world.getSkylightSubtracted();
                this.world.setSkylightSubtracted(10);
                i = this.world.getLightFromNeighbors(blockpos);
                this.world.setSkylightSubtracted(j);
            }

            return i <= this.rand.nextInt(8);
        }
    }

	public boolean getCanSpawnHere() 
	{
		if (this.getSpawnType() == 1)
		{
	        int i = MathHelper.floor(this.posX);
	        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
	        int k = MathHelper.floor(this.posZ);
	        BlockPos blockpos = new BlockPos(i, j, k);
	        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS && this.world.getLight(blockpos) > 8 && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
		}
		
		if (this.getSpawnType() == 2)
		{
	        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
		}
		
		if (this.getSpawnType() == 3)
		{
	        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.world.canSeeSky(new BlockPos(this)) && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
		}
		
		if (this.getSpawnType() == 4)
		{
	        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
		}
		
		if (this.getSpawnType() == 5)
		{
	        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.canSeeSky(new BlockPos(this)) && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
		}
		
		if (this.getSpawnType() == 6)
		{
	        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.isRaining() && this.isValidLightLevel() && this.world.canSeeSky(new BlockPos(this)) && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
		}
		
		return super.getCanSpawnHere();
	}
}