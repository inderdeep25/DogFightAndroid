package com.i4games.dogfight.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
import com.i4games.dogfight.util.Textures;

public class BaseScreen implements Screen, InputProcessor {

    public Stage stage;
    protected Table table;
    protected SpriteBatch batch;
    protected Texture backgroundImage;
    protected DogFight game;

    protected float screenHeight;
    protected float screenWidth;

    public boolean isDisposed = false;

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

        this.backgroundImage = Textures.backgroundImageTexture;

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
        System.out.println("MyClass diposed");

        if ( !this.isDisposed ){
            this.stage.dispose();
            this.batch.dispose();
            this.isDisposed = true;
        }

    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            // Do your optional back button handling (show pause menu?)
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
