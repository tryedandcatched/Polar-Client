package com.polarclient.modules.ghost;

import com.polarclient.modules.Module;
import com.polarclient.modules.Modules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import utils.Mouse;

import java.security.Key;
import java.util.Objects;

public class extraknockback extends Module {
    public static String name = "Extra Knockback";
    private int tickbase = 3;
    private int tickwait = tickbase;
    private String action = "nothing";
    static Minecraft mc = Minecraft.getMinecraft();
    int key = mc.gameSettings.keyBindBack.getKeyCode();
    protected static boolean toggled = false;
    public extraknockback(){
        setName("Extra Knockback");
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
    public void OnAttack(AttackEntityEvent ev) {
        if (toggled) {
            System.out.println("Extra Knockbak");
            action = "on";
        }
    }

    @SubscribeEvent
    public void OnTick(TickEvent.ClientTickEvent ev){
        if (Objects.equals(action, "nothing"))
            return;
        if (Objects.equals(action, "off")) {
            KeyBinding.setKeyBindState(key, false);
            action = "nothing";
        }
        if (Objects.equals(action, "on")){
            if (tickwait != 0){
                tickwait--;
                return;
            }
            tickwait = (int) (tickbase + (Math.random() % 5));
            KeyBinding.setKeyBindState(key, true);
            action = "off";
        }
    }
}
