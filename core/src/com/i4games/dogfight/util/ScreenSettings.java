package com.i4games.dogfight.util;

import com.badlogic.gdx.Gdx;

public class ScreenSettings {
    private static final ScreenSettings ourInstance = new ScreenSettings();

    public static ScreenSettings getInstance() {
        return ourInstance;
    }

    public int height;
    public int width;

    private ScreenSettings() {
    }

    public void initializeScreenSettings() {
        ScreenSettings.getInstance().height = Gdx.graphics.getHeight();
        ScreenSettings.getInstance().width = Gdx.graphics.getWidth();
    }
}
