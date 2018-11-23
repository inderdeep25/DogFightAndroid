package com.i4games.dogfight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.enumerations.Enumerations;
import com.i4games.dogfight.util.ScreenSettings;
import com.i4games.dogfight.util.Textures;

public class DogFightSplashScreen extends BaseScreen {

	private Texture logoImage;

	private float imageY;
	private float imageX;

	private float screenTime = 5;
	private float deltaTime = 0;

    @Override
	public void initializeVariables() {

	    super.initializeVariables();

        this.logoImage = Textures.logoImage;
        this.backgroundImage = Textures.splashBackgroundImage;

        this.imageY = ScreenSettings.getInstance().height/2 - logoImage.getHeight()/2;
        this.imageX = ScreenSettings.getInstance().width/2 - logoImage.getWidth()/2;

	}

	@Override
	public void render(float delta) {

	    super.render(delta);
		this.deltaTime += delta;

//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(this.shouldMoveToNextScreen()){
            this.game.fadeToScreen(Enumerations.ScreenType.MENU_SCREEN,5.0f);
//            this.game.fadeToScreenFrom(this, Enumerations.ScreenType.MENU_SCREEN, 0.5f);
		}else {
			this.drawImagesOnScreen();
		}

	}

	private boolean shouldMoveToNextScreen(){
		boolean result;
		result = this.deltaTime  >= this.screenTime;
		return result;
	}

	private void drawImagesOnScreen() {

        this.batch.begin();
        this.batch.draw(backgroundImage,0,0);
        this.batch.draw(logoImage, imageX, imageY);
        this.batch.end();
	}

	@Override
	public void dispose () {
        super.dispose();
        this.logoImage.dispose();
    }
}
