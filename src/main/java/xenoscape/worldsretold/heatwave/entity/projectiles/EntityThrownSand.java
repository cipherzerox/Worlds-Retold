package xenoscape.worldsretold.heatwave.entity.projectiles;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityThrownSand extends EntityThrowable
{
    public EntityThrownSand(World worldIn)
    {
        super(worldIn);
    }

    public EntityThrownSand(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityThrownSand(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
        	if (result.entityHit instanceof EntityLivingBase)
        	((EntityLivingBase)result.entityHit).knockBack(this, 1F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5F);
        }

        if (!this.world.isRemote)
        {
        	this.world.playEvent(2001, this.getPosition(), Block.getStateId(Blocks.SAND.getDefaultState()));
            this.setDead();
        }
    }
}