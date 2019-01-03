package xenoscape.worldsretold.heatwave.entity.neutral.scorpion;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScorpion extends RenderLiving<EntityScorpion>
{
    private static final ResourceLocation DESERT_SCORPION = new ResourceLocation("textures/entity/spider/spider.png");

    public RenderScorpion(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSpider(), 1.0F);
    }

    protected float getDeathMaxRotation(EntityScorpion entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityScorpion entity)
    {
        return DESERT_SCORPION;
    }
}