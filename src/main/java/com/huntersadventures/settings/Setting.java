package com.huntersadventures.settings;

import com.fasterxml.jackson.databind.JsonNode;
import com.huntersadventure.jsonparser.Json;

import java.io.IOException;
import java.util.Objects;

public class Setting{
    static private String name = String.valueOf(SettingType.VADER).toLowerCase();
    static private String difficulty = String.valueOf(SettingType.EASY).toLowerCase();


    public Setting() {
        loadSettings();
    }

    private void loadSettings() {
        Json json = new Json();
        try {
            JsonNode settingJson = json.parse(json.getResourceStream("/settings.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getName() {
        return name;
    }

    static void setName(String name) {
        Setting.name = name;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    void setDifficulty(String difficulty) {
        Setting.difficulty = difficulty;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Setting setting = (Setting) o;
//        return Objects.equals(getDifficulty(this), Setting.getDifficulty(setting)) && Objects.equals(getName(), getName());
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(getName(), getDifficulty(this));
//    }

    @Override
    public String toString() {
        return "Setting{" +
                "name='" + name + '\'' +
                ", strength=" + difficulty +
                '}';
    }


}