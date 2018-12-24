package xenoscape.worldsretold.hailstorm.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xenoscape.worldsretold.hailstorm.entity.projectiles.black_arrow.EntityBlackArrow;
import xenoscape.worldsretold.util.ModelRegistry;

public class BasicItemBlackArrow extends ItemArrow implements ModelRegistry
{
	protected String name;
	
	public BasicItemBlackArrow(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        return new EntityBlackArrow(worldIn, shooter);
    }
}