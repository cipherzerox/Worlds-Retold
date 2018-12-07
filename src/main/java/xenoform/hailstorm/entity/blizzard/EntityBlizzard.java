package xenoform.hailstorm.entity.blizzard;

import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.world.World;

public class EntityBlizzard extends EntityMob implements EntityFlying
{
    public EntityBlizzard(World world){
        super(world);
        setSize(2F, .5F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAIWander(this, .8D));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 64));
        this.tasks.addTask(2, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));

    }

    /**
     * Returns new PathNavigateGround instance
     */
    protected PathNavigate createNavigator(World worldIn)
    {
        PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
        pathnavigateflying.setCanOpenDoors(false);
        pathnavigateflying.setCanFloat(true);
        pathnavigateflying.setCanEnterDoors(true);
        return pathnavigateflying;
    }


    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        /*this.motionY *= 0.8000000238418579D;

        BlockPos pos = new BlockPos(posX, posY - 5, posZ);
        if(world.getBlockState(pos) != Blocks.AIR.getDefaultState() || (getAttackTarget() !=null && posY - getAttackTarget().posY < 5)){
            motionY +=.1;
        }

        if (!this.world.isRemote && this.getAttackTarget() != null)
        {
            Entity entity = this.getAttackTarget();

            if (entity != null)
            {
                if (this.posY < entity.posY )
                {
                    if (this.motionY < 0.0D)
                    {
                        this.motionY = 0.0D;
                    }

                    this.motionY += (0.5D - this.motionY) * 0.6000000238418579D;
                }

                if(this.posY < entity.posY + 2)
                    this.motionY += .1;
                else if(this.posY > entity.posY + 2)
                    this.motionY -= .1;
                else
                    this.motionY = 0;

                double d0 = entity.posX - this.posX + 2;
                double d1 = entity.posZ - this.posZ + 2;
                double d3 = d0 * d0 + d1 * d1;

                if (d3 > 9.0D)
                {
                    double d5 = (double) MathHelper.sqrt(d3);
                    this.motionX += (d0 / d5 * 0.5D - this.motionX) * 0.6000000238418579D;
                    this.motionZ += (d1 / d5 * 0.5D - this.motionZ) * 0.6000000238418579D;
                }
            }
        }

        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 0.05000000074505806D)
        {
            this.rotationYaw = (float)MathHelper.atan2(this.motionZ, this.motionX) * (180F / (float)Math.PI) - 90.0F;
        }*/
    }
}
