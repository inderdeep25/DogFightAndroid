package com.i4games.dogfight;

import com.badlogic.gdx.graphics.Texture;
import com.i4games.dogfight.base.BasePlayer;
import com.i4games.dogfight.util.Textures;

public class PaddlePlayer extends BasePlayer {

    public PaddlePlayer(){
        this.setTexture(Textures.paddleTexture);
        this.setPosition(screenWidth/2 - this.getWidth()/2, 0 + this.getHeight());
    }
}
