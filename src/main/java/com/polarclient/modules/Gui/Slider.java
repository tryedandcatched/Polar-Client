package com.polarclient.modules.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Mouse;
import scala.swing.event.MouseClicked;

public class Slider {
    private int x;
    private int y;
    private int width;
    private int height;
    private float minValue;
    private float maxValue;
    private float value;
    private boolean isDragging;
    private int dragOffset;

    public Slider(int x, int y, int width, int height, float minValue, float maxValue, float initialValue) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = initialValue;
    }

    public void draw(Minecraft mc, int mouseX, int mouseY) {
        // Draw slider track
        GuiScreen.drawRect(x, y + height / 2 - 1, x + width, y + height / 2, 0xFFFFFFFF);
        // Draw slider knob
        int knobX = x + (int) ((value - minValue) / (maxValue - minValue) * (width - 6));
        int knobY = y;
        GuiScreen.drawRect(knobX, knobY, knobX + 6, knobY + height, isDragging ? 0xFF00FF00 : 0xFFFF0000);
        mouseClicked(mouseX, mouseY);
    }

    public void mouseClicked(int mouseX, int mouseY) {
        Mouse.poll();
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height && Mouse.isButtonDown(0)) {
            int knobX = x + (int) ((value - minValue) / (maxValue - minValue) * (width - 6));
            if (mouseX > knobX) {
                value+=1;
            } else {
                value-=1;
            }
        }
    }


    public float getValue() {
        return value;
    }
    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }
}

