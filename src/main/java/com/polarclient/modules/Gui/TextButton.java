package com.polarclient.modules.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class TextButton extends GuiButton {

    private int x;
    private int y;
    boolean isHovered;


    public TextButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.id = buttonId;
        this.x = x;
        this.y = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            boolean isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            this.isHovered = isHovered;
            int color = this.enabled ? (isHovered ? 0xFFCCCCCC : 0xFF999999) : 0xFF666666; // Button color when enabled/disabled and hovered/not hovered
            if (isHovered)
                drawRect(this.x, this.y, this.x + this.width, this.y + this.height, color);
            this.drawCenteredString(mc.fontRendererObj, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, 0xFFFFFF); // Centering the text
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



