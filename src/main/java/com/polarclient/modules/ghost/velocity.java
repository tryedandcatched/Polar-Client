package com.polarclient.modules.ghost;

import java.util.Objects;
import java.util.concurrent.Executors;
import com.polarclient.modules.Module;
import com.polarclient.modules.Modules;
import jline.internal.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import org.lwjgl.input.Mouse;
import utils.Player;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class velocity extends Module {
    protected static boolean toggled = false;
    public velocity(){
        setName("Velocity");
    }
    @Override
    public void onEnable(){
        toggled = true;
    }
    @Override
    public void onDisable(){
        toggled = false;
    }
    private Minecraft mc =  Minecraft.getMinecraft();;
    private int tickIntervale;
    private Random rand = new Random();
    private static String mode = "";
    @Override
    public String getMode() {
        if (Objects.equals(mode, "Reduce")){
            return "Reduce   ";
        }
        return mode;
    }
    @SubscribeEvent
    public void OnTick(TickEvent.ClientTickEvent ev) throws AWTException {
        if (mc.thePlayer == null)
            return;
        if (toggled){
            if(Objects.equals(mode, "Reduce")) Reduce();
            if(Objects.equals(mode, "JumpReset")) JumpReset();
            if (tickIntervale != 0){
                tickIntervale--;
                return;
            }
            tickIntervale = 10;
            int rng = rand.nextInt(100) % 100;
            if (rng > 50)
                    mode = "Reduce";
            else
                    mode = "JumpReset";
        }
    }
    private void Reduce(){
        mode = "Reduce";
        int htime = mc.thePlayer.hurtTime;
        if(htime == 1+(7+rand.nextInt(8))%8){
            if(utils.Mouse.AimingAsEntity()) {
                for (int i = 0; i < (3 + rand.nextInt(5)); i++) {
                    utils.Mouse.Lclick();
                }
                tickIntervale = 50;
            }
        }
    }
    private void JumpReset(){
        mode = "JumpReset";
        if (mc.thePlayer.hurtTime > 0 && mc.thePlayer.onGround) {
            if (rand.nextDouble() < 0.4) {
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), true);
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), false);
            }
        }
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
