package com.i4games.dogfight.enumerations;

import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.screens.CreditScreen;
import com.i4games.dogfight.screens.DogFightSplashScreen;
import com.i4games.dogfight.screens.GameScreen;
import com.i4games.dogfight.screens.MenuScreen;
import com.i4games.dogfight.screens.PauseScreen;
import com.i4games.dogfight.screens.ResultScreen;

public class Enumerations {

    public enum Screen {

        SPLASH_SCREEN {
            private BaseScreen screen;
            public BaseScreen getScreen(Object... params) {
                if (screen == null){
                    screen = new DogFightSplashScreen();
                }
                return screen;
            }
        },
        MENU_SCREEN {
            private BaseScreen screen;
            public BaseScreen getScreen(Object... params) {
                if (screen == null){
                    screen = new MenuScreen();
                }
                return screen;
            }
        },
        CREDIT_SCREEN {
            private BaseScreen screen;
            public BaseScreen getScreen(Object... params) {
                if (screen == null){
                    screen = new CreditScreen();
                }
                return screen;
            }
        },
        GAME_SCREEN {
            private BaseScreen screen;
            public BaseScreen getScreen(Object... params) {
                if (screen == null){
                    screen = new GameScreen();
                }
                return screen;
            }
        },
        RESULT_SCREEN {
            private BaseScreen screen;
            public BaseScreen getScreen(Object... params) {
                if (screen == null){
                    screen = new ResultScreen();
                }
                return screen;
            }
        },
        PAUSE_SCREEN {
            private BaseScreen screen;
            public BaseScreen getScreen(Object... params) {
                if (screen == null){
                    screen = new PauseScreen();
                }
                return screen;
            }
        }
        ;
        public abstract BaseScreen getScreen(Object... params);
    }

}
