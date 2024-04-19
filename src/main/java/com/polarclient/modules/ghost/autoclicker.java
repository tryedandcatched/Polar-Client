package com.polarclient.modules.ghost;

import com.polarclient.modules.Module;
import com.polarclient.modules.Modules;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;
import utils.Player;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Objects;
import java.util.Random;

public class autoclicker extends Module {

    private Minecraft mc;
    private int clickCounter;
    private Random rand = new Random();
    protected static boolean toggled = false;
    public autoclicker(){
        setName("Auto Clicker");
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
    public void OnTick(TickEvent.ClientTickEvent ev) throws AWTException {
        if (toggled){
            if(mc != Minecraft.getMinecraft()) {
                mc = Minecraft.getMinecraft();
            }

            if(clickCounter != 0){
                clickCounter--;
            }

            Mouse.poll();
            if (Mouse.isButtonDown(0) && clickCounter == 0 && !Player.inGui()){
                utils.Mouse.Lclick();
                clickCounter = 2 + rand.nextInt(5);
            } else if (Mouse.isButtonDown(1)) {
                if(clickCounter != 0){
                    clickCounter--;
                    return;
                }
                if (Objects.requireNonNull(mc.objectMouseOver.typeOfHit) == MovingObjectPosition.MovingObjectType.BLOCK) {
                    clickCounter = 3;
                    utils.Mouse.Rclick();
                }
            }
        }

    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
