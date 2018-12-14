package com.i4games.dogfight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.enumerations.Enumerations;
import com.i4games.dogfight.managers.ScreenManager;
import com.i4games.dogfight.managers.SoundManager;
import com.i4games.dogfight.util.Textures;

public class ResultScreen extends BaseScreen {

    private float buttonWidth;
    private float buttonHeight;

    private Label.LabelStyle labelStyle;

    private float titleLabelScale;
    private float infoLabelScale;

//    private EventListener onBackButtonClicked;
    private EventListener onExitButtonClicked;

    public static String resultText = "Result!";
    public static int numberOfBricksDestroyed = 0;

    @Override
    public void show(){
        super.show();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void initializeVariables(){
        super.initializeVariables();
        this.backgroundImage = Textures.pauseBackgroundImageTexture;
        this.buttonWidth = this.screenWidth - 40;
        this.buttonHeight = this.screenHeight/5;

        this.titleLabelScale = 1.2f;
        this.infoLabelScale = 0.6f;

        this.setupListeners();
        this.setupLabelStyles();
        this.setupTable();
    }

    private void setupLabelStyles() {

        this.labelStyle = new Label.LabelStyle();
        this.labelStyle.font = this.fontGenerator.getAnabelleFont();

    }

    private void setupListeners() {

//        this.onBackButtonClicked = new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Gdx.app.log("Click","On Credit Clicked!");
//                //TODO: On Pause do not dispose the previous screen or save the previous screen state somewhere!
//                ScreenManager.getInstance().fadeInToScreen(ScreenManager.getInstance().previousScreen,0.5f);
//            }
//        };

        this.onExitButtonClicked = new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Click","On Exit Clicked!");
                ScreenManager.getInstance().fadeInToScreen(Enumerations.Screen.MENU_SCREEN,0.5f);
                SoundManager.getInstance().stopBackgroundMusic();
            }
        };
    }

    private void setupTable(){

        this.table.setFillParent(true);

        this.table.setBounds(0, 0, this.screenWidth, this.screenHeight);


        Label titleLabel = new Label(resultText, labelStyle);
        titleLabel.setColor(Color.NAVY);
        titleLabel.setFontScale(this.titleLabelScale);
        float titleLabelPadding = (this.screenWidth - titleLabel.getWidth() * this.titleLabelScale)/2;

        this.table.add(titleLabel)
                .left()
                .padTop(this.screenHeight/8)
                .padLeft(titleLabelPadding)
                .row();

        Label infoLabel = new Label("Current Progress: ", labelStyle);
        infoLabel.setFontScale(this.infoLabelScale);
        float infoLabelPadding = this.screenWidth/6;
        this.table.add(infoLabel)
                .left()
                .padLeft(infoLabelPadding)
                .row();

        String valueText = numberOfBricksDestroyed == 100 ? Integer.toString(numberOfBricksDestroyed) + " bricks" : "All bricks";
        Label valueLabel = new Label( valueText, labelStyle);
        valueLabel.setColor(Color.YELLOW);
        valueLabel.setFontScale(this.titleLabelScale);
        float valueLabelPadding = (this.screenWidth - valueLabel.getWidth() * this.titleLabelScale)/2;
        this.table.add(valueLabel)
                .left()
                .padLeft(valueLabelPadding)
                .row();

        Label infoLabel2 = new Label("Destroyed", labelStyle);
        infoLabel2.setFontScale(this.infoLabelScale);
        this.table.add(infoLabel2)
                .right()
                .padRight(infoLabelPadding)
                .padBottom(250)
                .row();

//        this.addButton(Textures.backButtonImageTexture,null, buttonWidth, buttonHeight);
        this.addButton(Textures.exitButtonImageTexture,this.onExitButtonClicked, buttonWidth, buttonHeight);

        this.stage.addActor(this.table);

    }

    @Override
    protected void addButton(Texture texture, EventListener eventListener, float width, float height){

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        ImageButton button = new ImageButton(drawable);

        button.addListener(eventListener);

        this.table.add(button)
                .size(width,height)
                .expandY()
                .padBottom(300)
                .row();
    }

}
