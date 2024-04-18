package com.example.examplemod.events;

import com.polarclient.modules.Modules;
import ibxm.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class ExampleKeybindListener {

    private final KeyBinding autokeybind = new KeyBinding("AutoClicker", Keyboard.KEY_J, "polar client");
    private final KeyBinding killaurakeybind = new KeyBinding("Kill Aura", Keyboard.KEY_F, "polar client");
    private final KeyBinding velocitykeybind = new KeyBinding("Velocity", Keyboard.KEY_K, "polar client");
    private final KeyBinding aimkeybind = new KeyBinding("Aim Assist", Keyboard.KEY_NONE, "polar client");
    private final KeyBinding triggerbind = new KeyBinding("Trigger Bot", Keyboard.KEY_NONE, "polar client");

    public ExampleKeybindListener() {
        // you need to register your keybind for it to show up in the settings menu
        ClientRegistry.registerKeyBinding(autokeybind);
        ClientRegistry.registerKeyBinding(killaurakeybind);
        ClientRegistry.registerKeyBinding(velocitykeybind);
        ClientRegistry.registerKeyBinding(aimkeybind);
        ClientRegistry.registerKeyBinding(triggerbind);
    }


    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        // this method runs everytime a key on the keyboard is pressed / unpressed


        if (killaurakeybind.isPressed()) {
            Modules.Toggle("Kill Aura");
        }
        if (autokeybind.isPressed()) { // using isPressed() will return true once when the key is pressed
            Modules.Toggle("Auto Clicker");
        }
        if (velocitykeybind.isPressed()) {
            Modules.Toggle("Velocity");
        }
        if (aimkeybind.isPressed()){
            Modules.Toggle("Aim Assist");
        }
        if (triggerbind.isPressed()){
            Modules.Toggle("Trigger Bot");
        }
    }
}
