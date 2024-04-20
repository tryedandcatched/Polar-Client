package com.polarclient.modules.Gui;

import com.polarclient.modules.Module;
import com.polarclient.modules.Modules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.xml.soap.Text;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

public class Gui extends GuiScreen {
    private List<TextButton> button = new ArrayList<TextButton>();
    private boolean isMenuToggled = false;
    private Module toggledModule;
    @Override
    public void drawScreen(int x, int y, float ticks) {
        GL11.glColor4f(1, 1, 1, 1);
        drawDefaultBackground();
        // mc.getTextureManager().bindTexture(new ResourceLocation("polarclient", "gui.png"));
        // drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth, guiHeight);
        final FontRenderer fr = new FontRenderer(mc.gameSettings, new ResourceLocation("polarclient", "ascii.png"), mc.renderEngine, true);
        if (isMenuToggled){
            drawModuleMenu(toggledModule);
            return;
        }
        int rectWidth = 100; // Adjust width of the rectangle
        int rectHeight = 200; // Adjust height of the rectangle
        int rectX = (width - rectWidth) / 4; // Center horizontally
        int rectY = (height - rectHeight) / 2; // Center vertically
        int rectColor = 0x80000000; // Adjust color of the rectangle
        int borderColor = 0xFFFFFFFF; // Adjust color of the border
        drawBlurredRect(rectX, rectY, rectX + rectWidth, rectY + rectHeight, rectColor, borderColor);

        // Draw title text
        String title = "Ghost";
        int titleWidth = fr.getStringWidth(title);
        int titleX = (width - titleWidth) / 4; // Center horizontally
        int titleY = rectY + (fr.FONT_HEIGHT) + 5; // Center vertically
        fr.drawString(title, titleX, titleY, 0xFFFFFFFF);

        int buttonWidth = 100;
        int buttonHeight = 20;
        int buttonX = (width - buttonWidth)/4;
        int padding = 20;
        int buttonY = titleY + 20; // Adjust the vertical position as needed
        for(Module mod : Modules.AllModule) {
            TextButton a =new TextButton(0, buttonX, buttonY, buttonWidth, buttonHeight, mod.getName());
            a.drawButton(mc, x,y,ticks);
            if (a.onClick()){
                drawModuleMenu(mod);
                toggledModule = mod;
                isMenuToggled = true;
            }
            buttonY += padding;
        }

        super.drawScreen(x, y, ticks);
    }
    private void drawModuleMenu(Module module){
        final FontRenderer fr = new FontRenderer(mc.gameSettings, new ResourceLocation("polarclient", "ascii.png"), mc.renderEngine, true);

        int rectWidth = 500; // Adjust width of the rectangle
        int rectHeight = 400; // Adjust height of the rectangle
        int rectX = (this.width - rectWidth) / 2; // Center horizontally
        int rectY = (this.height - rectHeight) / 2; // Center vertically
        int rectColor = 0x80000000; // Adjust color of the rectangle
        int borderColor = 0xFFFFFFFF; // Adjust color of the border
        drawBlurredRect(rectX, rectY, rectX + rectWidth, rectY + rectHeight, rectColor, borderColor);
        String title = module.getName();
        int titleWidth = fr.getStringWidth(title);
        int titleX = (rectX + 15); // Center horizontally
        int titleY = rectY + (fr.FONT_HEIGHT) + 5; // Center vertically
        fr.drawString(title, titleX, titleY, 0xFFFFFFFF);

    }

    private static void drawSmoothRect(int left, int top, int bottom, int right, int color, int borderColor, int smoothing) {
        GuiScreen.drawRect(left + smoothing, top + smoothing, right - smoothing, bottom - smoothing, color); // Draw inner rectangle
        // Draw smooth border
        for (int i = 0; i < smoothing + 1; i++) {
            GuiScreen.drawRect(left + i, top + i, right - i, top + i + smoothing, borderColor); // Top border
            GuiScreen.drawRect(left + i, bottom - i - smoothing, right - i, bottom - i, borderColor); // Bottom border
            GuiScreen.drawRect(left + i, top + smoothing, left + i + smoothing, bottom - smoothing, borderColor); // Left border
            GuiScreen.drawRect(right - i - smoothing, top + smoothing, right - i, bottom - smoothing, borderColor); // Right border
        }

    }

    private static void drawBlurredRect(int left, int top, int right, int bottom, int color, int borderColor) {
        GuiScreen.drawRect(left + 1, top + 1, right - 1, bottom - 1, color); // Draw inner rectangle

        // Draw blurred border
        for (int i = 0; i < 4; i++) {
            float alpha = 1.0F - (i + 1) / 5.0F; // Adjust alpha value for blurring effect
            int blurredColor = (Math.min((int) (alpha * 255), 255) << 24) | (borderColor & 0xFF000000); // Apply alpha to border color

            // Draw lines around the inner rectangle with decreasing opacity to create a blurred effect
            GuiScreen.drawRect(left + i, top + i, right - i, top + i + 1, blurredColor); // Top border
            GuiScreen.drawRect(left + i, bottom - i - 1, right - i, bottom - i, blurredColor); // Bottom border
            GuiScreen.drawRect(left + i, top + 1, left + i + 1, bottom - 1, blurredColor); // Left border
            GuiScreen.drawRect(right - i - 1, top + 1, right - i, bottom - 1, blurredColor); // Right border
        }
    }

    @Override
    public void initGui() {
        super.initGui();

    }
}
