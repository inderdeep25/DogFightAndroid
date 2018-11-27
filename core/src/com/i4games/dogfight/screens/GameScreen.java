package com.i4games.dogfight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.i4games.dogfight.PaddlePlayer;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.enumerations.Enumerations;
import com.i4games.dogfight.managers.ScreenManager;
import com.i4games.dogfight.managers.SoundManager;
import com.i4games.dogfight.util.Textures;

public class GameScreen extends BaseScreen {

    private float buttonWidth;
    private float buttonHeight;

    private Label.LabelStyle labelStyle;

    private float labelScale;
    private float smallLabelScale;

    private EventListener onPauseButtonClicked;

    PaddlePlayer bluePaddle;

    @Override
    public void show(){
        super.show();
        this.soundManager.pauseOrPlayBackgroundMusic();
    }

    @Override
    public void hide(){
        super.hide();
        this.soundManager.pauseOrPlayBackgroundMusic();
    }

    @Override
    public void initializeVariables(){
        super.initializeVariables();
        this.buttonWidth = 100;
        this.buttonHeight = 100;

        this.labelScale = 0.6f;
        this.smallLabelScale = 0.3f;
        this.addPlayer();
        this.setupListeners();
        this.setupLabelStyles();
        this.setupTable();

    }

    private void addPlayer(){

        bluePaddle = new PaddlePlayer();
        bluePaddle.setTexture(Textures.paddleTexture);
        bluePaddle.setPosition(250, 700);
        bluePaddle.setScale(5.0f);
        bluePaddle.setBoundaryRectangle();
        this.stage.addActor(bluePaddle);

        Gdx.app.log("Add Player Paddle","");

    }


    private void setupLabelStyles() {

        this.labelStyle = new Label.LabelStyle();
        this.labelStyle.font = this.fontGenerator.getAnabelleFont();

    }

    private void setupListeners() {

        this.onPauseButtonClicked = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Click","On Credit Clicked!");
                //TODO: On Pause do not dispose the previous screen or save the previous screen state somewhere!
                ScreenManager.getInstance().fadeInToScreen(Enumerations.Screen.PAUSE_SCREEN,0.5f);
            }
        };
    }

    private void setupTable(){

        this.table.setFillParent(true);

        this.table.setBounds(0, 0, this.screenWidth, this.screenHeight);

        this.table.padBottom(this.screenHeight-100);

        //Top row
        this.addCurrentProgressLabels();
        this.addHeartImages();
        this.addPauseButton();
        this.table.row();
        this.stage.addActor(this.table);

    }

    private void addCurrentProgressLabels() {

        Label titleLabel = new Label("0 ", labelStyle);
        titleLabel.setFontScale(this.labelScale);

        float titleLeftPadding = 50;
        float titleRightPadding = 10;
        this.table.add(titleLabel)
                .left()
                .padLeft(titleLeftPadding)
                .padRight(titleRightPadding);

        Label infoLabel = new Label("bricks destroyed", labelStyle);
        infoLabel.setAlignment(Align.bottomLeft);
        infoLabel.setFontScale(this.smallLabelScale);
        float infoLabelTopPadding = titleLabel.getHeight()/2 - infoLabel.getHeight() * this.smallLabelScale;
        float rightPadding = this.screenWidth - titleLabel.getWidth() - infoLabel.getWidth() - titleLeftPadding - titleRightPadding;
        this.table.add(infoLabel)
                .left()
                .padTop(infoLabelTopPadding)
                .padRight(rightPadding);

    }

    private void addHeartImages() {

        Image heartImage = new Image(Textures.fullHeartImageTexture);
        Image heart2Image = new Image(Textures.emptyHeartImageTexture);
        Image heart3Image = new Image(Textures.emptyHeartImageTexture);

        float heartTopPadding = heartImage.getHeight()/2;

        this.table.add(heartImage).padLeft(10).padTop(heartTopPadding);
        this.table.add(heart2Image).padLeft(10).padTop(heartTopPadding);
        this.table.add(heart3Image).padLeft(10).padTop(heartTopPadding);

    }
    private void addPauseButton() {

        this.addButton(Textures.pauseButtonImageTexture,this.onPauseButtonClicked, buttonWidth, buttonHeight);

        Label pauseLabel = new Label("Pause", labelStyle);
        pauseLabel.setAlignment(Align.bottomLeft);
        pauseLabel.setFontScale(this.smallLabelScale);
        this.table.add(pauseLabel).padLeft(15).padTop(15);

    }

    @Override
    protected void addButton(Texture texture, EventListener eventListener, float width, float height){

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        ImageButton button = new ImageButton(drawable);

        button.addListener(eventListener);

        this.table.add(button)
                .size(width,height)
                .expandY()
                .padLeft(buttonWidth)
                .padTop(buttonHeight/2);

    }

}
