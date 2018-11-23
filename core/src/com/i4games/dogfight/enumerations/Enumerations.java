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
            public BaseScreen getScreen(Object... params) {
                return new DogFightSplashScreen();
            }
        },
        MENU_SCREEN {
            public BaseScreen getScreen(Object... params) {
                return new MenuScreen();
            }
        },
        CREDIT_SCREEN {
            public BaseScreen getScreen(Object... params) {
                return new CreditScreen();
            }
        },
        GAME_SCREEN {
            public BaseScreen getScreen(Object... params) {
                return new GameScreen();
            }
        },
        RESULT_SCREEN {
            public BaseScreen getScreen(Object... params) {
                return new ResultScreen();
            }
        },
        PAUSE_SCREEN {
            public BaseScreen getScreen(Object... params) {
                return new PauseScreen();
            }
        }
        ;
        public abstract BaseScreen getScreen(Object... params);
    }

}
