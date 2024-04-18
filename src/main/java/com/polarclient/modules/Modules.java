package com.polarclient.modules;

import java.util.ArrayList;
import java.util.List;

public class Modules {
    static public List<String> EnabledModules = new ArrayList<String>();
    public static void Toggle(String name){
        if (Modules.EnabledModules.contains(name)){
            Modules.EnabledModules.remove(name);
            return;
        }
        Modules.EnabledModules.add(name);
    }

    public static boolean IsToggled(String name) {
        if (Modules.EnabledModules.contains(name)){
            return true;
        }
        return false;
    }
}
