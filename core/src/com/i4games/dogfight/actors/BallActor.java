package com.i4games.dogfight.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.i4games.dogfight.base.BaseActor;

public class BallActor extends BaseActor {

    public boolean isMovingRight;
    public boolean isMovingUp;

    PaddleActor paddle;

    public BallActor(PaddleActor paddle, Texture texture){
        super();
        this.paddle = paddle;
        this.setTexture(texture);
        this.setBoundaryRectangle();
        this.setPosition(screenWidth/2 - this.getWidth()/2, 0 + this.getHeight());
    }

    boolean didBallHitRightBorder(){

        boolean result = false;

        if(this.getX() + this.getScaledWidth() >= getWorldBounds().width - 30){
            result = true;
        }

        return result;
    }

    boolean didBallHitLeftBorder(){

        boolean result = false;

        if(this.getX() <= 0){
            result = true;
        }

        return result;
    }

    boolean didBallHitTopBorder(){

        boolean result = false;

        if(this.getY() + getHeight() > worldBounds.height){
            result = true;
        }

        return result;
    }

    boolean paddleHitLeftHalf=false;
    boolean didBallHitPaddle(){
        boolean result = false;

        if(this.overlaps(paddle)){
            if(this.getX() < (paddle.getX() + paddle.getWidth()/2)){
                paddleHitLeftHalf = true;
                Gdx.app.log("Paddle hit side", "Left Half");
            }else{
                Gdx.app.log("Paddle hit side", "Right Half");
                paddleHitLeftHalf = false;
            }
            result = true;
        }

        return result;
    }


    @Override
    public void setRotation(float degrees){
        this.accelarationAngle = degrees;
        super.setRotation(degrees);
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        float horizontalDirection = this.isMovingRight ? 1 : -1;
        float verticalDirection = this.isMovingUp ? -1 : 1;

        if(didBallHitRightBorder()){

            this.stopMovement();

            float angle = 180 - this.accelarationAngle;
            angle = horizontalDirection * angle;
            this.setRotation(angle);

            Gdx.app.log("Hit Right","Angle : " + Float.toString(angle));
            this.accelerateForward();

            this.isMovingRight = false;
        }

        if(didBallHitLeftBorder()){

            this.stopMovement();

            float angle = 180 + this.accelarationAngle;
            angle = horizontalDirection * angle;
            this.setRotation(angle);

            Gdx.app.log("Hit Left","Angle : " + Float.toString(angle));
            this.accelerateForward();

            this.isMovingRight = true;
        }

        if(didBallHitTopBorder()){

            this.stopMovement();

            float angle = 180 + this.accelarationAngle;
            this.setRotation(verticalDirection * angle);

            Gdx.app.log("Hit Top","Angle : " + Float.toString(angle));
            this.accelerateForward();

            this.isMovingUp = false;
            this.isMovingRight = !this.isMovingRight;
        }

        if(didBallHitPaddle()){
            this.stopMovement();

            float angle = 180 + this.accelarationAngle;
            angle = paddleHitLeftHalf ? -1 * angle - 180: angle;
            this.setRotation(angle);

            Gdx.app.log("Hit Paddle","Angle : " + Float.toString(angle));
            this.accelerateForward();

            this.isMovingUp = true;
            this.isMovingRight = !paddleHitLeftHalf;
        }

    }

}
