package com.i4games.dogfight.actors;

import com.badlogic.gdx.graphics.Texture;
import com.i4games.dogfight.base.BaseActor;

public class PaddleActor extends BaseActor {

    public PaddleActor(Texture texture){
        super();
        this.setTexture(texture);
        this.setBoundaryRectangle();
        this.setPosition(screenWidth/2 - this.getWidth()/2, 0 + this.getHeight());
    }
}
