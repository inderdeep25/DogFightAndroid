package com.i4games.dogfight.util;

public class ScreenSettings {
    private static final ScreenSettings ourInstance = new ScreenSettings();

    public static ScreenSettings getInstance() {
        return ourInstance;
    }

    public int height;
    public int width;

    private ScreenSettings() {



    }
}
