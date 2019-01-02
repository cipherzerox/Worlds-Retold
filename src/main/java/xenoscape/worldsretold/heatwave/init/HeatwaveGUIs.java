package xenoscape.worldsretold.heatwave.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import xenoscape.worldsretold.heatwave.entity.neutral.camel.EntityCamel;
import xenoscape.worldsretold.heatwave.entity.neutral.camel.container.GuiScreenCamelInventory;

public class HeatwaveGUIs implements IGuiHandler {
​	public static final int CAMEL = 0;

    @Override
    public Container getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case CAMEL:
                return new GuiScreenCamelInventory(player.inventory, camel.getInventoryColumns(), camel);
            default:
                return null;
        }
    }
}
