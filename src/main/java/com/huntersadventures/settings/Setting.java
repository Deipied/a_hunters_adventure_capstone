package com.huntersadventures.settings;

import com.fasterxml.jackson.databind.JsonNode;
import com.huntersadventure.jsonparser.Json;

import java.io.IOException;
import java.util.Objects;

public class Setting{
    private String name = String.valueOf(SettingType.VADER).toLowerCase();
    private String difficulty = String.valueOf(SettingType.EASY).toLowerCase();


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

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setting setting = (Setting) o;
        return getDifficulty() == setting.getDifficulty() && Objects.equals(getName(), setting.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDifficulty());
    }

    @Override
    public String toString() {
        return "Setting{" +
                "name='" + name + '\'' +
                ", strength=" + difficulty +
                '}';
    }


}