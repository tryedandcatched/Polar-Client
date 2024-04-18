package com.polarclient.modules.ghost;

import com.polarclient.modules.Modules;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import utils.Mouse;

public class trigger_bot {
    private int tickbase = 200;
    private int tickwait = tickbase;
    @SubscribeEvent
    public void OnTick(TickEvent.RenderTickEvent et) {
        Minecraft mc = Minecraft.getMinecraft();
        if (Modules.IsToggled("Trigger Bot")) {
            System.out.println("tickwait :" + tickwait);
            if (tickwait != 0) {
                tickwait--;
                return;
            }
            if(Mouse.AimingAsEntity()){
                Mouse.Rclick();
                tickwait = tickbase;
            }
        }
    }
}
