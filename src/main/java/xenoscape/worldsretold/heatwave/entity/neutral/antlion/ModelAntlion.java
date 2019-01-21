package xenoscape.worldsretold.heatwave.entity.neutral.antlion;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelTrapjaw - Enderman_of_D00M
 * Created using Tabula 7.0.0
 */
public class ModelAntlion extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer rightleg1;
    public ModelRenderer rightleg2;
    public ModelRenderer rightleg3;
    public ModelRenderer leftleg1;
    public ModelRenderer leftleg2;
    public ModelRenderer leftleg3;
    public ModelRenderer leftmouthpart;
    public ModelRenderer rightmouthpart;

    public ModelAntlion() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, -10.0F);
        this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        this.body = new ModelRenderer(this, 0, 17);
        this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.body.addBox(-5.0F, -4.5F, -10.0F, 10, 9, 20, 0.0F);
        this.rightleg3 = new ModelRenderer(this, 0, 46);
        this.rightleg3.setRotationPoint(-4.0F, 0.0F, 3.0F);
        this.rightleg3.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(rightleg3, 0.4363323129985824F, 2.1816615649929116F, 0.0F);
        this.leftleg3 = new ModelRenderer(this, 0, 46);
        this.leftleg3.mirror = true;
        this.leftleg3.setRotationPoint(4.0F, 0.0F, 3.0F);
        this.leftleg3.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(leftleg3, 0.4363323129985824F, -2.1816615649929116F, 0.0F);
        this.leftmouthpart = new ModelRenderer(this, 30, 2);
        this.leftmouthpart.mirror = true;
        this.leftmouthpart.setRotationPoint(2.0F, 0.0F, -8.0F);
        this.leftmouthpart.addBox(-1.5F, -0.5F, -12.0F, 3, 1, 14, 0.0F);
        this.rightleg2 = new ModelRenderer(this, 0, 46);
        this.rightleg2.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.rightleg2.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(rightleg2, 0.4363323129985824F, 1.5707963267948966F, 0.0F);
        this.rightmouthpart = new ModelRenderer(this, 30, 2);
        this.rightmouthpart.setRotationPoint(-2.0F, 0.0F, -8.0F);
        this.rightmouthpart.addBox(-1.5F, -0.5F, -12.0F, 3, 1, 14, 0.0F);
        this.rightleg1 = new ModelRenderer(this, 0, 46);
        this.rightleg1.setRotationPoint(-4.0F, 0.0F, -3.0F);
        this.rightleg1.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(rightleg1, 0.4363323129985824F, 0.9599310885968813F, 0.0F);
        this.leftleg2 = new ModelRenderer(this, 0, 46);
        this.leftleg2.mirror = true;
        this.leftleg2.setRotationPoint(4.0F, 0.0F, 0.0F);
        this.leftleg2.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(leftleg2, 0.4363323129985824F, -1.5707963267948966F, 0.0F);
        this.leftleg1 = new ModelRenderer(this, 0, 46);
        this.leftleg1.mirror = true;
        this.leftleg1.setRotationPoint(4.0F, 0.0F, -3.0F);
        this.leftleg1.addBox(-1.0F, -1.0F, -16.0F, 2, 2, 16, 0.0F);
        this.setRotateAngle(leftleg1, 0.4363323129985824F, -0.9599310885968813F, 0.0F);
        this.body.addChild(this.head);
        this.body.addChild(this.rightleg3);
        this.body.addChild(this.leftleg3);
        this.head.addChild(this.leftmouthpart);
        this.body.addChild(this.rightleg2);
        this.head.addChild(this.rightmouthpart);
        this.body.addChild(this.rightleg1);
        this.body.addChild(this.leftleg2);
        this.body.addChild(this.leftleg1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
