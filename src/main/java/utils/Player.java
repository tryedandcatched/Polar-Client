package utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.Sys;

public class Player {

    private static Minecraft mc = Minecraft.getMinecraft();
    public static boolean inGui(){
        if (mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof Gui){
            return true;
        }
        return false;
    }

    public static void ShowChat(String message){
        if (mc.thePlayer != null)
            mc.thePlayer.addChatMessage(new ChatComponentText(message));
    }

    public static boolean getTarget(int range){
        Entity entity = null;
        //gest the closest entity in the fov and the specified range
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityLivingBase) {
                EntityLivingBase e = (EntityLivingBase) o;
                if (e != mc.thePlayer && e.getDistanceToEntity(mc.thePlayer) <= range && e.ticksExisted>5) {
                    if (e.isCreatureType(EnumCreatureType.CREATURE, true)){
                        System.out.println(e.getDisplayName().toString().contains("player"));
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
