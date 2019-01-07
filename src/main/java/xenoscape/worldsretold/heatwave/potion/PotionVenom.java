package xenoscape.worldsretold.heatwave.potion;

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

public class PotionVenom extends Potion {

	public static final UUID MODIFIER_UUID = UUID.fromString("d8e4ae61-e49d-4231-aa67-0e6662669347");

	public PotionVenom(String name, boolean isBadPotion, int color, int IconIndexX, int IconIndexY) {
		super(isBadPotion, color);
		setPotionName("effect." + name);
		setIconIndex(IconIndexX, IconIndexY);
		setRegistryName(new ResourceLocation(WorldsRetold.MODID + ":" + name));
	}

	@Override
	public boolean hasStatusIcon() 
	{
		Minecraft.getMinecraft().getTextureManager()
				.bindTexture(new ResourceLocation(WorldsRetold.MODID + ":textures/gui/potion_effects.png"));
		return true;
	}

	@Override
	public void performEffect(final EntityLivingBase target, final int par2) 
	{
		if (!target.isPotionApplicable(new PotionEffect(MobEffects.POISON, 40)))
			target.removeActivePotionEffect(this);
		
		target.rotationYawHead += target.getRNG().nextFloat() * 10F - 5F;
		
		if (target instanceof EntityPlayer)
		{
			((EntityPlayer)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 40));
			((EntityPlayer)target).cameraYaw += target.getRNG().nextFloat() - 0.5F;
			((EntityPlayer)target).getFoodStats().addExhaustion(0.3331F);
			if (((EntityPlayer)target).getFoodStats().getFoodLevel() <= 10)
	    		target.attackEntityFrom(HeatwaveModule.VENOM, 1F);
		}
        if (!(target instanceof EntityPlayer) || (target instanceof EntityPlayer && ((EntityPlayer)target).getFoodStats().getFoodLevel() > 10 && target.getHealth() > 1.0F))
        	target.attackEntityFrom(HeatwaveModule.VENOM, 1F);

	}

	@Override
	public boolean isReady(int duration, int amplifier) {
        int j = 25 >> amplifier;

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
