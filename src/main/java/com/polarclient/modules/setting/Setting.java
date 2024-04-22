package com.polarclient.modules.setting;

public class Setting {
    private settingType type;
    private String name;
    private int value;
    private int Minvalue;
    private int Maxvalue;

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
        this.value = value;
    }
    public void setMaxValue(int value){
        Maxvalue = value;
    }
    public void setMinValue(int value){
        Minvalue = value;
    }
    public void setName(String name){
       this.name = name;
    }
}

