package xenoscape.hailstorm.entity.penguin;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderPenguin extends RenderLiving<EntityPenguin> {
	private ResourceLocation TEXTURE = new ResourceLocation("hailstorm:textures/entity/penguin.png");
	public static final RenderPenguin.Factory FACTORY = new RenderPenguin.Factory();

	public RenderPenguin(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelPenguin(), 0.4F);
	}

	@Override
	@Nonnull
	public ResourceLocation getEntityTexture(@Nonnull EntityPenguin entity) {
		return TEXTURE;
	}

	@Override
	protected void preRenderCallback(EntityPenguin entitylivingbaseIn, float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
	}

	@Override
	public void doRender(EntityPenguin entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	protected float handleRotationFloat(EntityPenguin livingBase, float partialTicks) {
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 0.0F) * f1;
	}

	protected void applyRotations(EntityPenguin entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

		float angle0 = 15.0F;
		float angle1 = angle0 / 2;
		float angle2 = angle1 / 2;
		float angle3 = angle2 / 2;
		float time = 1.5F;

		if ((double) entityLiving.limbSwingAmount >= 0.01D && !entityLiving.isSliding()) {
			float timeMaster = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + angle0;
			float angleMaster = (Math.abs(timeMaster % angle1 - angle2) - angle3) / angle3;
			GlStateManager.rotate(angle2 * angleMaster * time, 0.0F, 0.0F, 1.0F);
		}
	}

	public static class Factory implements IRenderFactory<EntityPenguin> {
		@Override
		public Render<? super EntityPenguin> createRenderFor(RenderManager manager) {
			return new RenderPenguin(manager);
		}
	}
}
