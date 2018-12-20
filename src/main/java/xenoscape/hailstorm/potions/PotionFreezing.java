package xenoscape.hailstorm.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import xenoscape.hailstorm.Hailstorm;
import xenoscape.hailstorm.entity.ISnowCreature;
import xenoscape.hailstorm.main.MSounds;
=======
import xenoscape.hailstorm.Hailstorm;
import xenoscape.hailstorm.entity.ISnowCreature;
import xenoscape.hailstorm.main.MSounds;
>>>>>>> 2f34effc0ad705294dd03b18a13038d17d9b8376:src/main/java/xenoscape/hailstorm/potions/PotionFreezing.java

import java.util.UUID;

public class PotionFreezing extends Potion {

	public static final UUID MODIFIER_UUID = UUID.fromString("d8e4ae61-e49d-4931-aa67-0e7662669399");

	public PotionFreezing(String name, boolean isBadPotion, int color, int IconIndexX, int IconIndexY) {
		super(isBadPotion, color);
		setPotionName("effect." + name);
		setIconIndex(IconIndexX, IconIndexY);
		setRegistryName(new ResourceLocation(Hailstorm.MODID + ":" + name));
	}

	@Override
	public boolean hasStatusIcon() {
		Minecraft.getMinecraft().getTextureManager()
				.bindTexture(new ResourceLocation(Hailstorm.MODID + ":textures/gui/potion_effects.png"));
		return true;
	}

	@Override
	public void performEffect(final EntityLivingBase target, final int par2) {
		if (target.isInLava() || target.isBurning() || target instanceof ISnowCreature) {
			target.removePotionEffect(this);
		} else {
			target.playSound(MSounds.FREEZING, 0.3F, 1.0F);
			target.attackEntityFrom(Hailstorm.FROSTBITE, 1.0F);
		}
	}

	@Override
	public boolean isReady(final int par1, final int par2) {
		return par1 % 60 == 0;
	}
}
