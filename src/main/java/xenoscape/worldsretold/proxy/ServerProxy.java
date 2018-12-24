package xenoscape.worldsretold.proxy;

import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy {
	
	protected void registerEventListeners() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event) {
	}

	public void preInit(FMLPreInitializationEvent e) {
	}
}
