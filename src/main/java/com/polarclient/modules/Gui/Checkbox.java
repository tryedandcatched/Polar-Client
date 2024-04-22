package com.polarclient.modules.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class Checkbox extends GuiButton {
    private int x;
    private int y;
    boolean isHovered;
    private static int hoveredcolor;
    private static int color;
    private boolean wasClicked = false;
    public Checkbox(int buttonId, int x, int y, int widthIn, int heightIn, int color, int hoveredcolor) {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.id = buttonId;
        this.x = x;
        this.y = y;
        this.width = widthIn;
        this.height = heightIn;
        this.color = color;
        this.hoveredcolor = hoveredcolor;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            boolean isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            this.isHovered = isHovered;
            int colore = this.enabled ? (isHovered ? hoveredcolor : color) : 0xFF666666; // Button color when enabled/disabled and hovered/not hovered
            drawRect(this.x, this.y, this.x + this.width, this.y + this.height, colore);
        }
    }

    public boolean onClick(){
        if(this.isHovered){
            Mouse.poll();
            if (Mouse.isButtonDown(0)){
                return true;
            }
        }
        return false;
    }
}



