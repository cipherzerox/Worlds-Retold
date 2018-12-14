package xenoform.hailstorm.entity.blizzard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xenoform.hailstorm.entity.EntitySurfaceMob;
import xenoform.hailstorm.entity.blizzard.hail.EntityHail;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class EntityBlizzard extends EntitySurfaceMob implements EntityFlying
{
    Random rand = new Random();

    public EntityBlizzard(World world){
        super(world);
        setSize(7F, 4F);
        noClip = true;
    }
    
	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_LIGHTNING_THUNDER;
	}

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAIWander(this, .8D));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 64));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, .4D, 64));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(70D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(.3D);
    }

    @Override
    public void onLivingUpdate() {
        if(getAttackTarget()==null)
        {
            List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(32.0D, 32.0D, 32.0D));
            for(EntityPlayer entity : list)
            {
                if(entity!=null)
                    setAttackTarget(entity);
            }
        }

        boolean minHeight = world.getBlockState(getPosition().down(8)) == Blocks.AIR.getDefaultState();

        if(!minHeight)
            motionY += .1;

        if (!this.world.isRemote && this.getAttackTarget() != null)
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
                    this.motionX += (d0 / d5 * 0.5D - this.motionX) * 0.1000000238418579D;
                    this.motionZ += (d1 / d5 * 0.5D - this.motionZ) * 0.1000000238418579D;
                }
            }
        }

        if (this.motionX * this.motionX + this.motionZ * this.motionZ >= 0.0025000011722640823D)
        {
            this.rotationYaw = (float)MathHelper.atan2(this.motionZ, this.motionX) * (180F / (float)Math.PI) - 90.0F;
        }

        super.onLivingUpdate();

        launchHailToCoords(posX, posY, posZ);
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    /**
     * Launches a Wither skull toward (par2, par4, par6)
     */
    private void launchHailToCoords(double x, double y, double z)
    {
        double d0 = this.posX;
        double d1 = this.posY + 1.7;
        double d2 = this.posZ;
        double d4 = y - d1;
        EntityHail entityHail = new EntityHail(this.world, this, 0, d4, 0);

        double x0 = d0 - 2;
        double z0 = d2 - 2;

        entityHail.posX = x0 + rand.nextInt(5);
        entityHail.posY = d1;
        entityHail.posZ = z0 + rand.nextInt(5);

        world.spawnEntity(entityHail);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }
}
