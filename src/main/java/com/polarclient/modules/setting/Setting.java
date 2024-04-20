package com.polarclient.modules.setting;

public class Setting {
    public static settingType type;
    public static String name;
    public static int value;
    public static int Minvalue;
    public static int Maxvalue;

    public String getName(){
        return name;
    }
    public settingType getType(){
        return type;
    }
    public void setType(settingType t){
        type = t;
    }

    public int getValue(){
        return value;
    }

    public int getMinvalue() {return this.Minvalue;}
    public int getMaxvalue() {return this.Maxvalue;}

    public void setValue(int value) {
        this.value = value;
    }
}
