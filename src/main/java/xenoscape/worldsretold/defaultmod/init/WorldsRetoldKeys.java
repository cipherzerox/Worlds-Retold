package xenoscape.worldsretold.defaultmod.init;

import net.minecraft.client.settings.*;
import net.minecraftforge.fml.relauncher.*;

import java.util.HashMap;
import java.util.UUID;

import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.player.*;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.packets.PacketSyncKeys;

public class WorldsRetoldKeys {
    @SideOnly(Side.CLIENT)
    public static KeyBinding ARMOR_ABILITY;
    public HashMap<UUID, Boolean> armor_ability;

    public WorldsRetoldKeys() {
        this.armor_ability = new HashMap<UUID, Boolean>();
        MinecraftForge.EVENT_BUS.register((Object) this);
    }

    public boolean armor_ability(final EntityPlayer player) {
        return player != null && this.armor_ability.containsKey(player.getPersistentID())
                && this.armor_ability.get(player.getPersistentID());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void updateKeys(final TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && Minecraft.getMinecraft().player != null) {
            final EntityPlayerSP player = Minecraft.getMinecraft().player;
            final UUID uuid = player.getPersistentID();
            if (!this.armor_ability.containsKey(uuid) || WorldsRetoldKeys.ARMOR_ABILITY.isKeyDown() != this.armor_ability.get(uuid)) {
                this.armor_ability.put(uuid, WorldsRetoldKeys.ARMOR_ABILITY.isKeyDown());
                WorldsRetold.NETWORK.sendToServer((IMessage) new PacketSyncKeys("Armor Ability", WorldsRetoldKeys.ARMOR_ABILITY.isKeyDown(), uuid));
            }
        }
    }
}
