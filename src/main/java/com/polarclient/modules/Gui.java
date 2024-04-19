package com.polarclient.modules;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import java.lang.ref.Reference;

public class Gui extends GuiScreen {
   int guiWidth = 518;
   int guiHeight = 241;

   @Override
    public void drawScreen(int x,int y, float ticks){
       int centerX = (width - guiWidth) / 2;
       int centerY = (height - guiHeight) / 2;
       GL11.glColor4f(1,1,1,1);
       drawDefaultBackground();
       mc.getTextureManager().bindTexture(new ResourceLocation("polarclient", "gui.png"));
       drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth, guiHeight);
       super.drawScreen(x,y,ticks);
    }

   @Override
    public void initGui() {
       super.initGui();
   }
}
