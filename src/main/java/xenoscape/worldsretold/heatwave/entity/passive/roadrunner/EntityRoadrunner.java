package xenoscape.worldsretold.heatwave.entity.passive.roadrunner;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.init.HailstormItems;

public class EntityRoadrunner extends EntityAnimal 
{
	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;

	public EntityRoadrunner(World worldIn) {
		super(worldIn);
		this.setSize(0.4F, 0.7F);
		this.setPathPriority(PathNodeType.WATER, 0.0F);
		this.stepHeight = 1F;
	    this.spawnableBlock = Blocks.SAND;
	}

	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 1.0D, 1.5D));
		this.tasks.addTask(3, new EntityAIPanic(this, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 0.5D, 0.01F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
	}

	public float getEyeHeight() {
		return this.height - 0.1F;
	}
	
	/**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return true;
    }

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	public void onLivingUpdate() 
	{
		super.onLivingUpdate();
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 2) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

		if (!this.onGround && this.wingRotDelta < 1.0F) {
			this.wingRotDelta = 1.0F;
		}

		this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);

		if (!this.onGround && this.motionY < 0.0D)
			this.motionY *= 0.8D;

		this.wingRotation += this.wingRotDelta * 2.0F;
	}

	protected SoundEvent getAmbientSound() 
	{
		return SoundEvents.ENTITY_CHICKEN_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return SoundEvents.ENTITY_CHICKEN_HURT;
	}

	protected SoundEvent getDeathSound() 
	{
		return SoundEvents.ENTITY_CHICKEN_DEATH;
	}

	protected void playStepSound(BlockPos pos, Block blockIn) 
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.2F);
	}
	
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_PARROT;
    }

	public EntityRoadrunner createChild(EntityAgeable ageable) 
	{
		return null;
	}
	
    public void fall(float distance, float damageMultiplier)
    {
    }

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	public boolean isBreedingItem(ItemStack stack) 
	{
		return false;
	}
}