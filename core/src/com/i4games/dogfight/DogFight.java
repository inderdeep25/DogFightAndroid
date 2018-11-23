package com.i4games.dogfight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.i4games.dogfight.screens.DogFightSplashScreen;
import com.i4games.dogfight.util.ScreenSettings;

public class DogFight extends Game {

    DogFightSplashScreen splashScreen;

    @Override
    public void create() {

        this.initializeScreenSettings();
        this.initializeSplashScreen();

    }

    private void initializeScreenSettings() {
        ScreenSettings.getInstance().height = Gdx.graphics.getHeight();
        ScreenSettings.getInstance().width = Gdx.graphics.getWidth();
    }

    private void initializeSplashScreen() {
        splashScreen = new DogFightSplashScreen(this);
        this.setScreen(splashScreen);
    }

}
