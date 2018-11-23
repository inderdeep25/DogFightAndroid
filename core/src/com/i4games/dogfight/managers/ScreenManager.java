package com.i4games.dogfight.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.i4games.dogfight.DogFight;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.enumerations.Enumerations;

public class ScreenManager {

    // Singleton: unique instance
    private static ScreenManager instance;

    // Reference to game
    private DogFight game;

    // Singleton: private constructor
    private ScreenManager() {
        super();
        game = DogFight.getInstance();
    }

    // Singleton: retrieve instance
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void showCurrentScreen(){
        // Get current screen to dispose it
        BaseScreen currentScreen = (BaseScreen) game.getScreen();
        game.setScreen(currentScreen);
    }

    public void fadeInToScreen(Enumerations.Screen screenEnum, final float duration, Object... params) {

        // Show new screen
        final BaseScreen nextScreen = screenEnum.getScreen(params);

        nextScreen.stage.getRoot().getColor().a = 0;
        nextScreen.stage.getRoot().addAction(Actions.fadeIn(duration));
        Gdx.app.log("INFO","Moving to next screen");

        game.setScreen(nextScreen);

    }
}