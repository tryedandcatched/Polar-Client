package utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;

public class Player {

    private static Minecraft mc = Minecraft.getMinecraft();
    public static boolean inGui(){

        if (mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof Gui){
            return true;
        }
        return false;
    }
}