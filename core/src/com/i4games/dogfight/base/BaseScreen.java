package com.i4games.dogfight.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.i4games.dogfight.DogFight;
import com.i4games.dogfight.util.ScreenSettings;

public class BaseScreen implements Screen {

    public Stage stage;
    protected Table table;
    protected SpriteBatch batch;
    protected Texture backgroundImage;
    protected DogFight game;

    protected float screenHeight;
    protected float screenWidth;

    public BaseScreen(){
        this.initializeVariables();
    }

    @Override
    public void show() {

    }

    public void initializeVariables() {
        stage = new Stage(new ScreenViewport());
        table = new Table();
        batch = new SpriteBatch();
        game = DogFight.getInstance();

        Gdx.input.setInputProcessor(stage);

        screenHeight = ScreenSettings.getInstance().height;
        screenWidth = ScreenSettings.getInstance().width;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();
        this.batch.draw(backgroundImage,0,0);
        this.batch.end();

        this.stage.act(delta);
        this.stage.draw();
    }

    protected void addButton(Texture texture, EventListener eventListener, float width, float height){

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        ImageButton button = new ImageButton(drawable);

        button.addListener(eventListener);

        this.table.add(button)
                .size(width,height).expandY().row();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
