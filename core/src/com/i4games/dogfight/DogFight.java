package com.i4games.dogfight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.enumerations.Enumerations;
import com.i4games.dogfight.managers.ScreenManager;
import com.i4games.dogfight.screens.DogFightSplashScreen;
import com.i4games.dogfight.screens.MenuScreen;
import com.i4games.dogfight.util.ScreenSettings;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

public class DogFight extends Game {

    private static final DogFight ourInstance = new DogFight();

    public static DogFight getInstance() {
        return ourInstance;
    }

    @Override
    public void create() {
        Gdx.input.setCatchBackKey(true);
        ScreenSettings.getInstance().initializeScreenSettings();
        ScreenManager.getInstance().fadeInToScreen(Enumerations.Screen.GAME_SCREEN,0.5f);
    }

    @Override
    public void resume(){
        ScreenManager.getInstance().showCurrentScreen();
    }

}
