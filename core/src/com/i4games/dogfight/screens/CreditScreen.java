package com.i4games.dogfight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.enumerations.Enumerations;
import com.i4games.dogfight.managers.ScreenManager;
import com.i4games.dogfight.util.Textures;

public class CreditScreen extends BaseScreen {

    private float buttonWidth;
    private float buttonHeight;

    private EventListener onStartButtonClicked;
    private EventListener onBackButtonClicked;


    @Override
    public void show(){
        super.show();
    }

    @Override
    public void initializeVariables(){
        super.initializeVariables();

        this.buttonWidth = this.screenWidth - 40;
        this.buttonHeight = this.screenHeight/5;
        this.setupListeners();
        this.setupTable();
    }

    private void setupListeners() {
        this.onStartButtonClicked = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Click","On Start Clicked!");
                ScreenManager.getInstance().fadeInToScreen(Enumerations.Screen.GAME_SCREEN,0.5f);
            }
        };

        this.onBackButtonClicked = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Click","On Back Clicked!");
                ScreenManager.getInstance().fadeInToScreen(ScreenManager.getInstance().previousScreen,0.5f);
            }
        };

    }

    private void setupTable(){

        this.table.setFillParent(true);

        this.table.setBounds(0, 0, screenWidth, screenHeight);
        this.table.padBottom(150);

        this.addTitleImages();

        this.addButton(Textures.startButtonImageTexture,this.onStartButtonClicked, buttonWidth, buttonHeight);
        this.addButton(Textures.backButtonImageTexture,this.onBackButtonClicked, buttonWidth, buttonHeight);

        this.stage.addActor(this.table);
    }

    private void addTitleImages() {

        Image image = new Image(Textures.logoImageTexture);
        this.table.add(image)
                .size(1.5f*screenWidth/3,screenHeight/2.5f).padTop(80).padBottom(50).row();

        Image creditImg = new Image(Textures.creditImageTexture);
        this.table.add(creditImg)
                .size(screenWidth-150,screenHeight/4 - 200).pad(100).row();

    }

}
