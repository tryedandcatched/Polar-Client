package com.example.examplemod.hud;

import com.polarclient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.polarclient.modules.Modules;
import org.lwjgl.opengl.GL11;
import sun.font.TrueTypeFont;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ExampleHUD {
    Minecraft mc = Minecraft.getMinecraft();

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

        final FontRenderer fr = new FontRenderer(mc.gameSettings, new ResourceLocation("polarclient", "ascii.png"), mc.renderEngine, true);

        // for example here we draw text on the screen
        final String text1 = "Polar";
        final String text2 = "Client";
        final String text3 = "Indev";

        GlStateManager.pushMatrix();
        GlStateManager.scale(2F, 2F, 2F);
        fr.drawStringWithShadow(text1, 5, 5, 0xCFC7FC);
        fr.drawStringWithShadow(text2, (8+fr.getStringWidth(text1)), 5, 0x9f8ff7);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(1F, 1F, 1F);
        fr.drawStringWithShadow(text3, (8+fr.getStringWidth(text1)+fr.getStringWidth(text2)*3), 8, 0x9f8ff7);
        GlStateManager.popMatrix();

        int first_padding = 20;
        int padding_incrementing = 9;
        int padding_height = 8;

        Comparator<Module> c = (module1, module2) -> {
            // Calculate the length of the concatenation of name and mode for each module
            int length1 = module1.getName().length() + module1.getMode().length();
            int length2 = module2.getName().length() + module2.getMode().length();
            // Compare the lengths
            return Integer.compare(length2, length1);
        };
        List<Module> modules = new ArrayList<Module>(Modules.EnabledModules);
        modules.sort(c);

        int max_right = 0 ;
        GlStateManager.pushMatrix();
        GlStateManager.scale(1.2f, 1.2f, 1.2f);
        for (Module module: modules)
        {
            String mode = "<" + module.getMode() + ">";
            int righ=2 + fr.getStringWidth(module.getName()+mode) + 5;
            if (righ > max_right){
                max_right = righ;
            }
            drawRect(2, first_padding + padding_height - 2, max_right, first_padding + padding_height + 10, 0xFF000000);
            String str = module.getName();
            fr.drawStringWithShadow(str, 2,first_padding+padding_height,0xcaa1e0);
            if (!Objects.equals(module.getMode(), "")){
                fr.drawStringWithShadow(mode, 2 + fr.getStringWidth(str) + 5,
                        first_padding + padding_height,
                        0xcaa000);
            }
            padding_height+=padding_incrementing;
        }
        GlStateManager.popMatrix();
    }
    private static void drawRect(int left, int top, int right, int bottom, int color) {
        GuiScreen.drawRect(left, top, right, bottom, color);
    }

}
