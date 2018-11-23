package com.i4games.dogfight.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.i4games.dogfight.DogFight;

public class BaseScreen implements Screen {

    public Stage stage;
    protected Table table;
    protected SpriteBatch batch;
    protected Texture backgroundImage;
    protected DogFight game;

    public BaseScreen(){
        this.initializeVariables();
    }

    @Override
    public void show() {
        Gdx.app.log("INFO","In show");
    }

    public void initializeVariables() {
        stage = new Stage();
        table = new Table();
        batch = new SpriteBatch();
        game = DogFight.getInstance();
    }

    @Override
    public void render(float delta) {
        this.stage.draw();
        this.batch.begin();
        this.batch.draw(backgroundImage,0,0);
        this.batch.end();
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
    public void dispose() {
        this.stage.dispose();
        this.batch.dispose();
        this.backgroundImage.dispose();
    }


}
