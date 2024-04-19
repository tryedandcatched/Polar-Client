package com.polarclient.modules;

public class Module {
    protected String name = "example module";
    protected String mode = "";

    public void onEnable(){
    }

    public void onDisable(){
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

    public void setMode(String mode) {
        this.mode = mode;
    }
}
