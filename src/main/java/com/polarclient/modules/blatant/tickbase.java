package com.polarclient.modules.blatant;

import com.polarclient.modules.Module;
import com.polarclient.modules.setting.Setting;
import com.polarclient.modules.setting.settingType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.Sys;
import utils.Mouse;
import utils.Player;

import java.io.IOException;
import java.util.Random;

public class tickbase extends Module {
    static Minecraft mc = Minecraft.getMinecraft();
    private static boolean toggled;
    public static long lastTime = 0;
    long delay = 5000;
    static long lasttimegothurt = 999999999;
    public static boolean afterTickbase = false;
    private final Setting delaySetting = new Setting();
    private final Setting freezeSetting = new Setting();
    private final Setting boostSetting = new Setting();

    private final Setting rangeSetting = new Setting();

    public tickbase() {
        setName("Tickbase");

        delaySetting.setType(settingType.Slider);
        delaySetting.setName("Delay");
        delaySetting.setMaxValue(100);
        delaySetting.setMinValue(10);
        delaySetting.setValue((int) this.delay / 100);
        settingList.add(delaySetting);

        freezeSetting.setType(settingType.Slider);
        freezeSetting.setName("Freeze Time");
        freezeSetting.setMinValue(1);
        freezeSetting.setMaxValue(20);
        freezeSetting.setValue(10);

        settingList.add(freezeSetting);

        boostSetting.setType(settingType.Slider);
        boostSetting.setName("Boost Tick");
        boostSetting.setMinValue(1);
        boostSetting.setMaxValue(50);
        settingList.add(boostSetting);

        rangeSetting.setType(settingType.Slider);
        rangeSetting.setName("Range");
        rangeSetting.setMinValue(1);
        rangeSetting.setMaxValue(6);
        rangeSetting.setValue(4);
        settingList.add(rangeSetting);
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
        afterTickbase = false;
        if (mc.thePlayer == null) return;
        if (mc.theWorld == null) return;
        if (mc.thePlayer.hurtTime == 10) {
            lasttimegothurt = System.currentTimeMillis();
        }
        delay = (long) delaySetting.getValue() * 100;
        boolean target = Player.getTarget(rangeSetting.getValue()-1);
        boolean target2 = Player.getTarget(rangeSetting.getValue());
        if(!target && target2 && System.currentTimeMillis() - lastTime > delay && lasttimegothurt > 800){
            long startTime = System.currentTimeMillis();
            lastTime = System.currentTimeMillis();
            while (System.currentTimeMillis() < startTime + (freezeSetting.getValue() * 50L)){
                if (!(tickboosed >= boostSetting.getValue())){
                    mc.runTick();
                    tickboosed++;
                }
            }
            afterTickbase = true;
        } else {
            afterTickbase = false;
        }
    }
}
