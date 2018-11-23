package com.i4games.dogfight.screens;

import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.util.Textures;

public class MenuScreen extends BaseScreen {

    @Override
    public void initializeVariables(){
        super.initializeVariables();
        this.backgroundImage = Textures.backgroundImage;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
