package xenoscape.hailstorm.util;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.registries.IRegistryDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelUtils
{
    private static final Map<IRegistryDelegate<Block>, IStateMapper> stateMappers;
    private static final IStateMapper defaultStateMapper;
    
    public static void registerToState(final Block b, final int itemMeta, final IBlockState state) {
        final ModelResourceLocation mrl = ModelUtils.stateMappers.getOrDefault(state.getBlock().delegate, ModelUtils.defaultStateMapper).putStateModelLocations(state.getBlock()).get(state);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), itemMeta, mrl);
    }
    
    public static <T extends Comparable<T>> void registerToStateSingleVariant(final Block b, final IProperty<T> variant) {
        final List<T> variants = new ArrayList<>(variant.getAllowedValues());
        for (int i = 0; i < variants.size(); ++i) {
            registerToState(b, i, b.getDefaultState().withProperty(variant, variants.get(i)));
        }
    }
    
    static {
        stateMappers = ReflectionHelper.getPrivateValue(ModelLoader.class, null, new String[] { "customStateMappers" });
        defaultStateMapper = new DefaultStateMapper();
    }
}