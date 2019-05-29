package xenoscape.worldsretold.hellfire.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.HailstormModule;
import xenoscape.worldsretold.hailstorm.entity.ISnowCreature;
import xenoscape.worldsretold.hailstorm.init.HailstormSounds;
import xenoscape.worldsretold.heatwave.HeatwaveModule;

import java.util.UUID;

public class PotionHellfire extends Potion 
{
	private boolean isEntityInWater;
	
	public PotionHellfire(String name, boolean isBadPotion, int color, int IconIndexX, int IconIndexY) {
		super(isBadPotion, color);
		setPotionName("effect." + name);
		setIconIndex(IconIndexX, IconIndexY);
		setRegistryName(new ResourceLocation(WorldsRetold.MODID + ":" + name));
	}

	@Override
	public boolean hasStatusIcon() 
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(WorldsRetold.MODID + ":textures/gui/potion_effects.png"));
		return true;
	}

	@Override
	public void performEffect(final EntityLivingBase target, final int par2) 
	{
		target.attackEntityFrom(WorldsRetold.HELLFIRE, 1 + par2);
		target.setFire(20);
		if (target.isWet())
			isEntityInWater = true;
		else
			isEntityInWater = false;
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
        int j = isEntityInWater ? 1 : 10 >> amplifier;

        if (j > 0)
        {
            return duration % j == 0;
        }
        else
        {
            return true;
        }
	}
}
