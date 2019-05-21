package xenoscape.worldsretold.heatwave.entity.hostile.evilcactus;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.hailstorm.entity.passive.caribou.EntityCaribou;
import xenoscape.worldsretold.hailstorm.entity.passive.caribou.RenderCaribou;

@SideOnly(Side.CLIENT)
public class RenderEvilCactus extends RenderLiving<EntityEvilCactus>
{
	public static final RenderEvilCactus.Factory FACTORY = new RenderEvilCactus.Factory();
	
	protected RenderEvilCactus(RenderManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelChest(), 0F);
		this.addLayer(new RenderEvilCactus.CactusBlocks(this));
	}

	protected ResourceLocation getEntityTexture(EntityEvilCactus entity) 
	{
		return null;
	}

    public static class Factory implements IRenderFactory<EntityEvilCactus>
    {
        @Override
        public Render<? super EntityEvilCactus> createRenderFor(RenderManager manager) 
        {
            return new RenderEvilCactus(manager);
        }
    }
    
	class CactusBlocks implements LayerRenderer<EntityEvilCactus>
	{
	    private final RenderEvilCactus endermanRenderer;

	    public CactusBlocks(RenderEvilCactus endermanRendererIn)
	    {
	        this.endermanRenderer = endermanRendererIn;
	    }

	    public void doRenderLayer(EntityEvilCactus entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	    {
	        float f = MathHelper.cos(limbSwing * 0.9993F) * limbSwingAmount;
	        IBlockState iblockstate = Blocks.CACTUS.getDefaultState();
            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
            GlStateManager.scale(-1F, -1F, 1F);
            GlStateManager.translate(-0.5F, -2.5F, 0.5F);
            if (entitylivingbaseIn.isAggressive() && entitylivingbaseIn.isEntityAlive())
            GlStateManager.translate(0F, (0.05F + 0.1F * f) * ((float)Math.PI * 2F), 0F);
            int i = entitylivingbaseIn.getBrightnessForRender();
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.endermanRenderer.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            for (float co = 0; co < (float)entitylivingbaseIn.getSize(); ++co)
            {
                GlStateManager.translate(0F, 1F, co > 0 ? 1F : 0F);
	            blockrendererdispatcher.renderBlockBrightness(iblockstate, entitylivingbaseIn.getBrightness());
            }
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
	    }

	    public boolean shouldCombineTextures()
	    {
	        return true;
	    }
	}
}
