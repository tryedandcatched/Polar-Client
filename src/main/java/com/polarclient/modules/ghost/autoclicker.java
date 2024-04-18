package com.polarclient.modules.ghost;

import com.polarclient.modules.Modules;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;
import utils.Player;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

public class autoclicker {
    private Minecraft mc;
    private int clickCounter;
    private Random rand = new Random();

    @SubscribeEvent
    public void OnTick(TickEvent.ClientTickEvent ev) throws AWTException {
        if (Modules.IsToggled("Auto Clicker")){
            if(mc != Minecraft.getMinecraft()) {
                mc = Minecraft.getMinecraft();
            }

            if(clickCounter != 0){
                clickCounter--;
            }

            Mouse.poll();
            if (Mouse.isButtonDown(0) && clickCounter == 0 && !Player.inGui()){
                utils.Mouse.Rclick();
                clickCounter = 8 + rand.nextInt(10);
            }
        }

    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
