package xenoform.hailstorm.util;

import net.minecraft.item.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraftforge.client.model.*;
import net.minecraft.block.*;
import net.minecraftforge.fml.relauncher.*;

public interface ModelRegistry {
	@SideOnly(Side.CLIENT)
	default void registerModel() {
		if (this instanceof Item) {
			ModelLoader.setCustomModelResourceLocation((Item) this, 0,
					new ModelResourceLocation(((Item) this).getRegistryName(), "inventory"));
		} else if (this instanceof Block) {
			ModelUtils.registerToState((Block) this, 0, ((Block) this).getDefaultState());
		}
	}
}
