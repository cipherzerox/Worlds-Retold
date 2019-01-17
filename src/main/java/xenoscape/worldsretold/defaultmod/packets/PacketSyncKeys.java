package xenoscape.worldsretold.defaultmod.packets;


import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class PacketSyncKeys implements IMessage {
    private boolean isKeyPressed;
    private UUID player;
    private String keyName;

    public PacketSyncKeys() {
    }

    public PacketSyncKeys(String keyName, boolean isKeyPressed, UUID player) {

        this.keyName = keyName;

        this.isKeyPressed = isKeyPressed;

        this.player = player;

    }


    public void fromBytes(ByteBuf buf) {

        this.keyName = ByteBufUtils.readUTF8String(buf);

        this.isKeyPressed = buf.readBoolean();

        this.player = UUID.fromString(ByteBufUtils.readUTF8String(buf));

    }


    public void toBytes(ByteBuf buf) {

        ByteBufUtils.writeUTF8String(buf, this.keyName);

        buf.writeBoolean(this.isKeyPressed);

        ByteBufUtils.writeUTF8String(buf, this.player.toString());

    }

    public static class Handler
            implements net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler<PacketSyncKeys, IMessage> {
        public IMessage onMessage(final PacketSyncKeys packet, final MessageContext ctx) {
            IThreadListener mainThread = (net.minecraft.world.WorldServer) ctx
                    .getServerHandler().player.world;
            mainThread.addScheduledTask(new Runnable() {
                public void run() {
                    net.minecraft.entity.player.EntityPlayer player = ctx.getServerHandler().player;
                }
            });
            return null;
        }
    }
}