package com.polarclient.modules.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

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
        this.value = clamp(initialValue, minValue, maxValue);
    }

    public void draw(Minecraft mc, int mouseX, int mouseY) {
        // Draw slider track
        GuiScreen.drawRect(x, y + height / 2 - 1, x + width, y + height / 2, 0xFFFFFFFF);
        // Draw slider knob
        int knobX = x + (int) ((value - minValue) / (maxValue - minValue) * (width - 6));
        int knobY = y;
        GuiScreen.drawRect(knobX, knobY, knobX + 6, knobY + height, isDragging ? 0xFF00FF00 : 0xFFFF0000);
    }

    public void mouseClicked(int mouseX, int mouseY) {
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            isDragging = true;
            dragOffset = mouseX - x - (int) ((value - minValue) / (maxValue - minValue) * (width - 6));
        }
    }

    public void mouseReleased(int mouseX, int mouseY) {
        isDragging = false;
    }

    public void mouseDragged(int mouseX, int mouseY) {
        if (isDragging) {
            int newValue = clamp(mouseX - x - dragOffset, 0, width - 6);
            value = minValue + ((float) newValue / (width - 6)) * (maxValue - minValue);
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

