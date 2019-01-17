package xenoscape.worldsretold.defaultmod.init;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.defaultmod.util.RenderHelper;
import xenoscape.worldsretold.hailstorm.entity.layer.LayerFreezingRenderer.LayerFreezing;
import xenoscape.worldsretold.hailstorm.potion.PotionFreezing;

public class DefaultClientEvents {

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {

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