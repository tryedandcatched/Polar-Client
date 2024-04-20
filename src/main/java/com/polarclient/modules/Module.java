package com.polarclient.modules;

import com.polarclient.modules.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

public class Module {
    protected String name = "example module";
    protected String mode = "";
    protected boolean toggled = false;
    public List<Setting> settingList = new ArrayList<Setting>();

    private Minecraft mc = Minecraft.getMinecraft();

    public void onEnable(){
        toggled = true;
    }

    public void onDisable(){
        toggled = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return this.mode;
    }

    public void drawMenu(){

    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
