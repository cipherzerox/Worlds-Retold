package xenoscape.worldsretold.hailstorm.entity.projectiles.black_arrow;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.hailstorm.entity.projectiles.frost_shot.EntityFrostShot;

@SideOnly(Side.CLIENT)
public class RenderBlackArrow extends RenderArrow<EntityBlackArrow> {
	public static final ResourceLocation RES_BLACK_ARROW = new ResourceLocation(
			"worldsretold:textures/entity/projectile/black_arrow.png");
	public static final RenderBlackArrow.Factory FACTORY = new RenderBlackArrow.Factory();

	public RenderBlackArrow(RenderManager manager) {
		super(manager);
	}

	protected ResourceLocation getEntityTexture(EntityBlackArrow entity) {
		return RES_BLACK_ARROW;
	}

	public static class Factory implements IRenderFactory<EntityBlackArrow> {
		@Override
		public Render<? super EntityBlackArrow> createRenderFor(RenderManager manager) {
			return new RenderBlackArrow(manager);
		}
	}
}