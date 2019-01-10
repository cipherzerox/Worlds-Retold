package xenoscape.worldsretold.hailstorm.entity.passive.caribou;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.hailstorm.entity.passive.nix.EntityNix;
import xenoscape.worldsretold.hailstorm.entity.passive.nix.RenderNix;

@SideOnly(Side.CLIENT)
public class RenderCaribou extends RenderLiving<EntityCaribou>
{
    private static final ResourceLocation CARIBOU_TEXTURES = new ResourceLocation("worldsretold:textures/entity/reindeer.png");

    public RenderCaribou(RenderManager p_i47210_1_)
    {
        super(p_i47210_1_, new ModelCaribou(), 0.8F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCaribou entity)
    {
        return CARIBOU_TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityCaribou>
    {

        @Override
        public Render<? super EntityCaribou> createRenderFor(RenderManager manager) {
            return new RenderCaribou(manager);
        }

    }
}