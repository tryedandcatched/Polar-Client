package com.polarclient.modules.ghost;

import com.polarclient.modules.Modules;
import jline.internal.Log;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.Random;

public class velocity {
    private Minecraft mc;
    private int tickIntervale;
    private Random rand = new Random();
    @SubscribeEvent
    public void OnTick(TickEvent.RenderTickEvent ev) throws AWTException {
        if (Modules.IsToggled("Velocity")){
            if (tickIntervale != 0){
                tickIntervale--;
                return;
            }
            mc = Minecraft.getMinecraft();
            int htime = mc.thePlayer.hurtTime;
            if(htime == 1+(7+rand.nextInt(8))%8){
                if(utils.Mouse.AimingAsEntity()) {
                    for (int i = 0; i < (3 + rand.nextInt(5)); i++) {
                        utils.Mouse.Rclick();
                    }
                    tickIntervale = 50;
                }
            }
        }
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
