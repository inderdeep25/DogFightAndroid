package com.i4games.dogfight.base;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.i4games.dogfight.util.ScreenSettings;


public class BaseActor extends Actor {

    protected TextureRegion textureRegion;
    protected Rectangle rectangle;
    protected Polygon boundaryPolygon;

    protected float screenHeight;
    protected float screenWidth;

    protected Vector2 velocityVec;
    protected Vector2 accelerationVec;
    protected float acceleration;
    protected float maxSpeed;
    protected float deceleration;

    public float accelarationAngle;

    // stores size of game world for all actors
    protected Rectangle worldBounds;

    public BaseActor() {
        super();
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();
        boundaryPolygon = null;
        screenHeight = ScreenSettings.getInstance().height;
        screenWidth = ScreenSettings.getInstance().width;

        velocityVec = new Vector2(0, 0);
        accelerationVec = new Vector2(0, 0);
        acceleration = 0;
        maxSpeed = 1000;
        deceleration = 0;

        setWorldBounds(screenWidth,screenHeight);
    }

    public BaseActor(float x, float y, Stage s){
        super();

        setPosition(x, y);
        s.addActor(this);

        boundaryPolygon = null;
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();
        screenHeight = ScreenSettings.getInstance().height;
        screenWidth = ScreenSettings.getInstance().width;

        velocityVec = new Vector2(0, 0);
        accelerationVec = new Vector2(0, 0);
        acceleration = 0;
        maxSpeed = 1000;
        deceleration = 0;

        setWorldBounds(screenWidth,screenHeight);

    }


    public Rectangle getRectangle() {
        rectangle.setPosition(this.getX(), this.getY());
        return rectangle;
    }

    public float getScaledWidth(){
        return getWidth()*getScaleX();
    }

    public float getScaledHeight(){
        return getHeight()*getScaleY();
    }

    public void setTexture(Texture t) {
        textureRegion.setRegion(t);
        this.setSize(t.getWidth(), t.getHeight());
        rectangle.setSize(t.getWidth(), t.getHeight());
    }

    public boolean overlaps(BaseActor other)
    {
        return this.getRectangle().overlaps( other.getRectangle() );
    }

    public void setBoundaryRectangle()
    {
        float w = getWidth();
        float h = getHeight();

        float [] vertices = {0, 0, w, h, 0, h};
        boundaryPolygon = new Polygon(vertices);
    }

    public void setWorldBounds(float width, float height)
    {
        worldBounds = new Rectangle(0, 0, width, height);
    }


    public Rectangle getWorldBounds()
    {
        return worldBounds;
    }

    public void boundToWorld()
    {
        if (getX() < 0)
            setX(0);
        if (getX() + getWidth() > worldBounds.width)
            setX(worldBounds.width - getWidth());
//        if (getY() - getHeight() < 0)
//            setY(0);
        if (getY() + getHeight() > worldBounds.height)
            setY(worldBounds.height - getHeight());
    }

    public void draw (Batch batch, float parentAlpha) {

        Color c = getColor(); // used to apply tint color effect

        batch.setColor(c.r, c.g, c.b, c.a);


        float y = this.getY();
        float x = this.getX();
        float width = this.getScaledWidth();
        float height = this.getScaledHeight();

        boundToWorld();

        if (width + x > worldBounds.width){
            x = worldBounds.width - width;
        }

        batch.draw(textureRegion, x, y, width, height);

        super.draw(batch, parentAlpha);

    }

    //Physics Methods

    //set acceleation of actor
    public void setAcceleration(float acc) {

        acceleration = acc;
    }

    public void setDeceleration(float dec) {
        deceleration = dec;
    }

    //set maximum speed of actor
    public void setMaxSpeed(float ms) {
        maxSpeed = ms;
    }

    //set current speed of actor
    public void setSpeed(float speed) {
        //if length is zero, then assume motion angle is zero degrees
        if (velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);
    }

    public float getSpeed() {
        return velocityVec.len();
    }

    public boolean isMoving() {
        return (getSpeed() > 0);
    }

    public void setMotionAngle(float angle) {

        velocityVec.setAngle(angle);
    }

    public float getMotionAngle() {
        return velocityVec.angle();
    }

    public void accelerateAtAngle(float angle)
    {
        accelerationVec = accelerationVec.add(
                new Vector2(acceleration, 0).setAngle(angle));
    }

    public void accelarateTo(float deltaX, float deltaY){
        Vector2 accVector = new Vector2(deltaX,deltaY);
        float angle = accVector.angle();
        this.accelerateAtAngle(angle);
    }

    public void accelerateForward() {
        accelerateAtAngle(getRotation());
    }

    public void stopMovement(){
        this.setSpeed(0);
        this.applyPhysics(0);
    }

    public void resumeMovement(){
        this.setAcceleration(this.acceleration);
    }

    public void applyPhysics(float dt)
    {
        //apply acceleration
        velocityVec.add(accelerationVec.x * dt, accelerationVec.y * dt);

        float speed = getSpeed();

        //decrease soeed (decelerate) when not accelerating
        if(accelerationVec.len() == 0)
            speed -= deceleration * dt;

        //keep speed within set bounds
        speed = MathUtils.clamp(speed, 0, maxSpeed);

        //update velocity
        setSpeed(speed);

        //update position according to value stored in velocity vector
        moveBy(velocityVec.x * dt, velocityVec.y * dt);

        //reset acceleration
        accelerationVec.set(0,0);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
    }

}
