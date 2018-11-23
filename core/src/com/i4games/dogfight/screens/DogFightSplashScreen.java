package com.i4games.dogfight.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.i4games.dogfight.util.ImageNames;
import com.i4games.dogfight.util.ScreenSettings;

public class DogFightSplashScreen implements Screen {

	private Game game;
	private Screen nextScreen;

	private SpriteBatch batch;
	private Texture img;

	private String logoImageName = ImageNames.getInstance().splashScreenLogo;
	private int imageY;
	private int imageX;

	private float screenTime = 5;
	private float deltaTime = 0;

	public DogFightSplashScreen(Game game){
		this.game = game;
	}

	@Override
	public void show() {
		this.initializeVariables();
	}

	private void initializeVariables() {

		nextScreen = new MenuScreen();

		batch = new SpriteBatch();
		img = new Texture(logoImageName);

		imageY = ScreenSettings.getInstance().height/2 - img.getHeight()/2;
		imageX = ScreenSettings.getInstance().width/2 - img.getWidth()/2;
	}

	@Override
	public void render(float delta) {

		this.deltaTime += delta;

		if(this.shouldMoveToNextScreen()){
			this.game.setScreen(new MenuScreen());
		}else {
			this.drawLogo();
		}

	}

	private boolean shouldMoveToNextScreen(){
		boolean result;
		result = this.deltaTime  >= this.screenTime;
		return result;
	}

	private void drawLogo() {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, imageX, imageY);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
