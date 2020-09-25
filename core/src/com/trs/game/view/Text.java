package com.trs.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {

    int xPos;
    int yPos;
    String text;
    Color color;

    public Text(int xPos, int yPos, String text, Color color){
        this.xPos = xPos;
        this.yPos = yPos;
        this.text = text;
        this.color = color;
    }

    public void render(SpriteBatch batch, BitmapFont font){
        if(batch.isDrawing()) batch.end();
        batch.begin();
        font.setColor(color);
        font.draw(batch,text,xPos,yPos);
        batch.end();
    }
}
