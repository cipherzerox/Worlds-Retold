package xenoscape.worldsretold.hellfire.entity.hostile.hellhound;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.hellfire.entity.hostile.livingflame.EntityLivingFlame;
import xenoscape.worldsretold.hellfire.entity.hostile.livingflame.RenderLivingFlame;

@SideOnly(Side.CLIENT)
public class RenderHellhound extends RenderLiving<EntityHellhound>
{
	public static final RenderHellhound.Factory FACTORY = new RenderHellhound.Factory();
    private static final ResourceLocation HELLHOUND_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

    public RenderHellhound(RenderManager p_i47187_1_)
    {
        super(p_i47187_1_, new ModelHellhound(), 0.6F);
        this.addLayer(new LayerFireMane(this));
    }
    
    public ModelHellhound getMainModel()
    {
        return (ModelHellhound)super.getMainModel();
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    protected void preRenderCallback(EntityHellhound entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityHellhound entity)
    {
        return HELLHOUND_TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityHellhound>
    {
        @Override
        public Render<? super EntityHellhound> createRenderFor(RenderManager manager) 
        {
            return new RenderHellhound(manager);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public class LayerFireMane implements LayerRenderer<EntityHellhound>
    {
        private final RenderHellhound snowManRenderer;

        public LayerFireMane(RenderHellhound snowManRendererIn)
        {
            this.snowManRenderer = snowManRendererIn;
        }

        public void doRenderLayer(EntityHellhound entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
        {
	        IBlockState iblockstate = Blocks.FIRE.getDefaultState();
            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.51F, 0.51F, 0.51F);
            GlStateManager.translate(-0.5F, 1.2F, -0.725F);
            GlStateManager.rotate(90F, 1F, 0F, 0F);
            int i = entitylivingbaseIn.getBrightnessForRender();
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.snowManRenderer.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            blockrendererdispatcher.renderBlockBrightness(iblockstate, entitylivingbaseIn.getBrightness());
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
        }

        public boolean shouldCombineTextures()
        {
            return true;
        }
    }
}