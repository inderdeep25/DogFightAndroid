package com.i4games.dogfight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.i4games.dogfight.actors.BallActor;
import com.i4games.dogfight.actors.PaddleActor;
import com.i4games.dogfight.base.BaseScreen;
import com.i4games.dogfight.enumerations.Enumerations;
import com.i4games.dogfight.managers.ScreenManager;
import com.i4games.dogfight.util.Textures;

public class GameScreen extends BaseScreen {

    private float buttonWidth;
    private float buttonHeight;

    private Label.LabelStyle labelStyle;

    private float labelScale;
    private float smallLabelScale;

    private EventListener onPauseButtonClicked;

    private boolean canStartGame = false;

    PaddleActor bluePaddle;
    float paddleY;
    float initialPaddleX;

    BallActor ball;
    boolean isBallMoving = false;
    float initialBallX;
    float initialBallY;
    float ballAccelaration = 30000;

    int lifesLeft = 3;
    Texture lifeTextures[] = {Textures.fullHeartImageTexture,
                                Textures.fullHeartImageTexture,
                                Textures.fullHeartImageTexture};

    Image heartImage;
    Image heart2Image;
    Image heart3Image;

    @Override
    public void show(){
        super.show();
        this.soundManager.pauseOrPlayBackgroundMusic();
    }

    @Override
    public void hide(){
        super.hide();
        this.soundManager.pauseOrPlayBackgroundMusic();
        this.canStartGame = false;
    }

    private void addPlayer(){

        bluePaddle = new PaddleActor(Textures.paddleTexture);
        initialPaddleX = this.screenWidth/2 - bluePaddle.getWidth();
        paddleY = 250;
        bluePaddle.setPosition(initialPaddleX, paddleY);
        bluePaddle.setScale(1.5f);

        bluePaddle.setWorldBounds(screenWidth,screenHeight - buttonHeight);

        this.stage.addActor(bluePaddle);

    }

    private void addBall(){
        ball = new BallActor(bluePaddle, Textures.ballTexture);
        ball.setScale(0.8f);

        initialBallX = screenWidth/2 - ball.getScaledWidth();
        initialBallY = paddleY + bluePaddle.getScaledHeight();

        ball.setPosition(initialBallX, initialBallY);

        ball.setWorldBounds(screenWidth,screenHeight - buttonHeight);

        this.stage.addActor(ball);
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

        heartImage = new Image(lifeTextures[0]);
        heart2Image = new Image(lifeTextures[1]);
        heart3Image = new Image(lifeTextures[2]);

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
    public void initializeVariables(){
        super.initializeVariables();
        this.buttonWidth = 100;
        this.buttonHeight = 100;

        this.labelScale = 0.6f;
        this.smallLabelScale = 0.3f;
        this.addPlayer();
        this.addBall();
        this.setupListeners();
        this.setupLabelStyles();
        this.setupTable();

    }

    @Override
    public void render(float delta){
        ball.act(delta);

        if(ball.getY() < 0){
            Gdx.app.log("Ball dead","take life!");
            lifesLeft--;
            ball.stopMovement();

            this.isBallMoving = false;
            this.canStartGame = true;

            ball.setPosition(initialBallX,initialBallY);
            bluePaddle.canActorMove = false;
            bluePaddle.setPosition(initialPaddleX,paddleY);

            if(lifesLeft == 2){
                lifeTextures[2] = Textures.emptyHeartImageTexture;
            }
            else if(lifesLeft == 1){
                lifeTextures[2] = Textures.emptyHeartImageTexture;
                lifeTextures[1] = Textures.emptyHeartImageTexture;
            }
            else if(lifesLeft == 0){
                lifeTextures[0] = Textures.emptyHeartImageTexture;
                lifeTextures[1] = Textures.emptyHeartImageTexture;
                lifeTextures[2] = Textures.emptyHeartImageTexture;
            }
            else{
                screenManager.fadeInToScreen(Enumerations.Screen.RESULT_SCREEN,0.5f);
            }

            this.table.reset();
            this.setupTable();

        }

        super.render(delta);

    }

    @Override
    protected void addButton(Texture texture, EventListener eventListener, float width, float height){

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        ImageButton button = new ImageButton(drawable);

        button.setTouchable(Touchable.enabled);
        button.addListener(eventListener);

        this.table.add(button)
                .size(width,height)
                .expandY()
                .padLeft(buttonWidth)
                .padTop(buttonHeight/2);

    }

    //Player input
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(!isBallMoving && canStartGame){
            isBallMoving = true;
            bluePaddle.canActorMove = true;
            //TODO: Start the ball movement!
            ball.setAcceleration(ballAccelaration);
            ball.isMovingUp = true;
            ball.isMovingRight = true;
//            ball.accelerateAtAngle(30);
//            ball.accelarateTo(1,1);
            ball.setRotation(45);
            ball.accelerateForward();
        }

        this.canStartGame = true;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (bluePaddle.canActorMove){
            float paddleX = screenX - bluePaddle.getScaledWidth();
            if(paddleX <= 0) {
                paddleX = 0;
            }
            bluePaddle.setPosition(paddleX, paddleY);
        }

        return true;
    }

}
