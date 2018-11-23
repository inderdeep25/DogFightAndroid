package com.i4games.dogfight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.util.Textures;

public class MenuScreen extends BaseScreen {

    private float buttonWidth;
    private float buttonHeight;

    private EventListener onStartButtonClicked;
    private EventListener onCreditButtonClicked;
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
        this.setupTable();
    }

    private void setupListeners() {
        this.onStartButtonClicked = new EventListener() {
            @Override
            public boolean handle(Event event) {
                Gdx.app.log("Click","On Start Clicked!");
                return true;
            }
        };

        this.onCreditButtonClicked = new EventListener() {
            @Override
            public boolean handle(Event event) {
                Gdx.app.log("Click","On Credit Clicked!");
                return true;
            }
        };

        this.onExitButtonClicked = new EventListener() {
            @Override
            public boolean handle(Event event) {
                Gdx.app.log("Click","On Exit Clicked!");
                return true;
            }
        };
    }

    private void setupTable(){

        this.table.setFillParent(true);

        this.table.setBounds(0, 0, screenWidth, screenHeight);

        this.addTitleImage();

        this.addButton(Textures.startButtonImageTexture,this.onStartButtonClicked, buttonWidth, buttonHeight);
        this.addButton(Textures.creditButtonImageTexture,this.onCreditButtonClicked, buttonWidth, buttonHeight);
        this.addButton(Textures.exitButtonImageTexture,this.onExitButtonClicked, buttonWidth, buttonHeight);

        this.stage.addActor(this.table);

    }

    private void addTitleImage() {
        Image image = new Image(Textures.titleImageTexture);
        this.table.add(image)
                .size(2*screenWidth/3,screenHeight/2).row();
    }

}
