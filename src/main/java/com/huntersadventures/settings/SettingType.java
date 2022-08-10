package com.huntersadventures.settings;

import java.awt.*;

public enum SettingType {
    DIFFICULTY("Difficulty"),
    CHARACTER("Character"),
    EASY("easy"),
    HARD("hard"),
    VADER("Vader"),
    POTTER("Harry");


    private final String setting;

    SettingType(String setting){
        this.setting = setting;
    }

    public String getSetting() {
        return setting;
    }
}