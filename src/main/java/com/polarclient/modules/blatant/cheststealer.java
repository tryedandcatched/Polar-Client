package com.polarclient.modules.blatant;

import com.polarclient.modules.Module;
import com.polarclient.modules.setting.Setting;
import com.polarclient.modules.setting.settingType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.eclipse.osgi.internal.loader.SystemBundleLoader;
import org.lwjgl.Sys;
import scala.collection.parallel.ParIterableLike;
import utils.Player;

import java.io.IOException;
import java.security.Key;

public class cheststealer extends Module {
    static Minecraft mc = Minecraft.getMinecraft();
    private static boolean toggled;
    public static long lastTime = 0;
    long nextAction = 0;
    private final Setting delaySetting = new Setting();
    private final Setting MaxDelaySetting = new Setting();
    public cheststealer() {
        setName("Chest Stealer");

        delaySetting.setType(settingType.Slider);
        delaySetting.setValue(50);
        delaySetting.setMaxValue(100);
        delaySetting.setMinValue(10);
        delaySetting.setName("Min Delay");
        settingList.add(delaySetting);

        MaxDelaySetting.setType(settingType.Slider);
        MaxDelaySetting.setValue(100);
        MaxDelaySetting.setMaxValue(150);
        MaxDelaySetting.setMinValue(10);
        MaxDelaySetting.setName("Max Delay");
        settingList.add(MaxDelaySetting);
    }

    @Override
    public void onEnable() {
        if (toggled) {
            onDisable();
            return;
        }
        //mc.gameSettings.keyBindForward = LastBackKey;
        //mc.gameSettings.keyBindBack = LastForwardKey;
        toggled = true;
    }

    @Override
    public void onDisable() {
        toggled = false;
    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent ev) throws IOException {
        int tickboosed = 0;
        if (!toggled) return;
        if (mc.thePlayer == null) return;
        if (mc.theWorld == null) return;
        if (mc.currentScreen instanceof GuiChest){
            if(nextAction != 0){
                nextAction--;
                return;
            }
            nextAction = delaySetting.getValue();
            GuiChest chest = (GuiChest) mc.currentScreen;
            if(IsChestEmpty(chest)){
                nextAction = delaySetting.getValue();
                return;
            }

            int rows = 3*9;
            for(int i =0; i < rows; i++){
                Slot slot = chest.inventorySlots.getSlot(i);
                if (slot.getHasStack()){
                    ItemStack stack = slot.getStack();
                    mc.thePlayer.sendQueue.addToSendQueue(
                            new C0EPacketClickWindow(
                                    chest.inventorySlots.windowId,
                                    i,
                                    0,
                                    1,
                                    stack,
                                    (short) 1
                            )
                    );
                    nextAction += getNextItem(chest, i)* 10L;
                    return;
                } else {
                    nextAction = 5;
                    if (Math.random()*100 < 1) {
                        ItemStack stack = slot.getStack();
                        mc.thePlayer.sendQueue.addToSendQueue(
                                new C0EPacketClickWindow(
                                        chest.inventorySlots.windowId,
                                        i,
                                        0,
                                        1,
                                        stack,
                                        (short) 1
                                )
                        );
                    }
                }
            }
        }

    }

    private static boolean IsChestEmpty(GuiChest chest){
        int rows = 3*9;
        for(int i =0; i < rows; i++) {
            Slot slot = chest.inventorySlots.getSlot(i);
            if (slot.getHasStack()) {
                return false;
            }
        }
        return true;
    }

    private static int getNextItem(GuiChest chest, int index){
        int rows = 3*9;
        int padding = 0;
        for(int i =index; i < rows; i++){
            padding += 1;
            Slot slot = chest.inventorySlots.getSlot(i);
            if (slot.getHasStack()) {
                return padding;
            }
        }
        return 0;
    }
}
