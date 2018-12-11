package xenoform.hailstorm.entity.automaton;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderAutomaton extends RenderLiving<EntityAutomaton> 
{
    private ResourceLocation TEXTURE = new ResourceLocation("hailstorm:textures/entity/automaton.png");
    public static final RenderAutomaton.Factory FACTORY = new RenderAutomaton.Factory();

    public RenderAutomaton(RenderManager renderManagerIn)
    {
      //  super(renderManagerIn, new ModelAutomaton(), 0.55F);
        super(renderManagerIn, new ModelZombie(), 1F);
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        });
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull EntityAutomaton entity)
    {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityAutomaton entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    @Override
    public void doRender(EntityAutomaton entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public static class Factory implements IRenderFactory<EntityAutomaton>
    {
        @Override
        public Render<? super EntityAutomaton> createRenderFor(RenderManager manager) {
            return new RenderAutomaton(manager);
        }
    }
}
