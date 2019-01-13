package xenoscape.worldsretold.defaultmod.entity.neutral.automaton;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xenoscape.worldsretold.hailstorm.entity.layer.LayerAutomatonGlow;

public class RenderAutomaton extends RenderBiped<EntityAutomaton> {
	private ResourceLocation ACTIVE_TEXTURE = new ResourceLocation("worldsretold:textures/entity/automaton_active.png");
	private ResourceLocation TEXTURE = new ResourceLocation("worldsretold:textures/entity/automaton.png");
	public static final RenderAutomaton.Factory FACTORY = new RenderAutomaton.Factory();

	public RenderAutomaton(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelAutomaton(), 0.5F);
		this.addLayer(new LayerAutomatonGlow(this, ACTIVE_TEXTURE));
		this.addLayer(new LayerBipedArmor(this) {
			protected void initArmor() {
				this.modelLeggings = new ModelBiped(0.5F);
				this.modelArmor = new ModelBiped(1.0F);
			}
		});
	}

	@Override
	@Nonnull
	public ResourceLocation getEntityTexture(@Nonnull EntityAutomaton entity) {
		return TEXTURE;
	}

	@Override
	protected void preRenderCallback(EntityAutomaton entitylivingbaseIn, float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
	}

	@Override
	public void doRender(EntityAutomaton entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public static class Factory implements IRenderFactory<EntityAutomaton> {
		@Override
		public Render<? super EntityAutomaton> createRenderFor(RenderManager manager) {
			return new RenderAutomaton(manager);
		}
	}
}
