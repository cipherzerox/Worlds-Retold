package xenoscape.worldsretold.hailstorm.entity.projectiles.frost_shot;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;

public class EntityFrostShot extends EntityFireball
{

    public EntityFrostShot(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityFrostShot(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ)
    {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    public static void registerFixesWitherSkull(DataFixer fixer)
    {
        EntityFireball.registerFixesFireball(fixer, "FrostShot");
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor()
    {
        return super.getMotionFactor();
    }

    @SideOnly(Side.CLIENT)
    public EntityFrostShot(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    public boolean isBurning()
    {
        return false;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
                if (this.shootingEntity != null)
                {
                    if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 7.0F))
                    {
                        if (result.entityHit.isEntityAlive())
                        {
                            this.applyEnchantments(this.shootingEntity, result.entityHit);
                        }
                    }
                }
                else
                {
                    result.entityHit.attackEntityFrom(DamageSource.MAGIC, 3.0F);
                }

                if (result.entityHit instanceof EntityLivingBase)
                {
                    int i = 0;

                    if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
                    {
                        i = 10;
                    }
                    else if (this.world.getDifficulty() == EnumDifficulty.HARD)
                    {
                        i = 20;
                    }

                    if (i > 0)
                    {
                        ((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(HailstormPotions.FREEZING, 20 * i, 1));
                    }
                }
            }
            this.setDead();
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return false;
    }

    protected boolean isFireballFiery()
    {
        return false;
    }
}