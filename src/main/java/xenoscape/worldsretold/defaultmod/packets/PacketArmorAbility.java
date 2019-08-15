package xenoscape.worldsretold.defaultmod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.entity.ISnowCreature;
import xenoscape.worldsretold.hailstorm.init.HailstormItems;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;

import java.util.List;

public class PacketArmorAbility implements IMessage {
    private int entityId;

    public PacketArmorAbility() {
    }

    public PacketArmorAbility(int parEntityId) {
        entityId = parEntityId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entityId = ByteBufUtils.readVarInt(buf, 4);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, entityId, 4);
    }

    public static class Handler implements IMessageHandler<PacketArmorAbility, IMessage> {
        @Override
        public IMessage onMessage(final PacketArmorAbility message, final MessageContext ctx) {
            final EntityPlayerMP thePlayer = (EntityPlayerMP) ctx.getServerHandler().player;
            thePlayer.getServer().addScheduledTask(
                    new Runnable() {
                        @Override
                        public void run() {
                            Minecraft mc = Minecraft.getMinecraft();
                            EntityPlayer player = mc.player;
                            World world = mc.world;
                            if (player != null) {
                                if (WorldsRetold.KEYS.armor_ability(player)) {
                                    WorldsRetold.LOGGER.info("Key Triggered");
                                    if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem().equals(HailstormItems.CRYONITE_HELMET)
                                            && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem().equals(HailstormItems.CRYONITE_CHESTPLATE)
                                            && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem().equals(HailstormItems.CRYONITE_LEGGINGS)
                                            && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem().equals(HailstormItems.CRYONITE_BOOTS)) {
                                        WorldsRetold.LOGGER.info("Calls Passed");
                                        List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(5.0D, 5.0D, 5.0D));
                                        for (Entity entity : entities)
                                            if (entity instanceof IMob && entity instanceof EntityLivingBase && !(entity instanceof ISnowCreature) && entity != null && !((EntityLivingBase) entity).isPotionActive(HailstormPotions.FREEZING)) {
                                                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(HailstormPotions.FREEZING, 600, 0));
                                                WorldsRetold.LOGGER.info("Freeze!");
                                            }
                                    }
                                }
                            }
                            return;
                        }
                    }
            );
            return null;
        }
    }
}
