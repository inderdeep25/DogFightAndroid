package com.i4games.dogfight.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.enumerations.Enumerations;
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

        this.backgroundImage = Textures.splashBackgroundImageTexture;

        this.setupTable();
	}

	private void setupTable(){

		this.table.setFillParent(true);
		this.table.center();

		this.table.setSize(screenWidth,screenHeight);
		this.table.setBounds(0, 0, screenWidth, screenHeight);

		Image image = new Image(Textures.logoImageTexture);

		this.table.row();
		this.table.add(image);


		this.stage.addActor(this.table);

	}

	@Override
	public void render(float delta) {

	    super.render(delta);
		this.deltaTime += delta;

		if(this.shouldMoveToNextScreen()){
            this.game.fadeToScreenFrom(this, Enumerations.ScreenType.MENU_SCREEN,1.0f);
		}

	}

	private boolean shouldMoveToNextScreen(){
		boolean result;
		result = this.deltaTime  >= this.screenTime;
		return result;
	}

	@Override
	public void dispose () {
        super.dispose();
        this.logoImage.dispose();
    }
}
