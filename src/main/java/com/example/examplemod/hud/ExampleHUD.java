package com.example.examplemod.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.polarclient.modules.Modules;

public class ExampleHUD {

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        // this method is called multiple times per frame, you want to filter it
        // by checking the event type to only render your HUD once per frame
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.drawHUD(event.resolution);
        }
    }

    /**
     * To help you learn how to render HUD you can look at some vanilla minecraft HUD
     * - Tab list : {@link net.minecraft.client.gui.GuiPlayerTabOverlay#renderPlayerlist(int, Scoreboard, ScoreObjective)}
     * - Scoreboard : {@link net.minecraft.client.gui.GuiIngame#renderScoreboard(ScoreObjective, ScaledResolution)}
     * - The Ingame gui, healthbar, hotbar... {@link net.minecraftforge.client.GuiIngameForge#renderGameOverlay(float)}
     */
    private void drawHUD(ScaledResolution resolution) {

        // when drawing a HUD, the coordinates (x, y) represent a point on your screen
        // coordinates (0, 0) is top left of your screen,
        // coordinates (screenWidth, screenHeight) is bottom right of your screen
        final int top = 0;
        final int left = 0;
        final int bottom = resolution.getScaledHeight();
        final int right = resolution.getScaledWidth();

        final FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        // for example here we draw text on the screen
        final String text1 = "Polar";
        final String text2 = "Client";
        final String text3 = "Indev";

        fr.drawStringWithShadow(text1, 5, 5, 0xCFC7FC);
        fr.drawStringWithShadow(text2, (8+fr.getStringWidth(text1)), 5, 0x9f8ff7);
        fr.drawStringWithShadow(text3, (8+fr.getStringWidth(text1)+fr.getStringWidth(text2)), 8, 0x9f8ff7);

        int first_padding = 15;
        int padding_incrementing = 9;
        int padding_height = 8;
        for (String str : Modules.EnabledModules)
        {
            fr.drawStringWithShadow(str, 0,first_padding+padding_height,0xcaa1e0);
            padding_height+=padding_incrementing;
        }

    }

}
