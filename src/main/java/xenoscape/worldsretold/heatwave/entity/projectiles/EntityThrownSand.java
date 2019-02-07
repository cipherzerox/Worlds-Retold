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
import xenoscape.worldsretold.heatwave.entity.hostile.antlion.EntityAntlion;

public class EntityThrownSand extends EntityThrowable
{
    public EntityThrownSand(World worldIn)
    {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
    }

    public EntityThrownSand(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
        this.setSize(0.5F, 0.5F);
    }

    public EntityThrownSand(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
        this.setSize(0.5F, 0.5F);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && this.getThrower() != null && !(result.entityHit instanceof EntityAntlion))
        {
        	--result.entityHit.hurtResistantTime;
        	if (result.entityHit instanceof EntityLivingBase)
        	((EntityLivingBase)result.entityHit).knockBack(this, -1F, (double)MathHelper.sin(this.getThrower().rotationYawHead * 0.017453292F), (double)(-MathHelper.cos(this.getThrower().rotationYawHead * 0.017453292F)));
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5F);
        	this.world.playEvent(2001, this.getPosition(), Block.getStateId(Blocks.SAND.getDefaultState()));
            this.setDead();
        }
        else if (result.entityHit == null)
        {
        	this.world.playEvent(2001, this.getPosition(), Block.getStateId(Blocks.SAND.getDefaultState()));
            this.setDead();
        }
    }
}