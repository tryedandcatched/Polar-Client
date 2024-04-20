package com.example.examplemod.events;

import com.polarclient.modules.Gui.Gui;
import com.polarclient.modules.Modules;
import com.polarclient.modules.blatant.scaffold;
import com.polarclient.modules.ghost.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class ExampleKeybindListener {

    public final KeyBinding autokeybind = new KeyBinding("AutoClicker", Keyboard.KEY_J, "polar client");
    public final KeyBinding velocitykeybind = new KeyBinding("Velocity", Keyboard.KEY_K, "polar client");
    public final KeyBinding aimkeybind = new KeyBinding("Aim Assist", Keyboard.KEY_NONE, "polar client");
    public final KeyBinding triggerbind = new KeyBinding("Trigger Bot", Keyboard.KEY_NONE, "polar client");
    public final KeyBinding guikebind = new KeyBinding("ClickGui", Keyboard.KEY_RSHIFT, "polar client");
    public final KeyBinding extraknockbind = new KeyBinding("ExtraKnockBack", Keyboard.KEY_NONE, "polar client");
    public final KeyBinding scaffoldbind = new KeyBinding("Scaffold", Keyboard.KEY_G, "polar client");

    public ExampleKeybindListener() {
        // you need to register your keybind for it to show up in the settings menu
        ClientRegistry.registerKeyBinding(autokeybind);
        ClientRegistry.registerKeyBinding(velocitykeybind);
        ClientRegistry.registerKeyBinding(aimkeybind);
        ClientRegistry.registerKeyBinding(triggerbind);
        ClientRegistry.registerKeyBinding(guikebind);
        ClientRegistry.registerKeyBinding(extraknockbind);
        ClientRegistry.registerKeyBinding(scaffoldbind);
    }


    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        // this method runs everytime a key on the keyboard is pressed / unpressed


        if (autokeybind.isPressed()) { // using isPressed() will return true once when the key is pressed
            Modules.Toggle(new autoclicker());
        }
        if (velocitykeybind.isPressed()) {
            Modules.Toggle(new velocity());
        }
        if (aimkeybind.isPressed()){
            Modules.Toggle(new aimassist());
        }
        if (triggerbind.isPressed()){
            Modules.Toggle(new trigger_bot());
        }
        if(guikebind.isPressed()){

            Minecraft.getMinecraft().displayGuiScreen(new Gui());
        }
        if (extraknockbind.isPressed()){
            Modules.Toggle(new extraknockback());
        }
        if (scaffoldbind.isPressed()){
            Modules.Toggle(new scaffold());
        }
    }
}
