package com.polarclient.modules.blatant;

import com.polarclient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class scaffold extends Module {
    Minecraft mc = Minecraft.getMinecraft();
    private int tickneeded = 5;
    @Override
    public void onEnable(){
        toggled = true;
        float newYaw = mc.thePlayer.rotationYaw + 180.0f;
        newYaw %= 360.0f;
        if (newYaw < 0) {
            newYaw += 360.0f;
        }
        float newPitch = 77.0f; // Look directly downward

        mc.thePlayer.rotationYaw = newYaw;
        mc.thePlayer.rotationPitch = newPitch;
    }

    @Override
    public void onDisable() {
        toggled = false;
        tickneeded = 5;
    }

    @SubscribeEvent
    private void onTick(TickEvent.ClientTickEvent ev){
        if(!toggled) return;
        if (mc.thePlayer == null) return;
        if (mc.theWorld == null) return;

        int key = mc.gameSettings.keyBindLeft.getKeyCode();
        if (tickneeded == 0){
            KeyBinding.setKeyBindState(key, false);
            return;
        }
        tickneeded--;
        KeyBinding.setKeyBindState(key, true);
    }
}
