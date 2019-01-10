package xenoscape.worldsretold;

import net.minecraft.creativetab.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;

public abstract class WorldsRetoldTabs extends CreativeTabs {
    public static final WorldsRetoldTabs W_TAB;

    public WorldsRetoldTabs(final String label) {
        super(label);
    }

    static {
        W_TAB = new WorldsRetoldTabs("tabWorldsRetold") {
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(Items.KNOWLEDGE_BOOK);
            }
        };
    }
}
