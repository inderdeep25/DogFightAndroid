package com.i4games.dogfight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.enumerations.Enumerations;
import com.i4games.dogfight.managers.ScreenManager;
import com.i4games.dogfight.util.Textures;


public class PauseScreen extends BaseScreen {

    private float buttonWidth;
    private float buttonHeight;

    private EventListener onBackButtonClicked;
    private EventListener onExitButtonClicked;

    @Override
    public void show(){
        super.show();
    }

    @Override
    public void initializeVariables(){
        super.initializeVariables();
        this.backgroundImage = Textures.backgroundImageTexture;
        this.buttonWidth = this.screenWidth - 40;
        this.buttonHeight = this.screenHeight/5;
        this.setupListeners();
        this.setupLabelStyles();
        this.setupTable();
    }

    private void setupLabelStyles() {

        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("truetypefont/anabelle_script_light.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderWidth = 1;
        parameter.color = Color.YELLOW;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        BitmapFont font24 = generator.generateFont(parameter); // font size 24 pixels
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;

        Label label2 = new Label("True Type Font (.ttf) - Gdx FreeType",labelStyle);
        label2.setSize(Gdx.graphics.getWidth()/Help_Guides*5,row_height);
        label2.setPosition(col_width*2,Gdx.graphics.getHeight()-row_height*4);
        stage.addActor(label2);
    }

    private void setupListeners() {

        this.onBackButtonClicked = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Click","On Credit Clicked!");
                //TODO: On Pause do not dispose the previous screen or save the previous screen state somewhere!
                ScreenManager.getInstance().fadeInToScreen(Enumerations.Screen.GAME_SCREEN,0.5f);
            }
        };

        this.onExitButtonClicked = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Click","On Exit Clicked!");
                Gdx.app.exit();
            }
        };
    }

    private void setupTable(){

        this.table.setFillParent(true);

        this.table.setBounds(0, 0, screenWidth, screenHeight);

        this.addTitleImage();

        this.addButton(Textures.creditButtonImageTexture,this.onBackButtonClicked, buttonWidth, buttonHeight);
        this.addButton(Textures.exitButtonImageTexture,this.onExitButtonClicked, buttonWidth, buttonHeight);

        this.stage.addActor(this.table);

    }

    private void addTitleImage() {
        Image image = new Image(Textures.titleImageTexture);
        this.table.add(image)
                .size(2*screenWidth/3,screenHeight/2).row();
    }

}
