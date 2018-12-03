package xenoform.hailstorm.proxy;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import xenoform.hailstorm.Hailstorm;
import xenoform.hailstorm.packets.PacketUniqueAttack;

public class ServerProxy {
	
	public RayTraceResult getMouseOver(double v) {
		return null;
	}
	
	protected void registerEventListeners() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event) {
	}

	public void preInit(FMLPreInitializationEvent e) {
		int id = 0;
		Hailstorm.network.registerMessage(PacketUniqueAttack.Handler.class, PacketUniqueAttack.class, id++, Side.SERVER);
	}
}
