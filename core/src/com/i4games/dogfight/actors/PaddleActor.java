package com.i4games.dogfight.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.i4games.dogfight.base.BaseActor;

public class PaddleActor extends BaseActor {

    public boolean canActorMove;

    public PaddleActor(Texture texture){
        super();
        this.setTexture(texture);
        this.setBoundaryRectangle();
        this.canActorMove = false;
        this.setPosition(screenWidth/2 - this.getWidth()/2, 0 + this.getHeight());
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {

//        super.draw(batch, parentAlpha);

        Color c = getColor(); // used to apply tint color effect

        batch.setColor(c.r, c.g, c.b, c.a);


        float y = this.getY();
        float x = this.getX();
        float width = this.getScaledWidth();
        float height = this.getScaledHeight();

        if(x - width < 5){
            x = 5;
        }

        if (width + x > worldBounds.width){
            x = worldBounds.width - width;
        }

        batch.draw(textureRegion, x, y, width, height);

    }
}
