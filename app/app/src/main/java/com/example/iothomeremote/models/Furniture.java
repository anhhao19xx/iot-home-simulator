package com.example.iothomeremote.models;

public class Furniture {
    private String name;
    private Boolean isEnabled;

    public Furniture(String name, Boolean isEnabled){
        this.name = name;
        this.isEnabled = isEnabled;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Boolean getIsEnabled(){
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled){
        this.isEnabled = isEnabled;
    }
}
