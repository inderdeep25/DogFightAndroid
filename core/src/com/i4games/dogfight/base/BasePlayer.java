package com.i4games.dogfight.base;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.i4games.dogfight.util.ScreenSettings;
import com.i4games.dogfight.util.Textures;


public class BasePlayer extends Actor {

    private TextureRegion textureRegion;
    private Rectangle rectangle;
    private Polygon boundaryPolygon;

    protected float screenHeight;
    protected float screenWidth;


    public BasePlayer() {
        super();
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();
        boundaryPolygon = null;
        screenHeight = ScreenSettings.getInstance().height;
        screenWidth = ScreenSettings.getInstance().width;
    }

    public BasePlayer(float x, float y, Stage s){
        super();

        setPosition(x, y);
        s.addActor(this);

        boundaryPolygon = null;
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();
        screenHeight = ScreenSettings.getInstance().height;
        screenWidth = ScreenSettings.getInstance().width;

    }


    public Rectangle getRectangle() {
        rectangle.setPosition(this.getX(), this.getY());
        return rectangle;
    }

    public void setTexture(Texture t) {
        textureRegion.setRegion(t);
        this.setSize(t.getWidth(), t.getHeight());
        rectangle.setSize(t.getWidth(), t.getHeight());
    }

    public boolean overlaps(BasePlayer other)
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


}
