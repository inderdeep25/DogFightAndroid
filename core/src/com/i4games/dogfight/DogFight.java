package com.i4games.dogfight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.enumerations.Enumerations;
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

    ArrayList<Screen> screens;

    @Override
    public void create() {

        ScreenSettings.getInstance().initializeScreenSettings();
        this.initializeScreens();
        this.showScreen(Enumerations.ScreenType.SPLASH_SCREEN);

    }

    private void initializeScreens() {

        screens = new ArrayList<Screen>();

        screens.add(new DogFightSplashScreen());
        screens.add(new MenuScreen());

    }

    public void showScreen(Enumerations.ScreenType screenType){
        BaseScreen screenToShow = (BaseScreen) screens.get(screenType.ordinal());
        screenToShow.stage.getRoot().getColor().a = 0;
        this.setScreen(screenToShow);
    }

    public void fadeToScreenFrom(BaseScreen currentScreen, final Enumerations.ScreenType nextScreenType, float duration) {

        currentScreen.stage.getRoot().getColor().a = 1;

        Action fadeAction = Actions.fadeOut(duration);

        RunnableAction nextScreenAction = new RunnableAction();
        nextScreenAction.setRunnable(new Runnable() {
            @Override
            public void run () {
                showScreen(nextScreenType);
            }
        });

        SequenceAction sequenceAction = Actions.sequence(fadeAction, nextScreenAction);

        currentScreen.stage.getRoot().addAction(sequenceAction);

    }

    public void fadeToScreen(Enumerations.ScreenType nextScreenType, float duration) {

        BaseScreen screenToShow = (BaseScreen) screens.get(nextScreenType.ordinal());
        screenToShow.stage.getRoot().getColor().a = 0;
        screenToShow.stage.getRoot().addAction(Actions.fadeIn(duration));
        Gdx.app.log("INFO","Moving to next screen");
        this.setScreen(screenToShow);

    }


}
