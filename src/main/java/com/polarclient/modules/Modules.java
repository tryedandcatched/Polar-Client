package com.polarclient.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modules {
    static public List<Module> EnabledModules = new ArrayList<Module>();
    static public List<Module> AllModule = new ArrayList<Module>();
    public static void Toggle(Module name){
        for (Module module: EnabledModules) {
            if (Objects.equals(module.getName(), name.getName())){
                name.onDisable();
                EnabledModules.remove(module);
                return;
            }
        }
        Modules.EnabledModules.add(name);
        name.onEnable();
    }
}
