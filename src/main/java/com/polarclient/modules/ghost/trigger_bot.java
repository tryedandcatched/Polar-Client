package com.polarclient.modules.ghost;

import com.polarclient.modules.Module;
import com.polarclient.modules.Modules;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import utils.Mouse;

public class trigger_bot extends Module {
    public static String name = "Trigger Bot";
    private int tickbase = 10;
    private int tickwait = tickbase;
    protected static boolean toggled = false;
    public trigger_bot(){
        setName("Trigger Bot");
    }
    @Override
    public void onEnable(){
        toggled = true;
    }
    @Override
    public void onDisable(){
        toggled = false;
    }
    @SubscribeEvent
    public void OnTick(TickEvent.ClientTickEvent et) {
        Minecraft mc = Minecraft.getMinecraft();
        if (toggled) {
            if (tickwait != 0) {
                tickwait--;
                return;
            }
            if(Mouse.AimingAsEntity()){
                Mouse.Lclick();
                tickwait = tickbase;
            }
        }
    }
}
