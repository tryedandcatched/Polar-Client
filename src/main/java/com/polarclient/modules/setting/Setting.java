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

    public int getMinvalue() {return Minvalue;}
    public int getMaxvalue() {return Maxvalue;}

    public void setValue(int value) {
        Setting.value = value;
    }
    public void setMaxValue(int value){
        Maxvalue = value;
    }public void setMinValue(int value){
        Minvalue = value;
    }
}
