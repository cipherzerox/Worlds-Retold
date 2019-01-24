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
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xenoscape.worldsretold.hailstorm.entity.passive.caribou.EntityCaribou;
import xenoscape.worldsretold.hailstorm.entity.passive.caribou.RenderCaribou;

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
	        IBlockState iblockstate = Blocks.CACTUS.getDefaultState();
	    	
	        if (!entitylivingbaseIn.isInvisible())
	        {
	            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
	            GlStateManager.enableRescaleNormal();
	            GlStateManager.pushMatrix();
	            GlStateManager.translate(0.5F, 1.5F, 0.5F);
	            GlStateManager.scale(-1F, -1F, 1F);
	            int i = entitylivingbaseIn.getBrightnessForRender();
	            int j = i % 65536;
	            int k = i / 65536;
	            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            this.endermanRenderer.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
	            for (int co = 0; co < entitylivingbaseIn.getSize(); ++co)
	            {
		            GlStateManager.translate(0, co, co);
		            if (co > 1)
		            GlStateManager.translate(0, -co * 0.5F, -co * 0.5F);
		            if (co > 2)
		            GlStateManager.translate(0, -co * 0.16675F, -co * 0.16675F);
		            blockrendererdispatcher.renderBlockBrightness(iblockstate, 1.0F);
	            }
	            GlStateManager.popMatrix();
	            GlStateManager.disableRescaleNormal();
	        }
	    }

	    public boolean shouldCombineTextures()
	    {
	        return true;
	    }
	}
}
