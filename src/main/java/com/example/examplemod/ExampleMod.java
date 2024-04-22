package com.example.examplemod;

import com.example.examplemod.commands.ExampleCommand;
import com.example.examplemod.config.ConfigHandler;
import com.example.examplemod.events.ExampleKeybindListener;
import com.example.examplemod.hud.ExampleHUD;
import com.polarclient.modules.Module;
import com.polarclient.modules.Modules;
import com.polarclient.modules.blatant.cheststealer;
import com.polarclient.modules.blatant.tickbase;
import com.polarclient.modules.ghost.*;
import com.polarclient.modules.blatant.scaffold;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.input.AutoCloseInputStream;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.MODNAME, version = ExampleMod.VERSION)
public class ExampleMod { // select ExampleMod and hit shift+F6 to rename it

    public static final String MODID = "polar.client"; // the id of your mod, it should never change, it is used by
                                                       // forge and servers to identify your mods
    public static final String MODNAME = "Polar Client";// the name of your mod
    public static final String VERSION = "1.0"; // the current version of your mod

    // this method is one entry point of you mod
    // it is called by forge when minecraft is starting
    // it is called before the other methods below
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // loads the config file from the disk
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // register your commands here
        ClientCommandHandler.instance.registerCommand(new ExampleCommand());

        // the ExampleKeybind has a method with the @SubscribeEvent annotation
        // for that code to run, that class needs to be registered on the MinecraftForge
        // EVENT_BUS
        // register your other EventHandlers here
        MinecraftForge.EVENT_BUS.register(new ExampleKeybindListener());
        MinecraftForge.EVENT_BUS.register(new ExampleHUD());

        Module autoclicker = new autoclicker();
        Module velocity = new velocity();
        Module aimassist = new aimassist();
        Module trigger_bot = new trigger_bot();
        Module extraknockback = new extraknockback();
        Module scaffold = new scaffold();
        Module tickbase = new tickbase();
        Module cheststealer = new cheststealer();

        MinecraftForge.EVENT_BUS.register(autoclicker);
        MinecraftForge.EVENT_BUS.register(velocity);
        MinecraftForge.EVENT_BUS.register(aimassist);
        MinecraftForge.EVENT_BUS.register(trigger_bot);
        MinecraftForge.EVENT_BUS.register(extraknockback);
        MinecraftForge.EVENT_BUS.register(scaffold);
        MinecraftForge.EVENT_BUS.register(tickbase);
        MinecraftForge.EVENT_BUS.register(cheststealer);
        
        Modules.AllModule.add(autoclicker);
        Modules.AllModule.add(velocity);
        Modules.AllModule.add(aimassist);
        Modules.AllModule.add(trigger_bot);
        Modules.AllModule.add(extraknockback);
        Modules.AllModule.add(scaffold);
        Modules.AllModule.add(tickbase);
        Modules.AllModule.add(cheststealer);

        if (Loader.isModLoaded("patcher")) {
            // this code will only run if the mod with id "patcher" is loaded
            // you can use it to load or not while modules of your mod that depends on other
            // mods
        }

    }
}
