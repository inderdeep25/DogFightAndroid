package com.i4games.dogfight.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.i4games.dogfight.base.BaseActor;

public class Brick extends BaseActor {

    public boolean isRemoved;

    public Brick(Texture texture){
        super();
        this.setTexture(texture);
        this.setBoundaryRectangle();
        this.isRemoved = false;
        this.setPosition(screenWidth/2 - this.getWidth()/2, 0 + this.getHeight());
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {

        Color c = getColor();

        batch.setColor(c.r, c.g, c.b, c.a);

        float y = this.getY();
        float x = this.getX();
        float width = this.getScaledWidth();
        float height = this.getScaledHeight();

        batch.draw(textureRegion, x, y, width, height);

    }

}
