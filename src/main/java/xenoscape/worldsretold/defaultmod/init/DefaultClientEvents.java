package xenoscape.worldsretold.defaultmod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.util.RenderHelper;
import xenoscape.worldsretold.hailstorm.entity.ISnowCreature;
import xenoscape.worldsretold.hailstorm.entity.layer.LayerFreezingRenderer.LayerFreezing;
import xenoscape.worldsretold.hailstorm.init.HailstormItems;
import xenoscape.worldsretold.hailstorm.init.HailstormPotions;
import xenoscape.worldsretold.hailstorm.potion.PotionFreezing;

import java.util.List;

public class DefaultClientEvents {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        World world = mc.world;
        if (player != null) {
            WorldsRetold.LOGGER.info("Player Found");
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
                        }
                }
            }

        }
    }

    @SubscribeEvent
    public void onPlayerEveryLogin(PlayerEvent.PlayerLoggedInEvent event) {
        event.player.sendMessage(
                new TextComponentString(TextFormatting.GOLD + event.player.getDisplayName().getFormattedText()
                        + TextFormatting.GOLD + ", thank you for downloading Worlds Retold!"));
        ClickEvent discordLink = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/xRdGTTH");
        Style clickableDiscordLink = new Style().setClickEvent(discordLink);
        TextComponentString discordLinkChatComponent = new TextComponentString(
                TextFormatting.AQUA + "[CLICK HERE!]" + TextFormatting.AQUA + " to join our Discord.");
        discordLinkChatComponent.setStyle(clickableDiscordLink);
        event.player.sendMessage(discordLinkChatComponent);
    }

}