package xenoscape.hailstorm.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xenoscape.hailstorm.Hailstorm;

public class ParticleShielded extends Particle {
	float oSize;
	private final ResourceLocation texture = new ResourceLocation(Hailstorm.MODID, "particle/shielded");

	public ParticleShielded(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i46284_8_,
			double p_i46284_10_, double p_i46284_12_) {
		this(worldIn, xCoordIn, yCoordIn, zCoordIn, p_i46284_8_, p_i46284_10_, p_i46284_12_, 1.0F);
	}

	public ParticleShielded(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i46285_8_,
			double p_i46285_10_, double p_i46285_12_, float p_i46285_14_) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		this.motionX += p_i46285_8_ * 0.4D;
		this.motionY += p_i46285_10_ * 0.4D;
		this.motionZ += p_i46285_12_ * 0.4D;
		this.particleRed = 1;
		this.particleGreen = 1;
		this.particleBlue = 1;
		this.particleScale *= 0.75F;
		this.particleScale *= p_i46285_14_;
		this.oSize = this.particleScale;
		this.particleMaxAge = (int) (6.0D / (Math.random() * 0.8D + 0.6D));
		this.particleMaxAge = (int) ((float) this.particleMaxAge * p_i46285_14_);
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks()
				.getAtlasSprite(texture.toString());
		setParticleTexture(sprite);
		this.onUpdate();
	}
	
	@Override
	public int getFXLayer() {
		return 1;
	}

	/**
	 * Renders the particle
	 */
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX,
			float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float f = ((float) this.particleAge + partialTicks) / (float) this.particleMaxAge * 32.0F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		this.particleScale = this.oSize * f;
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setExpired();
		}

		this.move(this.motionX, this.motionY, this.motionZ);
		this.particleGreen = (float) ((double) this.particleGreen * 0.96D);
		this.particleBlue = (float) ((double) this.particleBlue * 0.9D);
		this.motionX *= 0.699999988079071D;
		this.motionY *= 0.699999988079071D;
		this.motionZ *= 0.699999988079071D;
		this.motionY -= 0.019999999552965164D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
}