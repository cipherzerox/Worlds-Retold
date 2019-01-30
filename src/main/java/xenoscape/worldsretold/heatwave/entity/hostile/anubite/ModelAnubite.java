package xenoscape.worldsretold.heatwave.entity.hostile.anubite;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelAnubite - Enderman_of_D00M
 * Created using Tabula 7.0.0
 */
public class ModelAnubite extends ModelBiped 
{
    public ModelAnubite() 
    {
        super(0.0F, 0.0F, 64, 32);
        this.bipedHead = (new ModelRenderer(this));
        this.bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedHead.setTextureOffset(32, 6).addBox(-3.0F, -4.0F, -10.0F, 5, 4, 6, 0.0F);
        this.bipedHead.setTextureOffset(24, 0).addBox(-4.0F, -13.0F, -0.5F, 3, 5, 1, 0.0F);
        this.bipedHead.setTextureOffset(32, 0).addBox(1.0F, -13.0F, -0.5F, 3, 5, 1, 0.0F);
        this.bipedHead.setTextureOffset(48, 0).addBox(0.5F, -11.0F, 2.0F, 7, 11, 1, 0.0F);
        this.bipedHead.setTextureOffset(48, 0).addBox(-7.5F, -11.0F, 2.0F, 7, 11, 1, 0.0F);
    }
}
