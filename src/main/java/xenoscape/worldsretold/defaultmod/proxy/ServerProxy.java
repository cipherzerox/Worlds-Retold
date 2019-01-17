package xenoscape.worldsretold.defaultmod.proxy;

import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.packets.PacketSyncKeys;

public class ServerProxy {
	
	protected void registerEventListeners() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event) {
	}

	public void preInit(FMLPreInitializationEvent e) {
        int id = 0;
        WorldsRetold.NETWORK.registerMessage(PacketSyncKeys.Handler.class, PacketSyncKeys.class, id++, Side.SERVER);
	}
}
