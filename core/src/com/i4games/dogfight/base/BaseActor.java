package com.i4games.dogfight.base;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.i4games.dogfight.util.ScreenSettings;


public class BaseActor extends Actor {

    private TextureRegion textureRegion;
    private Rectangle rectangle;
    private Polygon boundaryPolygon;

    protected float screenHeight;
    protected float screenWidth;


    public BaseActor() {
        super();
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();
        boundaryPolygon = null;
        screenHeight = ScreenSettings.getInstance().height;
        screenWidth = ScreenSettings.getInstance().width;
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

    public void draw (Batch batch, float parentAlpha) {

        Color c = getColor(); // used to apply tint color effect

        batch.setColor(c.r, c.g, c.b, c.a);


        float y = this.getY();
        float x = this.getX();
        float width = this.getWidth()*this.getScaleX();
        float height = this.getHeight()*this.getScaleY();

        if(x - width < 0){
            x = 0;
        }

        if (width + x > screenWidth){
            x = screenWidth - width;
        }

        batch.draw(textureRegion, x, y, width, height);

        super.draw(batch, parentAlpha);

    }


}
