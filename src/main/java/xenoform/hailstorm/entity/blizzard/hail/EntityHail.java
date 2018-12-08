package xenoform.hailstorm.entity.blizzard.hail;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.entity.blizzard.EntityBlizzard;

public class EntityHail extends EntityFireball{
    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.<Boolean>createKey(EntityHail.class, DataSerializers.BOOLEAN);

    public EntityHail(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityHail(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ)
    {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    public static void registerFixesHail(DataFixer fixer)
    {
        EntityFireball.registerFixesFireball(fixer, "Hail");
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor()
    {
        return super.getMotionFactor();
    }

    @SideOnly(Side.CLIENT)
    public EntityHail(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
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

    @Override
    protected EnumParticleTypes getParticleType() {
        return EnumParticleTypes.SNOW_SHOVEL;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            int i = 3;

            if (result.entityHit instanceof EntityBlizzard)
            {
                i = 0;
            }

            result.entityHit.attackEntityFrom(Hailstorm.HAIL, (float)i);
        }

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
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
